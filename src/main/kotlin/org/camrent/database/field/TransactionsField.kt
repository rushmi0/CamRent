package org.camrent.database.field

data class TransactionsField(
    val txID: String,
    val totalPrice: String,
    val status: String,
    val timeSpam: String,
    val date: String,
    val orderID: String
)
