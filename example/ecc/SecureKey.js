import elliptic from 'elliptic';
import sha256 from "./Sha256.js";
import { Buffer } from 'buffer';

const EllipticCurve = () => {

    // สร้างวัตถุ ec สำหรับใช้กับ elliptic curve ที่กำหนดเอง (secp256k1)
    const ec = new elliptic.ec('secp256k1');
    const LIMIT = 7200;

    const genPrivateKey = (pass, username) => {
        if (!pass || !username) {
            // หรือจะทำอย่างอื่นตามที่คุณต้องการ เช่น การโยน error, สร้างค่าเริ่มต้น, ฯลฯ
            throw new Error("Invalid pass or username");
        }

        let encodedValue = Buffer.from(username.toString() + pass.toString());

        for (let i = 0; i < LIMIT; i++) {
            const hashResult = sha256.hash(encodedValue);
            encodedValue = Buffer.from(hashResult, 'hex');
        }

        return encodedValue.toString('hex');
    }




    // ฟังก์ชันสำหรับสร้างคู่คีย์จาก private key
    const generateKeyPair = (privateKey) => {
        let keyPair = ec.keyFromPrivate(privateKey);
        let pubKey = keyPair.getPublic();
        return pubKey.encodeCompressed("hex");
    }

    // ฟังก์ชันสำหรับเซ็นข้อความ
    const signMessage = (msg, privateKey) => {
        // หา hash ของข้อความ
        const msgHash = sha256.hash(msg);

        // เซ็นข้อความและคืนลายเซ็น
        const signature = ec.sign(msgHash, privateKey, 'hex', { canonical: true });
        return derEncode(signature);
    };

    // ฟังก์ชันสำหรับกู้คืน public key
    const recoverPublicKey = (msgHash, signature) => {
        const hexToDecimal = (x) => ec.keyFromPrivate(x, 'hex').getPrivate().toString(10);
        return ec.recoverPubKey(hexToDecimal(msgHash), signature, signature.recoveryParam, 'hex');
    };

    // ฟังก์ชันสำหรับตรวจสอบลายเซ็น
    const verifySignature = (pubKey, msgHash, signature) => {
        return ec.verify(msgHash, signature, pubKey);
    };

    // ฟังก์ชันสำหรับทำการ encode ลายเซ็นให้เป็นรูปแบบ DER
    const derEncode = (signature) => {

        const r = signature.r.toArrayLike(Buffer, 'be', 32);
        const s = signature.s.toArrayLike(Buffer, 'be', 32);

        const derLen = 4 + r.length + 4 + s.length;
        const der = Buffer.allocUnsafe(derLen);

        der[0] = 0x30;
        der[1] = derLen - 2;
        der[2] = 0x02;
        der[3] = r.length;

        r.copy(der, 4);

        der[4 + r.length] = 0x02;
        der[5 + r.length] = s.length;

        s.copy(der, 6 + r.length);
        return der.toString('hex');
    };

    return {
        genPrivateKey,
        generateKeyPair,
        signMessage,
        recoverPublicKey,
        verifySignature,
        derEncode
    };
};

export default EllipticCurve;

const ecc = EllipticCurve();

const key = ecc.genPrivateKey("1234", "root")
console.log(`Private Key: ${key}`)

const publickey = ecc.generateKeyPair("a66679d60de1659086f2138bd275e1d0ef53f143ca814442eb97b94ca9668a20")
console.log(`Public Key: ${publickey}`)

const massage = "a66679d60de1659086f2138bd275e1d0ef53f143ca814442eb97b94ca9668a20"

let sig = "304402204dbe6452e2299a3c3792af796cd250880835ad0d89e40b8058a94abe16c95ae9022030368ee50fdcb7936a2ce2be2ef376fedb8c4f62744774f20755aafb26f08bad"

// let verify_sig = ecc.verifySignature(
//     "03890e341656f3219ad43e0b2bab3cca109c240051242bca92b2e28efa7fecb84b",
//     "a66679d60de1659086f2138bd275e1d0ef53f143ca814442eb97b94ca9668a20",
//     "3044022008e8f57ed2e08c5434fa7187c008785e61ed342c1d62bace1f4be18ca94145390220797b7eea7ca62a019f8738bea43d4da98dcef389d7a452540875023ce1d5ab26"
// )
//
// console.log(verify_sig)