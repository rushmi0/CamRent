import React from 'react'

function StoreManagement() {

    return (
        <>
            <div className="flex bg-white mt-5 shadow-md border-2">
                <div className="md:flex w-2/5 md:w-1/4 h-screen bg-white border-r hidden pt-20">
                    <div className="mx-auto py-10">
                        <h1 className="text-2xl font-bold mb-10 cursor-pointer text-[#EC5252] duration-150">CamRent</h1>
                        <ul>
                            <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none"
                                     viewBox="0 0 24 24" stroke="currentColor">
                                    <path
                                        d="M12.075,10.812c1.358-0.853,2.242-2.507,2.242-4.037c0-2.181-1.795-4.618-4.198-4.618S5.921,4.594,5.921,6.775c0,1.53,0.884,3.185,2.242,4.037c-3.222,0.865-5.6,3.807-5.6,7.298c0,0.23,0.189,0.42,0.42,0.42h14.273c0.23,0,0.42-0.189,0.42-0.42C17.676,14.619,15.297,11.677,12.075,10.812 M6.761,6.775c0-2.162,1.773-3.778,3.358-3.778s3.359,1.616,3.359,3.778c0,2.162-1.774,3.778-3.359,3.778S6.761,8.937,6.761,6.775 M3.415,17.69c0.218-3.51,3.142-6.297,6.704-6.297c3.562,0,6.486,2.787,6.705,6.297H3.415z"/>
                                </svg>
                                <a href="./ProfileStore" className="font-semibold">PROFILE</a>
                            </li>
                            <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none"
                                     viewBox="0 0 24 24" stroke="currentColor">
                                    <path
                                        d="M17.638,6.181h-3.844C13.581,4.273,11.963,2.786,10,2.786c-1.962,0-3.581,1.487-3.793,3.395H2.362c-0.233,0-0.424,0.191-0.424,0.424v10.184c0,0.232,0.191,0.424,0.424,0.424h15.276c0.234,0,0.425-0.191,0.425-0.424V6.605C18.062,6.372,17.872,6.181,17.638,6.181 M13.395,9.151c0.234,0,0.425,0.191,0.425,0.424S13.629,10,13.395,10c-0.232,0-0.424-0.191-0.424-0.424S13.162,9.151,13.395,9.151 M10,3.635c1.493,0,2.729,1.109,2.936,2.546H7.064C7.271,4.744,8.506,3.635,10,3.635 M6.605,9.151c0.233,0,0.424,0.191,0.424,0.424S6.838,10,6.605,10c-0.233,0-0.424-0.191-0.424-0.424S6.372,9.151,6.605,9.151 M17.214,16.365H2.786V7.029h3.395v1.347C5.687,8.552,5.332,9.021,5.332,9.575c0,0.703,0.571,1.273,1.273,1.273c0.702,0,1.273-0.57,1.273-1.273c0-0.554-0.354-1.023-0.849-1.199V7.029h5.941v1.347c-0.495,0.176-0.849,0.645-0.849,1.199c0,0.703,0.57,1.273,1.272,1.273s1.273-0.57,1.273-1.273c0-0.554-0.354-1.023-0.849-1.199V7.029h3.395V16.365z"/>
                                </svg>
                                <a href="#" className="font-semibold">PRODUCT</a>
                            </li>
                            <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none"
                                     viewBox="0 0 24 24" stroke="currentColor">
                                    <path
                                        d="M18.303,4.742l-1.454-1.455c-0.171-0.171-0.475-0.171-0.646,0l-3.061,3.064H2.019c-0.251,0-0.457,0.205-0.457,0.456v9.578c0,0.251,0.206,0.456,0.457,0.456h13.683c0.252,0,0.457-0.205,0.457-0.456V7.533l2.144-2.146C18.481,5.208,18.483,4.917,18.303,4.742 M15.258,15.929H2.476V7.263h9.754L9.695,9.792c-0.057,0.057-0.101,0.13-0.119,0.212L9.18,11.36h-3.98c-0.251,0-0.457,0.205-0.457,0.456c0,0.253,0.205,0.456,0.457,0.456h4.336c0.023,0,0.899,0.02,1.498-0.127c0.312-0.077,0.55-0.137,0.55-0.137c0.08-0.018,0.155-0.059,0.212-0.118l3.463-3.443V15.929z M11.241,11.156l-1.078,0.267l0.267-1.076l6.097-6.091l0.808,0.808L11.241,11.156z"/>
                                </svg>
                                <a href="./ViewOrder" className="font-semibold">ORDERS</a>
                            </li>
                            <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none"
                                     viewBox="0 0 24 24" stroke="currentColor">
                                    <path
                                        d="M17.431,2.156h-3.715c-0.228,0-0.413,0.186-0.413,0.413v6.973h-2.89V6.687c0-0.229-0.186-0.413-0.413-0.413H6.285c-0.228,0-0.413,0.184-0.413,0.413v6.388H2.569c-0.227,0-0.413,0.187-0.413,0.413v3.942c0,0.228,0.186,0.413,0.413,0.413h14.862c0.228,0,0.413-0.186,0.413-0.413V2.569C17.844,2.342,17.658,2.156,17.431,2.156 M5.872,17.019h-2.89v-3.117h2.89V17.019zM9.587,17.019h-2.89V7.1h2.89V17.019z M13.303,17.019h-2.89v-6.651h2.89V17.019z M17.019,17.019h-2.891V2.982h2.891V17.019z"/>
                                </svg>
                                <span className="font-semibold">DASHBOARD</span>
                            </li>
                            <li className="flex space-x-2 mt-10 cursor-pointer hover:text-[#EC5252] duration-150">
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none"
                                     viewBox="0 0 24 24" stroke="currentColor">
                                    <path
                                        d="M14.613,10c0,0.23-0.188,0.419-0.419,0.419H10.42v3.774c0,0.23-0.189,0.42-0.42,0.42s-0.419-0.189-0.419-0.42v-3.774H5.806c-0.23,0-0.419-0.189-0.419-0.419s0.189-0.419,0.419-0.419h3.775V5.806c0-0.23,0.189-0.419,0.419-0.419s0.42,0.189,0.42,0.419v3.775h3.774C14.425,9.581,14.613,9.77,14.613,10 M17.969,10c0,4.401-3.567,7.969-7.969,7.969c-4.402,0-7.969-3.567-7.969-7.969c0-4.402,3.567-7.969,7.969-7.969C14.401,2.031,17.969,5.598,17.969,10 M17.13,10c0-3.932-3.198-7.13-7.13-7.13S2.87,6.068,2.87,10c0,3.933,3.198,7.13,7.13,7.13S17.13,13.933,17.13,10"/>
                                </svg>
                                <span className="font-semibold">ADD PRODUCT</span>
                            </li>
                            <button className="w-full mt-20 bg-[#EC5252] rounded-full py-1.5 text-white pt">Sign Out
                            </button>
                        </ul>
                    </div>
                </div>

                <main className="min-h-screen w-full bg-white border-l">
                    <nav className="flex items-center justify-between px-10 bg-white py-6 border-b">
                        <div className="flex items-center bg-gray-100 px-4 py-2 rounded-md space-x-3 w-96">
                            <input type="text" placeholder="ค้นหาร้านค้า" className="bg-gray-100 outline-none w-full"/>
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 cursor-pointer text-gray-500"
                                 fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                      d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"/>
                            </svg>
                        </div>
                        <div className="flex items-center space-x-4">
                            <img className="w-8 rounded-full"
                                 src="https://wallpapers.com/images/hd/funny-profile-picture-1l2l3tmmbobjqd53.jpg"
                                 alt="Elon Musk"/>
                            <p className="hidden md:block">Kaiwa</p>
                        </div>
                    </nav>
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
                        <button
                            className="py-2 px-4 mb-2 bg-transparent text-red-600 font-semibold border border-red-600 rounded hover:bg-red-600 hover:text-white hover:border-transparent transition ease-in duration-200">Add Product</button>
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
                                                        <input type="checkbox"
                                                               className="text-blue-500 border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700"></input>
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

                                                        <h2 className="text-sm font-normal">Renting</h2>
                                                    </div>
                                                </td>

                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    กล้องโง่ๆ สเปคกากๆ
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

                                                        <span>#0002</span>
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
                                                        className="inline-flex items-center px-3 py-1 text-red-500 rounded-full gap-x-2 bg-red-100/60 dark:bg-gray-800">
                                                        <svg width="12" height="12" viewBox="0 0 12 12" fill="none"
                                                             xmlns="http://www.w3.org/2000/svg">
                                                            <path d="M9 3L3 9M3 3L9 9" stroke="currentColor"
                                                                  strokeWidth="1.5" strokeLinecap="round"
                                                                  strokeLinejoin="round"/>
                                                        </svg>

                                                        <h2 className="text-sm font-normal">Cancelled</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">หน้าเบลอ หลังเบลอ
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

                                                        <span>#0003</span>
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

                                                        <h2 className="text-sm font-normal">Renting</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    กล้องสุภรรณ
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

                                                        <h2 className="text-sm font-normal">Rening</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    No caption
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

                                                        <h2 className="text-sm font-normal">Refunded</h2>
                                                    </div>
                                                </td>
                                                <td className="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">
                                                    กล้องดีๆสำหรับคนเทสดี
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