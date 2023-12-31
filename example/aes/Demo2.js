import {Buffer} from "buffer";
import AES from "./AES.js";

const aes = AES()
let sharedKey = Buffer.from("3e11810c67157bf584db16bbd85d9e9b339b4469e27390365195379cb2168a78", 'hex');

// ---------------------------------------------------
let email = {
    "email": "lnwzaa007@gmail.com",
}

const ref1 = JSON.stringify(email);
console.log(ref1);

let result1 = aes.encrypt(ref1, sharedKey);
console.log('Encrypted data:', result1);

// ---------------------------------------------------

let phoneNumber = {
    "phoneNumber": "0811223344",
}

const ref2 = JSON.stringify(phoneNumber);
console.log(ref2);

let result2 = aes.encrypt(ref2, sharedKey);
console.log('Encrypted data:', result2);

// ---------------------------------------------------




