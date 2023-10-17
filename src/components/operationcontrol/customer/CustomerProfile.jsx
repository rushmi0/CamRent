import React from 'react';
import {FaHistory} from "react-icons/fa";

const CustomerProfile = () => {
  return (
      <>
        <div className="flex bg-white mt-5 shadow-md border-2">
          <div className="md:flex w-2/5 md:w-1/4 h-screen bg-white border-r hidden pt-20">
            <div className="mx-auto py-10">
              <a href="http://localhost:5173/">
                <h1 className="text-2xl font-bold mb-10 cursor-pointer text-[#EC5252] duration-150">CamRent</h1>
              </a>

              <ul>
                <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path d="M12.075,10.812c1.358-0.853,2.242-2.507,2.242-4.037c0-2.181-1.795-4.618-4.198-4.618S5.921,4.594,5.921,6.775c0,1.53,0.884,3.185,2.242,4.037c-3.222,0.865-5.6,3.807-5.6,7.298c0,0.23,0.189,0.42,0.42,0.42h14.273c0.23,0,0.42-0.189,0.42-0.42C17.676,14.619,15.297,11.677,12.075,10.812 M6.761,6.775c0-2.162,1.773-3.778,3.358-3.778s3.359,1.616,3.359,3.778c0,2.162-1.774,3.778-3.359,3.778S6.761,8.937,6.761,6.775 M3.415,17.69c0.218-3.51,3.142-6.297,6.704-6.297c3.562,0,6.486,2.787,6.705,6.297H3.415z" />
                  </svg>
                  <a href="#" className="font-semibold">PROFILE</a>
                </li>
                <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <FaHistory/>
                  </svg>
                  <a href="/CustomerHistory" className="font-semibold">HISTORY</a>
                </li>
                <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path d="M18.303,4.742l-1.454-1.455c-0.171-0.171-0.475-0.171-0.646,0l-3.061,3.064H2.019c-0.251,0-0.457,0.205-0.457,0.456v9.578c0,0.251,0.206,0.456,0.457,0.456h13.683c0.252,0,0.457-0.205,0.457-0.456V7.533l2.144-2.146C18.481,5.208,18.483,4.917,18.303,4.742 M15.258,15.929H2.476V7.263h9.754L9.695,9.792c-0.057,0.057-0.101,0.13-0.119,0.212L9.18,11.36h-3.98c-0.251,0-0.457,0.205-0.457,0.456c0,0.253,0.205,0.456,0.457,0.456h4.336c0.023,0,0.899,0.02,1.498-0.127c0.312-0.077,0.55-0.137,0.55-0.137c0.08-0.018,0.155-0.059,0.212-0.118l3.463-3.443V15.929z M11.241,11.156l-1.078,0.267l0.267-1.076l6.097-6.091l0.808,0.808L11.241,11.156z" />
                  </svg>
                  <a href="/customerrentorders" className="font-semibold">ORDERS</a>
                </li>
                <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path d="M17.431,2.156h-3.715c-0.228,0-0.413,0.186-0.413,0.413v6.973h-2.89V6.687c0-0.229-0.186-0.413-0.413-0.413H6.285c-0.228,0-0.413,0.184-0.413,0.413v6.388H2.569c-0.227,0-0.413,0.187-0.413,0.413v3.942c0,0.228,0.186,0.413,0.413,0.413h14.862c0.228,0,0.413-0.186,0.413-0.413V2.569C17.844,2.342,17.658,2.156,17.431,2.156 M5.872,17.019h-2.89v-3.117h2.89V17.019zM9.587,17.019h-2.89V7.1h2.89V17.019z M13.303,17.019h-2.89v-6.651h2.89V17.019z M17.019,17.019h-2.891V2.982h2.891V17.019z" />
                  </svg>
                  <span className="font-semibold">DASHBOARD</span>
                </li>
                <a href="/login"><button className="w-full mt-20 bg-[#EC5252] rounded-full py-1.5 text-white pt">Sign Out</button></a>
              </ul>
            </div>
          </div>

          <main className="min-h-screen w-full bg-white border-l">
            <nav className="flex items-center justify-between px-10 bg-white py-6 border-b">
              <div>

              </div>

            </nav>
            <div className="mx-6">
              <h1 className="my-6 text-3xl">PROFILE</h1>
            </div>

            <div className="h-15 px-5">
              <div className="border-b-2 block md:flex">
                <div className="w-full md:w-2/5 p-4 sm:p-6 lg:p-8 bg-white shadow-md">
                  <div className="p-8 mx-2 flex justify-center">
                    <img id="showImage" className="max-w-xs w-41 items-center border rounded-lg" src="https://th.bing.com/th?id=OIP.oLS4rHOOEqv8160l-sfRuAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2" alt="" />
                  </div>

                  <div className="pb-6">
                    <label htmlFor="name" className="font-semibold text-gray-700 block pb-1">Description</label>
                    <div className="flex">
                      Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    </div>
                  </div>
                </div>

                <div className="max-w-2xl overflow-hidden bg-white shadow sm:rounded-lg">
                  <div className="px-4 py-5 sm:px-6">
                    <h3 className="text-lg font-medium leading-6 text-gray-900">
                      CUSTOMER INFORMATION
                    </h3>
                    <p className="max-w-2xl mt-1 text-sm text-gray-500">
                      Details and informations about customer.
                    </p>

                  </div>
                  <div className="border-t border-gray-200">
                    <dl>
                      <div className="px-4 py-5 bg-gray-50 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                        <dt className="text-sm font-medium text-gray-500">
                          Name
                        </dt>
                        <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                          DORA
                        </dd>
                      </div>
                      <div className="px-4 py-5 bg-white sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                        <dt className="text-sm font-medium text-gray-500">
                          Full name
                        </dt>
                        <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                          Tududu Dora
                        </dd>
                      </div>
                      <div className="px-4 py-5 bg-gray-50 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                        <dt className="text-sm font-medium text-gray-500">
                          Email address
                        </dt>
                        <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                          m.poul@example.com
                        </dd>
                      </div>
                      <div className="px-4 py-5 bg-white sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                        <dt className="text-sm font-medium text-gray-500">
                          Tel.
                        </dt>
                        <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                          090-090-0900
                        </dd>
                      </div>
                      <div className="px-4 py-5 bg-gray-50 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                        <dt className="text-sm font-medium text-gray-500">
                          Address
                        </dt>
                        <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                          Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                        </dd>
                      </div>
                    </dl>
                  </div>

                  <button className="px-6 py-2 mt-5 mb-5 mr-5 float-right transition ease-in duration-200 uppercase rounded-full hover:bg-red-600 hover:text-white border-2 border-red-600 focus:outline-none text-red-600">
                    <a href="/editprofile">
                      Edit
                    </a>
                  </button>

                </div>

              </div>

            </div>

          </main>
        </div>
      </>
  );
};


export default CustomerProfile;
