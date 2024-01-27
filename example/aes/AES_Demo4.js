import {Buffer} from "buffer";
import AES from "./AES.js";


const aes = AES()

let sharedKey = Buffer.from("3e11810c67157bf584db16bbd85d9e9b339b4469e27390365195379cb2168a78", 'hex');

let data = {
    "walkerID": 9,
    "dogID" : 3,
    "bookingDate" : "2023-12-16",
    "timeStart" : "14:00",
    "timeEnd" : "14:30"
}

const jsonString = JSON.stringify(data);
console.log(data);

let dataToSend = aes.encrypt(jsonString, sharedKey);
console.log('Encrypted data:', dataToSend);


const content = "kSlD+COVQn0jmtebpKAnjdNAG9ZxGwBxHEWi9NyicPu/X+gtPUX4kZzQAVCJCZgrlX5y/mfGkDDByYjulpUXdLacycPTg91UBFpaFg0FdSkyd/DTSS+gLDfUDhGvMaPL3lwsnkwvVA6XQ7Ei9ZY0xQ==?iv=J7wH7HqqqJQ3VDh5LNaLdQ=="
const decrypted = aes.decrypt(content, sharedKey)
console.log(decrypted)


