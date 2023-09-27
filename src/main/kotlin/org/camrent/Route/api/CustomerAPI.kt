package org.camrent.Route.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.DatabaseFactory
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.Customer
import org.camrent.database.service.Customer.insert
import org.camrent.database.table.CustomersTable
import org.camrent.database.table.CustomersTable.userName


fun Application.customerRoute() {

    routing {
        route("api/v1") {

            get("customers") {
                // เรียกใช้งานเมธอดเพื่อดึงข้อมูลลูกค้าทั้งหมด
                val customers = Customer.selectAll()
                call.respond(HttpStatusCode.OK, customers)
            }


            post("customers") {
                // อ่านข้อมูลจาก request แล้วเพิ่มข้อมูลลูกค้า
                val form = call.receive<CustomersForm>()
                println(form)
                insert(form)
                call.respond(HttpStatusCode.Created, "Customer created successfully")
            }


            put("customers") {
                // อ่านข้อมูลจาก request แล้วอัปเดตข้อมูลลูกค้า
                val customerForm = call.receive<CustomersForm>()
                Customer.update(1, userName.name, customerForm.UserName)
                // ส่งคำตอบกลับ
                call.respondText("Customer updated successfully")
            }

            patch("customers") {
                // อ่านข้อมูลจาก request แล้วอัปเดตข้อมูลลูกค้า
                val customerForm = call.receive<CustomersForm>()
                Customer.update(1, CustomersTable.authKey.name, customerForm.AuthKey)
                // ส่งคำตอบกลับ
                call.respondText("Customer updated successfully")
            }

            delete("customers") {
                // ลบข้อมูลลูกค้า
                Customer.delete(1)
                // ส่งคำตอบกลับ
                call.respondText("Customer deleted successfully")
            }

        }
    }

}