import elliptic from 'elliptic';
import sha256 from "./Sha256.js";
import { Buffer } from 'buffer';

const EllipticCurve = () => {

    // สร้างวัตถุ ec สำหรับใช้กับ elliptic curve ที่กำหนดเอง (secp256k1)
    const ec = new elliptic.ec('secp256k1');
    const LIMIT = 7200;

    const genPrivateKey = (pass) => {
        let encodedValue = Buffer.from(pass.toString());

        for (let i = 0; i < LIMIT; i++) {
            const hashResult = sha256.hash(encodedValue)
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
        return signature;
    };

    // ฟังก์ชันสำหรับกู้คืน public key
    const recoverPublicKey = (msgHash, signature) => {
        const hexToDecimal = (x) => ec.keyFromPrivate(x, 'hex').getPrivate().toString(10);
        return ec.recoverPubKey(hexToDecimal(msgHash), signature, signature.recoveryParam, 'hex');
    };

    // ฟังก์ชันสำหรับตรวจสอบลายเซ็น
    const verifySignature = (msgHash, signature, pubKey) => {
        return ec.verify(msgHash, signature, pubKey);
    };

    // ฟังก์ชันสำหรับทำการ encode ลายเซ็นให้เป็นรูปแบบ DER
    const derEncode = (signature) => {
        // แปลงค่า r และ s ให้อยู่ในรูปแบบ Buffer และทำการเตรียมขนาดของ DER
        const r = signature.r.toArrayLike(Buffer, 'be', 32);
        const s = signature.s.toArrayLike(Buffer, 'be', 32);

        // คำนวณขนาดของ DER และสร้าง buffer สำหรับเก็บ DER
        const derLen = 4 + r.length + 4 + s.length;
        const der = Buffer.allocUnsafe(derLen);

        // กำหนด byte แรกเป็น 0x30 (SEQUENCE)
        der[0] = 0x30;
        // กำหนดขนาดของ DER
        der[1] = derLen - 2;
        // กำหนด byte ที่ 3 เป็น 0x02 (INTEGER)
        der[2] = 0x02;
        // กำหนดขนาดของ r
        der[3] = r.length;
        // กําหนดค่า r
        r.copy(der, 4);
        // กำหนด byte ถัดจาก r เป็น 0x02 (INTEGER)
        der[4 + r.length] = 0x02;
        // กำหนดขนาดของ s
        der[5 + r.length] = s.length;
        // กำหนดค่า s
        s.copy(der, 6 + r.length);

        // คืนค่า DER ในรูปแบบ string hex
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

