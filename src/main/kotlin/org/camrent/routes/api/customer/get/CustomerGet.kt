package org.camrent.routes.api.customer.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.CustomersField
import org.camrent.database.service.CustomerService
import org.camrent.utils.ShiftTo.buildUrl
import org.camrent.utils.ShiftTo.getImageFileName


fun Route.CustomerGet() {

    get("customers") {
        // เรียกใช้งานเมธอดเพื่อดึงข้อมูลลูกค้าทั้งหมด
        val customers = CustomerService.selectAllFromCustomers()
        println("> Host: ${call.request.headers["Host"]}")

        val BASE_URL = "http://127.0.0.1:8080/api/v1/"


        val dataInfoList = customers.mapIndexed { _, customer ->

            val profileImage = getImageFileName(customer.profileImage!!)
            CustomersField(
                customerID = customer.customerID,
                userName = customer.userName,
                profileImage = if (profileImage != "N/A") "${BASE_URL}customers/img/id/${customer.customerID}/${profileImage.buildUrl()}" else "N/A",
                authKey = customer.authKey,
                timeStamp = customer.timeStamp,
                createAt = customer.createAt,
                personID = customer.personID
            )
        }


        call.respond(HttpStatusCode.OK, dataInfoList)
    }

}