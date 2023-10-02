package org.camrent.security.securekey


import org.camrent.utils.ShiftTo.ByteArrayToHex
import org.camrent.utils.ShiftTo.HexToByteArray
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

/**
 * Converts data to and from Bech32 strings. Not instantiable.
 */
object Bech32 {
    /*---- Static functions for segregated witness addresses ----*/
    /**
     * Encodes the specified segregated witness output into a Bech32 address string.
     * @param humanPart the prefix given to the resulting string, which should be a mnemonic for
     * the cryptocurrency name; must be not `null`, must have length in the range [1, 83],
     * must have all characters in the ASCII range [33, 126] but excluding uppercase characters
     * @param witVer the witness version number; must be in the range [0, 16]
     * @param witProg the raw witness program, without the length byte;
     * must be not `null`, must have length in the range [2, 40]
     * @return the Bech32 address of the specified segregated witness output;
     * the result is entirely ASCII, lacks uppercase, and at most 90 characters long
     * @throws NullPointerException if humanPart or witProg is `null`
     * @throws IllegalArgumentException if any argument violates the stated preconditions,
     * or the combination of humanPart and witProg would make the result exceed 90 characters
     */
    fun segwitToBech32(humanPart: String, witVer: Int, witProg: ByteArray): String {
        // Check arguments
        Objects.requireNonNull(humanPart)
        Objects.requireNonNull(witProg)
        require(!(witVer < 0 || witVer > 16)) { "Invalid witness version" }
        require(!(witProg.size < 2 || witProg.size > 40)) { "Invalid witness program length" }

        // Create buffer of 5-bit groups
        val data = ByteArrayOutputStream() // Every element is uint5
        assert(witVer ushr 5 == 0)
        data.write(witVer) // uint5

        // Variables/constants for bit processing
        val IN_BITS = 8
        val OUT_BITS = 5
        var inputIndex = 0
        var bitBuffer = 0 // Topmost bitBufferLen bits are valid; remaining lower bits are zero
        var bitBufferLen = 0 // Always in the range [0, 12]

        // Repack all 8-bit bytes into 5-bit groups, adding padding
        while (inputIndex < witProg.size || bitBufferLen > 0) {
            assert(0 <= bitBufferLen && bitBufferLen <= IN_BITS + OUT_BITS - 1)
            assert(bitBuffer shl bitBufferLen == 0)
            if (bitBufferLen < OUT_BITS) {
                if (inputIndex < witProg.size) {  // Read a byte
                    bitBuffer = bitBuffer or (witProg[inputIndex].toInt() and 0xFF shl 32 - IN_BITS - bitBufferLen)
                    inputIndex++
                    bitBufferLen += IN_BITS
                } else  // Create final padding
                    bitBufferLen = OUT_BITS
            }
            assert(bitBufferLen >= 5)

            // Write a 5-bit group
            data.write(bitBuffer ushr 32 - OUT_BITS) // uint5
            bitBuffer = bitBuffer shl OUT_BITS
            bitBufferLen -= OUT_BITS
        }
        return bitGroupsToBech32(humanPart, data.toByteArray())
    }

    /**
     * Decodes the specified Bech32 address string into a segregated witness output.
     * The result is a triple (human-readable part, witness version, witness program).
     * @param s the Bech32 string to decode, which must be either
     * all-lowercase or all-uppercase, and at most 90 characters long
     * @return a triple where index 0 is a `String` representing the human-readable part
     * (which obeys all the rules as stated in the encoder), index 1 is an `Integer`
     * representing the witness version (in the range [0, 16]), and index 2 is a new
     * `byte[]` containing the witness program (whose length is in the range [2, 40];
     * the array contains 8-bit data)
     * @throws NullPointerException if the string is `null`
     * @throws IllegalArgumentException if the string is too long, has mixed case,
     * lacks a separator, has an invalid human-readable part, has non-base-32
     * characters in the data, lacks a full checksum, has an incorrect checksum,
     * has an invalid witness version, or has an invalid length of witness program
     */
    fun bech32ToSegwit(s: String): Array<Any> {
        val decoded = bech32ToBitGroups(s)
        val data = decoded[1] as ByteArray

        // Extract leading value representing version
        require(data.size >= 1) { "Missing witness version" }
        val witVer = data[0].toInt()
        require(!(witVer < 0 || witVer > 16)) { "Invalid witness version" }

        // Initialize output array
        val witProg = ByteArray((data.size - 1) * 5 / 8) // Discard version prefix and padding suffix
        require(!(witProg.size < 2 || witProg.size > 40)) { "Invalid witness program length" }

        // Variables/constants for bit processing
        val IN_BITS = 5
        val OUT_BITS = 8
        var outputIndex = 0
        var bitBuffer = 0 // Topmost bitBufferLen bits are valid; remaining lower bits are zero
        var bitBufferLen = 0 // Always in the range [0, 10]

        // Repack all 5-bit groups into 8-bit bytes, discarding padding
        for (i in 1 until data.size) {
            val b = data[i].toInt()
            assert(0 <= bitBufferLen && bitBufferLen <= IN_BITS * 2)
            assert(bitBuffer shl bitBufferLen == 0)
            bitBuffer = bitBuffer or (b shl 32 - IN_BITS - bitBufferLen)
            bitBufferLen += IN_BITS
            if (bitBufferLen >= OUT_BITS) {
                witProg[outputIndex] = (bitBuffer ushr 32 - OUT_BITS).toByte()
                outputIndex++
                bitBuffer = bitBuffer shl OUT_BITS
                bitBufferLen -= OUT_BITS
            }
        }
        assert(outputIndex == witProg.size)
        require(bitBuffer == 0) { "Non-zero padding" }
        return arrayOf(decoded[0], witVer, witProg)
    }

    fun decodeBech32Address(bech32Address: String): Triple<String, Int, String>? {
        try {
            val result = bech32ToSegwit(bech32Address)
            val humanPart = result[0] as String
            val witVer = result[1] as Int
            val witProg = result[2] as ByteArray
            return Triple(humanPart, witVer, witProg.joinToString("") { "%02x".format(it) })
        } catch (e: Exception) {
            println("Failed to decode Bech32 address: ${e.message}")
            return null
        }
    }


    /*---- Static functions for bit groups ----*/
    /**
     * Encodes the specified human-readable part prefix plus
     * the specified array of 5-bit data into a Bech32 string.
     * @param humanPart the prefix given to the resulting string, which should be a mnemonic for
     * the cryptocurrency name; must be not `null`, must have length in the range [1, 83],
     * must have all characters in the ASCII range [33, 126] but excluding uppercase characters
     * @param data a non-`null` sequence of zero or more values, where each value is a uint5
     * @return the Bech32 string representing the specified two pieces of data;
     * the result is entirely ASCII, lacks uppercase, and at most 90 characters long
     * @throws NullPointerException if the string or data is `null`
     * @throws IllegalArgumentException if any argument violates the stated
     * preconditions, or `humanPart.length() + data.length > 83`
     */
    fun bitGroupsToBech32(humanPart: String, data: ByteArray): String {
        // Check arguments
        Objects.requireNonNull(humanPart)
        Objects.requireNonNull(data)
        val human = humanPart.toCharArray()
        checkHumanReadablePart(human)
        for (b in data) {
            require(b.toInt() ushr 5 == 0) { "Expected 5-bit groups" }
        }
        require(human.size + 1 + data.size + 6 <= 90) { "Output too long" }

        // Compute checksum
        val checksum: Int
        checksum = try {
            val temp = expandHumanReadablePart(human) // Every element is uint5
            temp.write(data)
            temp.write(ByteArray(CHECKSUM_LEN))
            polymod(temp.toByteArray()) xor 1
        } catch (e: IOException) {
            throw AssertionError(e) // Impossible
        }

        // Encode to base-32
        val sb = StringBuilder(humanPart).append('1')
        for (b in data) sb.append(ALPHABET[b.toInt()])
        for (i in 0 until CHECKSUM_LEN) {
            val b = checksum ushr (CHECKSUM_LEN - 1 - i) * 5 and 0x1F // uint5
            sb.append(ALPHABET[b])
        }
        return sb.toString()
    }

    /**
     * Decodes the specified Bech32 string into a human-readable part and an array of 5-bit data.
     * @param s the Bech32 string to decode, which must be either
     * all-lowercase or all-uppercase, and at most 90 characters long
     * @return a pair where index 0 is a `String` representing the human-readable part
     * (which obeys all the rules as stated in the encoder), and index 1 is a new
     * `byte[]` containing the 5-bit data (whose length is in the range [0, 82])
     * @throws NullPointerException if the string is `null`
     * @throws IllegalArgumentException if the string is too long, has mixed case,
     * lacks a separator, has an invalid human-readable part, has non-base-32
     * characters in the data, lacks a full checksum, or has an incorrect checksum
     */
    fun bech32ToBitGroups(s: String): Array<Any> {
        // Basic checks
        var s = s
        Objects.requireNonNull(s)
        require(s.length <= 90) { "Input too long" }
        run {
            // Normalize to lowercase, rejecting mixed case
            var hasLower = false
            val temp = s.toCharArray()
            for (i in temp.indices) {
                val c = temp[i]
                hasLower = hasLower or ('a' <= c && c <= 'z')
                if ('A' <= c && c <= 'Z') {
                    require(!hasLower) { "String has mixed case" }
                    val offset = ('a'.code - 'A'.code).toChar().code
                    temp[i] = (temp[i] + offset).toChar()
                }
            }
            s = String(temp)
        }


        // Split human-readable part and data
        var humanPart: String
        run {
            val i = s.lastIndexOf('1')
            require(i != -1) { "No separator found" }
            humanPart = s.substring(0, i)
            s = s.substring(i + 1)
        }
        val human = humanPart.toCharArray()
        checkHumanReadablePart(human)

        // Decode from base-32
        require(s.length >= CHECKSUM_LEN) { "Data too short" }
        val dataAndCheck = ByteArray(s.length) // Every element is uint5
        for (i in 0 until s.length) {
            val index = ALPHABET.indexOf(s[i])
            require(index != -1) { "Invalid data character" }
            dataAndCheck[i] = index.toByte()
        }
        try {  // Verify checksum
            val temp = expandHumanReadablePart(human)
            temp.write(dataAndCheck)
            require(polymod(temp.toByteArray()) == 1) { "Checksum mismatch" }
        } catch (e: IOException) {
            throw AssertionError(e) // Impossible
        }

        // Remove checksum, return pair
        val data = Arrays.copyOf(dataAndCheck, dataAndCheck.size - CHECKSUM_LEN)
        return arrayOf(humanPart, data)
    }

    // Throws an exception if any of the following:
    // * Its length is outside the range [1, 83].
    // * It contains non-ASCII characters outside the range [33, 126].
    // * It contains uppercase characters.
    // Otherwise returns silently.
    fun checkHumanReadablePart(s: CharArray) {
        val n = s.size
        require(!(n < 1 || n > 83)) { "Invalid length of human-readable part string" }
        for (c in s) {
            require(!(c.code < 33 || c.code > 126)) { "Invalid character in human-readable part string" }
            require(!('A' <= c && c <= 'Z')) { "Human-readable part string must be lowercase" }
        }
    }

    // Returns a new byte buffer containing uint5 values, representing the given string
    // expanded into the prefix data for the purpose of computing/verifying a checksum.
    private fun expandHumanReadablePart(s: CharArray): ByteArrayOutputStream {
        val result = ByteArrayOutputStream() // Every element is uint5
        for (c in s) result.write(c.code ushr 5) // uint3 from high bits
        result.write(0)
        for (c in s) result.write(c.code and 0x1F) // uint5 from low bits
        return result
    }

    // Computes the polynomial remainder of the given sequence of 5-bit groups. The result is a uint30.
    private fun polymod(data: ByteArray): Int {
        var result = 1
        for (b in data) {
            assert(
                0 <= b && b < 32 // uint5
            )
            val x = result ushr 25
            result = result and (1 shl 25) - 1 shl 5 or b.toInt()
            for (i in GENERATOR.indices) result = result xor (x ushr i and 1) * GENERATOR[i]
            assert(
                result ushr 30 == 0 // uint30
            )
        }
        return result
    }

    /*---- Class constants ----*/ // The base-32 alphabet. Designed so that visually similar characters having small bit differences.
    private const val ALPHABET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l"

    // For computing/verifying checksums. Each element is a uint30.
    private val GENERATOR = intArrayOf(0x3B6A57B2, 0x26508E6D, 0x1EA119FA, 0x3D4233DD, 0x2A1462B3)

    // Number of uint5 groups. Do not modify.
    private const val CHECKSUM_LEN = 6


    fun encode(dataHex: String): String {
        val publicKey = this.segwitToBech32("npub", 0, dataHex.HexToByteArray())
        return publicKey
    }

    fun decode(publicKey: String): Triple<String, Int, String>? {
        try {
            val result = this.bech32ToSegwit(publicKey)
            val humanPart = result[0] as String
            val witVer = result[1] as Int
            val witProg = result[2] as ByteArray
            val originalData = witProg.joinToString("") { "%02x".format(it) }
            return Triple(humanPart, witVer, originalData)
        } catch (e: Exception) {
            println("Failed to decode Bech32 address: ${e.message}")
            return null
        }
    }
}


fun main() {

    val dataHex = "027b83ad6afb1209f3c82ebeb08c0c5fa9bf6724548506f2fb4f991e2287a77090"

    val publicKey = Bech32.encode(dataHex)
    println("Public Key: $publicKey")

    val decodedResult = Bech32.decode(publicKey)
    if (decodedResult != null) {
        println("Human-readable part: ${decodedResult.first}")
        println("Witness version: ${decodedResult.second}")
        println("Witness program in original: ${decodedResult.third}")
    } else {
        println("Decode failed.")
    }


}
