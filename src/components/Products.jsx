//library
import { React} from "react";
import { motion } from "framer-motion";

//css


//png,svg
import camSony from "../assets/img/SonyCam.png";
import micImg from "../assets/img/micImg.png";
import accImg from "../assets/img/acc.png";
import saleImg from "../assets/img/sale.png";


// Done!
function Products() {

  return (
    <>
      <section>
        <div className="flex justify-center items-center">
          <div className="xl:grid xl:grid-rows-3 xl:grid-flow-col gap-6 h-[38rem] mt-[5rem] mb-[5rem]">
            <motion.div
              className="row-span-3 mb-[2rem] xl:mb-0 w-[auto] bg-[#e4e4e4] overflow-hidden"
              initial={{x:-300, opacity: 0}}
              animate={{x:0, opacity:1}}
              exit={{x: 300, opacity:0}}
              transition={{ delay: 1, duration: 0.5 }}
            >
              {/* Camera Box */}
              <div className="flex flex-col justify-start items-center p-3">
                <motion.div
                  whileHover={{ scale: 1.1, rotate: 2 }}
                  transition={{ duration: 0.3 }}
                >
                  <img
                    className="h-[250px] mt-[3rem] "
                    src={camSony}
                    alt="camBrand"
                  />
                </motion.div>
              </div>
              <div className="p-[2rem] flex flex-col items-center justify-center lg:justify-start lg:items-end ">
                <h3 className="font-black text-5xl mb-2 text-[#96773f]">
                  Camera
                </h3>
                <p className="font-light text-2xl mb-5 italic ">BEST CHOICE</p>
                <motion.a
                  href=""
                  className="bg-slate-800 px-6 py-3 text-white text-lg rounded font-medium"
                  whileHover={{
                    backgroundColor: "#96773f",

                  }}
                >
                  RENT NOW
                </motion.a>
              </div>
              {/* End Camera Box */}
            </motion.div>
            <motion.div
              className="col-span-2 mb-[2rem] xl:mb-0 w-[500px] md:w-[600px] bg-[#e4e4e4]"
              initial={{ y: -300, opacity: 0 }}
              animate={{ y: 0, opacity: 1 }}
              exit={{ y: 300, opacity: 0 }}
              transition={{ delay: 1, duration: 0.5 }}
            >
              <div className="grid grid-cols-2">
                {/* Accessories Box */}
                <div className="flex flex-col items-start justify-center p-6">
                  <h3 className="font-black text-4xl mb-2 text-[#96773f]">
                    Accessories
                  </h3>
                  <p className="font-light text-2xl mb-3 italic">MUST HAVE</p>
                  <motion.a
                    href=""
                    className="bg-slate-800 px-4 py-2 text-white text-lg rounded font-medium"
                    whileHover={{
                      backgroundColor: "#96773f",
                    }}
                  >
                    RENT NOW
                  </motion.a>
                </div>
                <div className="mt-5 mr-4">
                  <motion.div
                    whileHover={{ scale: 1.1, rotate: 2 }}
                    transition={{ duration: 0.3 }}
                  >
                    <img src={accImg} alt="Accessories image" />
                  </motion.div>
                </div>
                {/* End Accessories Box */}
              </div>
            </motion.div>
            <motion.div
              className="row-span-2 mb-[2rem] xl:mb-0 w-[500px] md:w-[600px] col-span-2 bg-[#e4e4e4]"
              initial={{ y: 300, opacity: 0 }}
              animate={{ y: 0, opacity: 1 }}
              exit={{ y: -300, opacity: 0 }}
              transition={{ delay: 1, duration: 0.5 }}
            >
              {/* On Sale Box */}
              <div className="flex flex-col justify-start items-center ">
                <motion.div
                  whileHover={{ scale: 1.1 }}
                  transition={{ duration: 0.3 }}
                >
                  <img
                    className="h-[200px] mt-[2.3rem] "
                    src={saleImg}
                    alt="camBrand"
                  />
                </motion.div>
              </div>
              <div className="mt-[-3rem] flex flex-col justify-start items-center ">
                <h3 className="font-black text-5xl mb-2 text-[#f87001] ">
                  ON SALE
                </h3>
                <p className="font-light text-2xl mb-4 italic ">
                  SAVE UP TO 60%
                </p>
                <motion.a
                  href=""
                  className="bg-slate-800 mb-6 px-6 py-3 text-white text-lg rounded font-medium"
                  whileHover={{
                    backgroundColor: "#96773f",
                  }}
                >
                  RENT NOW
                </motion.a>
              </div>
              {/* End On Sale Box */}
            </motion.div>
            <motion.div
              className="row-span-3 w-[auto] bg-[#e4e4e4]"
              initial={{ x: 300, opacity: 0 }}
              animate={{ x: 0, opacity: 1 }}
              exit={{ x: -300, opacity: 0 }}
              transition={{ delay: 1, duration: 0.5 }}
            >
              {/* Mic Box */}
              <div className="flex flex-col justify-start items-center overflow-hidden">
                <motion.div
                  whileHover={{ scale: 1.1, rotate: -2 }}
                  transition={{ duration: 0.3 }}
                >
                  <img
                    className="h-[350px] mt-[3rem] "
                    src={micImg}
                    alt="camBrand"
                  />
                </motion.div>
              </div>
              <div className="p-[2rem] flex flex-col items-center justify-center lg:justify-start lg:items-end mt-[-1.9rem]">
                <h3 className="font-black text-5xl mb-2 text-[#96773f]">
                  Studio
                </h3>
                <p className="font-light text-2xl mb-4 italic">MOST VISITED</p>
                <motion.a
                  href=""
                  className="bg-slate-800 px-6 py-3 text-lg text-white rounded font-medium"
                  whileHover={{
                    backgroundColor: "#96773f",
                  }}
                >
                  RENT NOW
                </motion.a>
              </div>
              {/* End Mic Box */}
            </motion.div>
          </div>
        </div>
      </section>
    </>
  );
}

export default Products;
