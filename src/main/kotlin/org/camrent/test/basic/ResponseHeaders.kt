package org.camrent.test.basic

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.reponseHeaders() {

    get ("/headers") {
        call.response.headers.append("server-name", "Ktor")
        call.response.headers.append("mai", "na ai sus")
        call.respondText("Header Attached")
    }

}