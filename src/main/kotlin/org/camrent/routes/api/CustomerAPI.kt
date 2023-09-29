package org.camrent.routes.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.Customer
import org.camrent.security.sqlInjection.SQLInjectionProtector
import org.camrent.security.xss.XssDetector


fun Application.customerRoute() {

    routing {
        route("api/v1") {

            get("customers") {
                // เรียกใช้งานเมธอดเพื่อดึงข้อมูลลูกค้าทั้งหมด
                val customers = Customer.selectAllFromCustomers()
                println("> Host: ${call.request.headers["Host"]}")
                call.respond(HttpStatusCode.OK, customers)
            }


            post("customers") {
                try {
                    // รับข้อมูลจาก body ของคำขอ POST
                    val payload = call.receive<CustomersForm>()

                    // ตรวจสอบว่า UserName และ AuthKey ไม่เป็นค่าว่าง
                    if (payload.userName.isNotBlank() && payload.authKey.isNotBlank()) {

                        // ตรวจสอบ XSS
                        if (XssDetector.containsHtmlTags(payload.userName) || XssDetector.containsJavascript(payload.userName) ||
                            XssDetector.containsHtmlTags(payload.authKey) || XssDetector.containsJavascript(payload.authKey)
                        ) {
                            // ถ้าพบ Cross-site Scripting (XSS), ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                            call.respond(
                                HttpStatusCode.BadRequest,
                                "Cross-site scripting detected."
                            )
                        } else {
                            // ทำการกำจัด SQL Injection จากข้อมูล
                            val sanitizedUserName = SQLInjectionProtector.sanitizeInput(payload.userName)
                            println("\nUser Name $sanitizedUserName")
                            val sanitizedAuthKey = SQLInjectionProtector.sanitizeInput(payload.authKey)
                            println("Publick Key $sanitizedAuthKey \n")

                            // เพิ่มข้อมูลลูกค้า ถ้าทำสำเร็จจะคือค่าเป็น true
                            val status = Customer.insert(CustomersForm(sanitizedUserName, sanitizedAuthKey))

                            if (status) {
                                // เพื่อตอบกลับให้กับ client, ตรวจสอบว่าการค้นหาผู้ใช้ด้วย userName สำเร็จหรือไม่
                                val result = Customer.findCustomerByUserName(sanitizedUserName)
                                if (result != null) {
                                    call.respond(result) // ส่งข้อมูลลูกค้าที่ค้นหาได้กลับไปยัง client
                                } else {
                                    call.respond(HttpStatusCode.NotFound) // ถ้าไม่พบข้อมูลลูกค้า, ตอบกลับด้วยสถานะผลลัพธ์ 404 Not Found
                                }
                            }

                        }
                    } else {
                        // ถ้า UserName หรือ AuthKey ว่าง, ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "UserName and AuthKey must not be empty")
                    }

                } catch (e: ContentTransformationException) {
                    // ถ้าข้อมูลไม่ตรงตามรูปแบบ, ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Invalid data format. Please provide data in the correct format."
                    )
                } catch (e: Exception) {
                    // ถ้าเกิดข้อผิดพลาดอื่น ๆ, ตอบกลับด้วยสถานะผลลัพธ์ 500 Internal Server Error
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "An error occurred while processing your request."
                    )
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
                                call.respond(
                                    HttpStatusCode.BadRequest,
                                    "Cross-site scripting detected in the field: $fieldName"
                                )
                                return@patch
                            }

                            // ถ้าไม่พบ XSS, ทำการอัปเดตข้อมูล
                            Customer.update(id, fieldName, newValue)
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


            delete("customers/{id}") {
                val id = call.parameters["id"]?.toInt()
                // ลบข้อมูลลูกค้า
                if (id != null) {
                    val deletionStatus = Customer.delete(id)
                    if (deletionStatus) {
                        // ถ้าการลบข้อมูลสำเร็จ
                        call.respondText("Customer deleted successfully")
                    } else {
                        // ถ้าไม่สามารถลบข้อมูลได้ (เช่น ID ไม่ถูกต้อง)
                        call.respond(
                            HttpStatusCode.NotFound,
                            "Customer not found or unable to delete"
                        )
                    }
                } else {
                    // ถ้าไม่พบ ID ในคำขอ
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Invalid ID format or missing ID in request"
                    )
                }
            }


        }
    }

}