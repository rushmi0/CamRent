package org.camrent.database.table

import org.jetbrains.exposed.sql.Table

object CreditScoreTable : Table("CreditScore") {

    val scoreID = integer("ScoreID")
    val score = integer("Score")
    val storeID = integer("StoreID").references(StoresTable.storeID).nullable()
    val customerID = integer("CustomerID").references(CustomersTable.customerID).nullable()

    override val primaryKey = PrimaryKey(scoreID)
}