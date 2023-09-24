package org.camrent.securekey


import org.camrent.securekey.ECDSA.SignSignatures
import org.camrent.securekey.ECDSA.VerifySignature
import org.camrent.securekey.ECDSA.toDERFormat

import org.camrent.securekey.EllipticCurve.compressed
import org.camrent.securekey.EllipticCurve.getPublicKey
import org.camrent.securekey.EllipticCurve.multiplyPoint
import org.camrent.utils.ShiftTo.ByteArrayToBigInteger
import org.camrent.utils.ShiftTo.HexToByteArray


import java.math.BigInteger


    /*
    * https://github.com/wobine/blackboard101/blob/master/EllipticCurvesPart5-TheMagic-SigningAndVerifying.py
    * https://cryptobook.nakov.com/asymmetric-key-ciphers/elliptic-curve-cryptography-ecc
    * https://www.secg.org/sec2-v2.pdf
    *
    * < Elliptic Curve Cryptography >
    *  ในส่วนนี้เป็นการคำนวณ Public Key
    * */

object EllipticCurve {

    // * Parameters secp256k1
    private val curveDomain: Secp256K1.CurveParams = Secp256K1.getCurveParams()

    private val A: BigInteger = curveDomain.A
    private val B: BigInteger = curveDomain.B
    private val P: BigInteger = curveDomain.P
    private val N: BigInteger = curveDomain.N
    private val G: Point = curveDomain.G


    // * จุดบนเส้นโค้งวงรี มีพิกัด x และ y
    data class Point(val x: BigInteger, val y: BigInteger)

    // ──────────────────────────────────────────────────────────────────────────────────────── \\

    /*
    * ตรวจสอบจุดบนโค้งวงรี Secp256k1
    * */

    fun isPointOnCurve(point: Pair<BigInteger, BigInteger>): Boolean {
        val (x, y) = point

        // * ตรวจสอบว่าจุดนั้นเป็นไปตามสมการเส้นโค้งวงรี หรือไม่: y^2 = x^3 + Ax + B (mod P)
        val leftSide = (y * y).mod(P)
        val rightSide = (x.pow(3) + A * x + B).mod(P)

        return leftSide == rightSide
    }

    // ──────────────────────────────────────────────────────────────────────────────────────── \\

    /*
    * Function สำหรับคำนวณ modular inverse
    * https://www.dcode.fr/modular-inverse
    * */
    fun modinv(A: BigInteger, N: BigInteger = P) = A.modInverse(N)


    // * Function สำหรับคำนวณค่าจุดหลังการคูณด้วย 2 บนเส้นโค้งวงรี
    fun doublePoint(point: Point): Point {
        val (x, y) = point

        // * คำนวณค่า slope ด้วยสูตร (3 * x^2 + A) * (2 * y)^-1 mod P
        val slope = (BigInteger.valueOf(3) * x * x + A) % P

        // *  คำนวณค่า lam_denom = (2 * y) mod P
        val lam_denom = (BigInteger.valueOf(2) * y) % P

        // * คำนวณค่า lam = slope * (lam_denom)^-1 mod P
        val lam = (slope * modinv(lam_denom)) % P

        // * คำนวณค่า xR = (lam^2 - 2 * x) mod P
        val xR = (lam * lam - BigInteger.valueOf(2) * x) % P


        /*
        * < จุดใหม่ที่ได้หลังจากการคูณด้วย 2 บนเส้นโค้งวงรี >
        *  คำนวณค่า yR = (lam * (x - xR) - y) mod P เป็นส่วนที่คำนวณหาค่า y  ของจุดใหม่หลังจากการคูณด้วย 2 บนเส้นโค้งวงรี
        *
        *  lam   คือค่าเอียงของเส้นที่ผ่านจุดเดิมและจุดใหม่หลังจากการคูณด้วย 2 บนเส้นโค้งวงรี
        *  x      คือค่า x ของจุดเดิม
        *  xR    คือค่า x ของจุดใหม่หลังจากการคูณด้วย 2 บนเส้นโค้งวงรี
        *  y     คือค่า y ของจุดเดิม
        *
        * นำค่าเหล่านี้มาใช้เพื่อหาค่า yR ใหม่ที่ถูกปรับเพิ่มหรือลดจากค่า y ของจุดเดิม โดยการคูณ lam กับผลต่างระหว่าง x และ xR
        * */
        val yR = (lam * (x - xR) - y) % P

        return Point(xR, (yR + P) % P)
    }


    fun addPoint(point1: Point, point2: Point): Point {
        if (point1 == point2) {
            return doublePoint(point1)
        }
        val (x1, y1) = point1
        val (x2, y2) = point2

        val slope = ((y2 - y1) * modinv(x2 - x1)) % P

        val x = (slope * slope - x1 - x2) % P

        val y = (slope * (x1 - x) - y1) % P

        // ! จัดการพิกัด Y ที่เป็นค่าลบ
        val yResult = if (y < A) y + P else y

        return Point(x, yResult)
    }

    fun multiplyPoint(k: BigInteger, point: Point? = null): Point {
        // * ตัวแปร current ถูกกำหนดให้เป็น point ที่รับเข้ามา หากไม่มีการระบุ point ค่าเริ่มต้นจะเป็นจุด G ที่ใช้ในการคูณเช่นกับ private key
        val current: Point = point ?: G

        // * แปลงจำนวนเต็ม k เป็นเลขฐานสอง
        val binary = k.toString(2)

        // * เริ่มต้นด้วยจุดเริ่มต้นปัจจุบัน
        var currentPoint = current

        // * วนลูปตามจำนวน binary digits ของ k
        for (i in 1 until binary.length) {
            currentPoint = doublePoint(currentPoint)

            // * ถ้า binary digit ที่ตำแหน่ง i เป็น '1'  ให้บวกจุดเริ่มต้น (current) เข้ากับจุดปัจจุบัน (currentPoint)
            if (binary[i] == '1') {
                currentPoint = addPoint(currentPoint, current)
            }
        }
        // * ส่งคืนจุดที่คำนวณได้
        return currentPoint
    }


    // ──────────────────────────────────────────────────────────────────────────────────────── \\

    /*
    * ปรับแต่ง Public key
    * */

    private fun fullPublicKeyPoint(k: BigInteger): String {
        val point: Point = multiplyPoint(k)
        val publicKeyPoint = "04${point.x.toString(16)}${point.y.toString(16)}"

        // * ถ้าขนาด public key Hex น้องกว่า 130 จะต้องแทรก "0" เข้าไปอยู่ระหว่าง "04" และพิกัด X
        if (publicKeyPoint.length < 130) {

            // * "04" + "0" + X + Y
            return publicKeyPoint.substring(0, 2) + "0" + publicKeyPoint.substring(2)
        }
        return publicKeyPoint
    }

    private fun groupSelection(publicKey: String): String {
        if (publicKey.length == 130 && publicKey.substring(0, 2) != "04") {
            throw IllegalArgumentException("Invalid Public Key")
        }
        val x = BigInteger(publicKey.substring(2, 66), 16)
        val y = BigInteger(publicKey.substring(66), 16)

        val groupkeys = if (y and BigInteger.ONE == BigInteger.ZERO) {
            "02" + x.toString(16).padStart(64, '0')
        } else {
            "03" + x.toString(16).padStart(64, '0')
        }
        return groupkeys
    }

    private fun decompressPublicKey(compressedPublicKey: String): Point {
        val byteArray = compressedPublicKey.HexToByteArray()
        val xCoord = byteArray.copyOfRange(1, byteArray.size).ByteArrayToBigInteger()
        val isYEven = byteArray[0] == 2.toByte()

        val xSquare = (xCoord.modPow(BigInteger.valueOf(3), N) + B) % N
        val y = xSquare.modPow((N + BigInteger("1")) / BigInteger("4"), N)
        val isYSquareEven = y.mod(BigInteger.TWO) == BigInteger.ZERO

        val computedY = if (isYSquareEven != isYEven) N - y else y

        return Point(xCoord, computedY)
    }


    fun String.getDecompress(): Point {
        return decompressPublicKey(this)
    }

    fun BigInteger.getPublicKey(): String {
        return fullPublicKeyPoint(this)
    }

    fun String.compressed(): String {
        return groupSelection(this)
    }


    // ──────────────────────────────────────────────────────────────────────────────────────── \\

}

// * ตัวอย่าง
fun main() {

    //val privateKey = BigInteger(256, SecureRandom())
    val privateKey = BigInteger("165F1C58AFB81B9D767FCEF47CBCDFFD3298E0480575AC8A0CA9FEC04F600C26", 16)
    println("[H] Private key: ${privateKey.toString(16)}")
    println("Private key: $privateKey")

    val message = BigInteger("0e2bd2792e5b75cbb05561ce5836d12abbdc201b328a2626c27484458a1a9ee", 16)
    println("Message: $message")

    val curvePoint = multiplyPoint(privateKey)
    println("\nKey Point: $curvePoint")

    val publicKeyPoint = privateKey.getPublicKey()
    println("[U] Public Key: $publicKeyPoint")

    val compress = publicKeyPoint.compressed()
    println("[C] Public Key: $compress")

    val sign = SignSignatures(privateKey, message)
    println("\nSignature: \n r = ${sign.first} \n s = ${sign.second}")

    val der = toDERFormat(sign)
    println("Der format: $der")

    val validate = VerifySignature(curvePoint, message, sign)
    if (validate) {
        println("ECDSA Signature is Valid")
    } else {
        println("ECDSA Signature is Invalid")
    }

}
