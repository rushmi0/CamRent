package org.camrent.routes.api.customer


import io.ktor.server.application.*
import io.ktor.server.routing.*

import org.camrent.routes.api.customer.delete.CustomerDeleteByID
import org.camrent.routes.api.customer.get.CustomerByUserName
import org.camrent.routes.api.customer.get.CustomerGet
import org.camrent.routes.api.customer.get.CustomerGetByID
import org.camrent.routes.api.customer.get.CustomerOpenImage
import org.camrent.routes.api.customer.patch.CustomerPatchByID
import org.camrent.routes.api.customer.patch.CustomerUploadImage
import org.camrent.routes.api.customer.post.CustomerPost
import org.camrent.routes.api.customer.test.AuthTest
import org.camrent.routes.api.customer.test.UploadFileTest

fun Application.customerRoute() {

    routing {

        route("api/v1") {

            AuthTest()
            UploadFileTest()


            /**
             * `GET` "/customers"
             * @sample CustomerGet: ใช้เพื่อเรียกดูข้อมูลจาก ตาราง Customers ทั้งหมด
             * */
            CustomerGet()

            // `GET` "/customers/id/{id}"
            CustomerGetByID()

            // `GET` "/customers/img/id/{id}"
            CustomerOpenImage()

            // `GET` "/customers/name/{name}"
            CustomerByUserName()

            // `POST` "/customers"
            CustomerPost()

            // `PATCH` "/customers/id/{id}"
            CustomerPatchByID()

            // `PATCH` "/customers/img/id/{id}"
            CustomerUploadImage()

            // `DELETE` "/customers/id/{id}"
            CustomerDeleteByID()

        }
    }

}