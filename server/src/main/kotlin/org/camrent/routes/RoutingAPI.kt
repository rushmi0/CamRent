package org.camrent.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.camrent.routes.api.customer.customerRoute
import org.camrent.routes.api.people.peopleRoute
import org.camrent.routes.api.products.productsRoute
import org.camrent.routes.api.stores.storesRoute
import org.camrent.routes.api.test.testRoutingAPI

fun Application.routingLayerAPI() {

    customerRoute()

    storesRoute()

    peopleRoute()

    productsRoute()


    // testRoutingAPI()
}