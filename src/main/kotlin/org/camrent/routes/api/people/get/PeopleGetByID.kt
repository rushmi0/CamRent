package org.camrent.routes.api.people.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.PeopleService


fun Route.PeopleGetByID() {

    get("people/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id != null) {
            // ค้นหาข้อมูลลูกค้าด้วย ID
            val store = PeopleService.findPeopleByUserID(id)

            if (store != null) {
                // ตอบกลับด้วยข้อมูลบุคคลถ้าพบ
                call.respond(HttpStatusCode.OK, store)
            } else {
                // ถ้าไม่พบข้อมูลส่วนบุคคลให้ตอบกลับด้วยสถานะ 404 Not Found
                call.respond(
                    HttpStatusCode.NotFound,
                    "People not found for ID: $id"
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