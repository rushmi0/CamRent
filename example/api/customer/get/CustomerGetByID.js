import axios from 'axios';
import EllipticCurve from "../../../ecc/SecureKey.js";
import {encode} from "../../../ecc/Bech32.js";


const ec = EllipticCurve();
const pass = "ASDFGHackle;'12334"
const privateKey = ec.genPrivateKey(pass)


const publicKey = ec.generateKeyPair(privateKey)
console.log(`raw Public Key: \n\t${publicKey}`)

const swathe = encode(publicKey)
console.log(`Public Key: \n\t${swathe}`)


const userName = 1;
const url = `http://localhost:8080/api/v1/customers/id/${encodeURIComponent(userName)}`;
console.log(url)

const signature = ec.signMessage(url, privateKey);
const derEncodedSignature = ec.derEncode(signature);
console.log(derEncodedSignature)


const config = {
    headers: {
        'Content-Type': 'application/json',
        'Signature': derEncodedSignature,
        'Witness': url
    }
};

axios.get(url, config)
    .then(response => {
        console.log('Customer data:', response.data);
    })
    .catch(error => {
        // Handle error
        console.error('Error fetching customer data:', error);
    });
