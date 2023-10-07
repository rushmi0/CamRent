import axios from 'axios';


// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\

const id = 1;
const accountType= ["Customer", "Stores"]

// ++++++++++++++++++++++++++++++++++++++++++++++++++++ \\


const config = {
    headers: {
        'Content-Type': 'application/json',
        'AccountID' : id,
        "AccountType" : accountType[0]
    }
};


const payload = {
    firstName: 'Watcharapol',
    lastName: 'Phongwilai',
    email: 'ph.lnwza1150@example.com',
    phoneNumber: '098654321'
};

const postResponse = await axios.post('http://127.0.0.1:8080/api/v1/people', payload, config)
const data = postResponse.data;
console.log(data);
