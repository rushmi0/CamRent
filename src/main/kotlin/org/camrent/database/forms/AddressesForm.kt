package org.camrent.database.forms

data class AddressesForm(
    val addressID: String,
    val locationName: String,
    val city: String,
    val province: String,
    val postalNumber: String,
    val streetName: String
)
