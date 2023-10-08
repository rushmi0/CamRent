package org.camrent.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.camrent.routes.api.customer.customerRoute
import org.camrent.routes.api.people.peopleRoute
import org.camrent.routes.api.test.TestRoutingAPI

fun Application.routingLayerAPI() {

    customerRoute()
    peopleRoute()

    TestRoutingAPI()


}