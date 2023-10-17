package org.camrent.routes.api.stores.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService
import java.io.File


fun Route.StoreGetImageURL() {

    get("stores/img/id/{id}/{image}") {

        try {

            // ดึงค่า parameter id จาก URL และตรวจสอบว่าไม่เป็น null
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("ไม่ได้ระบุค่า id")

            // ค้นหาข้อมูลลูกค้าด้วย ID
            val customerData = CustomerService.findCustomerByUserID(id)
                ?: throw NotFoundException("ไม่พบลูกค้าสำหรับ `ID`: $id")

            val file = customerData.profileImage
            println(file)

            // ตรวจสอบว่าไฟล์มีอยู่จริงหรือไม่
            if (file != "N/A" && file != null && file.isNotBlank()) {
                call.respondFile(File(file))
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
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาด: ${e.message}"
            )
        }
    }

}