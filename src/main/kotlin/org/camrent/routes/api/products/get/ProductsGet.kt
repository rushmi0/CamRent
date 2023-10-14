package org.camrent.routes.api.products.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.ProductsField
import org.camrent.database.service.ProductsService
import org.camrent.database.table.ProductsTable.productID
import org.camrent.utils.ShiftTo.buildUrl
import java.io.File


fun Route.ProductGet() {

    get("product") {

        val products = ProductsService.selectAllFromProducts()

        val BASE_URL = "http://127.0.0.1:8080/api/v1/"

        println("> Host: ${call.request.headers["Host"]}")


        val dataInfoList = products.mapIndexed { index, product ->
            val img1 = File(product.image1).name
            val img2 = File(product.image2).name
            val img3 = File(product.image3).name
            val img4 = File(product.image4).name

            ProductsField(
                productID = product.productID,
                productName = product.productName,
                image1 = "${BASE_URL}product/id/${product.productID}/idx/img1/${img1.buildUrl()}",
                image2 = "${BASE_URL}product/id/${product.productID}/idx/img2/${img2.buildUrl()}",
                image3 = "${BASE_URL}product/id/${product.productID}/idx/img3/${img3.buildUrl()}",
                image4 = "${BASE_URL}product/id/${product.productID}/idx/img4/${img4.buildUrl()}",
                type = product.type,
                price = product.price,
                specDetail = product.specDetail,
                description = product.description,
                status = product.status,
                storeID = product.storeID
            )
        }

        call.respond(HttpStatusCode.OK, dataInfoList)


    }

}