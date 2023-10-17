package org.camrent.routes.api.people.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.PeopleService


fun Route.PeopleGet() {

    get("people") {
        val people = PeopleService.selectAllFromPeople()
        println("> Host: ${call.request.headers["Host"]}")
        call.respond(HttpStatusCode.OK, people)
    }

}