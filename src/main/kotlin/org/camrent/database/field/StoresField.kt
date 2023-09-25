package org.camrent.database.field

data class StoresField(
    val storeID: String,
    val storeName: String,
    val profileImage: String,
    val paymentMethod: String,
    val authKey: String? = null,
    val timeStamp: String,
    val createAt: String,
    val personID: String
)
