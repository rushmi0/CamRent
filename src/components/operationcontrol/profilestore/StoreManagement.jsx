import React from 'react'
import Sidebar from "./Sidebar.jsx";

function StoreManagement() {

    return (
        <>
            <div className="flex bg-white mt-5 shadow-md border-2">
                <Sidebar/>

                <main className="min-h-screen w-full bg-white border-l">
                    <div className="mx-6">
                        <h1 className="my-6 text-3xl">STORE PRODUCTS</h1>
                        <div className="flex items-center bg-gray-100 px-4 py-2 rounded-md space-x-3 w-96 float-right">
                            <input type="text" placeholder="ค้นหาสินค้า" className="bg-gray-100 outline-none w-full"/>
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 cursor-pointer text-gray-500"
                                 fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                      d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"/>
                            </svg>
                        </div>
                        <a href="/AddNewProduct">
                        <button
                            className="py-2 px-4 mb-2 bg-transparent text-red-600 font-semibold border border-red-600 rounded hover:bg-red-600 hover:text-white hover:border-transparent transition ease-in duration-200">Add Product</button>
                        </a>
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
                                                    className="py-3.5 px-4 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    <div className="flex items-center gap-x-3">
                                                        <button className="flex items-center gap-x-2">
                                                            <span>Product ID</span>

                                                            <svg className="h-3" viewBox="0 0 10 11" fill="none"
                                                                 xmlns="http://www.w3.org/2000/svg">
                                                                <path
                                                                    d="M2.13347 0.0999756H2.98516L5.01902 4.79058H3.86226L3.45549 3.79907H1.63772L1.24366 4.79058H0.0996094L2.13347 0.0999756ZM2.54025 1.46012L1.96822 2.92196H3.11227L2.54025 1.46012Z"
                                                                    fill="currentColor" stroke="currentColor"
                                                                    strokeWidth="0.1"/>
                                                                <path
                                                                    d="M0.722656 9.60832L3.09974 6.78633H0.811638V5.87109H4.35819V6.78633L2.01925 9.60832H4.43446V10.5617H0.722656V9.60832Z"
                                                                    fill="currentColor" stroke="currentColor"
                                                                    strokeWidth="0.1"/>
                                                                <path
                                                                    d="M8.45558 7.25664V7.40664H8.60558H9.66065C9.72481 7.40664 9.74667 7.42274 9.75141 7.42691C9.75148 7.42808 9.75146 7.42993 9.75116 7.43262C9.75001 7.44265 9.74458 7.46304 9.72525 7.49314C9.72522 7.4932 9.72518 7.49326 9.72514 7.49332L7.86959 10.3529L7.86924 10.3534C7.83227 10.4109 7.79863 10.418 7.78568 10.418C7.77272 10.418 7.73908 10.4109 7.70211 10.3534L7.70177 10.3529L5.84621 7.49332C5.84617 7.49325 5.84612 7.49318 5.84608 7.49311C5.82677 7.46302 5.82135 7.44264 5.8202 7.43262C5.81989 7.42993 5.81987 7.42808 5.81994 7.42691C5.82469 7.42274 5.84655 7.40664 5.91071 7.40664H6.96578H7.11578V7.25664V0.633865C7.11578 0.42434 7.29014 0.249976 7.49967 0.249976H8.07169C8.28121 0.249976 8.45558 0.42434 8.45558 0.633865V7.25664Z"
                                                                    fill="currentColor" stroke="currentColor"
                                                                    strokeWidth="0.3"/>
                                                            </svg>
                                                        </button>
                                                    </div>
                                                </th>

                                                <th scope="col"
                                                    className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    Product Name
                                                </th>

                                                <th scope="col"
                                                    className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    Selling Date
                                                </th>

                                                <th scope="col"
                                                    className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    Status
                                                </th>

                                                <th scope="col"
                                                    className="px-4 py-3.5 text-sm font-normal text-left rtl:text-right text-gray-500 dark:text-gray-400">
                                                    Info
                                                </th>

                                                <th scope="col" className="relative py-3.5 px-4">
                                                    <span className="sr-only">Actions</span>
                                                </th>
                                            </tr>
                                            </thead>
                                            <tbody
                                                className="bg-white divide-y divide-gray-200 dark:divide-gray-700 dark:bg-gray-900">
                                            <tr>
                                                <td className="px-4 py-4 text-sm font-medium text-gray-700 dark:text-gray-200 whitespace-nowrap">
                                                    <div className="inline-flex items-center gap-x-3">
                                                        <input type="checkbox"
                                                               className="text-blue-500 border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700"></input>

                                                        <span>#0001</span>
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

                                                        <h2 className="text-sm font-normal">ยังอยู่</h2>
                                                    </div>
                                                </td>

                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    Full-Frame, FX Format, Mirrorless, Nikon, กล้องดิจิตอล
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <a href="/editproduct">
                                                            <button
                                                                className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                                Edit
                                                            </button>
                                                        </a>
                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Delete
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td className="px-4 py-4 text-sm font-medium text-gray-700 dark:text-gray-200 whitespace-nowrap">
                                                    <div className="inline-flex items-center gap-x-3">
                                                        <input type="checkbox"
                                                               className="text-blue-500 border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700"></input>

                                                        <span>#0002</span>
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
                                                        className="inline-flex items-center px-3 py-1 text-red-500 rounded-full gap-x-2 bg-red-100/60 dark:bg-gray-800">
                                                        <svg width="12" height="12" viewBox="0 0 12 12" fill="none"
                                                             xmlns="http://www.w3.org/2000/svg">
                                                            <path d="M9 3L3 9M3 3L9 9" stroke="currentColor"
                                                                  strokeWidth="1.5" strokeLinecap="round"
                                                                  strokeLinejoin="round"/>
                                                        </svg>

                                                        <h2 className="text-sm font-normal">กำลังปล่อยเช่า</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">Live, Micro Four Thirds, Mirrorless, Panasonic, Vlog
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <button
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Edit
                                                        </button>

                                                        <a href="/RemoveProduct"
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Delete
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td className="px-4 py-4 text-sm font-medium text-gray-700 dark:text-gray-200 whitespace-nowrap">
                                                    <div className="inline-flex items-center gap-x-3">
                                                        <input type="checkbox"
                                                               className="text-blue-500 border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700"></input>

                                                        <span>#0003</span>
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

                                                        <h2 className="text-sm font-normal">ยังอยู่</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    Micro Four Thirds, Mirrorless, Olympus, กล้องดิจิตอล
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <button
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Edit
                                                        </button>

                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Delete
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td className="px-4 py-4 text-sm font-medium text-gray-700 dark:text-gray-200 whitespace-nowrap">
                                                    <div className="inline-flex items-center gap-x-3">
                                                        <input type="checkbox"
                                                               className="text-blue-500 border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700"></input>

                                                        <span>#0004</span>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://www.ec-mall.com/wp-content/uploads/2022/08/Fujifilm-Instax-Mini-EVO-1-510x510.webp"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Fujifilm Instax Mini EVO</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">Fuji</p>
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

                                                        <h2 className="text-sm font-normal">ยังอยู่</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    Fujifilm, Instax Mini, Polaroid
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <button
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Edit
                                                        </button>

                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Delete
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td className="px-4 py-4 text-sm font-medium text-gray-700 dark:text-gray-200 whitespace-nowrap">
                                                    <div className="inline-flex items-center gap-x-3">
                                                        <input type="checkbox"
                                                               className="text-blue-500 border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700"></input>

                                                        <span>#0005</span>
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
                                                    Olympus, OM System, Tough TG-7, Waterproof, กล้องดิจิตอล
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <button
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Edit
                                                        </button>

                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Delete
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="flex items-center justify-between mt-6">
                            <a href="#"
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
                                <a href="#"
                                   className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">...</a>
                                <a href="#"
                                   className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">12</a>
                                <a href="#"
                                   className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">13</a>
                                <a href="#"
                                   className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">14</a>
                            </div>

                            <a href="#"
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
        </>

    )
}

export default StoreManagement