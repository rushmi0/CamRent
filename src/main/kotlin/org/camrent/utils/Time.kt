package com.CamRent.utils

import com.CamRent.utils.Time.getCurrentDate
import com.CamRent.utils.Time.getCurrentTime
import java.text.SimpleDateFormat
import java.util.*

object Time {

    fun getCurrentTime(): String {
        val currentTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTime = Date()
        return currentTimeFormat.format(currentTime)
    }

    fun getCurrentDate(): String {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return currentDate.format(Date())
    }

}

fun main() {
    val currentTime = getCurrentTime()
    val currentDate = getCurrentDate()
    println("Current time: $currentTime")
    println("Current date: $currentDate")
}