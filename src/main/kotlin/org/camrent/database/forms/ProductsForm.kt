package org.camrent.database.forms

data class ProductsForm(
    val productName: String,
    val productType: String,
    val productPrice: Int,
    val specDetail: Map<String, Any>
)
