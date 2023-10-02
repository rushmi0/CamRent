package org.camrent.routes.api.people

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import org.camrent.security.xss.XssDetector
import org.camrent.database.forms.PeopleForm
import org.camrent.database.service.PeopleService
import org.camrent.routes.api.people.get.PeopleGet
import org.camrent.routes.api.people.get.PeopleGetByID
import org.camrent.routes.api.people.patch.PeoplePatchByID
import org.camrent.routes.api.people.post.PeoplePost

fun Application.peopleRoute() {

    routing {

        route("api/v1") {

            // `GET` "/people"
            PeopleGet()

            // `GET` "/people/{id}"
            PeopleGetByID()

            // `PATCH` "/people/{id}"
            PeoplePatchByID()

            // `POST` "/people"
            PeoplePost()

        }
    }


}