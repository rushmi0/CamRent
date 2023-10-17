import React from 'react'
import Sidebar from "../profilestore/Sidebar.jsx";
import {FaHistory} from "react-icons/fa";

function CustomerRentHistory() {
    return (
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
                            <a href="/profile" className="font-semibold">PROFILE</a>
                        </li>
                        <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <FaHistory/>
                            </svg>
                            <a href="/customerhistory" className="font-semibold">HISTORY</a>
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
                        <a href="/login">
                            <button className="w-full mt-20 bg-[#EC5252] rounded-full py-1.5 text-white pt">Sign Out</button>
                        </a>
                    </ul>
                </div>
            </div>

            <main className="min-h-screen w-full bg-white border-l">
                <div className="mx-6">
                    <h1 className="my-6 text-3xl">ORDERS</h1>
                </div>


                <section className="container px-4 mx-auto">
                    <div className="flex flex-col">
                        <div className="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                            <div className="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                                <div
                                    className="overflow-hidden border border-gray-200 dark:border-gray-700 md:rounded-lg">
                                    <table className="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                                        <thead className="bg-gray-50 dark:bg-gray-800">
                                        <tr>
                                            <th scope="col"
                                                className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                Ordered from
                                            </th>

                                            <th scope="col"
                                                className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                Product
                                            </th>

                                            <th scope="col"
                                                className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                Ordered Date
                                            </th>

                                            <th scope="col"
                                                className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                Status
                                            </th>

                                            <th scope="col"
                                                className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                Address
                                            </th>

                                            <th scope="col" className="relative py-3.5 px-4">
                                                <span className="sr-only">Actions</span>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody
                                            className="bg-white divide-y divide-gray-200 dark:divide-gray-700 dark:bg-gray-900">
                                        <tr>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://wallpapers.com/images/hd/funny-profile-picture-1l2l3tmmbobjqd53.jpg"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Somsri Store</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#21394</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://www.ec-mall.com/wp-content/uploads/2018/09/Nikon-Z-7-1.jpg"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Nikon Z7</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">Nikon</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                Jan 6, 2022
                                            </td>
                                            <td className="px-4 py-4 text-sm font-medium text-gray-700 whitespace-nowrap">
                                                <div
                                                    className="inline-flex items-center px-3 py-1 rounded-full gap-x-2 text-emerald-500 bg-emerald-100/60 dark:bg-gray-800">
                                                    <svg width="12" height="12" viewBox="0 0 12 12" fill="none"
                                                         xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M10 3L4.5 8.5L2 6" stroke="currentColor"
                                                              strokeWidth="1.5" strokeLinecap="round"
                                                              strokeLinejoin="round"/>
                                                    </svg>

                                                    <h2 className="text-sm font-normal">ได้รับของแล้ว</h2>
                                                </div>
                                            </td>

                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                            </td>
                                            
                                        </tr>

                                        <tr>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://wallpapers.com/images/hd/funny-profile-picture-1l2l3tmmbobjqd53.jpg"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Somsri Store</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#41312</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://www.ec-mall.com/wp-content/uploads/2019/10/Canon-EOS-1D-X-Mark-III-1-768x768.jpg"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Panasonic Lumix DC-G100</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">Panasonic</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">Jan
                                                5, 2022
                                            </td>
                                            <td className="px-4 py-4 text-sm font-medium text-gray-700 whitespace-nowrap">
                                                <div
                                                    className="inline-flex items-center px-3 py-1 text-yellow-500 rounded-full gap-x-2 bg-yellow-100/60 dark:bg-gray-800">
                                                    <svg width="10" height="10" viewBox="0 0 16 16" fill="none"
                                                         xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M14.854 4.854a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 4H3.5A2.5 2.5 0 0 0 1 6.5v8a.5.5 0 0 0 1 0v-8A1.5 1.5 0 0 1 3.5 5h9.793l-3.147 3.146a.5.5 0 0 0 .708.708l4-4z" stroke="currentColor"
                                                              strokeWidth="1.5" strokeLinecap="round"
                                                              strokeLinejoin="round"/>
                                                    </svg>

                                                    <h2 className="text-sm font-normal">ของกำลังมา</h2>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                            </td>
                                            
                                        </tr>

                                        <tr>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://wallpapers.com/images/hd/funny-profile-picture-1l2l3tmmbobjqd53.jpg"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Somsri Store</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#21434</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://www.ec-mall.com/wp-content/uploads/2020/03/Canon-EOS-850D-1-768x768.jpg"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Olympus OM System OM-5 Mirrorless</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">Olympus</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">Jan
                                                5, 2022
                                            </td>
                                            <td className="px-4 py-4 text-sm font-medium text-gray-700 whitespace-nowrap">
                                                <div
                                                    className="inline-flex items-center px-3 py-1 rounded-full gap-x-2 text-emerald-500 bg-emerald-100/60 dark:bg-gray-800">
                                                    <svg width="12" height="12" viewBox="0 0 12 12" fill="none"
                                                         xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M10 3L4.5 8.5L2 6" stroke="currentColor"
                                                              strokeWidth="1.5" strokeLinecap="round"
                                                              strokeLinejoin="round"/>
                                                    </svg>

                                                    <h2 className="text-sm font-normal">ได้รับของแล้ว</h2>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                            </td>
                                            
                                        </tr>

                                        <tr>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://wallpapers.com/images/hd/funny-profile-picture-1l2l3tmmbobjqd53.jpg"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Somsri Store</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#35312</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://www.ec-mall.com/wp-content/uploads/2022/08/Fujifilm-Instax-Mini-EVO-1-510x510.webp"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Fujifilm Instax Mini EVO</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">Fujifilm</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">Jan
                                                4, 2022
                                            </td>
                                            <td className="px-4 py-4 text-sm font-medium text-gray-700 whitespace-nowrap">
                                                <div
                                                    className="inline-flex items-center px-3 py-1 rounded-full gap-x-2 text-emerald-500 bg-emerald-100/60 dark:bg-gray-800">
                                                    <svg width="12" height="12" viewBox="0 0 12 12" fill="none"
                                                         xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M10 3L4.5 8.5L2 6" stroke="currentColor"
                                                              strokeWidth="1.5" strokeLinecap="round"
                                                              strokeLinejoin="round"/>
                                                    </svg>

                                                    <h2 className="text-sm font-normal">ได้รับของแล้ว</h2>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                            </td>
                                            
                                        </tr>

                                        <tr>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://wallpapers.com/images/hd/funny-profile-picture-1l2l3tmmbobjqd53.jpg"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Somsri Store</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#51235</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                <div className="flex items-center gap-x-2">
                                                    <img className="object-cover w-8 h-8 rounded-full"
                                                         src="https://www.ec-mall.com/wp-content/uploads/2023/09/Olympus-OM-SYSTEM-Tough-TG-7-Waterproof-Camera-6-510x510.webp"
                                                         alt=""/>
                                                    <div>
                                                        <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Olympus OM SYSTEM Tough TG-7</h2>
                                                        <p className="text-xs font-normal text-gray-600 dark:text-gray-400">Olympus</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">Jan
                                                4, 2022
                                            </td>
                                            <td className="px-4 py-4 text-sm font-medium text-gray-700 whitespace-nowrap">
                                                <div
                                                    className="inline-flex items-center px-3 py-1 text-gray-500 rounded-full gap-x-2 bg-gray-100/60 dark:bg-gray-800">
                                                    <svg width="12" height="12" viewBox="0 0 12 12" fill="none"
                                                         xmlns="http://www.w3.org/2000/svg">
                                                        <path
                                                            d="M4.5 7L2 4.5M2 4.5L4.5 2M2 4.5H8C8.53043 4.5 9.03914 4.71071 9.41421 5.08579C9.78929 5.46086 10 5.96957 10 6.5V10"
                                                            stroke="#667085" strokeWidth="1.5" strokeLinecap="round"
                                                            strokeLinejoin="round"/>
                                                    </svg>

                                                    <h2 className="text-sm font-normal">กำลังส่งคืน</h2>
                                                </div>
                                            </td>
                                            <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                            </td>
                                            
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="flex items-center justify-between mt-6">
                        <a href=""
                           className="flex items-center px-5 py-2 text-sm text-gray-700 capitalize transition-colors duration-200 bg-white border rounded-md gap-x-2 hover:bg-gray-100 dark:bg-gray-900 dark:text-gray-200 dark:border-gray-700 dark:hover:bg-gray-800">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                 strokeWidth="1.5" stroke="currentColor" className="w-5 h-5 rtl:-scale-x-100">
                                <path strokeLinecap="round" strokeLinejoin="round"
                                      d="M6.75 15.75L3 12m0 0l3.75-3.75M3 12h18"/>
                            </svg>

                            <span>previous</span>
                        </a>

                        <div className="items-center hidden md:flex gap-x-3">
                            <a href="#"
                               className="px-2 py-1 text-sm text-blue-500 rounded-md dark:bg-gray-800 bg-blue-100/60">1</a>
                            <a href="#"
                               className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">2</a>
                            <a href="#"
                               className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">3</a>
                        </div>

                        <a href=""
                           className="flex items-center px-5 py-2 text-sm text-gray-700 capitalize transition-colors duration-200 bg-white border rounded-md gap-x-2 hover:bg-gray-100 dark:bg-gray-900 dark:text-gray-200 dark:border-gray-700 dark:hover:bg-gray-800">
                            <span>Next</span>

                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                 strokeWidth="1.5" stroke="currentColor" className="w-5 h-5 rtl:-scale-x-100">
                                <path strokeLinecap="round" strokeLinejoin="round"
                                      d="M17.25 8.25L21 12m0 0l-3.75 3.75M21 12H3"/>
                            </svg>
                        </a>
                    </div>
                </section>

            </main>
        </div>
    )
}

export default CustomerRentHistory
