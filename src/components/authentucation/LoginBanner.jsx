import React from "react";
import { motion } from "framer-motion";
import classes from "./LoginBanner.module.css";

function LoginBanner() {
  return (
    <>
      <motion.div
        className={classes.bgImg}
        initial={{ x: -700, opacity: 0 }}
        animate={{ x: 0, opacity: 1 }}
        exit={{ x: 700, opacity: 0 }}
        transition={{ duration: 0.8 }}
      >
        
      </motion.div>
    </>
  );
}

export default LoginBanner;
