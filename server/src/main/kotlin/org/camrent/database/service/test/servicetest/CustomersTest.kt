package org.camrent.database.service.test.servicetest

import org.camrent.database.DatabaseFactory
import org.camrent.database.field.CustomersField
import org.camrent.database.service.CustomerService


// ฟังก์ชัน main สำหรับทดสอบการใช้งาน
suspend fun main() {

    // กำหนดค่าและเชื่อมต่อกับฐานข้อมูล
    DatabaseFactory.initialize()

    // ดึงค่า ID ที่มากที่สุดและค่า ID ที่น้อยที่สุด
    println("Min CustomerID: ${CustomerService.selectMinID()}")
    println("Max CustomerID: ${CustomerService.selectMaxID()}")


    // แสดงข้อมูลทั้งหมดของลูกค้า
    // สร้าง Coroutine Scope สำหรับรันการทำงานที่ต้องใช้ runBlocking
    // เลือกข้อมูลทั้งหมดของลูกค้า
    val customersList: List<CustomersField> = CustomerService.selectAllFromCustomers()

    // วนลูปผ่านลิสต์ของลูกค้าและแสดงข้อมูล
    customersList.forEach { E ->
        println("\n=====================\n")
        println("Customer ID: ${E.customerID}")
        println("User Name: ${E.userName}")
        println("Profile Image: ${E.profileImage}")
        println("Auth Key: ${E.authKey}")
        println("Time Stamp: ${E.timeStamp}")
        println("Create Account Date: ${E.createAt}")
        println("Person ID: ${E.personID}")
    }

//    // อัปเดตข้อมูล
//    val updateData = update(1, "ProfileImage", "lala.png")
//    println(updateData)
//
//    // ลบข้อมูล
//    val customerIDToDelete = delete(1)
//    println(customerIDToDelete)


    val userNameToSearch = "ทดสอบ 2"
    val foundCustomer = CustomerService.findCustomerByUserName(userNameToSearch)

    println("Found Customer by UserName \n'$userNameToSearch': \n${foundCustomer}")

}