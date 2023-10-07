import axios from 'axios';
import EllipticCurve from "../../ecc/SecureKey.js";
import {b32encode} from "../../ecc/Bech32.js";


const ec = EllipticCurve();
const pass = "TestPass123<T>"
const privateKey = ec.genPrivateKey(pass)

const publicKey = ec.generateKeyPair(privateKey)
const authKey = b32encode(publicKey)
console.log(authKey)
// npub1qqvk42zem553azjt6w2s982tulgxnvwxhhk6yu2gvsf5thk94l6ge77zgnxe

const url = 'http://localhost:8080/api/v1/user/auth/' + authKey;
const witness = encodeURIComponent(url)

const signature = ec.signMessage(witness, privateKey);
const derEncodedSignature = ec.derEncode(signature);

const type = ["Stores", "Customer"]

const config = {
    headers: {
        'Content-Type': 'application/json',
        'AccountType': type[0], // `Stores` , `Customer`
        'Signature': derEncodedSignature,
        'Witness': witness
    }
};


console.log(config)

axios.get(
    url,
    config
)
    .then(response => {
        console.log('Response:', response.data);
    })
    .catch(error => {
        // ดำเนินการเมื่อเกิด error
        console.error('Error:', error);
    });
