import React from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";


function ShopStyleOne({ id, prod_name, img, price }) {
    const navigate = useNavigate();
    
    const toProduct = () => {
        navigate("/productdetail")
    }
  return (
    <>
      <Link key={id}>
        <div className="bg-[#f0f0f0] h-auto">
            <div className="flex justify-center ">
              <img className="h-[400px] p-5" src={img} alt="" onClick={toProduct}/>
            </div>
            <div className="flex flex-col justify-center items-center">
              <p className="font-bold text-2xl">{prod_name}</p>
              <p className="font-medium text-lg">${price}/hr</p>
            </div>
            <Link to="/productdetail" className="btn bg-[#5a6680] border-0 rounded flex text-white hover:bg-[#f7bdb2]">View Details</Link>
          </div>
      </Link>
    </>
  );
}

export default ShopStyleOne;
