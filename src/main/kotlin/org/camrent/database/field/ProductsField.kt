package org.camrent.database.field

data class ProductsField(
    val id: Int,
    val name: String,
    val img1: String,
    val img2: String,
    val img3: String,
    val img4: String,
    val type: String,
    val price: Int,
    val specDetail: String,
    val description: String,
    val status: String,
    val storeID: Int
)
