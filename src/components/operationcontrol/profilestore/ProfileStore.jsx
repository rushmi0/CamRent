import React from 'react'
import Sidebar from "./Sidebar.jsx";

function ProfileStore() {

    return (
        <>
            <img alt="banner" src="https://media.istockphoto.com/id/1176705356/photo/banner-camera-lens-with-blue-light-side-view-of-the-lens-of-camera-on-blue-background-camera.jpg?s=170667a&w=0&k=20&c=Fy8HewOTgJ6c41QRynsskhwZjr2E32qbzv2pQuz31GE=" className="object-cover w-full max-h-40"/>
            <div className="flex bg-white mt-5 shadow-md border-2 mb-5 pb-5 items-center">
                <Sidebar/>

                <main className=" w-full bg-white border-l">
                    <div className="mx-6">
                        <h1 className="my-6 text-3xl">STORE PROFILE</h1>
                    </div>

                    <div className="h-15 px-5 rounded-full">
                        <div className="block md:flex rounded-full ">

                            <div className="w-full md:w-2/5 p-4 sm:p-6 lg:p-8 bg-white shadow-md rounded-2xl mr-3">
                                <div className="flex justify-between">
                                    <span className="text-xl font-semibold block">Welcome to, Tududu Dora</span>
                                </div>
                                {/*<span className="text-gray-600">Change your profile avatar here!</span>*/}
                                <div className="p-8 mx-2 flex justify-center">
                                    <img id="showImage" className="max-w-xs w-32 items-center border" src="https://wallpapers.com/images/hd/funny-profile-picture-1l2l3tmmbobjqd53.jpg" alt="" />
                                </div>

                                <div className="pb-6">
                                    <label htmlFor="name" className="font-semibold text-gray-700 block pb-1">Description</label>
                                    <div className="flex">
                                        <p className="max-w-2xl mt-1 text-sm text-gray-500">
                                            ซื้อกล้อง แถมกุ้ง
                                        </p>
                                    </div>
                                </div>
                            </div>



                            <div className="max-w-2xl overflow-hidden bg-white shadow-md sm:rounded-lg w-screen">
                                <div className="px-4 py-5 sm:px-6">
                                    <h3 className="text-lg font-medium leading-6 text-gray-900">
                                        STORE INFORMATION
                                    </h3>
                                    <p className="max-w-2xl mt-1 text-sm text-gray-500">
                                        Details and informations about store.
                                    </p>

                                </div>
                                <div className="border-t border-gray-200">
                                    <dl>
                                        <div className="px-4 py-5 bg-gray-50 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                                            <dt className="text-sm font-medium text-gray-500">
                                                Full name
                                            </dt>
                                            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">
                                                DORA DORAEMON
                                            </dd>
                                        </div>
                                        <div className="px-4 py-5 bg-white sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6">
                                            <dt className="text-sm font-medium text-gray-500">
                                                Store name
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
                                                d.dora@example.com
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
                                                ที่ไหนสักที่ ที่ไหนสักที่ ที่ไหนสักที่
                                            </dd>
                                        </div>
                                    </dl>
                                </div>

                                <a href="./EditStoreProfile" className=" px-6 py-2 mt-5 mb-5 mr-5 float-right transition ease-in duration-200 uppercase rounded-full hover:bg-red-600 hover:text-white border-2 border-red-600 focus:outline-none text-red-600">
                                    Edit
                                </a>

                            </div>

                        </div>


                    </div>

                </main>
            </div>


        </>
    );
}

export default ProfileStore