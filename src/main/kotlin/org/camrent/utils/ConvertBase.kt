package com.CamRent.utils

object ConvertBase {

    fun decimalToHexLittleEndian(decimal: Int, numBytes: Int): String {
        val hex = decimal.toString(16).padStart(numBytes * 2, '0').toUpperCase()
        val reversedHex = hex.chunked(2).asReversed().joinToString("")
        return reversedHex
    }

}