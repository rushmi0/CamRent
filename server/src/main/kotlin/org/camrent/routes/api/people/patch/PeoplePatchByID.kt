package org.camrent.routes.api.people.patch

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.PeopleService
import org.camrent.security.xss.XssDetector


fun Route.PeoplePatchByID() {

    patch("people/id/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        val payload = call.receive<Map<String, String>>()

        try {
            if (id != null) {
                for ((fieldName, newValue) in payload) {
                    if (XssDetector.containsXss(fieldName) || XssDetector.containsXss(fieldName)) {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            "Cross-site scripting detected in the field: $fieldName"
                        )
                        return@patch
                    }

                    // ถ้าไม่พบ XSS, ทำการอัปเดตข้อมูล
                    PeopleService.update(id, fieldName, newValue)
                }
                call.respondText("People updated successfully")
            } else {
                call.respond(
                    HttpStatusCode.BadRequest,
                    "Invalid People ID"
                )
            }

        } catch (e: ContentTransformationException) {
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