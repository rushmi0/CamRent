import axios from 'axios';


const id = 1;
const accountType= "Customer"

const config = {
    headers: {
        'Content-Type': 'application/json',
        'AccountID' : id,
        "AccountType" : accountType
    }
};


const payload = {
    firstName: 'Watcharapol',
    lastName: 'Phongwilai',
    email: 'ph.lnwza1150@example.com',
    phoneNumber: '098654321'
};

const postResponse = await axios.post('http://localhost:8080/api/v1/people', payload, config)
const data = postResponse.data;
console.log(data);
