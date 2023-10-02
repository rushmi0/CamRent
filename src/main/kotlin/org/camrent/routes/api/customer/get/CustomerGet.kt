package org.camrent.routes.api.customer.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService


fun Route.CustomerGet() {

    get("customers") {
        // เรียกใช้งานเมธอดเพื่อดึงข้อมูลลูกค้าทั้งหมด
        val customers = CustomerService.selectAllFromCustomers()
        println("> Host: ${call.request.headers["Host"]}")
        call.respond(HttpStatusCode.OK, customers)
    }

}