package org.camrent.securekey


import org.camrent.securekey.EllipticCurve.addPoint
import org.camrent.securekey.EllipticCurve.modinv
import org.camrent.securekey.EllipticCurve.multiplyPoint
import org.camrent.utils.ShiftTo.ByteArrayToHex
import java.math.BigInteger
import java.security.SecureRandom

    /*
    * สร้างลายเซ็นและตรวจสอบ ECDSA
    * */

object ECDSA {

    /*
    * https://github.com/bitcoin/bips/blob/master/bip-0062.mediawiki
    */

    // * Parameters secp256k1
    private val curveDomain: Secp256K1.CurveParams = Secp256K1.getCurveParams()
    private val N: BigInteger = curveDomain.N

    fun SignSignatures(privateKey: BigInteger, message: BigInteger): Pair<BigInteger, BigInteger> {
        val m = message
        //val k = BigInteger("42854675228720239947134362876390869888553449708741430898694136287991817016610")
        val k = BigInteger(256, SecureRandom())

        val point: EllipticCurve.Point = multiplyPoint(k)
        val kInv: BigInteger = modinv(k, N)

        val r: BigInteger = point.x % N
        var s: BigInteger = ((m + r * privateKey) * kInv) % N

        // * https://github.com/bitcoin/bips/blob/master/bip-0146.mediawiki
        if (s > N.shiftRight(1)) {
            s = N - s
        }

        return Pair(r, s)
    }

    fun VerifySignature(
        publicKeyPoint: EllipticCurve.Point,
        message: BigInteger,
        signature: Pair<BigInteger, BigInteger>
    ): Boolean {
        val (r, s) = signature

        val w = modinv(s, N)
        val u1 = (message * w) % N
        val u2 = (r * w) % N

        val point1 = multiplyPoint(u1)
        val point2 = multiplyPoint(u2, publicKeyPoint)

        val point = addPoint(point1, point2)

        val x = point.x % N

        return x == r
    }

    // * https://github.com/bitcoin/bips/blob/master/bip-0066.mediawiki
    fun toDERFormat(signature: Pair<BigInteger, BigInteger>): String {
        val (r, s) = signature
        val rb = r.toByteArray()
        val sb = s.toByteArray()

        val der_r = byteArrayOf(0x02.toByte()) + rb.size.toByte() + rb
        val der_s = byteArrayOf(0x02.toByte()) + sb.size.toByte() + sb
        val der_sig = byteArrayOf(0x30.toByte()) + (der_r.size + der_s.size).toByte() + der_r + der_s
        return der_sig.ByteArrayToHex()
    }

}