package org.camrent.utils.test

import org.camrent.utils.AccountDirectory

fun main() {

    val id = 0

    val customersPath = "customers"
    AccountDirectory.createDirectory(customersPath, id)

    val storesPath = "stores"
    AccountDirectory.createDirectory(storesPath, id)

    val target = 0
    AccountDirectory.deleteDirectory(customersPath, target)
    AccountDirectory.deleteDirectory(storesPath, target)
}
