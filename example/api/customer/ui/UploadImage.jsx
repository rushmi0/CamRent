import React, {useState} from 'react';
import axios from 'axios';

const ImageUpload = () => {
    const [file, setFile] = useState(null);
    const [previewImage, setPreviewImage] = useState(null);
    const [buttonHovered, setButtonHovered] = useState(false);

    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0];
        setFile(selectedFile);
        setPreviewImage(URL.createObjectURL(selectedFile));
    };

    const handleUpload = async () => {
        if (file) {
            try {
                const formData = new FormData();
                formData.append('file', file);

                const apiUrl = 'http://localhost:8080/api/v1/auth/user/upload';

                await axios.post(apiUrl, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'Access-Token' : 'eyJ1c2VyTmFtZSI6IlJ1c2htaTAiLCJwZXJtaXNzaW9uIjoiZWRpdCIsImV4cCI6ODY0MDAwMDAwMDE3MDU0MTM1NTM3NzIsImlhdCI6MTcwNTQxMzY0MDE3Miwic2lnbmF0dXJlIjoiMzA0NTAyMjEwMGQ5YWMzODc2YWMyMzc5NTQyM2Q4ODhmNjZlMjk1Nzc0OWI4YmJmMzhlM2FiNWMwNjgyZmM5Njk1NDIwYjdmYmMwMjIwMGRhNmE4ZWRiNWI0Y2Y4Mjk2MjQwZGEzMGFhOWJjNDAwYTFlYjU2ZDNlNGRjZDc2NGExYmM2ZTVjNmUzMDNiOSJ9'
                    }
                });

                console.log('Image uploaded successfully');
            } catch (error) {
                console.error('Error uploading image:', error);
            }
        } else {
            console.error('No file selected.');
        }
    };

    return (
        <div
            style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                minHeight: '100vh',
                padding: '20px',
                boxSizing: 'border-box',
            }}
        >
            <h1 style={{marginBottom: '20px'}}>Upload Image</h1>
            <input type="file" onChange={handleFileChange} accept="image/*" style={{marginBottom: '20px'}}/>
            {previewImage && (
                <div>
                    <h2 style={{marginBottom: '10px'}}>Preview:</h2>
                    <img
                        src={previewImage}
                        alt="Preview"
                        style={{
                            maxWidth: '100%',
                            maxHeight: '521px',
                            border: '1px solid #ccc',
                            borderRadius: '10px',
                            boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
                        }}
                    />
                </div>
            )}
            <button
                onClick={handleUpload}
                onMouseEnter={() => setButtonHovered(true)}
                onMouseLeave={() => setButtonHovered(false)}
                style={{
                    padding: '10px 20px',
                    backgroundColor: buttonHovered ? '#45a049' : '#4CAF50',
                    color: 'white',
                    border: 'none',
                    borderRadius: '5px',
                    cursor: 'pointer',
                    transition: 'background-color 0.3s',
                }}
            >
                {buttonHovered ? 'Upload Now!' : 'Upload'}
            </button>
        </div>
    );
};

export default ImageUpload;
