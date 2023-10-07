package org.camrent.database.service


import org.camrent.database.DatabaseFactory.dbQuery

import org.camrent.database.field.FineField
import org.camrent.database.forms.FineForms
import org.camrent.database.forms.ProductsForm
import org.camrent.database.table.FineTable
import org.camrent.database.table.FineTable.customerID
import org.camrent.database.table.FineTable.date
import org.camrent.database.table.FineTable.description
import org.camrent.database.table.FineTable.fineID
import org.camrent.database.table.FineTable.image1
import org.camrent.database.table.FineTable.image2
import org.camrent.database.table.FineTable.image3
import org.camrent.database.table.FineTable.image4
import org.camrent.database.table.FineTable.penaltyFee
import org.camrent.database.table.FineTable.productID
import org.camrent.database.table.FineTable.status
import org.camrent.database.table.FineTable.storeID
import org.camrent.database.table.FineTable.timeStamp
import org.camrent.database.table.ProductsTable
import org.camrent.utils.Time
import org.camrent.utils.Time.getCurrentTime
import org.camrent.utils.Time.getCurrentDate
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

object FineService {

    suspend fun selectAllFromFine(): List<FineField> {
        return dbQuery {

            FineTable.selectAll().map {
                FineField(
                    it[fineID],
                    it[timeStamp],
                    it[date],
                    it[description],
                    it[image1],
                    it[image2],
                    it[image3],
                    it[image4],
                    it[penaltyFee],
                    it[status],
                    it[storeID],
                    it[productID],
                    it[customerID]

                )
            }
        }
    }

    suspend fun findProductsByfineID(finesID: Int): FineField?{
        return dbQuery {
            FineTable.select {fineID eq finesID }
                .mapNotNull {
                    FineField(

                        it[fineID],
                        it[timeStamp],
                        it[date],
                        it[description],
                        it[image1],
                        it[image2],
                        it[image3],
                        it[image4],
                        it[penaltyFee],
                        it[status],
                        it[storeID],
                        it[productID],
                        it[customerID]

                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }

    suspend fun insert(field: FineForms): Boolean {
        return try {
            dbQuery {

                FineTable.insert {
                    it[description] = field.description
                    it[timeStamp] = getCurrentTime()
                    it[date] = getCurrentDate()
                }
            }
            true // สำเร็จ
        } catch (e: Exception) {

            false
        }
    }

    suspend fun update(finesID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = FineTable.update({ FineTable.fineID eq finesID }) {

                when (fieldName) {

                    "Description" -> {
                        it[description] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "Image1" -> {
                        it[image1] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "Image2" -> {
                        it[image2] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "Image3" -> {
                        it[image3] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "Image4" -> {
                        it[image4] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "PenaltyFee" -> {
                        it[penaltyFee] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "Status" -> {
                        it[status] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "StoreID" -> {
                        it[storeID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "ProductID" -> {
                        it[productID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    "CustomerID" -> {
                        it[customerID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี fine ID: $finesID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
    }

}