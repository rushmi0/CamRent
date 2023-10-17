import React from "react";

//react-icons
import { FaShippingFast, FaExchangeAlt } from "react-icons/fa";
import { RiSecurePaymentLine } from "react-icons/ri";
import { MdSupportAgent } from "react-icons/md";

//module.css
import classes from './Feature.module.css'


function Feature() {
  return (
    <>
      <div className="container mx-auto border border-slate-300 mt-[3rem] mb-[3rem] rounded">
        <div className="grid xl:grid-cols-4 sm:grid-cols-2 sm:justify-items-start xl:justify-items-center m-5">
          <div className={classes.featureBox}>
            <FaShippingFast className={classes.icons} />
            <div className={classes.desContent}>
              <h1 className={classes.headText}>Free Shipping</h1>
              <p className={classes.subText}>On all orders over $75.00</p>
            </div>
          </div>
          <div className={classes.featureBox}>
            <FaExchangeAlt className={classes.icons} />
            <div className={classes.desContent}>
              <h1 className={classes.headText}>Free Returns</h1>
              <p className={classes.subText}>Returns are free within 9 days.</p>
            </div>
          </div>
          <div className={classes.featureBox}>
            <RiSecurePaymentLine className={classes.icons} />
            <div className={classes.desContent}>
              <h1 className={classes.headText}>100% Payment Secure</h1>
              <p className={classes.subText}>Your payment are safe here.</p>
            </div>
          </div>
          <div className={classes.featureBox}>
            <MdSupportAgent className={classes.icons} />
            <div className={classes.desContent}>
              <h1 className={classes.headText}>Support 24/7</h1>
              <p className={classes.subText}>Contact us 24 hours a day</p>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Feature;
