package org.camrent.routes.api.people.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.PeopleService
import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve.getDecompress
import org.camrent.security.securekey.Sha256
import org.camrent.utils.ShiftTo.B32decode
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger

fun Route.PeopleGetByID() {

    get("people/{id}") {

        try {
            // ดึงค่า ID จากพารามิเตอร์ที่ระบุใน URL และแปลงเป็น Int หรือ null ถ้าไม่สามารถแปลงได้
            val id = call.parameters["id"]?.toIntOrNull() ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            // ค้นหาข้อมูลลูกค้าด้วย ID
            val store = PeopleService.findPeopleByUserID(id)
            val pk = store?.personID!!
            val customer = PeopleService.findCustomerByPersonID(pk)!!

            // ดึงค่า Witness และ Signature จาก Headers
            val signature = call.request.headers["Signature"]
            val witness = call.request.headers["Witness"]

            // ตรวจสอบว่า `Signature` และ `Witness` ไม่เป็น `null`
            if (signature == null || witness == null) {
                throw IllegalArgumentException("`Signature` หรือ `Witness` เป็น `null`")
            }

            // คำนวณ Hash ของ Witness และเปลี่ยนให้เป็นตัวเลข BigInteger
            val rawPermit = Sha256.hash(witness.toByteArray()).ByteArrayToBigInteger()

            // ถอดรหัส (decode) เพื่อดึงเอาพิกัด R และ S ออกมาใช้ในการคำนวณ
            val signatureRecovered = ECDSA.derRecovered(signature)!!

            // ดึง `AuthKey` ของ `User` จากฐานข้อมูล และถอดรหัสด้วย Bech32
            // เพื่อให้ได้ Public Key (ECC) แบบฐาน 16
            val publicKey = customer.authKey?.B32decode()?.third!!

            // นำ Public Key ที่ถูกบีบอัดให้เป็นตัวเลขฐาน 10 มาคำนวณหา Public Key
            // ที่เป็นพิกัดบนเส้นโค้ง X และ Y
            val pubKeyRecovered = publicKey.getDecompress()!!

            // ตรวจสอบลายเซ็นด้วย `ECDSA`
            val verify = ECDSA.VerifySignature(
                pubKeyRecovered,
                rawPermit,
                signatureRecovered
            )

            // ตรวจสอบการยืนยันลายเซ็น
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
        } catch (e: IllegalArgumentException) {
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
