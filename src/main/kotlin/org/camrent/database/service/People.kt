//package org.camrent.database.service
//
//import com.CamRent.utils.Time
//import org.camrent.database.DatabaseFactory
//import org.camrent.database.field.CustomersField
//import org.camrent.database.forms.CustomersForm
//import org.camrent.database.forms.PeopleForm
//import org.camrent.database.gen.INITKEY
//import org.camrent.database.table.CustomersTable
//import org.camrent.database.table.PeopleTable
//import org.camrent.utils.ShiftTo.DectoLittleEndian
//import org.jetbrains.exposed.sql.*
//import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
//
//object People {
//
//    // เมธอดสำหรับสร้าง Primary Key ใหม่
//    suspend fun genPrimaryKey(): String {
//        // ตรวจสอบ ID ที่น้อยที่สุด (หากไม่มีข้อมูลให้สร้าง ID ใหม่)
//        val minID = this.maxSequenceNumber()
//        return if (minID == "ไม่มีข้อมูล") {
//            INITKEY.MAIN["PS"].toString()
//        } else {
//            // สร้าง ID ใหม่โดยเพิ่ม 1 จากค่า ID ที่มากที่สุด
//            val maxID = this.selectMaxID()
//            val prefix = maxID?.substring(0, 3) ?: ""
//            val suffix = maxID?.substring(3) ?: ""
//            prefix + (suffix.toInt() + 1).DectoLittleEndian(2)
//        }
//    }
//
//    // เมธอดสำหรับเพิ่มข้อมูลลูกค้า
//    suspend fun insert(form: PeopleForm) {
//        val primaryKey = this.genPrimaryKey()
//        DatabaseFactory.dbQuery {
//            PeopleTable.insert {
//                // กำหนดข้อมูลในคอลัมน์ต่างๆ
//                it[personID] = primaryKey
//                it[sequenceNumber] = form.
//                it[profileImage] = form.ProfileImage ?: "N/A"
//                it[authKey] = form.AuthKey
//                it[timeStamp] = Time.getCurrentTime()
//                it[createAt] = Time.getCurrentDate()
//                it[personID] = form.PersonID
//            }
//        }
//    }
//
//    // เมธอดสำหรับอัปเดตข้อมูลลูกค้า
//    suspend fun update(customerID: String, fieldName: String, newValue: String): Unit {
//        return DatabaseFactory.dbQuery {
//            CustomersTable.update({ CustomersTable.customerID eq customerID }) {
//                // กำหนดค่าใหม่ตามเมื่อเงื่อนไข ลงในคอลัมน์ที่ต้องการอัปเดต
//                when (fieldName) {
//                    userName.name -> {
//                        // อัปเดต userName ให้กับลูกค้าที่มี Customer ID: $customerID
//                        it[userName] = newValue
//                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
//                    }
//
//                    profileImage.name -> {
//                        // อัปเดต profileImage ให้กับลูกค้าที่มี Customer ID: $customerID
//                        it[profileImage] = newValue
//                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
//                    }
//
//                    authKey.name -> {
//                        // อัปเดต authKey ให้กับลูกค้าที่มี Customer ID: $customerID
//                        it[authKey] = newValue
//                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
//                    }
//
//                    personID.name -> {
//                        // อัปเดต personID ให้กับลูกค้าที่มี Customer ID: $customerID
//                        it[personID] = newValue
//                        println("อัปเดต $fieldName ให้กับลูกค้าที่มี Customer ID: $customerID")
//                    }
//
//                    else -> throw IllegalArgumentException("ไม่พบชื่อฟิลด์ $fieldName")
//                }
//            }
//        }
//    }
//
//
//    // เมธอดสำหรับลบข้อมูลลูกค้า
//    suspend fun delete(customerID: String): Unit {
//        DatabaseFactory.dbQuery {
//            // ลบข้อมูลลูกค้าที่มี customerID ตรงกับที่ระบุ
//            CustomersTable.deleteWhere { CustomersTable.customerID eq customerID }
//        }
//    }
//
//
//    // * เมธอดสำหรับดึงข้อมูลลูกค้าทั้งหมด
//    suspend fun selectAll(): List<CustomersField> {
//        return DatabaseFactory.dbQuery {
//            // ดึงข้อมูลทั้งหมดจาก CustomersTable แล้วแปลงให้อยู่ในรูปของ CustomersField
//            CustomersTable.selectAll().map {
//                CustomersField(
//                    it[CustomersTable.customerID],
//                    it[CustomersTable.userName],
//                    it[CustomersTable.profileImage],
//                    it[CustomersTable.authKey],
//                    it[CustomersTable.timeStamp],
//                    it[CustomersTable.createAt],
//                    it[CustomersTable.personID]
//                )
//            }
//        }
//    }
//
//
//    suspend fun maxSequenceNumber(): String? {
//        return DatabaseFactory.dbQuery {
//            // ดึงข้อมูล customerID โดยเรียงลำดับ sequenceNumber จากมากไปน้อย
//            val maxSequence = CustomersTable
//                .slice(CustomersTable.customerID)
//                .selectAll()
//
//                /**
//                 * ในกรณีนี้เราใช้ `orderBy` เพื่อเรียงลำดับตาม `sequenceNumber` ในทิศทางจากมากไปน้อย (SortOrder.DESC)
//                 * และใช้ limit(1) เพื่อจำกัดให้เรียกเฉพาะหนึ่งค่าที่มากที่สุด โดยจะใช้ singleOrNull()
//                 * เพื่อดึงค่าเดียวหรือถ้าไม่มีข้อมูลจะได้เป็น null
//                 * */
//                .orderBy(CustomersTable.sequenceNumber, SortOrder.DESC)
//                .limit(1)
//                .singleOrNull()
//
//            // ดึงข้อมูล customerID จาก maxSequenceCustomer
//            val maxSequenceCustomerID = maxSequence?.get(CustomersTable.customerID)
//
//            /**
//             *  `dbQuery` เป็น Higher-order Function: dbQuery เป็นฟังก์ชันที่รับฟังก์ชันอื่นเป็นอาร์กิวเมนต์
//             *  `return@dbQuery` จึงใช้ return@dbQuery เพื่อระบุว่าเราต้องการคืนค่าจาก dbQuery ในจุดนั้น
//             *  `maxSequenceCustomerID` เป็นค่าที่เราต้องการให้คืน
//             * */
//
//            // คืนค่า maxSequenceCustomerID จากส่วนของ dbQuery และถ้าไม่พบข้อมูลให้คืนค่า "ไม่มีข้อมูล"
//            return@dbQuery maxSequenceCustomerID ?: "ไม่มีข้อมูล"
//        }
//    }
//
//
//    // เมธอดสำหรับดึงค่า ID ที่มากที่สุด
//    suspend fun selectMaxID(): String? {
//        return DatabaseFactory.dbQuery {
//            CustomersTable.slice(CustomersTable.customerID.max())
//                .selectAll()
//                .singleOrNull()
//                ?.let {
//                    it[CustomersTable.customerID.max()] ?: ""
//                }
//        }
//    }
//
//    // เมธอดสำหรับดึงค่า ID ที่น้อยที่สุด
//    suspend fun selectMinID(): String {
//        return DatabaseFactory.dbQuery {
//            CustomersTable.slice(CustomersTable.customerID.min())
//                .selectAll()
//                .singleOrNull()
//                ?.getOrNull(CustomersTable.customerID.min())
//                ?.toString() ?: "ไม่มีข้อมูล"
//        }
//    }
//
//}