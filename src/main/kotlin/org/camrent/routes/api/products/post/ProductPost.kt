package org.camrent.routes.api.products.post

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.request.ContentTransformationException
import io.ktor.server.response.*
import io.ktor.server.routing.*

import org.camrent.database.forms.ProductsForm
import org.camrent.database.service.ProductsService
import org.camrent.database.service.StoresService
import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve.getDecompress
import org.camrent.security.securekey.Sha256
import org.camrent.security.xss.XssDetector
import org.camrent.utils.ShiftTo.B32decode
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger


fun Route.ProductPost() {

    post("product") {

        try {

            val payload = call.receive<ProductsForm>()

            val name: String = payload.productName
            val type: String = payload.productType
            val price: Int = payload.productPrice.toInt()
            val spec: Map<String, Any> = payload.specDetail
            val desc = payload.description

            // หมายเลข `ID` ที่จะใช้ในการ update ข้อมูล จาก `Product Table`
            val targetID = call.request.headers["StoresID"]?.toIntOrNull()!!
            println("Stores ID: $targetID")

            val store = StoresService.findStoresByUserID(targetID)

            // ดึงค่า Witness และ Signature จาก Headers
            val signature = call.request.headers["Signature"]
            val witness = call.request.headers["Witness"]

            // ตรวจสอบว่า `Signature` และ `Witness` ไม่เป็น `null`
            if (signature == null || witness == null) {
                throw IllegalArgumentException("`Signature` หรือ `Witness` เป็น `null`")
            }

            if (
               XssDetector.containsXss(name) ||
                XssDetector.containsXss(type) ||
                XssDetector.containsXss(price.toString()) ||
                XssDetector.containsXss(spec.toString())
            ) {
                // * ถ้าพบ Cross-site Scripting (XSS), ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                call.respond(
                    HttpStatusCode.BadRequest,
                    "ตรวจพบการเขียน Cross-site scripting"
                )
            } else {

                val rawPermit = Sha256.hash(witness.toByteArray()).ByteArrayToBigInteger()

                // ถอดรหัส (decode) เพื่อดึงเอาพิกัด R และ S ออกมาใช้ในการคำนวณ
                val signatureRecovered = ECDSA.derRecovered(signature)!!

                // ดึง `AuthKey` ของ `User` จากฐานข้อมูล และถอดรหัสด้วย Bech32
                // เพื่อให้ได้ Public Key (ECC) แบบฐาน 16
                val publicKey = store?.authKey?.B32decode()?.third!!

                // นำ Public Key ที่ถูกบีบอัดให้เป็นตัวเลขฐาน 10 มาคำนวณหา Public Key
                // ที่เป็นพิกัดบนเส้นโค้ง X และ Y
                val pubKeyRecovered = publicKey.getDecompress()!!

                // ตรวจสอบลายเซ็นด้วย `ECDSA`
                val verify = ECDSA.VerifySignature(
                    pubKeyRecovered,
                    rawPermit,
                    signatureRecovered
                )

                if (verify) {

                    val productRecord = ProductsService.insert(
                       ProductsForm(
                           name,
                           type,
                           price,
                           spec,
                            desc
                       )
                    )

                    // ตอบกลับด้วยข้อมูลลูกค้าถ้าพบ
                    call.respond(HttpStatusCode.OK, productRecord)
                } else {
                    // ถ้า `verify` เป็นเท็จ ให้ตอบกลับด้วยสถานะ `400 Bad Request`
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "ปฏิเสธการให้ข้อมูล เนื่องจากลายเซ็นไม่ถูกต้อง"
                    )
                }


            }


        } catch (e: ContentTransformationException) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Invalid data format. Please provide data in the correct format."
            )
        } catch (e: Exception) {
            // กรณีเกิด Exception อื่น ๆ
            call.respond(
                HttpStatusCode.InternalServerError,
                "An error occurred while processing your request."
            )
        }

    }

}