package org.camrent.database.service.test

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.testJSON() {
    routing {
        post("/parse-json") {
            val simpleJSON = call.receive<SimpleJSON>()
            println(simpleJSON)
            call.respond("Received JSON with squadName: ${simpleJSON.squadName}")
        }
    }
}