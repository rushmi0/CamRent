package org.camrent.database.service

import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.field.CustomersField
import org.camrent.database.field.PeopleField
import org.camrent.database.forms.PeopleForm
import org.camrent.database.table.CustomersTable
import org.camrent.database.table.PeopleTable
import org.camrent.database.table.PeopleTable.addressID
import org.camrent.database.table.PeopleTable.email
import org.camrent.database.table.PeopleTable.firstName
import org.camrent.database.table.PeopleTable.lastName
import org.camrent.database.table.PeopleTable.personID
import org.camrent.database.table.PeopleTable.phoneNumber
import org.jetbrains.exposed.sql.*

object PeopleService {

    // เมธอดสำหรับดึงค่า ID ที่มากที่สุด
    suspend fun selectMaxID(): Int {
        return dbQuery {
            PeopleTable.slice(personID.max())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(personID.max()) ?: 0
        }
    }

    // เมธอดสำหรับดึงค่า ID ที่น้อยที่สุด
    suspend fun selectMinID(): Int {
        return dbQuery {
            PeopleTable.slice(personID.min())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(personID.min()) ?: 0
        }
    }

    suspend fun selectAllFromPeople(): List<PeopleField> {
        return dbQuery {
            // ดึงข้อมูลทั้งหมดจาก `PeopleTable` แล้วแปลงให้อยู่ในรูปของ `PeopleField`
            PeopleTable.selectAll().map {
                PeopleField(
                    it[personID],
                    it[firstName],
                    it[lastName],
                    it[email],
                    it[phoneNumber],
                    it[addressID]
                )
            }
        }
    }

    suspend fun findPeopleByUserID(userID: Int): PeopleField? {
        return dbQuery {
            PeopleTable.select { personID eq userID }
                .mapNotNull {
                    PeopleField(
                        it[personID],
                        it[firstName],
                        it[lastName],
                        it[email],
                        it[phoneNumber],
                        it[addressID]
                    )
                }
                .singleOrNull()
        }
    }

    suspend fun findPeopleByEmail(emailPeople: String): PeopleField? {
        return dbQuery {
            PeopleTable.select { email eq emailPeople }
                .mapNotNull {
                    PeopleField(
                        it[personID],
                        it[firstName],
                        it[lastName],
                        it[email],
                        it[phoneNumber],
                        it[addressID]
                    )
                }
                .singleOrNull() // คืนค่าผลลัพธ์เดียวหรือ null ถ้าไม่พบข้อมูล
        }
    }

    
    // เมธอดสำหรับหา CustomerID จาก PersonID
    suspend fun findCustomerByPersonID(personID: Int): CustomersField? {
        return dbQuery {
            // ดึงข้อมูลจากตาราง CustomersTable โดยใช้ PersonID
            CustomersTable.select { CustomersTable.personID eq personID }
                .singleOrNull()?.let {
                    // สร้าง CustomersField จากข้อมูลที่ได้
                    CustomersField(
                        it[CustomersTable.customerID],
                        it[CustomersTable.userName],
                        it[CustomersTable.profileImage],
                        it[CustomersTable.authKey],
                        it[CustomersTable.timeStamp],
                        it[CustomersTable.createAt],
                        it[CustomersTable.personID]
                    )
                }
        }
    }


    suspend fun insert(record: PeopleForm): Boolean {
        return try {
            dbQuery {
                // เพิ่มข้อมูลบุคคลใน PeopleTable
                PeopleTable.insert {
                    // กำหนดข้อมูลในคอลัมน์ต่างๆ
                    it[firstName] = record.firstName
                    it[lastName] = record.lastName
                    it[email] = record.email
                    it[phoneNumber] = record.phoneNumber
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }


    suspend fun update(personID: Int, fieldName: String, newValue: String): Boolean {
        return dbQuery {
            val updatedRowCount = PeopleTable.update({ PeopleTable.personID eq personID }) {
                when (fieldName) {
                    "FirstName" -> {
                        it[firstName] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Person ID: $personID")
                    }

                    "LastName" -> {
                        it[lastName] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Person ID: $personID")
                    }

                    "Email" -> {
                        it[email] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Person ID: $personID")
                    }

                    "PhoneNumber" -> {
                        it[phoneNumber] = newValue
                        println("อัปเดต $fieldName ให้กับบุคคลที่มี Person ID: $personID")
                    }

                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
                }
            }

            updatedRowCount > 0 // คืนค่าเป็น true ถ้ามีการอัปเดตเรียบร้อย
        }
    }


}