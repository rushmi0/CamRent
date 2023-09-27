package org.camrent.test.basic

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val email: String
)

fun Route.requestsBody() {

    post ("/login") {

        try {
            val user = call.receive<User>()
            println(user)
            // ใช้ข้อมูลที่รับมาจาก client ในตัวแปร user
            val name = user.name
            val email = user.email
            // ตรวจสอบและดำเนินการต่อไปตามความต้องการ
            call.respondText("Received name: $name, email: $email")
        } catch (e: ContentTransformationException) {
            // จัดการข้อผิดพลาดในการแปลงข้อมูล
            call.respond(HttpStatusCode.BadRequest, "Invalid data format")
        }

    }

}