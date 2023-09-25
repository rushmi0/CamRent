package org.camrent.database.field

data class ProductsField(
    val productID: String,
    val productName: String,
    val image1: String,
    val image2: String,
    val image3: String,
    val image4: String,
    val type: String,
    val price: Int,
    val specDetail: String,
    val description: String,
    val productStats: String,
    val storeID: String
)
