import axios from 'axios';
import EllipticCurve from "../../../ecc/SecureKey.js";


// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\

const userID = 1;
const url = `http://127.0.0.1:8080/api/v1/customers/id/${userID}`;

// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\

const resultData = await axios.get(url)
console.log("Customer Data from: ", resultData.data)

const id = resultData.data["customerID"]
console.log(`Customer ID : ${id}`)

const userName = resultData.data["userName"]
console.log(userName)
