package org.camrent.database.field

data class PeopleField(
    val personID: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val addressID: String
)
