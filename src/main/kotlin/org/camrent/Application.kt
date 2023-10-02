package org.camrent

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

import org.camrent.database.DatabaseFactory
import org.camrent.routes.routingLayerAPI
import org.camrent.plugins.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.initialize()
    configureSecurity()
    configureSerialization()
    configureSockets()
    routingLayerAPI()
    configureRouting()
}
