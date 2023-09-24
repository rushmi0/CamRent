package org.camrent.database.forms

data class PeopleForm(
    val peopleID: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val addressID: String
)
