import React from 'react'
import { motion } from 'framer-motion'

//module.css
import classes from './Homepage.module.css'


function Homepage() {
  return (
    <>
      <motion.section
      id={classes.bgImage}
        className="h-[45rem] bg-gradient-to-r from-cyan-500 to-blue-500"
        initial={{ y: -300, opacity: 0 }}
        animate={{ y: 0, opacity: 1 }}
        exit={{ y: 300, opacity: 0 }}
        transition={{ duration: 0.5 }}
      >
        <div>
          <div className="flex justify-start lg:justify-start">
            <motion.div
              className="lg:flex lg:justify-center lg:items-center flex items-center justify-center"
              initial={{ x: -300, opacity: 0 }}
              animate={{ x: 0, opacity: 1 }}
              exit={{ x: 300, opacity: 0 }}
              transition={{ delay: 0.7, duration: 0.8 }}
            >
              <div className=" p-[2rem] flex-col md:ml-[4rem] lg:ml-[4rem] xl:ml-[18rem]">
                <div className="text-7xl sm:text-7xl md:text-9xl font-medium">
                  <h1 className="text-[#96663f]">CAM</h1>
                  <h1 className="pl-[6rem]  text-[#96663f] md:text-[#353535]">RENT</h1>
                </div>
                <div className=" mt-4 flex items-center justify-between">
                  <p className="font-light text-2xl text-[#96663f] mr-4">
                    Save up to 50%
                  </p>
                  <motion.a
                    href="/shop"
                    className="bg-[#96663f] py-2 px-5 text-white font-medium rounded"
                    whileHover={{ 
                      backgroundColor: "#353535", 
                    }}
                  >
                    RENT NOW
                  </motion.a>
                </div>
              </div>
            </motion.div>
            <div className="h-[45rem]"></div>
          </div>
        </div>
      </motion.section>
    </>
  )
}

export default Homepage