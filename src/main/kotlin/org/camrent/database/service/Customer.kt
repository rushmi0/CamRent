package org.camrent.database.service


import com.CamRent.utils.Time.getCurrentDate
import com.CamRent.utils.Time.getCurrentTime
import kotlinx.coroutines.runBlocking
import org.camrent.database.DatabaseFactory
import org.camrent.database.DatabaseFactory.dbQuery
import org.camrent.database.forms.CustomersForm
import org.camrent.database.service.Customer.genPrimaryKey
import org.camrent.database.service.Customer.selectMinID
import org.camrent.database.table.CustomersTable
import org.camrent.database.table.CustomersTable.authKey
import org.camrent.database.table.CustomersTable.customerID
import org.camrent.database.table.CustomersTable.personID
import org.camrent.database.table.CustomersTable.profileImage
import org.camrent.database.table.CustomersTable.userName
import org.camrent.utils.ShiftTo.DectoLittleEndian
import org.jetbrains.exposed.sql.*

object Customer {

    suspend fun genPrimaryKey(): String {
        val minID = this.selectMinID()
        return if (minID == "ไม่มีข้อมูล") {
            "CTM-000001"
        } else {
            val maxID = this.selectMaxID()
            val prefix = maxID?.substring(0, 4) ?: ""
            val suffix = maxID?.substring(4) ?: ""
            prefix + (suffix.toInt() + 1).DectoLittleEndian(3)
        }
    }


    suspend fun insert(form: CustomersForm) {
        val primaryKey = this.genPrimaryKey()
        dbQuery {
            CustomersTable.insert {
                it[customerID] =  primaryKey
                it[userName] = form.UserName
                it[profileImage] = form.ProfileImage ?: ""
                it[authKey] = form.AuthKey
                it[timeStamp] = getCurrentTime()
                it[createAt] = getCurrentDate()
                it[personID] = form.PersonID
            }
        }
    }

    suspend fun selectAll(): List<CustomersForm> {
        return dbQuery {
            CustomersTable.selectAll().map {
                CustomersForm(
                    it[customerID],
                    it[userName],
                    it[profileImage],
                    it[authKey],
                    it[personID]
                )
            }
        }
    }


    suspend fun selectMaxID(): String? {
        return dbQuery {
            CustomersTable.slice(customerID.max())
                .selectAll()
                .singleOrNull()
                ?.let {
                    it[customerID.max()] ?: ""
                }
        }
    }

    suspend fun selectMinID(): String {
        return dbQuery {
            CustomersTable.slice(customerID.min())
                .selectAll()
                .singleOrNull()
                ?.getOrNull(customerID.min())
                ?.toString() ?: "ไม่มีข้อมูล"
        }
    }


}

suspend fun main() {

    DatabaseFactory.initialize()
//    val dataAll = selectAll()
//    println(dataAll)
//
    val maxCustomerId = Customer.selectMaxID()
    println("Min ID: ${selectMinID()}")
    println("Max CustomerID: $maxCustomerId")
    println("New ID: ${genPrimaryKey()}")

    runBlocking {
        val customersList = Customer.selectAll()
        customersList.forEach { customer ->
            println("Customer ID: ${customer.CustomerID}")
            println("User Name: ${customer.UserName}")
            println("Profile Image: ${customer.ProfileImage}")
            println("Auth Key: ${customer.AuthKey}")
            println("Person ID: ${customer.PersonID}")
            println("\n=====================\n")
        }
    }

//    val idBytes = maxCustomerId?.stringToSignedIntArray()
//    println(Arrays.toString(idBytes))


//    transaction {
//        // SELECT
//        val result: Query = CustomersTable.selectAll()
//
//        // SELECT WHERE
//        val filteredResult = CustomersTable.select { customerID eq "CTM-00013" }.toList()
//
//        // SELECT MAX
//        val maxCustomerID = CustomersTable.slice(customerID.max()).selectAll()
//            .single()[customerID.max()].toString()
//
//        // SELECT MIN
//        val minCustomerID = CustomersTable.slice(customerID.min()).selectAll()
//            .single()[customerID.min()].toString()
//
//        // SELECT SUM
//        val sumOfCustomerIDs = CustomersTable.slice(customerID.sum()).selectAll()
//            .single()[customerID.sum()].toString()
//
//        // Print results
//        println("SELECT All: $result")
//        println("SELECT WHERE: $filteredResult")
//        println("MAX CustomerID: $maxCustomerID")
//        println("MIN CustomerID: $minCustomerID")
//        println("SUM of CustomerIDs: $sumOfCustomerIDs")
//
//        val prefix = maxCustomerID.substring(0, 4)
//        val suffix = maxCustomerID.substring(4)
//        val newID = prefix + (suffix.toInt() + 1).DectoLittleEndian(3)
//        newID
//        //println("Prefix: $prefix \n Suffix: ${ (suffix.toInt() +1).DectoLittleEndian(3) }")
//    }


}