import axios from 'axios';
import EllipticCurve from "../../ecc/SecureKey.js";
import {b32encode} from "../../ecc/Bech32.js";
import sha256 from "../../ecc/Sha256.js";


const ec = EllipticCurve();
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\
// `Account 1`
const username = "root"
const pass = "lnw1150"
const privateKey = ec.genPrivateKey(pass, username)
console.log(privateKey)

const publicKey = ec.generateKeyPair(privateKey)
console.log(publicKey)

const authKey = b32encode(publicKey)
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\

const url = 'http://127.0.0.1:8080/api/v1/user/auth/' + authKey;
const witness = encodeURIComponent(url)

const msgHash = sha256.hash(witness);
console.log(msgHash)

const signature = ec.signMessage(witness, privateKey);


console.log(signature)

// const accountType = ["Customer", "Stores"]
//
// const config = {
//     headers: {
//         'Content-Type': 'application/json',
//         'AccountType': accountType[0],
//         'Signature': derEncodedSignature,
//         'Witness': witness
//     }
// };
//
// axios.get(
//     url,
//     config
// )
//     .then(response => {
//         console.log('Response:', response.data);
//     })
//     .catch(error => {
//         // ดำเนินการเมื่อเกิด error
//         console.error('Error:', error);
//     });
