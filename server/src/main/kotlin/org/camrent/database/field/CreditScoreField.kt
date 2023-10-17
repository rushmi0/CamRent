package org.camrent.database.field

data class CreditScoreField(
    val scoreID: Int,
    val score: Int,
    val storeID: Int?,
    val customerID: Int?
)
