import axios from 'axios';
import EllipticCurve from "../../../ecc/SecureKey.js";


const ec = EllipticCurve();
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\
const pass = "lnw1150"
const privateKey = ec.genPrivateKey(pass)

const userID = 1;
const url = `http://127.0.0.1:8080/api/v1/customers/id/${userID}`;

const signature = ec.signMessage(url, privateKey);
const derEncodedSignature = ec.derEncode(signature);
// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\

const config = {
    headers: {
        'Content-Type': 'application/json',
        'Signature': derEncodedSignature,
        'Witness': url
    }
};

const resultData = await axios.get(url, config)
console.log(resultData.data)

const id = resultData.data["customerID"]
console.log(`Customer ID : ${id}`)

const userName = resultData.data["userName"]
console.log(userName)
