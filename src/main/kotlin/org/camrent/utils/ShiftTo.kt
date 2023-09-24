package com.CamRent.utils

import com.CamRent.utils.ConvertBase.decimalToHexLittleEndian

object ShiftTo {

    fun String.toLittleEndian(decimal: Int, bytes: Int): String {
        return decimalToHexLittleEndian(decimal, bytes)
    }

}