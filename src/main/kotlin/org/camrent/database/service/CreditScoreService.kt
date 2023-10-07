package org.camrent.database.service



import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.CreditScoreField
import org.camrent.database.forms.CreditScoreForm


import org.camrent.database.table.CreditScoreTable
import org.camrent.database.table.CreditScoreTable.customerID
import org.camrent.database.table.CreditScoreTable.score
import org.camrent.database.table.CreditScoreTable.scoreID
import org.camrent.database.table.CreditScoreTable.storeID

import org.jetbrains.exposed.sql.insert

import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update


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


    suspend fun findCreditScoreByScoresID(scoresID: Int): CreditScoreField?{
        return dbQuery {
            CreditScoreTable.select { CreditScoreTable.scoreID eq scoresID }
                .mapNotNull {
                    CreditScoreField(

                        it[scoreID],
                        it[score],
                        it[storeID],
                        it[customerID]

                    )
                }
                .singleOrNull()
        }
    }




    suspend fun insert(field: CreditScoreForm): Boolean {
        return try {
            dbQuery {
                CreditScoreTable.insert {

                    it[score] = field.score
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }



    suspend fun update(scoresID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = CreditScoreTable.update({ CreditScoreTable.scoreID eq scoresID }) {

                when (fieldName) {

                    "Score" -> {
                        it[score] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Score ID: $scoresID")
                    }

                    "StoreID" -> {
                        it[storeID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Score ID: $scoresID")
                    }

                    "CustomerID" -> {
                        it[customerID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Score ID: $scoresID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
    }

}


