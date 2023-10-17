package org.camrent.database.forms

data class AddressesForm(
    val LocationName: String,
    val City: String,
    val Province: String,
    val PostalNumber: String,
    val StreetName: String
)
