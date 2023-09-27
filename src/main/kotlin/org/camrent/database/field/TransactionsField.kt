package org.camrent.database.field

data class TransactionsField(
    val txID: Int,
    val totalPrice: Int,
    val status: String,
    val timeSpam: String,
    val date: String,
    val orderID: Int
)
