package org.camrent.database.field

data class TransactionsField(
    val txID: Int,
    val totalPrice: Int,
    val status: String,
    val timeStamp: String,
    val date: String,
    val orderID: Int?
)
