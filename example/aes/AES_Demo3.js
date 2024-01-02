import AES from "./AES.js";
import {Buffer} from "buffer";
import EllipticCurve from "../ecc/SecureKey.js";


const aes = AES();
const ecc = EllipticCurve();

const server_public_key = Buffer.from("0347d5cb133e59866bd1adf84adc291bf00fb05e03fb5355deda66e91815e320a8", 'hex');

const user_name = "Rushmi0";
const pass = "lnwza69";

const private_key = ecc.genPrivateKey(
    pass,
    user_name
);

const public_key = ecc.generateKeyPair(private_key);
console.log(`Private Key: ${private_key}`);
console.log(`Public Key: ${public_key}`);

let sharedKey = ecc.calculateSharedKey(
    private_key,
    server_public_key
);
console.log(`Shared Key: ${sharedKey}`)


let data = {
    "firstName": "Watcharapol",
    "lastName": "Phongwilai",
    "email": "ph.watcharapol_st@tni.ac.th",
    "phoneNumber": "0001115511",
    "userType": "DogWalkers"
};

const json_to_string = JSON.stringify(data);
console.log(json_to_string);

let dataToSend = aes.encrypt(json_to_string, sharedKey);
console.log('Encrypted data:', dataToSend);