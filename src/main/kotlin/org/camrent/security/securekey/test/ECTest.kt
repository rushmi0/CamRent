package org.camrent.security.securekey.test

import org.camrent.security.securekey.ECDSA
import org.camrent.security.securekey.EllipticCurve
import org.camrent.security.securekey.EllipticCurve.getDecompress
import java.math.BigInteger

fun main() {

    val message = BigInteger("ce7df6b1b2852c5c156b683a9f8d4a8daeda2f35f025cb0cf34943dcac70d6a3", 16)
    println("Message: $message")

    val der = "304602206f0156091cbe912f2d5d1215cc3cd81c0963c8839b93af60e0921b61a19c543002200c71006dd93f3508c432daca21db0095f4b16542782b7986f48a5d0ae3c583d40000"
    println("ECDS Signature: \n\t$der")

    val signatureRecovered = ECDSA.derRecovered(der)
    println("Signature Recovered: \n\tr = ${signatureRecovered?.R} \n\ts = ${signatureRecovered?.S}")

    val authKey = "027b83ad6afb1209f3c82ebeb08c0c5fa9bf6724548506f2fb4f991e2287a77090"
    println("Public Key = $authKey")

    val pubKeyRecovered = authKey.getDecompress()
    println("Public Key Recovered: \n\t$pubKeyRecovered")

    val test = EllipticCurve.isPointOnCurve(pubKeyRecovered)
    println("It is point on curve: \n\t$test ")

    val server: Boolean = ECDSA.VerifySignature(pubKeyRecovered!!, message, signatureRecovered!!)
    println("Verify Signature: \n\t$server")

}