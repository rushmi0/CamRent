package org.camrent.routes.api.customer.patch

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService
import org.camrent.security.xss.XssDetector

fun Route.CustomerPatchByID() {

    patch("customers/id/{id}") {

        try {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

            // รับข้อมูล payload
            val payload = call.receive<Map<String, Any>>()

            // ตรวจสอบว่า payload ไม่ว่าจะรับมีข้อมูลหรือไม่
            if (payload.isNotEmpty()) {
                // ตรวจสอบ XSS สำหรับทุก field ที่รับมา
                for ((fieldName, newValue) in payload) {
                    if (XssDetector.containsXss(newValue.toString())) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "ตรวจพบ Cross-site scripting ใน field: $fieldName"
                        )
                        return@patch
                    }

                    // ถ้าไม่พบ XSS, ทำการอัปเดตข้อมูล
                    CustomerService.update(id, fieldName, newValue.toString())
                }
                call.respondText("อัปเดตข้อมูลลูกค้าเรียบร้อยแล้ว")
            } else {
                call.respond(HttpStatusCode.BadRequest, "Payload ไม่ได้ระบุข้อมูล")
            }


        } catch (e: IllegalArgumentException) {
            // กรณีเกิดข้อผิดพลาดที่เกี่ยวกับข้อมูลที่ไม่ถูกต้อง
            call.respond(HttpStatusCode.BadRequest, e.message ?: "ข้อมูลไม่ถูกต้อง")
        } catch (e: ContentTransformationException) {
            // กรณีเกิดข้อผิดพลาดในกระบวนการแปลงข้อมูล
            call.respond(HttpStatusCode.BadRequest, "รูปแบบข้อมูลไม่ถูกต้อง")
        } catch (e: Exception) {
            // กรณีเกิดข้อผิดพลาดทั่วไป
            call.respond(HttpStatusCode.InternalServerError, "เกิดข้อผิดพลาดขณะประมวลผลคำขอของคุณ")
        }
    }
}
