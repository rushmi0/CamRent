import React from "react";
import { motion } from "framer-motion";
import PasswordStrengthBar from 'react-password-strength-bar';
import { useNavigate } from "react-router-dom";
import logo from "../../assets/Cam_black2.svg";
import classes from "./SignUp.module.css";


function SignUp() {
  const navigate = useNavigate();

  const toSignIn = () => {
    navigate("/login")
  }
  return (
    <>
        <div id={classes.bgImg} className="flex justify-center items-center h-screen">
          {/* <div className={classes.bgImg}></div> */}
          <div className={classes.registerBox}>
          <img
              src={logo}
              alt=""
              className="w-[100px] mb-4"
            />
            <h1 className="text-4xl font-medium mb-[1rem]">Register</h1>
            <form>
              <h3>Username</h3>
              <input
                type="text"
                placeholder="USERNAME"
                className={classes.infoInput}
                required
              />
              <h3>Email</h3>
              <input
                type="email"
                placeholder="EMAIL"
                className={classes.infoInput}
                required
              />
              <h3>Password</h3>
              <input
                type="password"
                placeholder="PASSWORD"
                className={classes.infoInput}
                required
              />
              
              <h3>Confirm Password</h3>
              <input
                type="password"
                placeholder="CONFIRM YOUR PASSWORD"
                className={classes.infoInput}
                required
              />
              <div className="grid grid-cols-2">
                <h3>First Name</h3>
                <h3>Last Name</h3>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <input
                  type="text"
                  placeholder="FIRST NAME"
                  className={classes.infoInput}
                  required
                />
                <input
                  type="text"
                  placeholder="LAST NAME"
                  className={classes.infoInput}
                  required
                />
              </div>

              {/* <h3>Address Line 1</h3>
              <input
                type="text"
                placeholder=". . ."
                className={classes.infoInput}
                required
              />
              <h3>Address Line 2</h3>
              <input
                type="text"
                placeholder=". . ."
                className={classes.infoInput}
              />
              <h3>Town/City</h3>
              <input
                type="text"
                placeholder="TOWN/CITY"
                className={classes.infoInput}
                required
              /> */}
              <div className="grid grid-cols-2">
                {/* <h3>Post Code</h3> */}
                <h3>Phone Number</h3>
              </div>
              <div className="grid grid-cols-1 gap-4">
                {/* <input
                  type="number"
                  placeholder="POST CODE"
                  className={classes.infoInput}
                  required
                /> */}

                <input
                  type="number"
                  placeholder="+66"
                  className={classes.infoInput}
                  required
                />
              </div>
              
              <div className="flex justify-between items-center mt-5">
              <label htmlFor="" className="text-lg ">
                Already have an account?{" "}
                <a
                  href="/login"
                  className="font-light text-[#008CFF] italic underline"
                >
                  Login
                </a>
              </label>
              <button className="btn btn-success w-[200px] rounded-full text-lg text-white hover:border-4 hover:border-green-200 border-0 " onClick={toSignIn}>
                Sign Up
              </button>
              </div>
              
            </form>
          </div>
        </div>
    </>
  );
}

export default SignUp;
