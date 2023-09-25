package org.camrent.database.field

data class CustomersField(
    val customerID: String,
    val userName: String,
    val profileImage: String,
    val authKey: String? = null,
    val timeStamp: String,
    val createAt: String,
    val personID: String
)
