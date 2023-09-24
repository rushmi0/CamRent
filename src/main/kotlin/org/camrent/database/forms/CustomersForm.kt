package org.camrent.database.forms

data class CustomersForm(
    val CustomerID: String,
    val UserName: String,
    val ProfileImage: String?,
    val AuthKey: String,
    val PersonID: String
)
