package org.camrent.test.basic

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.basicBasePath() {

    get ("/base") {

            println("URL: ${call.request.uri}")
            println("Headers: ${call.request.headers}")

            // เรียกใช้ object request
            val request = call.request
            // เรียกใช้ URI ของ request
            val uri = request.uri
            // เรียกใช้ HTTP method ของ request
            val method = request.httpMethod
            // เรียกใช้ headers ของ request
            val headers = request.headers
            // เรียกใช้ query parameters ของ request
            val queryParams = request.queryParameters

            println()
            println("> Request: ${request.headers.names()}")
            println("> User-Agent: ${call.request.headers["User-Agent"]}")
            println("> Accept: ${call.request.headers["Accept"]}")
            println("> Host: ${call.request.headers["Host"]}")
            println("> Connection: ${call.request.headers["Connection"]}")
            println("> Accept-Encoding: ${call.request.headers["Accept-Encoding"]}")
            println("> Query Parameters: ${call.request.queryParameters.names()}")
            println("> Name: ${call.request.queryParameters["name"]}")
            println("> Email: ${call.request.queryParameters["email"]}")
            println("> URI: $uri")
            println("> Method: $method")
            println("> Headers: $headers")
            println("> Query Params: $queryParams")
            println()

            // ส่ง HTTP response กลับไปยัง client
            call.respondText( "Request: $request \n URI: $uri \n Method: $method \n Headers: $headers \n QueryParams: $queryParams")

    }

}