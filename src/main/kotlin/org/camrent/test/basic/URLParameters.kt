package org.camrent.test.basic

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.urlParameters() {

    get ("/number") {
        val pageNumber = call.parameters["index"]
        println(pageNumber)
        call.respondText("หน้าเว็บที่: $pageNumber")
    }

}