import React from 'react'
import Sidebar from "./Sidebar.jsx";

function CustomerDeliveryInfo() {
    return (
        <div className="flex bg-white mt-5 shadow-md border-2 mb-5 pb-5 items-center">
            <Sidebar/>

            <main className=" w-full bg-white border-l">
                <div className="mx-6">
                    <h1 className="flex justify-center items-center my-6 text-3xl r">ข้อมูลของลูกค้าที่กำลังเช่ากล้อง</h1>
                </div>

                <div className="bg-white">
                    <div className="max-w-lg mx-auto my-10 bg-gray-100 rounded-lg shadow-md p-5">
                        <img className="w-32 h-32 rounded-full mx-auto" src="https://th.bing.com/th?id=OIP.oLS4rHOOEqv8160l-sfRuAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                             alt="Profile picture"/>
                        <h2 className="text-center text-2xl font-semibold mt-3">User1</h2>
                        <p className="text-center text-gray-600 mt-1">#21394</p>
                        <p className="text-center text-green-700 mt-1">กำลังเช่า</p>
                        <div className="flex justify-center mt-5">
                            <a href="/profile" className="text-blue-500 hover:text-blue-700 mx-3">ดูหน้าโปรไฟล์</a>
                        </div>
                        <div className="mt-5">
                            <h3 className="text-xl font-semibold">ชื่อผู้เช่า</h3>
                            <p className="text-gray-600 mt-2">นาย แดง แซงทางโค้ง</p>
                        </div>
                        <div className="mt-5">
                            <h3 className="text-xl font-semibold">ที่อยู่จัดส่ง</h3>
                            <p className="text-gray-600 mt-2">1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250</p>
                        </div>
                        <div className="mt-5">
                            <h3 className="text-xl font-semibold">รายละเอียดสินค้าที่เช่า</h3>
                            <p className="text-gray-600 mt-2">กล้อง nikon z7</p>
                            <p className="text-gray-600 mt-2">ราคาเช่า 20,000 บาท</p>
                            <p className="text-gray-600 mt-2">ระยะการเช่า 21 วัน</p>
                        </div>
                        <div className="mt-5">
                            <h3 className="text-xl font-semibold">รายละเอียดเพิ่มเติม</h3>
                            <p className="text-gray-600 mt-2">ขอซอสเพิ่ม</p>
                        </div>
                    </div>
                </div>

                <a href="./ViewOrder" className="flex justify-center items-center px-6 py-2 mt-5 mb-5 mr-5 transition ease-in duration-200 uppercase rounded-full hover:bg-red-600 hover:text-white border-2 border-red-600 focus:outline-none text-red-600">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                         strokeWidth="1.5" stroke="currentColor" className="w-5 h-5 rtl:-scale-x-100">
                        <path strokeLinecap="round" strokeLinejoin="round"
                              d="M6.75 15.75L3 12m0 0l3.75-3.75M3 12h18"/>
                    </svg>
                    ย้อนกลับ
                </a>


            </main>
        </div>
    )
}

export default CustomerDeliveryInfo
