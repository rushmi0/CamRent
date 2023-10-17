package org.camrent.database.field

data class AddressesField(
    val addressID: Int,
    val locationName: String,
    val city: String,
    val province: String,
    val postalNumber: String,
    val streetName: String
)
