import crypto from 'crypto';
import { Buffer } from 'buffer';

class AESEncryption {
    static encrypt(data, sharedKey) {
        // Convert JSON data to string
        const jsonString = JSON.stringify(data);

        // Generate a random IV
        let iv = crypto.randomFillSync(new Uint8Array(16));

        // Create a cipher
        const cipher = crypto.createCipheriv('aes-256-cbc', Buffer.from(sharedKey, 'hex'), iv);

        // Encrypt the data
        let encryptedData = cipher.update(jsonString, 'utf8', 'base64');
        encryptedData += cipher.final('base64');

        // Convert IV to base64
        let ivBase64 = Buffer.from(iv.buffer).toString('base64');

        // Combine encrypted data and IV
        let dataToSend = encryptedData + '?iv=' + ivBase64;

        return { dataToSend };
    }

    static decrypt(encryptedData, sharedKey) {
        // Split encrypted data and IV
        let [encryptedString, ivBase64] = encryptedData.split('?iv=');

        // Decode IV from base64
        let ivDecoded = Buffer.from(ivBase64, 'base64');

        // Create a decipher
        const decipher = crypto.createDecipheriv('aes-256-cbc', Buffer.from(sharedKey, 'hex'), ivDecoded);

        // Decrypt the data
        let decryptedString = decipher.update(encryptedString, 'base64', 'utf8');
        decryptedString += decipher.final('utf8');

        // Parse JSON string to object
        let decryptedData = JSON.parse(decryptedString);

        return decryptedData;
    }
}

// Example usage
let sharedKey = "8fda492e3522673b0b0561526e4b1b96b3bdf81484ca5a1db4f30125fc04be54";
let data = {
    "name": "Mai na",
    "age": 12
};

console.log(data)


// Encrypt
let { dataToSend } = AESEncryption.encrypt(data, sharedKey);
console.log('Encrypted data:', dataToSend);

// Decrypt
let decryptedData = AESEncryption.decrypt("W/LwC5hEtdT2P/cyqXkr4hRkCnNDOQrZEgManO5Ruak=?iv=jgYVljNNJZh/Ywycul65Aw==", sharedKey);
console.log('Decrypted data:', decryptedData);

// Access only the property
let name = decryptedData.name;
console.log('Name:', name);

let age = decryptedData.age;
console.log('Age:', age);


// let event = {
//     pubkey: ourPubKey,
//     created_at: Math.floor(Date.now() / 1000),
//     kind: 4,
//     tags: [['p', theirPublicKey]],
//     content: encryptedMessage + '?iv=' + ivBase64
// }