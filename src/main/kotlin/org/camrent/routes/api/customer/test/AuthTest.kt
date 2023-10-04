package org.camrent.routes.api.customer.test

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.utils.io.core.*
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.CustomerService
import org.camrent.security.securekey.Sha256
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger
import org.camrent.utils.ShiftTo.ByteArrayToHex
import java.math.BigInteger
import kotlin.text.toByteArray

fun Route.AuthTest() {

    post("/endpoint") {
        // อ่านข้อมูลที่ส่งมาจาก client
        val data = call.receive<CustomersForm>()
        data.authKey
        data.userName

        // อ่านค่าจาก headers
        val contentType = call.request.headers["Content-Type"]
        val signature = call.request.headers["Signature"]
        val witness = call.request.headers["Witness"]

        // ตอบกลับไปยัง client
        call.respond(
            HttpStatusCode.OK,
            "Received data: , Content-Type: $contentType, Signature: $signature, Witness: $witness"
        )
    }


    get("/customers/detail/{name}") {

        val signature = call.request.headers["Signature"]
        println("Signature: \n\t$signature")

        val witness = call.request.headers["Witness"]
        println("Witness: \n\t$witness")

        val accountName = call.parameters["name"].toString()
        println(accountName)

        val customer =  CustomerService.findCustomerByUserName(accountName)
        println("customer data: \n\t $customer")

        val hash = Sha256.hash(accountName.toByteArray())
        println(hash.ByteArrayToHex())
        val message = hash.ByteArrayToBigInteger()
        println(message)
        call.respond(customer!!)

    }

}

