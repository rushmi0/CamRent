import React from 'react'
import { useState } from 'react';
import { useLocation } from "react-router-dom";

function test() {
  const [serviceList, setServiceList] = useState([{ service: "" }]);
  const handleServiceChange = (e, index) => {
    const { name, value } = e.target;
    const list = [...serviceList];
    list[index][name] = value;
    setServiceList(list);
  };

  const handleServiceRemove = (index) => {
    const list = [...serviceList];
    list.splice(index, 1);
    setServiceList(list);
  };

  const handleServiceAdd = () => {
    setServiceList([...serviceList, { service: "" }]);
  };
  const location = useLocation().hash;
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
              <textarea className="border rounded w-full" />
              <div className="grid grid-cols-4">
              </div>
            </div>
          </div> 
          {/* save */}
          <button
            id="SaveButton"
            type="save"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold w-14 h-8" 
          >
            Save
          </button>
        </div>
      </div>
    </form>
    {/*Specification part */}
    <form className="width-70vh margin-5rem mx-auto display-flex round border" autoComplete="off">
      <div className="flex:2">
        <label className="display-flex justify-content:space-between ml-3">SPECIFICATION</label>
        {serviceList.map((singleService, index) => (
          <div key={index} className="services">
            <div className="display:flex flex-direction:column margin:05px 0.5rem 0 width:100%">
              <input
                name="service"
                type="text"
                id="specType"
                size={80}
                className='border rounded lg:col-span-1 md:h-7 lg:h-7 ml-10'
               
              />
              <input
                name="service"
                type="text"
                id="specDetail"
                size={80}
                className='border rounded lg:col-span-1 md:h-7 lg:h-7 ml-10'
                value={singleService.service}
                onChange={(e) => handleServiceChange(e, index)}
                required
              />
               {/*add button */}
              {serviceList.length - 1 === index && serviceList.length < 10 && (
                <button
                  type="button"
                  onClick={handleServiceAdd}
                  className="add-btn bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-3 ml-2"
                >
                  <span>Add Extra Line</span>
                </button>
              )}
              {/*Remove button */}
              {serviceList.length !== 1 && (
                <button
                  type="button"
                  onClick={() => handleServiceRemove(index)}
                  className="remove-btn bg-red-600 hover:bg-red-700 text-white font-bold py-0.1 px-2 mt-2 mb-2 ml-2"
                >
                  <span>X</span>
                </button>
              )}
            </div>
            {/*Remove button */}
            {/* <div className="second-division">
              {serviceList.length !== 1 && (
                <button
                  type="button"
                  onClick={() => handleServiceRemove(index)}
                  className="remove-btn bg-red-600 hover:bg-blue-700 text-white font-bold py-1 px-3 rounded-full mt-2 mb-2 ml-2"
                >
                  <span>Remove</span>
                </button>
              )}
            </div> */}
          </div>
        ))}
        <button
            id="SaveButton"
            type="save"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold w-14 h-8 ml-4 mb-4 mt-2" 
          >
            Save
          </button>
      </div>
    </form>
    </>
  );
}




// value={singleService.service}
//                 onChange={(e) => handleServiceChange(e, index)}
//                 required lhth7lll;khgjhtyj6o 
export default AddNewProduct
