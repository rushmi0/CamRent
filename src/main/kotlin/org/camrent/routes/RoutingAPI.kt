package org.camrent.routes

import io.ktor.server.application.*
import org.camrent.routes.api.customer.customerRoute
import org.camrent.routes.api.people.peopleRoute

fun Application.routingLayerAPI() {

    customerRoute()
    peopleRoute()

}