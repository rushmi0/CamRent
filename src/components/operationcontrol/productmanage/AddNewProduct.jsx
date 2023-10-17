import React from 'react'
import { useState } from 'react';
import { useLocation } from "react-router-dom";

function AddNewProduct() {

  return (
    <>
      <form className="p-5 round border">
        <h1 className="text-4xl">Add New Product</h1>
        <div className="grid md:grid-cols-3 lg:flex space-x-1 lg:justify-center">
          {/* image div */}
          <div className="grid justify-center lg:justify-normal">
            <img
              src="https://cdn-icons-png.flaticon.com/512/2983/2983798.png"
              alt="profile img"
              className="object-cover w-80"
            />
            <input type="file" accept="image/*" />
          </div>
          {/* details div */}
          <div className="mt-3 md:col-span-2 lg:p-4 grid justify-center">
            {/* Product Name */}
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
              />
            </div>
            {/* Product Detail */}
            <div id="Prod_Detail" className="grid grid-cols-1 lg:grid-cols-4">
              <b>
                <h2>Product Detail:</h2>
              </b>
              <div className="lg:col-span-3 md:w-80 lg:w-95 md:h-95 lg:h-95">
                <textarea className="border rounded w-full h-full" />
                <div className="grid grid-cols-4">
                </div>
              </div>
            </div>
            {/* save */}
            <div className='mx-auto mt-4'>
            <button
              id="SaveButton"
              type="save"
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold w-14 h-8 mx-auto mt-2"
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
                />
              </div>
              <div className="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                <dt className="text-sm font-medium leading-6 text-gray-900 text-center">สัดส่วนภาพ (Image Ratio):</dt>
                <input
                  name="service"
                  type="text"
                  id="specDetail"
                  className='border rounded mt-1 text-sm leading-6 w-full sm:w-80 sm:mt-0 justify-center'
                />
              </div>
              <div className="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                <dt className="text-sm font-medium leading-6 text-gray-900 text-center">พิกเซล (Effective Pixels):</dt>
                <input
                  name="service"
                  type="text"
                  id="specDetail"
                  className='border rounded mt-1 text-sm leading-6 w-full sm:w-80 sm:mt-0 justify-center'
                />
              </div>
              <div className="px-4 py-4 sm:grid sm:grid-cols-2 sm:gap-4 sm:px-0">
                <dt className="text-sm font-medium leading-6 text-gray-900 text-center">เซลล์รับภาพ (Image Sensor):</dt>
                <input
                  name="service"
                  type="text"
                  id="specDetail"
                  className='border rounded mt-1 text-sm leading-6 w-full sm:w-80 sm:mt-0 justify-center'
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
              id="CancelButton"
            type="cancel"
            className="remove-btn bg-red-600 hover:bg-blue-700 text-white font-bold w-14 h-8 ml-2 mb-4 mt-4 lg:col-span-1"
          >
            <span>Clear</span>
          </button>
        </div>
      </form>
    </>
  );
}

export default AddNewProduct