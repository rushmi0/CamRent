import React from "react";
import { motion } from 'framer-motion'


//module.css
import classes from "./Banner.module.css";


//png
import bannerImg1 from "../../assets/Homepage/bannerimg1.png";
import bannerImg2 from "../../assets/Homepage/bannerimg2.png";
import bannerImg3 from "../../assets/Homepage/bannerimg3.png";


function Banner() {
  return (
    <>
      <div className="container mx-auto mt-[3rem]">
        <div className="grid lg:grid-rows-4 xl:grid-flow-col gap-4 justify-items-center">
          <motion.div className={classes.camSetBox1}
          initial={{ x: 300, opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          exit={{ x: -300, opacity: 0 }}
          transition={{ duration: 0.5, delay: 0.6 }}>
            <div className="pt-[3rem] p-5">
              <h1 className="text-3xl lg:text-5xl font-medium mb-5">Camera Set</h1>
              <p className="text-lg lg:text-xl font-light mb-8">
                Rent high-end camera from a professional studio with the lowest price
                possible.
              </p>
              <motion.a
                href="/shop"
                className={classes.mainBtn}
                whileHover={{backgroundColor: "#f7bdb2"}}
              >
                RENT NOW
              </motion.a>
            </div>
            <div className="flex items-center">
              <motion.img src={bannerImg1} alt="image banner"  whileHover={{scale:1.1}} transition={{duration: 0.3}}/>
            </div>
          </motion.div>

          <motion.div className={classes.camSetBox2}
          initial={{ x: 300, opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          exit={{ x: -300, opacity: 0 }}
          transition={{ duration: 0.5, delay: 1}}
          >
            <div className="p-3">
              <h1 className="text-3xl font-medium">ACCESSORIES & PARTS</h1>
              <p className="text-lg font-light mb-5">
                Rent accessories to make your project better.
              </p>
              <motion.a
                href="/shop"
                className={classes.mainBtn}
                whileHover={{backgroundColor: "#f7bdb2"}}
              >
                RENT NOW
              </motion.a>
            </div>
            <motion.img src={bannerImg2} alt="image banner" className="w-[200px] h-auto md:w-[300px]" whileHover={{scale:1.1}} transition={{duration: 0.3}}/>
          </motion.div>
          <motion.div className={classes.camSetBox3}
          initial={{ x: 300, opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          exit={{ x: -300, opacity: 0 }}
          transition={{ duration: 0.5, delay: 1.2 }}
          >
            <div className="p-3">
              <h1 className="text-3xl font-medium">RECOMMANDED</h1>
              <p className="text-lg font-light mb-5">
                Check out top rated store and product.
              </p>
              <motion.a
                href="/shop"
                className={classes.mainBtn}
                whileHover={{backgroundColor: "#f7bdb2"}}
              >
                RENT NOW
              </motion.a>
            </div>
            <motion.img src={bannerImg3} alt="image banner" className="w-[200px] h-auto md:w-[300px]" whileHover={{scale:1.1}} transition={{duration: 0.3}}/>
          </motion.div>
        </div>
      </div>
    </>
  );
}

export default Banner;
