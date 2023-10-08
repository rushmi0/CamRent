import axios from 'axios';
import EllipticCurve from "../../../ecc/SecureKey.js";
import {b32encode} from "../../../ecc/Bech32.js";


const ec = EllipticCurve();

const url = 'http://127.0.0.1:8080/api/v1/user/sign-up';

// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\
// `Account 1`
const pass = "lnw1150"
const privateKey = ec.genPrivateKey(pass)

const publicKey = ec.generateKeyPair(privateKey)
const authKey = b32encode(publicKey)

const userName = "lnwzaa007"
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\

const accountType= ["Customer", "Stores"]

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


const config = {
    headers: {
        'Content-Type': 'application/json',
        "AccountType" : accountType[0]
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
