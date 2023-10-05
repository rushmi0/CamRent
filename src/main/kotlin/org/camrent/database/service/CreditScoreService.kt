package org.camrent.database.service


import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.CreditScoreField
import org.camrent.database.table.CreditScoreTable
import org.camrent.database.table.CreditScoreTable.customerID
import org.camrent.database.table.CreditScoreTable.score
import org.camrent.database.table.CreditScoreTable.scoreID
import org.camrent.database.table.CreditScoreTable.storeID
import org.jetbrains.exposed.sql.selectAll

object CreditScoreService {

    suspend fun selectAllFromCreditScore(): List<CreditScoreField> {
        return dbQuery {
            CreditScoreTable.selectAll().map {
                CreditScoreField(
                    it[scoreID],
                    it[score],
                    it[storeID],
                    it[customerID]
                )
            }
        }
    }


}