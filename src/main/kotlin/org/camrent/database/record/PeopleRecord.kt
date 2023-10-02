package org.camrent.database.record

data class PeopleRecord(
    val customerID: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
)
