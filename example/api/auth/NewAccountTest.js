import axios from 'axios';
import {b32encode} from "../../ecc/Bech32.js";
import EllipticCurve from "../../ecc/SecureKey.js";



const ec = EllipticCurve();

const url = 'http://127.0.0.1:8080/api/v1/user/sign-up';

// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\
// `Account 1`
// const pass = "lnw1150"
// const privateKey = ec.genPrivateKey(pass)
//
// const publicKey = ec.generateKeyPair(privateKey)
// const authKey = b32encode(publicKey)
//
// const userName = "lnwzaa007"
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\

const accountType= ["Customers", "Stores"]

// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\
// `Account 2`
// const pass = "lala12345"
// const privateKey = ec.genPrivateKey(pass)
//
// const publicKey = ec.generateKeyPair(privateKey)
// const authKey = b32encode(publicKey)
//
// const userName = "la la"
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\
// `Account 3`
// const pass = "Mini1234"
// const privateKey = ec.genPrivateKey(pass)
//
// const publicKey = ec.generateKeyPair(privateKey)
// const authKey = b32encode(publicKey)
//
// const userName = "Mini1234"
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\
// `Account 4`
const pass = "app-store"
const privateKey = ec.genPrivateKey(pass)

const publicKey = ec.generateKeyPair(privateKey)
const authKey = b32encode(publicKey)

const userName = "app-store"
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\


const config = {
    headers: {
        'Content-Type': 'application/json',
        "AccountType" : accountType[1]
    }
};

const payload = {
    userName: userName,
    authKey: authKey,
};

const postResponse = await axios.post(
    url,
    payload,
    config
);

const resultData = postResponse.data
console.log(resultData)
