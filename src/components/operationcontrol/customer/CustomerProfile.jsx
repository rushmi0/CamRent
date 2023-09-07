import React from "react";
import EditProfile from "./EditProfile";
import { useLocation } from "react-router-dom";

function CustomerProfile() {
  const location = useLocation().hash;
  return (
    <>
      <div className="mt-3 grid grid-cols-1 lg:grid-cols-5">
        <div className="mb-3 lg:mb-0 lg:ml-3 round border">
          <ul className="mx-3 text-lg">
            <a href="#">
              <li className="py-2 border-b-2">Edit profile</li>
            </a>
            <a href="#order">
              <li className="py-2 lg:border-b-2">My order history</li>
            </a>
          </ul>
        </div>
        <div className="col-span-4 xl:col-span-3 lg:ml-3">
          {location === "" ? <EditProfile /> : <>order</>}
        </div>
      </div>
    </>
  );
}

export default CustomerProfile;
