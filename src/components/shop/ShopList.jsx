import React from "react";
import { AiOutlineSearch } from "react-icons/ai";

// Done!
function ShopList() {
  return (
    <>
      <section>
        <div className="container mx-auto mt-[4rem] mb-[4rem]">
          <div className="grid grid-cols-1 md:grid-cols-3 lg:px-[10rem] m-5 md:m-3 justify-items-start md:justify-items-center">
            <div className=" flex justify-start items-center ">
              <h3 className="text-lg font-medium">Filter: </h3>
              <select
                name="shop"
                id="shop"
                className="border border-slate-500 bg-[#f8f9fa] p-1 ml-5"
              >
                <option value="noFilter">-</option>
                <option value="camera">Camera</option>
                <option value="mic">Microphone</option>
                <option value="acc">Accessories</option>
                <option value="onsale">Top Rated</option>
              </select>
            </div>
            <div className="flex justify-start items-center mt-5 mb-5">
              <input
                type="text"
                placeholder="Search..."
                className="input input-bordered w-full max-w-xs rounded-full bg-slate-200"
              />
            </div>
            <div className="flex justify-start md:justify-end items-center">
              <h3 className="text-lg font-medium">SORT BY: </h3>
              <select
                name="shop"
                id="shop"
                className="border border-slate-500 bg-[#f8f9fa] p-1 ml-5"
              >
                <option value="nosort">-</option>
                <option value="camera">Lowest-Highest</option>
                <option value="mic">Highest-Lowest</option>
                <option value="acc">A-Z</option>
                <option value="onsale">Stars</option>
              </select>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default ShopList;
