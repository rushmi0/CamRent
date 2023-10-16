package org.camrent.routes.api.stores.get

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.camrent.database.field.StoresField
import org.camrent.database.service.StoresService
import org.camrent.utils.ShiftTo.buildUrl

fun Route.StoreGet() {

    get("stores") {

        val stores = StoresService.selectAllFromStores()
        println("> Host: ${call.request.headers["Host"]}")

        val BASE_URL = "http://127.0.0.1:8080/api/v1/"

        val dataInfoList = stores.mapIndexed { _, store ->
            StoresField(
                storeID = store.storeID,
                storeName = store.storeName,
                profileImage = if (store.profileImage != "N/A") "${BASE_URL}stores/img/id/${store.storeID}/${store.profileImage.buildUrl()}" else "N/A",
                paymentMethod = store.paymentMethod,
                authKey = store.authKey,
                timeStamp = store.timeStamp,
                createAt = store.createAt,
                personID = store.personID
            )
        }

        call.respond(HttpStatusCode.OK, dataInfoList)
    }

}