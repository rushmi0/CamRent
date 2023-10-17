package org.camrent.security.securekey.test

import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve
import org.camrent.security.securekey.EllipticCurve.compressed
import org.camrent.security.securekey.EllipticCurve.getDecompress
import org.camrent.security.securekey.EllipticCurve.getPublicKey
import org.camrent.security.securekey.Sha256
import java.math.BigInteger


// * ตัวอย่าง
fun main() {

    val me = "Hello, world!".toByteArray()
    val hash = Sha256.hash(me)
    println("Hash: ${hash.joinToString("") { "%02x".format(it) }}")

    //val privateKey = BigInteger(256, SecureRandom())
    val privateKey = BigInteger("97ddae0f3a25b92268175400149d65d6887b9cefaf28ea2c078e05cdc15a3c0a", 16)
    println("[H] Private key: ${privateKey.toString(16)}")
    println("Private key: $privateKey")

    val message = BigInteger("ce7df6b1b2852c5c156b683a9f8d4a8daeda2f35f025cb0cf34943dcac70d6a3", 16)
    println("Message: $message")

    val curvePoint = EllipticCurve.multiplyPoint(privateKey)
    println("\nKey Point: $curvePoint")

    val publicKeyPoint = privateKey.getPublicKey()
    println("[U] Public Key: $publicKeyPoint")

    val compress = publicKeyPoint.compressed()
    println("[C] Public Key: $compress")

    val sign = ECDSA.SignSignatures(privateKey, message)
    println("\nSignature: \n\t r = ${sign.R} \n\t s = ${sign.S}")

    val der = ECDSA.toDERFormat(sign)
    println("Der format: $der")

    val validate = ECDSA.VerifySignature(curvePoint, message, sign)
    if (validate) {
        println("ECDSA Signature is Valid")
    } else {
        println("ECDSA Signature is Invalid")
    }

    println()

    val signatureRecovered = ECDSA.derRecovered(der)
    println("Signature Recovered: \n\tr = ${signatureRecovered?.R} \n\ts = ${signatureRecovered?.S}")

    val authKey = compress
    println("AuthKey = $authKey")

    val pubKeyRecovered = compress.getDecompress()
    println("Pub Key Recovered: \n\t$pubKeyRecovered")

    val test = EllipticCurve.isPointOnCurve(pubKeyRecovered)
    println(test)

    val server: Boolean = ECDSA.VerifySignature(pubKeyRecovered!!, message, signatureRecovered!!)
    println(server)
}
