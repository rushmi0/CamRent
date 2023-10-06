package org.camrent.database.service


import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.StoresField
import org.camrent.database.forms.StoresForm
import org.camrent.database.table.StoresTable
import org.camrent.database.table.StoresTable.authKey
import org.camrent.database.table.StoresTable.createAt
import org.camrent.database.table.StoresTable.paymentMethod
import org.camrent.database.table.StoresTable.personID
import org.camrent.database.table.StoresTable.profileImage
import org.camrent.database.table.StoresTable.storeID
import org.camrent.database.table.StoresTable.storeName
import org.camrent.database.table.StoresTable.timeStamp
import org.camrent.utils.Time
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

object StoresService {



    suspend fun findStoresByUserID(userID: Int): StoresField? {
        return dbQuery {
            // เลือกข้อมูลลูกค้าที่มีชื่อผู้ใช้ตรงกับ `accountName`
            StoresTable.select { storeID eq userID }
                .mapNotNull {
                    // แปลงข้อมูลที่ดึงมาในแต่ละแถวเป็น StoresField object
                    StoresField(
                        it[storeID],
                        it[storeName],
                        it[profileImage],
                        it[paymentMethod],
                        it[authKey],
                        it[timeStamp],
                        it[createAt],
                        it[personID]
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }


    suspend fun findStoresByUserName(accountName: String): StoresField? {
        return dbQuery {
            // เลือกข้อมูลลูกค้าที่มีชื่อผู้ใช้ตรงกับ `accountName`
            StoresTable.select { storeName eq accountName }
                .mapNotNull {
                    // แปลงข้อมูลที่ดึงมาในแต่ละแถวเป็น StoresField object
                    StoresField(
                        it[storeID],
                        it[storeName],
                        it[profileImage],
                        it[paymentMethod],
                        it[authKey],
                        it[timeStamp],
                        it[createAt],
                        it[personID]
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }



    suspend fun findStoresByPublicKey(publicKey: String): StoresField? {
        return dbQuery {
            // เลือกข้อมูลลูกค้าที่มีชื่อผู้ใช้ตรงกับ `accountName`
            StoresTable.select { authKey eq publicKey }
                .mapNotNull {
                    // แปลงข้อมูลที่ดึงมาในแต่ละแถวเป็น StoresField object
                    StoresField(
                        it[storeID],
                        it[storeName],
                        it[profileImage],
                        it[paymentMethod],
                        it[authKey],
                        it[timeStamp],
                        it[createAt],
                        it[personID]
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }



    // เมธอดสำหรับเพิ่มข้อมูลลูกค้าใหม่
    suspend fun insert(field: StoresForm): Boolean {
        return try {
            dbQuery {
                // เพิ่มข้อมูลลูกค้าใน StoresTable
                StoresTable.insert {
                    // กำหนดข้อมูลในคอลัมน์ต่างๆ
                    it[storeName] = field.storeName
                    it[profileImage] = field.profileImage
                    it[paymentMethod] = field.paymentMethod
                    it[authKey] = field.authKey
                    it[timeStamp] = Time.getCurrentTime()  // กำหนดเวลาที่เพิ่มลงฐานข้อมูล
                    it[createAt] = Time.getCurrentDate()   // กำหนดวันที่เพิ่มลงฐานข้อมูล
                }
            }
            true // สำเร็จ
        } catch (e: Exception) {
            // ปัญหาในการเพิ่มข้อมูล
            false
        }
    }
    
    
    suspend fun update(storesID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = StoresTable.update( { StoresTable.storeID eq storesID } ) {

                when (fieldName) {
                    "StoreName" -> {
                        // อัปเดต storeName ให้กับลูกค้าที่มี Stores ID: $storesID
                        it[storeName] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Stores ID: $storesID")
                    }

                    "ProfileImage" -> {
                        // อัปเดต profileImage ให้กับลูกค้าที่มี Stores ID: $storesID
                        it[profileImage] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Stores ID: $storesID")
                    }

                    "PaymentMethod" -> {
                        // อัปเดต paymentMethod ให้กับลูกค้าที่มี Stores ID: $storesID
                        it[paymentMethod] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Stores ID: $storesID")
                    }

                    "AuthKey" -> {
                        // อัปเดต authKey ให้กับลูกค้าที่มี Stores ID: $storesID
                        it[authKey] = newValue
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Stores ID: $storesID")
                    }

                    "PersonID" -> {
                        // อัปเดต personID ให้กับลูกค้าที่มี Stores ID: $storesID
                        // แปลง newValue เป็น Int ก่อน
                        it[personID] = newValue.toInt()
                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Stores ID: $storesID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }
            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
        
    }
    
    

    
}