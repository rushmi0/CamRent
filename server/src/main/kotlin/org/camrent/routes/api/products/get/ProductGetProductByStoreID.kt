package org.camrent.routes.api.products.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.ProductsField
import org.camrent.database.service.ProductsService
import org.camrent.utils.ShiftTo.buildUrl
import java.io.File

fun Route.ProductGetProductByStoreID() {

    get("product/store-id/{id}") {

        val id = call.parameters["id"]?.toIntOrNull()
            ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

        val products = ProductsService.findStoreByProductID(id)

        val BASE_URL = "http://127.0.0.1:8080/api/v1/"

        val dataInfoList = products.mapIndexed { index, product ->
            val img1 = File(product.img1).name
            val img2 = File(product.img2).name
            val img3 = File(product.img3).name
            val img4 = File(product.img4).name

            ProductsField(
                id = product.id,
                name = product.name,
                img1 = "${BASE_URL}product/id/$id/idx/img1/${img1.buildUrl()}",
                img2 = "${BASE_URL}product/id/$id/idx/img2/${img2.buildUrl()}",
                img3 = "${BASE_URL}product/id/$id/idx/img3/${img3.buildUrl()}",
                img4 = "${BASE_URL}product/id/$id/idx/img4/${img4.buildUrl()}",
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
