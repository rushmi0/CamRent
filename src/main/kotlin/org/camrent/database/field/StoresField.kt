package org.camrent.database.field

data class StoresField(
    val storeID: Int,
    val storeName: String,
    val profileImage: String,
    val paymentMethod: String,
    val authKey: String,
    val timeStamp: String,
    val createAt: String,
    val personID: Int
)
