import React from "react";

function EditProfile() {
  return (
    <form className="p-5 round border">
      <h1 className="text-4xl">Account Details</h1>
      <div className="grid md:grid-cols-3 lg:flex space-x-1 lg:justify-center">
        {/* image div */}
        <div className="grid justify-center lg:justify-normal">
          <img
            src="https://imgs.search.brave.com/WocHJaJ_zM10htx3EFr4o3y0jtHHmeaUHz3C4wg4uio/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9zdGF0/aWMudGhlbm91bnBy/b2plY3QuY29tL3Bu/Zy81MDM0OTAxLTIw/MC5wbmc"
            alt="profile img"
            className="object-cover w-80"
          />
          <input type="file" accept="image/*" />
        </div>
        {/* details div */}
        <div className="mt-3 md:col-span-2 lg:p-4 grid justify-center">
          {/* firstname */}
          <div
            id="FirstNameBox"
            className="grid grid-cols-1 lg:grid-cols-4 mb-2"
          >
            <b>
              <h2 className="mr-1">First Name:</h2>
            </b>
            <p>fname</p>
          </div>
          {/* LastName  */}
          <div
            id="LastNameBox"
            className="grid grid-cols-1 lg:grid-cols-4 mb-2"
          >
            <b>
              <h2 className="mr-1">Last Name:</h2>
            </b>
            <p>lname</p>
          </div>
          {/* email */}
          <div id="EmailBox" className="grid grid-cols-1 lg:grid-cols-4 mb-2">
            <b>
              <h2>Email:</h2>
            </b>
            <input
              type="email"
              className="border rounded lg:col-span-3 md:w-80 lg:w-95"
            />
          </div>
          {/* password */}
          <div
            id="PasswordBox"
            className="grid grid-cols-1 lg:grid-cols-4 mb-2"
          >
            <b>
              <h2>Password:</h2>
            </b>
            <input
              type="password"
              className="border rounded lg:col-span-3 md:w-80 lg:w-95"
            />
          </div>
          {/* phone */}
          <div id="PhoneBox" className="grid grid-cols-1 lg:grid-cols-4 mb-2">
            <b>
              <h2>Phone:</h2>
            </b>
            <input
              type="text"
              className="border rounded lg:col-span-3 md:w-80 lg:w-95"
              maxLength="10"
            />
          </div>
          {/* Address */}
          <div id="Address" className="grid grid-cols-1 lg:grid-cols-4 mb-2">
            <b>
              <h2>Address:</h2>
            </b>
            <div className="lg:col-span-3 md:w-80 lg:w-95">
              <textarea className="border rounded w-full" />
              <div className="grid grid-cols-4 mb-2">
                <h3>Street:</h3>
                <input
                  type="text"
                  className="border rounded w-full col-span-3"
                />
              </div>
              <div className="grid grid-cols-4 mb-2">
                <h3>City:</h3>
                <input
                  type="text"
                  className="border rounded w-full col-span-3"
                />
              </div>
              <div className="grid grid-cols-4 mb-2">
                <h3>Province:</h3>
                <input
                  type="text"
                  className="border rounded w-full col-span-3"
                />
              </div>
              <div className="grid grid-cols-4 mb-2">
                <h3>Postal number:</h3>
                <input
                  type="text"
                  className="border rounded w-full col-span-3"
                  maxLength="5"
                />
              </div>
            </div>
          </div>
          {/* submit */}
          <button
            id="smButton"
            type="submit"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full"
          >
            update
          </button>
        </div>
      </div>
    </form>
  );
}

export default EditProfile;
