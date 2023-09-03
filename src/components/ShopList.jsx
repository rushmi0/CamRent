import React from "react";
import { BsFillGrid3X3GapFill, BsFillGridFill } from "react-icons/bs";
import { MdTableRows } from "react-icons/md";
import { motion } from "framer-motion";


// Done!
function ShopList() {

  return (
    <>
      <section>
        <div className="container mx-auto mt-[4rem] mb-[4rem]">
          <div className="grid grid-cols-1 md:grid-cols-2 lg:px-[10rem]">
            <div className="p-5  flex justify-start items-center ">
              <h3 className="text-lg font-medium">Filter: </h3>
              <select
                name="shop"
                id="shop"
                className="border border-slate-500 p-1 ml-5"
              >
                <option value="noFilter">-</option>
                <option value="camera">Camera</option>
                <option value="mic">Microphone</option>
                <option value="acc">Accessories</option>
                <option value="onsale">On Sale</option>
              </select>
            </div>
            <div className="p-5 flex justify-start md:justify-end items-center">
              <h3 className="text-lg font-medium">SORT BY: </h3>
              <select
                name="shop"
                id="shop"
                className="border border-slate-500 p-1 ml-5"
              >
                <option value="nosort">-</option>
                <option value="camera">Lowest-Highest</option>
                <option value="mic">Highest-Lowest</option>
                <option value="acc">A-Z</option>
                <option value="onsale">Stars</option>
              </select>
              <div className="flex p-5 gap-3 justify-center items-center">
                <motion.a whileHover={{ scale: 1.2, color: "#96663f" }}>
                  <MdTableRows className="text-xl" />
                </motion.a>
                <motion.a whileHover={{ scale: 1.2, color: "#96663f" }}>
                  <BsFillGridFill className="text-xl" />
                </motion.a>
                <motion.a whileHover={{ scale: 1.2, color: "#96663f" }}>
                  <BsFillGrid3X3GapFill className="text-xl" />
                </motion.a>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default ShopList;
