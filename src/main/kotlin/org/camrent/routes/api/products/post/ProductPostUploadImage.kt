package org.camrent.routes.api.products.post

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.ProductsService
import org.camrent.database.service.StoresService
import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve.getDecompress
import org.camrent.security.securekey.Sha256
import org.camrent.utils.AccountDirectory.saveImage
import org.camrent.utils.ShiftTo.B32decode
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger
import java.io.File

fun Route.ProductUploadImage() {

    post("products/img/id/{id}") {

        try {

            val typeImage = call.request.headers["ImageType"]!!
            val type = call.request.headers["ProductType"]!!

            // ดึงค่า Witness และ Signature จาก Headers
            val signature = call.request.headers["Signature"]
            val witness = call.request.headers["Witness"]

            // ดึงค่า id จากพารามิเตอร์และแปลงเป็น Int ถ้าเป็นไปได้
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            val productData = ProductsService.findProductsByID(id)
                ?: throw NotFoundException("ไม่พบสินค้าสำหรับ `ID`: $id")


            val owner = StoresService.findStoresByUserID(productData.storeID)
                ?: throw NotFoundException("ไม่พบร้านสำหรับ `ID`: $id")


            // ตรวจสอบว่า `Signature` และ `Witness` ไม่เป็น `null`
            if (signature == null || witness == null) {
                throw IllegalArgumentException("`Signature` หรือ `Witness` เป็น `null`")
            }

            val rawPermit = Sha256.hash(witness.toByteArray()).ByteArrayToBigInteger()

            // ถอดรหัส (decode) เพื่อดึงเอาพิกัด R และ S ออกมาใช้ในการคำนวณ
            val signatureRecovered = ECDSA.derRecovered(signature)!!

            // ดึง `AuthKey` ของ `User` จากฐานข้อมูล และถอดรหัสด้วย Bech32
            // เพื่อให้ได้ Public Key (ECC) แบบฐาน 16
            val publicKey = owner.authKey?.B32decode()?.third!!

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

                // รับข้อมูลจาก multipart request
                val multipart = call.receiveMultipart()

                var imageFile: File? = null
                var reciveName: String? = null
                var reciveValue: String? = null

                multipart.forEachPart { part ->
                    when (part) {

                        is PartData.FormItem -> {
                            val formItemValue = part.value
                            val formItemName = part.name
                            println("Name: ${part.name} Value: $formItemValue")
                            reciveValue = formItemValue  // บันทึกค่า formItemValue
                            reciveName = formItemName
                        }

                        is PartData.FileItem -> {
                            // บันทึกไฟล์ภาพลงในระบบ
                            imageFile = saveImage(
                                part,
                                id,
                                "stores",
                                typeImage, // camera, accessories
                                type, // compact, mirrorless, dslr
                            )
                        }

                        is PartData.BinaryChannelItem, is PartData.BinaryItem -> {
                            // ไม่รองรับข้อมูล Binary
                            call.respond(HttpStatusCode.UnsupportedMediaType, "ไม่รองรับข้อมูล Binary")
                            return@forEachPart
                        }

                    }

                }

                if (imageFile != null) {

                    val img1 = productData.image1
                    val img2 = productData.image2
                    val img3 = productData.image3
                    val img4 = productData.image4

                    when {


                    }
                }


            } else {
                // ตอบกลับว่าไม่มีการอัปโหลดไฟล์
                call.respond(
                    HttpStatusCode.BadRequest,
                    "ไม่มีการอัปโหลดไฟล์"
                )
            }


        } catch (e: IllegalArgumentException) {
            // ตอบกลับว่าเกิดข้อผิดพลาดที่รับข้อมูลเข้ามาไม่ถูกต้อง
            call.respond(
                HttpStatusCode.BadRequest,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        } catch (e: NotFoundException) {
            // ตอบกลับว่าไม่พบข้อมูลลูกค้าสำหรับ `ID` ที่ระบุ
            call.respond(
                HttpStatusCode.NotFound,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        } catch (e: Exception) {
            // ตอบกลับว่าเกิดข้อผิดพลาดภายในระบบ
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        }


    }

}