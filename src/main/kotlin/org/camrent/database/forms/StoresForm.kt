package org.camrent.database.forms

data class StoresForm(
    val storeName: String,
    val profileImage: String,
    val paymentMethod: String,
    val authKey: String
)
