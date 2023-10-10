import React, { useState } from 'react';
import axios from 'axios';

const TestUpload1 = () => {
    const [file, setFile] = useState(null);

    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const formData = new FormData();
        formData.append('file', file);

        try {
            // Send the file to the API endpoint
            const response = await axios.post('http://127.0.0.1:8080/api/v1/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Stores': 'Stores'
                }
            });

            console.log('Response:', response.data);
        } catch (error) {
            console.error('Error uploading image:', error);
        }
    };

    return (
        <div>
            <h2>Test Upload Image</h2>
            <form onSubmit={handleSubmit}>
                <input type="file" onChange={handleFileChange} accept="image/*" />
                <button type="submit">Upload</button>
            </form>
        </div>
    );
};

export default TestUpload1;
