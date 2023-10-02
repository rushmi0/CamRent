package org.camrent.routes.api.customer.post

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.CustomersField
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.CustomerService
import org.camrent.security.xss.XssDetector
import org.camrent.utils.AccountDirectory

fun Route.CustomerPost() {

    post("customers") {
        try {
            // รับข้อมูลจาก body ของคำขอ POST
            val payload = call.receive<CustomersForm>()

            val userName = payload.userName
            val publicKey = payload.authKey

            // ตรวจสอบว่า `UserName` และ `AuthKey` ไม่เป็นค่าว่าง
            if (userName.isNotBlank() && publicKey.isNotBlank()) {
                // ตรวจสอบ XSS
                if (XssDetector.containsXss(userName) ||
                    XssDetector.containsXss(publicKey)
                ) {
                    // ถ้าพบ Cross-site Scripting (XSS), ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "ตรวจพบการเขียน Cross-site scripting"
                    )
                } else {
                    // เพิ่มข้อมูลลูกค้า ถ้าทำสำเร็จจะคือค่าเป็น true
                    val statement = CustomerService.insert(
                        CustomersForm(
                            userName,
                            publicKey
                        )
                    )

                    if (statement) {
                        // เพื่อตอบกลับให้กับ client, ตรวจสอบว่าการค้นหาผู้ใช้ด้วย `userName` สำเร็จหรือไม่
                        val customerRecord: CustomersField? = CustomerService.findCustomerByUserName(userName)
                        val id: Int? = customerRecord?.customerID
                        AccountDirectory.createDirectory("customers", id!!)

                        call.respond(customerRecord ?: HttpStatusCode.NotFound) // ส่งข้อมูลลูกค้าที่ค้นหาได้กลับไปยัง client หรือ ถ้าไม่พบข้อมูลลูกค้า, ตอบกลับด้วยสถานะผลลัพธ์ 404 Not Found
                    }
                }
            } else {
                // ถ้า UserName หรือ AuthKey ว่าง, ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                call.respond(
                    HttpStatusCode.BadRequest,
                    "UserName และ AuthKey จำเป็นต้องระบุข้อมูล"
                )
            }

        } catch (e: ContentTransformationException) {
            // ถ้าข้อมูลไม่ตรงตามรูปแบบ, ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
            call.respond(
                HttpStatusCode.BadRequest,
                "รูปแบบข้อมูลไม่ถูกต้อง กรุณาระบุข้อมูลในรูปแบบที่ถูกต้อง"
            )
        } catch (e: Exception) {
            // ถ้าเกิดข้อผิดพลาดอื่น ๆ, ตอบกลับด้วยสถานะผลลัพธ์ 500 Internal Server Error
            call.respond(
                HttpStatusCode.InternalServerError,
                "เกิดข้อผิดพลาดขณะประมวลผลคำขอของคุณ ข้อผิดพลาด: ${e.message}"
            )
        }
    }

}