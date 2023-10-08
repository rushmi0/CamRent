package org.camrent.routes.api.customer.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService


fun Route.CustomerOpenImage() {

    get("customers/img/id/{id}") {

        try {

            // ดึงค่า id จากพารามิเตอร์และแปลงเป็น Int ถ้าเป็นไปได้
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            // ค้นหาข้อมูลลูกค้าด้วย ID
            val customerData = CustomerService.findCustomerByUserID(id)
                ?: throw NotFoundException("ไม่พบลูกค้าสำหรับ `ID`: $id")

            val file = customerData.profileImage

            // ตรวจสอบว่าไฟล์มีอยู่จริงหรือไม่
            if (file != null && file.isNotBlank()) {
                call.respondFile(java.io.File(file))
            } else {
                // หากไม่พบไฟล์ ให้ส่ง HTTP 404 Not Found
                call.respond(
                    HttpStatusCode.NotFound,
                    "ยังไม่รูป"
                )
            }



        } catch (e: IllegalArgumentException) {
            call.respond(
                HttpStatusCode.BadRequest,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        } catch (e: NotFoundException) {
            call.respond(
                HttpStatusCode.NotFound,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        }
    }
}
