//framework
import React from "react";
import { motion } from "framer-motion";


//css
import classes from "./ShopBanner.module.css";


// Done!
function ShopBanner() {
  return (
    <>
    {/* add motion to shopBanner background */}
      <motion.section id={classes.shopBanner}
      initial={{x: -300, opacity: 0}}
      animate={{x: 0, opacity: 1}}
      exit={{x: 300, opacity: 0}}
      transition={{duration: 0.4}}
      >
        <div className="container mx-auto h-[20rem]">
          <div className="grid grid-cols-1">
            <motion.div className="flex justify-center items-center h-[20rem]"
            initial={{x: -300, opacity: 0}}
            animate={{x: 0, opacity: 1}}
            exit={{x: 300, opacity: 0}}
            transition={{deley: 1, duration: 0.8}}
            >
              <h3 className="font-bold text-4xl text-white">SHOP</h3>
            </motion.div>
          </div>
        </div>
      </motion.section>
    </>
  );
}

export default ShopBanner;
