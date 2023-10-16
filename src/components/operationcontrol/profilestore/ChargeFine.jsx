import React from 'react'
import Sidebar from "./Sidebar.jsx";

function ChargeFine() {
  return (
      <>
          <img alt="banner" src="https://media.istockphoto.com/id/1176705356/photo/banner-camera-lens-with-blue-light-side-view-of-the-lens-of-camera-on-blue-background-camera.jpg?s=170667a&w=0&k=20&c=Fy8HewOTgJ6c41QRynsskhwZjr2E32qbzv2pQuz31GE=" className="object-cover w-full max-h-40"/>
          <div className="flex bg-white mt-5 shadow-md border-2 mb-5 pb-5 items-center min-h-screen">
              <Sidebar/>

              <main className=" w-full bg-white border-l ">
                  <div className="mx-6 flex justify-center">
                      <h1 className="my-6 text-3xl">กรอกข้อมูลรายละเอียดของสินค้าที่เสียหายเพื่อเป็นหลักฐาน</h1>
                  </div>

                  <section className="min-h-screen bg-white-100/50">
                      <form className="container max-w-2xl mx-auto shadow-md md:w-3/4">
                          <div className="p-4 border-t-2 border-indigo-400 rounded-lg bg-gray-100/5">
                              <div className="max-w-sm mx-auto md:w-full md:mx-0">
                                  <div className="inline-flex items-center space-x-4">
                                      <div className="ml-3">อัพโหลด</div>
                                      <input className="items-end" type="file" id="avatar" name="avatar" accept="image/png, image/jpeg" />
                                  </div>
                              </div>
                          </div>
                          <hr/>
                          <div className="space-y-6 bg-white">
                              <div className="items-center w-full p-4 space-y-4 text-gray-500 md:inline-flex md:space-y-0">
                                  <h2 className="max-w-sm mx-auto md:w-1/3">
                                      ข้อมูลผู้โดนปรับ
                                  </h2>
                                  <div className="max-w-sm mx-auto md:w-2/3">
                                      <div className=" relative ">
                                          ชื่อ-นามสกุล
                                          <input type="text" id="store-name" className="mb-4 rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="ใส่ชื่อ"/>
                                      </div>
                                      <div className=" relative ">
                                          ที่อยู่ผู้โดนปรับ
                                          <input type="text" id="address" className="mb-3 rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="ใส่ที่อยู่"/>
                                      </div>
                                      <div className=" relative ">
                                          เบอร์ติดต่อ
                                          <input type="text" id="address" className="rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="ใส่เบอร์ติดต่อ"/>
                                      </div>
                                  </div>
                              </div>
                              <hr/>
                              <div className="items-center w-full p-4 space-y-4 text-gray-500 md:inline-flex md:space-y-0">
                                  <h2 className="max-w-sm mx-auto md:w-1/3">
                                      รายละเอียดสินค้าที่เสียหาย
                                  </h2>
                                  <div className="max-w-sm mx-auto space-y-5 md:w-2/3">
                                      <div>
                                          ชื่อรุ่น
                                          <div className=" relative ">
                                              <input type="text" id="user-info-phone" className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="ใส่ชื่อของกล้องที่เสียหาย"/>
                                          </div>
                                      </div>
                                      <div>
                                          วันที่เสียหาย
                                          <div className=" relative ">
                                              <input type="date" id="date" className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="ใส่วันที่เสียหาย"/>
                                          </div>
                                      </div>
                                      <div>
                                          รายละเอียดเพิ่มเติม
                                          <div className=" relative ">
                                              <input type="text" id="user-info-phone" className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="ใส่รายละเอียดเพิ่มเติม"/>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                              <hr/>
                              <div className="items-center w-full p-8 space-y-4 text-gray-500 md:inline-flex md:space-y-0">
                                  <h2 className="max-w-sm mx-auto md:w-4/12">
                                      ใส่ค่าปรับ ฿
                                  </h2>
                                  <div className="max-w-sm mx-auto space-y-5 md:w-2/3">
                                      <div>
                                          <div className=" relative ">
                                              <input type="number" id="user-info-password" className=" rounded-lg border-transparent flex-1 appearance-none border border-gray-300 w-full py-2 px-4 bg-white text-gray-700 placeholder-gray-400 shadow-md text-base focus:outline-none focus:ring-2 focus:ring-purple-600 focus:border-transparent" placeholder="ราคา"/>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                              <hr/>
                              <div className="w-full px-4 pb-4 ml-auto text-gray-500 md:w-1/3">
                                  <button type="submit" className="py-2 px-4  bg-blue-600 hover:bg-blue-700 focus:ring-blue-500 focus:ring-offset-blue-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2  rounded-lg ">
                                      ยืนยัน
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

export default ChargeFine