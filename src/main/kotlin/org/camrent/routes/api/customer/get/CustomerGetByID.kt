package org.camrent.routes.api.customer.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService
import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve.getDecompress
import org.camrent.security.securekey.Sha256
import org.camrent.utils.ShiftTo.B32decode
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger

fun Route.CustomerGetByID() {

    get("customers/id/{id}") {

        try {
            // ดึงค่า Customer name จากพารามิเตอร์ที่ระบุใน URL และแปลงเป็น Int หรือ null ถ้าไม่สามารถแปลงได้
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            // ค้นหาข้อมูลลูกค้าด้วย ID
            val customer = CustomerService.findCustomerByUserID(id)
                ?: throw NotFoundException("ไม่พบลูกค้าสำหรับ `ID`: $id")

            // ตอบกลับด้วยข้อมูลลูกค้าถ้าพบ
            call.respond(HttpStatusCode.OK, customer)

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
