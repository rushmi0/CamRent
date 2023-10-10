package org.camrent.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.routes.api.auth.get.UserAuthentication
import org.camrent.routes.api.auth.post.SignUpNewAccount

import java.io.File


fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {

        route("api/v1") {
            // `GET` "/user/auth/{publicKey}" : ใช้สำหรับ `Sign-in` ยืนยังตัวตน และจะได้รับข้อมูลที่สำคัญ
            UserAuthentication()

            // `POST` "/user/sign-up" : ใช้สำหรับ `Sign-up` สร้างบัญชี และจะได้รับข้อมูลที่สำคัญ
            SignUpNewAccount()
        }

        get ("") {
            val file = File("src/main/resources/images/preview/start/Start_Server.jpg")

            // ตรวจสอบว่าไฟล์มีอยู่จริงหรือไม่
            if (file.exists()) {
                call.respondFile(file)
            } else {
                // หากไม่พบไฟล์ ให้ส่ง HTTP 404 Not Found
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
