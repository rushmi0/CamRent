package org.camrent.routes.api.customer.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService


fun Route.CustomerGetByID() {

    get("customers/id/{id}") {
        // อ่านค่า IP address และ port ของ client จาก request
        val clientIp = call.request.local.remoteHost
        val clientPort = call.request.local.port
        // อ่านค่า cookie ชื่อ "user" จาก request
        val userCookie = call.request.cookies["user"]
        println("Client IP: $clientIp, Port: $clientPort Cookie: $userCookie")

        // ดึงค่า Customer name จากพารามิเตอร์ที่ระบุใน URL และแปลงเป็น Int หรือ null ถ้าไม่สามารถแปลงได้
        val id = call.parameters["id"]?.toIntOrNull()

        if (id != null) {
            // ค้นหาข้อมูลลูกค้าด้วย ID
            val customer = CustomerService.findCustomerByUserID(id)

            if (customer != null) {
                // ตอบกลับด้วยข้อมูลลูกค้าถ้าพบ
                call.respond(HttpStatusCode.OK, customer)
            } else {
                // ถ้าไม่พบข้อมูลลูกค้าให้ตอบกลับด้วยสถานะ 404 Not Found
                call.respond(
                    HttpStatusCode.NotFound,
                    "Customer not found for ID: $id"
                )
            }
        } else {
            // ถ้า ID ไม่ถูกต้องหรือไม่ได้ระบุ ให้ตอบกลับด้วยสถานะ 400 Bad Request
            call.respond(
                HttpStatusCode.BadRequest,
                "Invalid ID format"
            )
        }
    }

}