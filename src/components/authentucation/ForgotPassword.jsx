import React from "react";

function ForgotPassword() {
  return (
    <>
      <section className=" bg-[url('https://images.unsplash.com/photo-1510127034890-ba27508e9f1c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80')] bg-cover bg-center bg-no-repeat">
        <div className="container mx-auto my-auto">
          <div className="grid grid-cols-2 justify-items-center h-screen my-auto">
            <div className="my-auto">
              <h1 className="text-8xl">Forgot</h1>
              <h1 className="text-8xl">Password</h1>
            </div>
            <div className="my-auto border border-rose-500 p-8 bg-slate-100 rounded">
              <div className="flex flex-col">
                <h1 className="text-2xl">Reset your password</h1>
                <p>Enter your email to reset your password.</p>
                <input
                  type="email"
                  className="input bg-white w-[300px]"
                  placeholder="Enter email"
                />
                <button className="btn btn-info px-6 w-[300px]">
                  Continue
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>

      
    </>
  );
}

export default ForgotPassword;
