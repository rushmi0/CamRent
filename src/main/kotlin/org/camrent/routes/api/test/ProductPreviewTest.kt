package org.camrent.routes.api.test

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.service.ProductsService
import org.camrent.database.service.StoresService


fun Route.ListProductPreview() {

    get("stores/product/img/{id}") {

        val targetID = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")

        val storeData = StoresService.findStoresByUserID(targetID)
        val storeID = storeData?.storeID!!

        val productData = ProductsService.findStoreByProductStoreID(storeID)
        println(productData)
        call.respond(productData)
    }

    get("stores/product/img/{id}/i{index}") {

        val targetID = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("`id` ไม่ถูกต้องหรือไม่ได้ระบุ")
        val targetIndex = call.parameters["index"]

    }

}