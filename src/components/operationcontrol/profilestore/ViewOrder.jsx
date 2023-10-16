import React from 'react'
import Sidebar from "./Sidebar.jsx";


function ViewOrder() {
    return (
        <>
            <div className="flex bg-white mt-5 shadow-md border-2">
                <Sidebar/>

                <main className="min-h-screen w-full bg-white border-l">
                    <div className="mx-6">
                        <h1 className="my-6 text-3xl">STORE ORDERS</h1>
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
                                                    Ordered by
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
                                                             src="https://th.bing.com/th?id=OIP.oLS4rHOOEqv8160l-sfRuAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">User1</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#21394</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://img.freepik.com/premium-photo/funny-ultra-soft-photo-camera-isolated-pink-background-pastel-colors-colorful-poster-banner-cartoon-minimal-air-style-3d-illustration_76964-5273.jpg?w=2000"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Rainbow Camera</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#0001</p>
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

                                                        <h2 className="text-sm font-normal">Sent</h2>
                                                    </div>
                                                </td>

                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <a href="./CustomerDeliveryInfo"
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Info
                                                        </a>

                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Cancel
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://th.bing.com/th?id=OIP.oLS4rHOOEqv8160l-sfRuAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">User2</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#41312</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://img.freepik.com/premium-photo/funny-ultra-soft-photo-camera-isolated-pink-background-pastel-colors-colorful-poster-banner-cartoon-minimal-air-style-3d-illustration_76964-5273.jpg?w=2000"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Blur Camera</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#0002</p>
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

                                                        <h2 className="text-sm font-normal">Sending</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <a href="./CustomerDeliveryInfo"
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Info
                                                        </a>

                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Cancel
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://th.bing.com/th?id=OIP.oLS4rHOOEqv8160l-sfRuAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">User3</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#21434</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://img.freepik.com/premium-photo/funny-ultra-soft-photo-camera-isolated-pink-background-pastel-colors-colorful-poster-banner-cartoon-minimal-air-style-3d-illustration_76964-5273.jpg?w=2000"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Supan Camera</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#0003</p>
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

                                                        <h2 className="text-sm font-normal">Sent</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <a href="./CustomerDeliveryInfo"
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Info
                                                        </a>

                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Cancel
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://th.bing.com/th?id=OIP.oLS4rHOOEqv8160l-sfRuAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">User4</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#35312</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://img.freepik.com/premium-photo/funny-ultra-soft-photo-camera-isolated-pink-background-pastel-colors-colorful-poster-banner-cartoon-minimal-air-style-3d-illustration_76964-5273.jpg?w=2000"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Film Camera</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#0004</p>
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

                                                        <h2 className="text-sm font-normal">Sent</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <a href="./CustomerDeliveryInfo"
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Info
                                                        </a>

                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Cancel
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://th.bing.com/th?id=OIP.oLS4rHOOEqv8160l-sfRuAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">User5</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#51235</p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    <div className="flex items-center gap-x-2">
                                                        <img className="object-cover w-8 h-8 rounded-full"
                                                             src="https://img.freepik.com/premium-photo/funny-ultra-soft-photo-camera-isolated-pink-background-pastel-colors-colorful-poster-banner-cartoon-minimal-air-style-3d-illustration_76964-5273.jpg?w=2000"
                                                             alt=""/>
                                                        <div>
                                                            <h2 className="text-sm font-medium text-gray-800 dark:text-white ">Real Camera</h2>
                                                            <p className="text-xs font-normal text-gray-600 dark:text-gray-400">#0005</p>
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

                                                        <h2 className="text-sm font-normal">Returning</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    1771/1 ถ. พัฒนาการ แขวงสวนหลวง เขตสวนหลวง กรุงเทพมหานคร 10250
                                                </td>
                                                <td className="px-4 py-4 text-sm whitespace-nowrap">
                                                    <div className="flex items-center gap-x-6">
                                                        <a href="./CustomerDeliveryInfo"
                                                            className="text-blue-700 transition-colors duration-200 dark:hover:text-indigo-500 dark:text-gray-300 hover:text-indigo-500 focus:outline-none">
                                                            Info
                                                        </a>

                                                        <button
                                                            className="text-red-700 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                                                            Cancel
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
                                <a href="#"
                                   className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">...</a>
                                <a href="#"
                                   className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">12</a>
                                <a href="#"
                                   className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">13</a>
                                <a href="#"
                                   className="px-2 py-1 text-sm text-gray-500 rounded-md dark:hover:bg-gray-800 dark:text-gray-300 hover:bg-gray-100">14</a>
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

        </>
    );
}

export default ViewOrder