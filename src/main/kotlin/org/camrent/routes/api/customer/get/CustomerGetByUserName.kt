package org.camrent.routes.api.customer.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
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
import org.slf4j.event.Level


fun Route.CustomerByUserName() {

    get("customers/name/{name}") {
        try {
            // ดึงค่า `Customer name` จากพารามิเตอร์ที่ระบุใน URL และแปลงเป็น String หรือ null ถ้าไม่สามารถแปลงได้
            val accountName = call.parameters["name"]

            // ตรวจสอบว่า `Customer name` ไม่เป็น `null`
            if (accountName != null) {
                // ค้นหาข้อมูลลูกค้าด้วย `User Name`
                val customer = CustomerService.findCustomerByUserName(accountName) ?: throw NotFoundException("ไม่พบลูกค้าสำหรับ `Account Name`: $accountName")

                // ดึงค่า Witness และ Signature จาก Headers
                val signature = call.request.headers["Signature"]
                val witness = call.request.headers["Witness"]

                // ตรวจสอบว่า `Signature` และ `Witness` ไม่เป็น `null`
                if (signature != null && witness != null) {
                    // คำนวณ Hash ของ Witness และเปลี่ยนให้เป็นตัวเลข BigInteger
                    val rawPermit = Sha256.hash(witness.toByteArray()).ByteArrayToBigInteger()

                    val signatureRecovered = ECDSA.derRecovered(signature)!!
                    val publicKey = customer.authKey?.B32decode()?.third!!
                    val pubKeyRecovered = publicKey.getDecompress()!!

                    // ตรวจสอบลายเซ็นด้วย `ECDSA`
                    val verify = ECDSA.VerifySignature(
                        pubKeyRecovered,
                        rawPermit,
                        signatureRecovered
                    )

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
                    "รูปแบบข้อมูลไม่ถูกต้อง"
                )
            }


        }  catch (e: IllegalArgumentException) {
            // ตอบกลับด้วยสถานะ `400 Bad Request` ในกรณีที่มีข้อผิดพลาดเกี่ยวกับข้อมูลขาเข้าไม่ถูกต้อง
            call.respond(
                HttpStatusCode.BadRequest,
                e.localizedMessage
            )
        } catch (e: NotFoundException) {
            // ตอบกลับด้วยสถานะ `404 Not Found` ในกรณีที่ไม่พบข้อมูลลูกค้า
            call.respond(
                HttpStatusCode.NotFound,
                e.localizedMessage
            )
        } catch (e: Exception) {
            // ตอบกลับด้วยสถานะ `500 Internal Server Error` ในกรณีที่เกิดข้อผิดพลาดอื่น ๆ
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาด: ${e.localizedMessage}"
            )
        }
    }
}
