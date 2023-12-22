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
        return keyPair.getPublic().encodeCompressed("hex");
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


    // ฟังก์ชันสำหรับคำนวณคีย์ที่แชร์ระหว่าง Private Key และ Public Key
    const calculateSharedKey = (privateKey, publicKey) => {
        const keyPair = ec.keyFromPrivate(privateKey);
        const otherPublicKey = ec.keyFromPublic(publicKey, 'hex');
        const sharedKey = keyPair.derive(otherPublicKey.getPublic());

        // ดึงค่าที่แชร์จากข้อมูล
        const sharedKeyBuffer = Buffer.from(sharedKey.toArray('be'), 'hex');

        // หรือคุณสามารถนำ sharedKeyBuffer ไปใช้ต่อไปได้ตามที่คุณต้องการ

        return sharedKeyBuffer.toString('hex');
    };


    return {
        calculateSharedKey,
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


// ใช้ calculateSharedKey เพื่อคำนวณคีย์ที่แชร์
const sharedKey = ecc.calculateSharedKey(
    "a66679d60de1659086f2138bd275e1d0ef53f143ca814442eb97b94ca9668a20",
    "02d98ec7e615933c501c64f790e3c516538c1612ced15bdcac9f9db705efb0fac6"
);

console.log(`Shared Key: ${sharedKey}`);

