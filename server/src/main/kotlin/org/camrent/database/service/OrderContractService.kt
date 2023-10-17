package org.camrent.database.service


import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.OrderContractField
import org.camrent.database.forms.OrderContractForm
import org.camrent.database.table.OrderContractTable
import org.camrent.database.table.OrderContractTable.addressID
import org.camrent.database.table.OrderContractTable.customerID
import org.camrent.database.table.OrderContractTable.duration
import org.camrent.database.table.OrderContractTable.orderID
import org.camrent.database.table.OrderContractTable.productID
import org.camrent.database.table.OrderContractTable.quantity
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

object OrderContractService {

    // * เมธอดสำหรับดึงข้อมูลลูกค้าทั้งหมด
    suspend fun selectAllFromOrderContract(): List<OrderContractField> {
        return dbQuery {
            // ดึงข้อมูลทั้งหมดจากตาราง `CustomersTable` แล้วแปลงให้อยู่ในรูปของ `CustomersField`
            OrderContractTable.selectAll().map {
                OrderContractField(
                    it[orderID],
                    it[quantity],
                    it[duration],
                    it[customerID],
                    it[addressID],
                    it[productID]
                )
            }
        }
    }


    suspend fun findOrderContractByOrderID(itemID: Int): OrderContractField? {
        return dbQuery {
            OrderContractTable.select { orderID eq itemID }
                .mapNotNull {
                    // แปลงข้อมูลที่ดึงมาในแต่ละแถวเป็น CustomersField object
                    OrderContractField(
                        it[orderID],
                        it[quantity],
                        it[duration],
                        it[customerID],
                        it[addressID],
                        it[productID]
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }


    // เมธอดสำหรับเพิ่มข้อมูลลูกค้าใหม่
    suspend fun insert(field: OrderContractForm): Boolean {
        return try {
            dbQuery {
                // เพิ่มข้อมูลใน OrderContractTable
                OrderContractTable.insert {
                    // กำหนดข้อมูลในคอลัมน์ต่างๆ
                    it[quantity] = field.quantity
                    it[duration] = field.duration
                }
            }
            true // สำเร็จ
        } catch (e: Exception) {
            // ปัญหาในการเพิ่มข้อมูล
            false
        }
    }



    suspend fun update(itemID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = OrderContractTable.update({ orderID eq itemID }) {
                // กำหนดค่าใหม่ตามเมื่อเงื่อนไข ลงในคอลัมน์ที่ต้องการอัปเดต
                when (fieldName) {

                    "Quantity" -> {
                        // อัปเดต profileImage ให้กับลูกค้าที่มี OrderContract ID: $itemID
                        it[quantity] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี OrderContract ID: $itemID")
                    }

                    "Duration" -> {
                        // อัปเดต authKey ให้กับลูกค้าที่มี OrderContract ID: $itemID
                        it[duration] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี OrderContract ID: $itemID")
                    }

                    "CustomerID" -> {
                        // อัปเดต personID ให้กับลูกค้าที่มี OrderContract ID: $itemID
                        // แปลง newValue เป็น Int ก่อน
                        it[customerID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี OrderContract ID: $itemID")
                    }

                    "AddressID" -> {
                        // อัปเดต personID ให้กับลูกค้าที่มี OrderContract ID: $itemID
                        // แปลง newValue เป็น Int ก่อน
                        it[addressID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี OrderContract ID: $itemID")
                    }

                    "ProductID" -> {
                        // อัปเดต personID ให้กับลูกค้าที่มี OrderContract ID: $itemID
                        // แปลง newValue เป็น Int ก่อน
                        it[productID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี OrderContract ID: $itemID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
    }



}