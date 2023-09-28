package org.camrent.routes.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import org.camrent.database.forms.PeopleForm
import org.camrent.database.service.People
import org.camrent.security.xss.XssDetector


fun Application.peopleRoute() {

    routing {

        route("api/v1") {

            get("people") {
                val people = People.selectAll()
                println("> Host: ${call.request.headers["Host"]}")
                call.respond(HttpStatusCode.OK, people)
            }

            post("people") {
                // รับข้อมูลจาก body ของคำขอ POST
                val payload = call.receive<PeopleForm>()
                if (
                    payload.firstName.isNotBlank() &&
                    payload.lastName.isNotBlank() &&
                    payload.email.isNotBlank() &&
                    payload.phoneNumber.isNotBlank()
                ) {
                    if (XssDetector.containsHtmlTags(payload.firstName) || XssDetector.containsJavascript(payload.firstName) ||
                        XssDetector.containsHtmlTags(payload.lastName) || XssDetector.containsJavascript(payload.lastName) ||
                        XssDetector.containsHtmlTags(payload.email) || XssDetector.containsJavascript(payload.email) ||
                        XssDetector.containsHtmlTags(payload.phoneNumber) || XssDetector.containsJavascript(payload.phoneNumber)
                    ) {
                        // ถ้าพบ Cross-site Scripting (XSS), ตอบกลับด้วยสถานะผลลัพธ์ 400 Bad Request
                        call.respond(HttpStatusCode.BadRequest, "Cross-site scripting detected.")
                    } else {

                    }
                }
            }

        }

    }

}