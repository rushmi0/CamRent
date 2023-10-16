import React from 'react'
import { useState } from 'react';
import { useLocation } from "react-router-dom";

function ManageProduct() {

  const [proName,setproName] = useState('Nikon Z7');
  const [proDetail,setproDetail] = useState('Nikon Z7เรือธงใหม่ของค่ายนิคอนที่เป็นกล้องฟูลเฟรมมิลเอลร์เลสรุ่นแรกของค่ายที่มาพร้อมกับ Z6ที่มีสเปคเป็นรองกว่าพี่ใหญ่ทั้งจุดโฟกัสและรายละเอียดเล็กๆน้อยรวมถึงราคาที่ห่างกัน เพื่อเป็นการทำความรู้จักกับ Z7 ให้ดีมากขึ้นลองไปดูรายละเอียดกันว่าจุดเด่นจุดแข็งของพี่ใหญ่คนนี้เป็นอย่างไร');
  const [proResolution,setproResolution] = useState('8256 x 5504');
  const [proRatio,setproRatio] = useState('1:1, 3:2, 4:5, 16:9');
  const [proPixel,setproPixel] = useState('45.7 Megapixel');
  const [proSensor,setproSensor] = useState('Full frame (35.9 x 23.9 mm)/CMOS/Expeed 6');

  return (
    <>
    <form className="p-5 round border">
      <h1 className="text-4xl">Add New Product</h1>
      <div className="grid md:grid-cols-3 lg:flex space-x-1 lg:justify-center">
        <div className="grid justify-center lg:justify-normal">
          <img
            src="https://www.digitaltrends.com/wp-content/uploads/2018/10/nikon-z7-review-5179.jpg?resize=1483%2C989&p=1"
            alt="profile img"
            className="object-cover w-80"
          />
          <input type="file" className="mt-2"accept="image/*" />
        </div>
        <div className="mt-3 md:col-span-2 lg:p-4 grid justify-center">
          <div
            id="Prod_Box"
            className="grid grid-cols-1 lg:grid-cols-4"
          >
            <b>
              <h2 className="mr-1 mt-1">Product Name:</h2>
            </b>
            <input
              type="prod_name"
              size={50}
              className="border rounded lg:col-span-3 md:w-80 md:h-7 lg:h-7 font-bold"
              value={proName}
              onChange={e => {setproName(e.target.value)}}
            />
          </div>
           <div id="Prod_Detail" className="grid grid-cols-1 lg:grid-cols-4">
            <b>
              <h2>Product Detail:</h2>
            </b>
            <div className="lg:col-span-3 md:w-80 lg:w-95 md:h-95 lg:h-95">
              <textarea className="border rounded w-full h-full"
              value={proDetail}
              onChange={e => {setproDetail(e.target.value)}}/>
              <div className="grid grid-cols-4">
              </div>
            </div>
          </div> 
          <div className='mx-auto mt-4'>
          <button
            id="SaveButton"
            type="save"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold w-14 h-8" 
          >
            Save
          </button>
          <button
            id="CancelButton"
            type="cancel"
            className="bg-red-500 hover:bg-red-700 text-white font-bold w-14 h-8 ml-2"
          >
            Cancel
          </button>
          </div>
        </div>
      </div>
    </form>
    {/*Specification part */}
    <form className="grid cols-2 width-70vh margin-5rem mx-auto display-flex round border" autoComplete="off">
        <div className="px-4 sm:px-0 pt-2 ml-7">
          <h3 className="text-base font-semibold leading-7 text-gray-900">SPECIFICATION</h3>
        </div>
        <div>
          <div className="mt-2 border-t border-gray-100">
            <dl className="divide-y divide-gray-100">
              <div className="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                <dt className="text-sm font-medium leading-6 text-gray-900 text-center">ความละเอียดภาพสูงสุด (Max Resolution):</dt>
                <input
                  name="service"
                  type="text"
                  id="specDetail"
                  className='border rounded mt-1 text-sm leading-6 w-full sm:w-80 sm:mt-0 justify-center'
                  value={proResolution}
                  onChange={e => {setproResolution(e.target.value)}}
                />
              </div>
              <div className="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                <dt className="text-sm font-medium leading-6 text-gray-900 text-center">สัดส่วนภาพ (Image Ratio):</dt>
                <input
                  name="service"
                  type="text"
                  id="specDetail"
                  className='border rounded mt-1 text-sm leading-6 w-full sm:w-80 sm:mt-0 justify-center'
                  value={proRatio}
                  onChange={e => {setproRatio(e.target.value)}}
                />
              </div>
              <div className="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                <dt className="text-sm font-medium leading-6 text-gray-900 text-center">พิกเซล (Effective Pixels):</dt>
                <input
                  name="service"
                  type="text"
                  id="specDetail"
                  className='border rounded mt-1 text-sm leading-6 w-full sm:w-80 sm:mt-0 justify-center'
                  value={proPixel}
                  onChange={e => {setproPixel(e.target.value)}}
                />
              </div>
              <div className="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                <dt className="text-sm font-medium leading-6 text-gray-900 text-center">เซลล์รับภาพ (Image Sensor):</dt>
                <input
                  name="service"
                  type="text"
                  id="specDetail"
                  className='border rounded mt-1 text-sm leading-6 w-full sm:w-80 sm:mt-0 justify-center'
                  value={proSensor}
                  onChange={e => {setproSensor(e.target.value)}}
                />
              </div>
            </dl>
          </div>
        </div>
        <div className='mx-auto'>
          <button
            id="SaveButton"
            type="save"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold w-14 h-8 ml-4 mb-4 mt-2 lg:col-span-1"
          >
            Save
          </button>
          <button
            type="button"
            className="remove-btn bg-red-600 hover:bg-blue-700 text-white font-bold w-14 h-8 ml-2 mb-4 mt-4 lg:col-span-1"
          >
            <span>Clear</span>
          </button>
        </div>
      </form>
    </>
  );
}

export default ManageProduct