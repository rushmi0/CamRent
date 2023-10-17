package org.camrent.database.field

data class ProductsField(
    val id: Int,
    val name: String,
    val image1: String,
    val image2: String,
    val image3: String,
    val image4: String,
    val type: String,
    val price: Int,
    val specDetail: String,
    val description: String,
    val status: String,
    val storeID: Int
)
