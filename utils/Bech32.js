const ALPHABET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";
const GENERATOR = [0x3B6A57B2, 0x26508E6D, 0x1EA119FA, 0x3D4233DD, 0x2A1462B3];
const CHECKSUM_LEN = 6;

const segwitToBech32 = (humanPart, witVer, witProg) => {
    if (witVer < 0 || witVer > 16) {
        throw new Error("Invalid witness version");
    }
    if (witProg.length < 2 || witProg.length > 40) {
        throw new Error("Invalid witness program length");
    }

    const data = [witVer];

    const IN_BITS = 8;
    const OUT_BITS = 5;
    let inputIndex = 0;
    let bitBuffer = 0;
    let bitBufferLen = 0;

    while (inputIndex < witProg.length || bitBufferLen > 0) {
        if (bitBufferLen < OUT_BITS) {
            if (inputIndex < witProg.length) {
                bitBuffer = bitBuffer | ((witProg[inputIndex] & 0xFF) << (32 - IN_BITS - bitBufferLen));
                inputIndex++;
                bitBufferLen += IN_BITS;
            } else {
                bitBufferLen = OUT_BITS;
            }
        }

        if (bitBufferLen >= OUT_BITS) {
            data.push(bitBuffer >>> (32 - OUT_BITS));
            bitBuffer = bitBuffer << OUT_BITS;
            bitBufferLen -= OUT_BITS;
        }
    }

    return bitGroupsToBech32(humanPart, new Uint8Array(data));
};

const bech32ToSegwit = (s) => {
    if (s.length > 90) {
        throw new Error("Input too long");
    }

    let hasLower = false;
    const temp = [];
    for (let i = 0; i < s.length; i++) {
        const c = s[i];
        hasLower = hasLower || ('a' <= c && c <= 'z');
        if ('A' <= c && c <= 'Z') {
            if (hasLower) {
                throw new Error("String has mixed case");
            }
            const offset = 'a'.charCodeAt(0) - 'A'.charCodeAt(0);
            temp.push(String.fromCharCode(c.charCodeAt(0) + offset));
        } else {
            temp.push(c);
        }
    }
    s = temp.join('');

    let humanPart = "";
    let index = s.lastIndexOf('1');
    if (index === -1) {
        throw new Error("No separator found");
    }
    humanPart = s.substring(0, index);
    s = s.substring(index + 1);

    const dataAndCheck = new Uint8Array(s.length);
    for (let i = 0; i < s.length; i++) {
        const char = s[i];
        const charIndex = ALPHABET.indexOf(char);
        if (charIndex === -1) {
            throw new Error("Invalid data character");
        }
        dataAndCheck[i] = charIndex;
    }

    if (s.length < CHECKSUM_LEN) {
        throw new Error("Data too short");
    }

    const data = dataAndCheck.slice(0, -CHECKSUM_LEN);
    const checksum = dataAndCheck.slice(-CHECKSUM_LEN);
    const computedChecksum = polymod([...expandHumanReadablePart(humanPart), ...data, ...Array(CHECKSUM_LEN)]);
    if (computedChecksum !== 1) {
        throw new Error("Checksum mismatch");
    }

    return [humanPart, data];
};

const checkHumanReadablePart = (s) => {
    const n = s.length;
    if (n < 1 || n > 83) {
        throw new Error("Invalid length of human-readable part string");
    }
    for (let i = 0; i < n; i++) {
        const c = s[i];
        if (c.charCodeAt(0) < 33 || c.charCodeAt(0) > 126) {
            throw new Error("Invalid character in human-readable part string");
        }
        if ('A' <= c && c <= 'Z') {
            throw new Error("Human-readable part string must be lowercase");
        }
    }
};

const expandHumanReadablePart = (s) => {
    const result = [];
    for (let i = 0; i < s.length; i++) {
        result.push(s[i].charCodeAt(0) >>> 5);
    }
    result.push(0);
    for (let i = 0; i < s.length; i++) {
        result.push(s[i].charCodeAt(0) & 0x1F);
    }
    return result;
};

const polymod = (data) => {
    let result = 1;
    for (const b of data) {
        const x = result >>> 25;
        result = ((result & ((1 << 25) - 1)) << 5) | b;
        for (let i = 0; i < GENERATOR.length; i++) {
            result ^= (x >>> i) & 1 ? GENERATOR[i] : 0;
        }
    }
    return result;
};

const bitGroupsToBech32 = (humanPart, data) => {
    const human = humanPart.split('');
    checkHumanReadablePart(human);

    const temp = expandHumanReadablePart(human);
    temp.push(...Array.from(data));
    temp.push(...Array(CHECKSUM_LEN).fill(0));

    const checksum = polymod(temp) ^ 1;

    if (human.length + 1 + data.length + 6 > 90) {
        throw new Error("Output too long");
    }

    let sb = humanPart + '1';
    for (const b of data) {
        sb += ALPHABET[b];
    }
    for (let i = 0; i < CHECKSUM_LEN; i++) {
        const b = (checksum >>> ((CHECKSUM_LEN - 1 - i) * 5)) & 0x1F;
        sb += ALPHABET[b];
    }

    return sb;
};

const b32encode = (dataHex) => {
    const publicKey = segwitToBech32("npub", 0, HexToByteArray(dataHex));
    return publicKey;
};

const b32decode = (publicKey) => {
    try {
        const result = bech32ToSegwit(publicKey);
        const humanPart = result[0];
        const data = result[1];
        const originalData = data.map(b => ('00' + b.toString(16)).slice(-2)).join('');
        return [humanPart, originalData];
    } catch (e) {
        console.error("Failed to decode Bech32 address:", e.message);
        return null;
    }
};

const HexToByteArray = (hexString) => {
    const byteArray = [];
    for (let i = 0; i < hexString.length; i += 2) {
        byteArray.push(parseInt(hexString.substr(i, 2), 16));
    }
    return byteArray;
};

export { b32encode, b32decode };



// ตัวอย่างการใช้งาน
// const publicKey = '027b83ad6afb1209f3c82ebeb08c0c5fa9bf6724548506f2fb4f991e2287a77090';
// const result = b32encode(publicKey);
// console.log('Encoded Bech32 result:', result);
