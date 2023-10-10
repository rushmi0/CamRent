import React from 'react';
import axios from 'axios';

const TestApi = () => {
    const handleTestApi = async () => {
        try {
            const id = 1;  // ระบุ ID ที่ต้องการทดสอบ

            // สร้าง FormData เพื่อส่งไฟล์รูปภาพ
            const formData = new FormData();
            formData.append('file', selectedFile);  // กำหนดชื่อ 'file' ตาม API

            // ส่ง request ไปที่ API
            const response = await axios.post(`http://127.0.0.1:8080/customers/img/id/${id}`, formData);

            // แสดงผลลัพธ์จาก API
            console.log('API response:', response.data);
        } catch (error) {
            console.error('Error calling API:', error);
        }
    };

    return (
        <div>
            <h1>Test API</h1>
            <button onClick={handleTestApi}>Call API</button>
        </div>
    );
};

export default TestApi;
