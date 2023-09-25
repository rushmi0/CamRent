package org.camrent.database.field

data class FineField(
    val fineID: String,
    val timeStamp: String,
    val date: String,
    val description: String,
    val image1: String,
    val image2: String,
    val image3: String,
    val image4: String,
    val penaltyFee: Int,
    val status: String,
    val storeID: String,
    val productID: String,
    val customerID: String
)
