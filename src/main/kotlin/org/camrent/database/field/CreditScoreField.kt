package org.camrent.database.field

data class CreditScoreField(
    val scoreID: String,
    val score: Int,
    val report: String,
    val storeID: String,
    val customerID: String
)
