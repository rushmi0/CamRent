import React from "react";
import { Link } from "react-router-dom";

function ShopStyleOne({ id, cate, img, price }) {
  return (
    <>
      <Link key={id}>
        <div className="bg-[#f0f0f0] h-[30rem] border-b-4 border-[#96663f]">
            <div className="flex justify-center ">
              <img className="h-[400px] p-5" src={img} alt="" />
            </div>
            <div className="flex flex-col justify-center items-center">
              <p className="font-bold text-2xl">{cate}</p>
              <p className="font-medium text-lg">${price}/hr</p>
            </div>
          </div>
      </Link>
    </>
  );
}

export default ShopStyleOne;
