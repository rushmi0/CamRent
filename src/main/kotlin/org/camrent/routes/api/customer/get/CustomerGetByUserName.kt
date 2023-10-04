package org.camrent.routes.api.customer.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService
import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve
import org.camrent.security.securekey.EllipticCurve.getDecompress
import org.camrent.security.securekey.Sha256
import org.camrent.utils.ShiftTo
import org.camrent.utils.ShiftTo.B32decode
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger

fun Route.CustomerByUserName() {

    get("customers/name/{name}") {

        // ดึงค่า `Customer name` จากพารามิเตอร์ที่ระบุใน URL และแปลงเป็น String หรือ null ถ้าไม่สามารถแปลงได้
        val accountName = call.parameters["name"]

        if (accountName != null) {
            // ค้นหาข้อมูลลูกค้าด้วย `User Name`
            val customer = CustomerService.findCustomerByUserName(accountName)

            // ดึงค่า Witness และ Signature จาก Headers
            val signature = call.request.headers["Signature"]
            val witness = call.request.headers["Witness"]

            // ตรวจสอบว่า `Signature` และ `Witness` ไม่เป็น `null`
            if (signature != null && witness != null) {
                val rawPermit = Sha256.hash(witness.toByteArray()).ByteArrayToBigInteger()

                if (customer != null) {
                    val signatureRecovered = ECDSA.derRecovered(signature)!!
                    val publicKey = customer.authKey?.B32decode()?.third!!
                    val pubKeyRecovered = publicKey.getDecompress()!!

                    // ตรวจสอบลายเซ็นด้วย `ECDSA`
                    val verify = ECDSA.VerifySignature(pubKeyRecovered, rawPermit, signatureRecovered)
                    if (verify) {
                        // ตอบกลับด้วยข้อมูลลูกค้าถ้าพบ
                        call.respond(HttpStatusCode.OK, customer)
                    } else {
                        // ถ้า `verify` เป็นเท็จ ให้ตอบกลับด้วยสถานะ `400 Bad Request`
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "ปฏิเสธการให้ข้อมูล เนื่องจากลายเซ็นไม่ถูกต้อง"
                        )
                    }
                } else {
                    // ถ้าไม่พบข้อมูลลูกค้าให้ตอบกลับด้วยสถานะ `404 Not Found`
                    call.respond(
                        HttpStatusCode.NotFound,
                        "ไม่พบลูกค้าสำหรับ `ID`: $accountName"
                    )
                }
            } else {
                // ถ้า `Signature` หรือ `Witness` เป็น `null` ให้ตอบกลับด้วยสถานะ `400 Bad Request`
                call.respond(
                    HttpStatusCode.BadRequest,
                    "ไม่พบข้อมูล `Signature` หรือ `Witness`"
                )
            }
        } else {
            // ถ้า `Customer name` ไม่ถูกต้องหรือไม่ได้ระบุ ให้ตอบกลับด้วยสถานะ `400 Bad Request`
            call.respond(
                HttpStatusCode.BadRequest,
                "รูปแบบ `ID` ไม่ถูกต้อง"
            )
        }
    }

}
