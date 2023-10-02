package org.camrent.routes.api.customer.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService


fun Route.CustomerByUerName() {

    get("customers/name/{name}") {
        // ดึงค่า `Customer name` จากพารามิเตอร์ที่ระบุใน URL และแปลงเป็น String หรือ null ถ้าไม่สามารถแปลงได้
        val accountName = call.parameters["name"]
        println(accountName)

        if (accountName != null) {
            // ค้นหาข้อมูลลูกค้าด้วย `User Name`
            val customer = CustomerService.findCustomerByUserName(accountName)

            if (customer != null) {
                // ตอบกลับด้วยข้อมูลลูกค้าถ้าพบ
                call.respond(HttpStatusCode.OK, customer)
            } else {
                // ถ้าไม่พบข้อมูลลูกค้าให้ตอบกลับด้วยสถานะ 404 Not Found
                call.respond(
                    HttpStatusCode.NotFound,
                    "Customer not found for ID: $accountName"
                )
            }
        } else {
            // ถ้า `Customer name` ไม่ถูกต้องหรือไม่ได้ระบุ ให้ตอบกลับด้วยสถานะ 400 Bad Request
            call.respond(
                HttpStatusCode.BadRequest,
                "Invalid ID format"
            )
        }
    }

}