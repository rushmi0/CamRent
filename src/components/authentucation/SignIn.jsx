import React, {useState} from "react";
import { useNavigate } from "react-router-dom";
import { motion } from "framer-motion";
import axios from 'axios';

import classes from "./SignIn.module.css"
import logo from "../../assets/Cam_black2.svg";
import LoginBanner from "./LoginBanner";
// import EllipticCurve from "../../../utils/Sha256.js";
// import {b32encode} from "../../../utils/Bech32.js";
function SignIn() {

  const navigate = useNavigate("");

  const toHome = () => {
    navigate("/");
  };

  // const [ username, setUsername ] = useState("");
  // const [ pass, setPass ] = useState("");
  //
  //
  //
  // const accountType= ["Customers", "Stores"]
  //
  // const ec = EllipticCurve();
  // const url = 'http://127.0.0.1:8080/api/v1/user/sign-up';
  //
  // const onUsernameEnter = (e) => {
  //   setUsername(e.target.value);
  // };
  //
  // const onPassEnter = (e) => {
  //   setPass(e.target.value);
  // };
  //
  // const onSubmit = (e) => {
  //   e.preventDefault();
  //   const privateKey = ec.genPrivateKey(pass);
  //   console.log(privateKey);
  //
  //   const publicKey = ec.generateKeyPair(privateKey);
  //   const authKey = b32encode(publicKey);
  //   const payload = {
  //     userName: username,
  //     authKey: authKey,
  //   };
  //
  //   axios.post(url, payload)
  //       .then(console.log("Data posted!")).catch((err) => console.log(err));
  // };


  return (
    <>
      <div className={classes.box}>
        <LoginBanner />

        <motion.div
        className="mx-auto"
          initial={{ x: 500, opacity: 0 }}
          animate={{ x: 0, opacity: 1 }}
          exit={{ x: -500, opacity: 0 }}
          transition={{ duration: 0.8, delay: 0.5 }}
        >
          <div className=" flex flex-col justify-center items-center w-auto my-auto p-[3rem] rounded shadow-lg bg-[white] opacity-95 ">
            <img
              src={logo}
              alt=""
              className="w-[150px] mb-[2rem]"
            />
            <h1 className="text-4xl font-medium mb-[1rem]">Sign In</h1>
            <form action="" className="flex flex-col">
              <label htmlFor="" className="justify-items-start mb-2 text-xl">
                Username/Email
              </label>
              <input
                type="text"
                placeholder="Username..."
                className="input input-bordered w-[400px] bg-slate-100 mb-[1rem]"
              />
              <label htmlFor="" className="justify-items-start mb-2 text-xl">
                Password
              </label>
              <input
                type="password"
                placeholder="Password..."
                className="input input-bordered w-[400px] bg-slate-100 mb-[0.5rem]"
              />
              <div className="mb-[3rem] flex justify-between">
                <label htmlFor="">
                  Don't have an account?{" "}
                  <a
                    href="/register/user"
                    className="font-light text-[#008CFF] italic underline"
                  >
                    Sign Up
                  </a>
                </label>
                {/* <a
                  href="/recovery"
                  className="font-light text-[#008CFF] italic underline"
                >
                  Forgot password?
                </a> */}
              </div>

              <div className="flex flex-col justify-center items-center mb-4">
                <button
                  className="btn btn-success w-[200px] rounded-full text-lg text-white hover:border-4 hover:border-green-200 border-0"
                  onClick={toHome}
                >
                  Sign In
                </button>
              </div>
            </form>
          </div>
        </motion.div>
      </div>
    </>
  );
}

export default SignIn;
