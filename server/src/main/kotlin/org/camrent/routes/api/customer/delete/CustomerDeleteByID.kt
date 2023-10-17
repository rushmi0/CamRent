package org.camrent.routes.api.customer.delete

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.CustomerService
import org.camrent.utils.AccountDirectory


fun  Route.CustomerDeleteByID() {

    delete("customers/id/{id}") {
        val id = call.parameters["id"]?.toInt()
        // ลบข้อมูลลูกค้า
        if (id != null) {
            val deletionStatus = CustomerService.delete(id)
            val status = AccountDirectory.deleteDirectory("customers", id)
            if (deletionStatus) {
                // ถ้าการลบข้อมูลสำเร็จ
                call.respondText("Customer deleted successfully Status: $status")
            } else {
                // ถ้าไม่สามารถลบข้อมูลได้ (เช่น ID ไม่ถูกต้อง)
                call.respond(
                    HttpStatusCode.NotFound,
                    "Customer not found or unable to delete Status: $status"
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