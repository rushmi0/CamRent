import React from "react";
import { Link } from "react-router-dom";




// Done!
function ShopListOne({ id, cate, img, price }) {
  // receive data from shop page using props: id, cate, img , price
  
  return (
    <>
        <Link key={id}>
          <div className="bg-slate-300 h-[30rem]">
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

export default ShopListOne;
