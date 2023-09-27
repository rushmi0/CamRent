package org.camrent.Route.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.Customer
import org.camrent.database.service.Customer.insert
import org.camrent.security.xss.XssDetector


fun Application.customerRoute() {

    routing {
        route("api/v1") {

            get("customers") {
                // เรียกใช้งานเมธอดเพื่อดึงข้อมูลลูกค้าทั้งหมด
                val customers = Customer.selectAll()
                println("> Host: ${call.request.headers["Host"]}")
                call.respond(HttpStatusCode.OK, customers)
            }


            post("customers") {
                try {
                    val payload = call.receive<CustomersForm>()

                    // ตรวจสอบว่า UserName และ AuthKey ไม่เป็นค่าว่าง
                    if (payload.UserName.isNotBlank() && payload.AuthKey.isNotBlank()) {

                        // ตรวจสอบ XSS
                        if (XssDetector.containsHtmlTags(payload.UserName) || XssDetector.containsJavascript(payload.UserName) ||
                            XssDetector.containsHtmlTags(payload.AuthKey) || XssDetector.containsJavascript(payload.AuthKey)
                        ) {
                            call.respond(HttpStatusCode.BadRequest, "Cross-site scripting detected.")
                        } else {
                            // หากไม่พบ XSS ทำการเพิ่มข้อมูล
                            insert(payload)
                            call.respond(HttpStatusCode.Created, "Customer table inserted data successfully")
                        }
                    } else {
                        call.respond(HttpStatusCode.BadRequest, "UserName and AuthKey must not be empty")
                    }
                } catch (e: ContentTransformationException) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid data format. Please provide data in the correct format.")
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, "An error occurred while processing your request.")
                }
            }




            patch("customers/{id}") {
                val id = call.parameters["id"]?.toInt()
                val payload = call.receive<Map<String, String>>() // รับข้อมูลในรูปแบบ Map<String, String>

                try {
                    if (id != null) {
                        // ตรวจสอบ XSS สำหรับทุก field ที่รับมา
                        for ((fieldName, newValue) in payload) {
                            if (XssDetector.containsHtmlTags(newValue) || XssDetector.containsJavascript(newValue)) {
                                call.respond(HttpStatusCode.BadRequest, "Cross-site scripting detected in the field: $fieldName")
                                return@patch
                            }
                        }

                        // ถ้าไม่พบ XSS, ทำการอัปเดตข้อมูล
                        for ((fieldName, newValue) in payload) {
                            Customer.update(id, fieldName, newValue)
                        }
                        call.respondText("Customer updated successfully")
                    } else {
                        call.respond(HttpStatusCode.BadRequest, "Invalid customer ID")
                    }

                } catch (e: ContentTransformationException) {
                    // กรณีเกิด ContentTransformationException (รูปแบบข้อมูลไม่ตรงตาม)
                    call.respond(HttpStatusCode.BadRequest, "Invalid data format. Please provide data in the correct format.")
                } catch (e: Exception) {
                    // กรณีเกิด Exception อื่น ๆ
                    call.respond(HttpStatusCode.InternalServerError, "An error occurred while processing your request.")
                }
            }


            delete("customers/{id}") {
                val id = call.parameters["id"]?.toInt()
                // ลบข้อมูลลูกค้า
                if (id != null) {
                    Customer.delete(id)
                }
                // ส่งคำตอบกลับ
                call.respondText("Customer deleted successfully")
            }

        }
    }

}