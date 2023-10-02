package org.camrent.routes.api.customer


import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.CustomerService
import org.camrent.security.xss.XssDetector
import org.camrent.utils.AccountDirectory

import org.camrent.routes.api.customer.delete.CustomerDeleteByID
import org.camrent.routes.api.customer.get.CustomerByUerName
import org.camrent.routes.api.customer.get.CustomerGet
import org.camrent.routes.api.customer.get.CustomerGetByID
import org.camrent.routes.api.customer.patch.CustomerPatchByID
import org.camrent.routes.api.customer.post.CustomerPost

fun Application.customerRoute() {

    routing {

        route("api/v1") {

            // `GET` "/customers"
            CustomerGet()

            // `GET` "/customers/id/{id}"
            CustomerGetByID()

            // `GET` "/customers/name/{name}"
            CustomerByUerName()

            // `POST` "/customers"
            CustomerPost()

            // `PATCH` "/customers/id/{id}"
            CustomerPatchByID()

            // `DELETE` "/customers/id/{id}"
            CustomerDeleteByID()

        }
    }

}