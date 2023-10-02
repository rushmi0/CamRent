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
        val id = call.parameters["id"]?.toIntOrNull()
        val payload = call.receive<Map<String, String>>()

        try {
            if (id != null) {
                // ตรวจสอบ XSS สำหรับทุก field ที่รับมา
                for ((fieldName, newValue) in payload) {
                    if (XssDetector.containsHtmlTags(newValue) || XssDetector.containsJavascript(newValue)) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "Cross-site scripting detected in the field: $fieldName"
                        )
                        return@patch
                    }

                    // ถ้าไม่พบ XSS, ทำการอัปเดตข้อมูล
                    CustomerService.update(id, fieldName, newValue)
                }
                call.respondText("Customer updated successfully")
            } else {
                call.respond(
                    HttpStatusCode.BadRequest,
                    "Invalid customer ID"
                )
            }

        } catch (e: ContentTransformationException) {
            // กรณีเกิด รูปแบบข้อมูลไม่ตรงตามโครงร้างที่กำหนด
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