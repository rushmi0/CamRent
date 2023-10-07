import axios from 'axios';
import EllipticCurve from "../../../ecc/SecureKey.js";
import {b32encode} from "../../../ecc/Bech32.js";


const ec = EllipticCurve();
const pass = "TestPass123<T>"
const privateKey = ec.genPrivateKey(pass)


const publicKey = ec.generateKeyPair(privateKey)
const authKey = b32encode(publicKey)

const userName = "lnwzaa007"

const payload = {
    userName: userName,
    authKey: authKey,
};

const postResponse = await axios.post('http://localhost:8080/api/v1/customers', payload);
const result = postResponse.data
console.log(result)


// const id = postResponse.data["customerID"];
// console.log(postResponse.data);
// const getResponse = await axios.get('http://localhost:8080/api/v1/customers/id/' + id);
// const customerData = getResponse.data;
// console.log('Customer Data from GET:', customerData);
