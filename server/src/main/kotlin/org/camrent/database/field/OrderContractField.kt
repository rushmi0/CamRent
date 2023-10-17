package org.camrent.database.field

data class OrderContractField(
    val orderID: Int,
    val quantity: Int?,
    val duration: String,
    val customerID: Int?,
    val addressID: Int?,
    val productID: Int?
)
