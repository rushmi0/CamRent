import React from 'react'
import Sidebar from "./Sidebar.jsx";

function EditStoreProfile() {
  return (
    <>
        <img alt="banner" src="https://media.istockphoto.com/id/1176705356/photo/banner-camera-lens-with-blue-light-side-view-of-the-lens-of-camera-on-blue-background-camera.jpg?s=170667a&w=0&k=20&c=Fy8HewOTgJ6c41QRynsskhwZjr2E32qbzv2pQuz31GE=" className="object-cover w-full max-h-40"/>
        <div className="flex bg-white mt-5 shadow-md border-2 mb-5 pb-5 items-center min-h-screen">
            <Sidebar/>

            <main className=" w-full bg-white border-l ">
                <div className="mx-6">
                    <h1 className="my-6 text-3xl flex justify-center">EDIT YOUR PROFILE</h1>
                </div>

                <section className="min-h-screen bg-white-100/50">
                    <form className="container max-w-2xl mx-auto shadow-md md:w-3/4">
                        <div className="p-4 border-t-2 border-indigo-400 rounded-lg bg-gray-100/5">
                            <div className="max-w-sm mx-auto md:w-full md:mx-0">
                                <div className="inline-flex items-center space-x-4">
                                    <div className="p-8 mx-2 flex justify-center">
                                        <img id="showImage" className="max-w-xs w-32 items-center border" src="https://wallpapers.com/images/hd/funny-profile-picture-1l2l3tmmbobjqd53.jpg" alt="" />
                                    </div>
                                    <input className="items-end" type="file" id="avatar" name="avatar" accept="image/png, image/jpeg" />
                                </div>
                            </div>
                        </div>
                        <hr/>
                        <div className="space-y-6 bg-white">
                            <div className="items-center w-full p-4 space-y-4 text-gray-500 md:inline-flex md:space-y-0">
                                <h2 className="max-w-sm mx-auto md:w-1/3">
                                    Store info
                                </h2>
                                <div className="max-w-sm mx-auto md:w-2/3">
                                    Store name
                                    <div className=" relative mb-4">
                                        <input type="text" id="user-info-name" className=" rounded-lg flex-1 appearance-none border border-gray-300 w-full py-2 px-4 text-gray-700 placeholder-black shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="Somsri Store" disabled/>
                                        <div className="text-red-600">*ต้องการใบรับรองจากบริษัทในการเปลี่ยนชื่อ</div>
                                    </div>
                                    <div className=" relative ">
                                        Store address
                                        <input type="text" id="address" className="rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="Input address"/>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div className="items-center w-full p-4 space-y-4 text-gray-500 md:inline-flex md:space-y-0">
                                <h2 className="max-w-sm mx-auto md:w-1/3">
                                    Personal info
                                </h2>
                                <div className="max-w-sm mx-auto space-y-5 md:w-2/3">
                                    <div>
                                        First name
                                        <div className=" relative ">
                                            <input type="text" id="user-info-name" className=" rounded-lg flex-1 appearance-none border border-gray-300 w-full py-2 px-4 text-gray-700 placeholder-black shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="Dora" disabled/>
                                        </div>
                                    </div>
                                    <div>
                                        Last name
                                        <div className=" relative ">
                                            <input type="text" id="user-info-name" className=" rounded-lg flex-1 appearance-none border border-gray-300 w-full py-2 px-4 text-gray-700 placeholder-black shadow-sm text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="Doraemon" disabled/>
                                        </div>
                                    </div>
                                    <div>
                                        Tel.
                                        <div className=" relative ">
                                            <input type="text" id="user-info-phone" className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="Phone number"/>
                                        </div>
                                    </div>
                                    <div className=" relative ">
                                        Email Address
                                        <input type="text" id="user-info-email" className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="Email"/>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div className="items-center w-full p-8 space-y-4 text-gray-500 md:inline-flex md:space-y-0">
                                <h2 className="max-w-sm mx-auto md:w-4/12">
                                    Change password
                                </h2>
                                <div className="max-w-sm mx-auto space-y-5 md:w-2/3">
                                    <div>
                                        <div className=" relative ">
                                            <input type="password" id="user-info-password" className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="Password"/>
                                        </div>
                                    </div>
                                    <div>
                                        <div className=" relative ">
                                            <input type="password" id="user-info-password" className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="Confirm password"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div className="w-full px-4 pb-4 ml-auto text-gray-500 md:w-1/3">
                                <button type="submit" className="py-2 px-4  bg-blue-600 hover:bg-blue-700 focus:ring-blue-500 focus:ring-offset-blue-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2  rounded-lg ">
                                    Save
                                </button>
                            </div>
                        </div>
                    </form>
                </section>

            </main>
        </div>

    </>

  )
}

export default EditStoreProfile