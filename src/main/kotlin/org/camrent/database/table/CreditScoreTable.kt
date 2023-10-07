package org.camrent.database.table

import org.camrent.database.table.PeopleTable.default
import org.camrent.database.table.StoresTable.uniqueIndex
import org.jetbrains.exposed.sql.Table

object CreditScoreTable : Table("CreditScore") {

    val scoreID = integer("ScoreID").uniqueIndex()
    val score = integer("Score").default(0)
    val storeID = integer("StoreID").references(StoresTable.storeID).nullable()

    val customerID = integer("CustomerID").references(CustomersTable.customerID).nullable()

    override val primaryKey = PrimaryKey(scoreID)
}