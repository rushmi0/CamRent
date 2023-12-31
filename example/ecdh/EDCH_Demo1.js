import {Buffer} from 'buffer';
import EllipticCurve from "../ecc/SecureKey.js";

const ecc = EllipticCurve();

// ตัวอย่างข้อมูลที่กำหนด
const clientPrivateKey = Buffer.from("e143924567f4a20128f4b3457d97145a7e1922864e9a1cf47d7efff8b7d85979", 'hex');
const serverPublicKey = Buffer.from("0347d5cb133e59866bd1adf84adc291bf00fb05e03fb5355deda66e91815e320a8", 'hex');

// คำนวณ Shared Key
const sharedKey = ecc.calculateSharedKey(
    clientPrivateKey,
    serverPublicKey
);

// นำ Shared Key ไปใช้งานต่อไป... โดยนำไป Encrypt ข้อมูลด้วย AES
console.log("Shared Key:", sharedKey);