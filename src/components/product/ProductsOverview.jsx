import React, {useEffect} from 'react';
import ProductDetail from "./ProductsDetail.jsx";
import ProductsDetail from "./ProductsDetail.jsx";
import axios from 'axios';



const ProductsOverview = () => {
    // const [productData, setProductData] = useState(null);
    //
    // const fetchProductData = async () => {
    //     try {
    //         const userID = 1;
    //         const url = `http://127.0.0.1:8080/api/v1/customers/id/${userID}`;
    //         const response = await axios.get(url);
    //         setProductData(response.data);
    //     } catch (error) {
    //         console.error('Error fetching product data:', error);
    //     }
    // };
    //
    // useEffect(() => {
    //     fetchProductData();
    // }, []);


    return (
        <>
            <div className="container mx-auto mt-6">
                <div className="grid grid-cols-2 border border-base-500 p-3 rounded-lg">
                    <div className="carousel carousel-center rounded-box">
                        <div id="slide1" className="carousel-item relative w-full">
                            <img
                                src="https://www.jib.co.th/img_master/product/original/20180828153939_30039_24_1.png"
                                className="w-full"
                            />
                            <div
                                className="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                                <a href="#slide3" className="btn btn-circle">
                                    ❮
                                </a>
                                <a href="#slide2" className="btn btn-circle">
                                    ❯
                                </a>
                            </div>
                        </div>
                        <div id="slide2" className="carousel-item relative w-full">
                            <img
                                src="https://www.zoomcamera.net/wp-content/uploads/2019/08/Canon-PowerShot-G7-X-Mark-III-Digital-Camera-5.jpg"
                                className="w-full"
                            />
                            <div
                                className="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                                <a href="#slide1" className="btn btn-circle">
                                    ❮
                                </a>
                                <a href="#slide3" className="btn btn-circle">
                                    ❯
                                </a>
                            </div>
                        </div>
                        <div id="slide3" className="carousel-item relative w-full">
                            <img
                                src="https://www.ec-mall.com/wp-content/uploads/2019/08/Canon-PowerShot-G7-X-Mark-III_5-768x768.jpg"
                                className="w-full"
                            />
                            <div
                                className="absolute flex justify-between transform -translate-y-1/2 left-5 right-5 top-1/2">
                                <a href="#slide2" className="btn btn-circle">
                                    ❮
                                </a>
                                <a href="#slide1" className="btn btn-circle">
                                    ❯
                                </a>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div
                            className="mx-auto max-w-2xl px-4 pb-16 pt-10 sm:px-6 lg:grid lg:max-w-7xl lg:grid-cols-3 lg:grid-rows-[auto,auto,1fr] lg:gap-x-8 lg:px-8 lg:pb-24 lg:pt-16">
                            <div className="lg:col-span-2 lg:pr-8">
                                <h1 className="text-2xl font-bold tracking-tight text-gray-900 sm:text-3xl">
                                    Canon PowerShot G7X Mark III
                                </h1>

                                <div className="space-y-6 mt-7">
                                    <p className="text-base text-gray-900">
                                        Canon PowerShot G7 X Mark III ขนาดกะทัดรัดให้ความสามารถที่น่าตื่นเต้นจากบริการ Live Streaming * โดยตรงไปยัง YouTube ™ไปจนถึงการถ่ายวิดีโอแนวตั้งในมือของคุณ ความละเอียด 20.1 ล้านพิกเซล เซ็นเซอร์ CMOS  ขนาด 1.0 นิ้ว Processor DIGIC 8 ที่ทรงพลังช่วยในการสร้างคุณภาพสูง
                                    </p>

                                    <div className="mt-10">
                                        <h3 className="text-sm font-medium text-gray-900">
                                            Highlights
                                        </h3>

                                        <div className="mt-4">
                                            <ul
                                                role="list"
                                                className="list-disc space-y-2 pl-4 text-sm"
                                            >
                                                <li className="text-gray-400">
                          <span className="text-gray-600">
                            เซ็นเซอร์ CMOS Stacked ขนาด 1″ ความละเอียด 20.1 ล้านพิกเซล
                          </span>
                                                </li>
                                                <li className="text-gray-400">
                          <span className="text-gray-600">
                            DIGIC 8 Image Processor
                          </span>
                                                </li>
                                                <li className="text-gray-400">
                          <span className="text-gray-600">
                            เลนส์ซูมออปติคอล 4.2x (24-100 มม. f / 1.8-2.8)
                          </span>
                                                </li>
                                                <li className="text-gray-400">
                          <span className="text-gray-600">
                            วิดีโอ 4K 30p
                          </span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <p className="text-lg">Price: ฿ 10000/hr</p>

                                    <div className="flex">
                                        <button className="bg-[#5a6680] px-6 py-3 text-lg font-medium text-white"
                                                onClick={() => document.getElementById("my_modal_3").showModal()}
                                        >RENT</button>
                                        <dialog id="my_modal_3" className="modal">
                                            <div className="modal-box">
                                                <form>
                                                    <div className="grid grid-cols-2">
                                                        <h3>First Name</h3>
                                                        <h3>Last Name</h3>
                                                    </div>
                                                    <div className="grid grid-cols-2 gap-4">
                                                        <input
                                                            type="text"
                                                            placeholder="FIRST NAME"
                                                            className="input input-bordered w-full bg-slate-200 mb-2 p-2 xl:p-5"
                                                            required
                                                        />
                                                        <input
                                                            type="text"
                                                            placeholder="LAST NAME"
                                                            className="input input-bordered w-full bg-slate-200 mb-2 p-2 xl:p-5"
                                                            required
                                                        />
                                                    </div>

                                                    <h3>Address Line 1</h3>
                                                    <input
                                                        type="text"
                                                        placeholder=". . ."
                                                        className="input input-bordered w-full bg-slate-200 mb-2 p-2 xl:p-5"
                                                        required
                                                    />
                                                    <h3>Address Line 2</h3>
                                                    <input
                                                        type="text"
                                                        placeholder=". . ."
                                                        className="input input-bordered w-full bg-slate-200 mb-2 p-2 xl:p-5"
                                                    />
                                                    <h3>Town/City</h3>
                                                    <input
                                                        type="text"
                                                        placeholder="TOWN/CITY"
                                                        className="input input-bordered w-full bg-slate-200 mb-2 p-2 xl:p-5"
                                                        required
                                                    />
                                                    <div className="grid grid-cols-2">
                                                        <h3>Post Code</h3>
                                                        <h3>Phone Number</h3>
                                                    </div>
                                                    <div className="grid grid-cols-2 gap-4">
                                                        <input
                                                            type="number"
                                                            placeholder="POST CODE"
                                                            className="input input-bordered w-full bg-slate-200 mb-2 p-2 xl:p-5"
                                                            required
                                                        />

                                                        <input
                                                            type="number"
                                                            placeholder="+66"
                                                            className="input input-bordered w-full bg-slate-200 mb-2 p-2 xl:p-5"
                                                            required
                                                        />
                                                    </div>
                                                    <div className="flex justify-between ">
                                                        <p className="text-xl">Cash</p>
                                                        <input type="checkbox" className="toggle toggle-success" />
                                                    </div>
                                                    <div className="flex justify-between ">
                                                        <p className="text-xl">Mobile Banking</p>
                                                        <input type="checkbox" className="toggle toggle-success" />
                                                    </div>
                                                    <div className="flex justify-between ">
                                                        <p className="text-xl">Promptpay</p>
                                                        <input type="checkbox" className="toggle toggle-success" />
                                                    </div>

                                                    <div className="flex justify-center items-center mt-5">

                                                        <button className="btn btn-success w-[200px] rounded text-lg text-white hover:border-4 hover:border-green-200 border-0 "
                                                                onClick={() => {
                                                                    alert("Success!!"); window.location.href('/Customerrentorders');
                                                                } }
                                                        >

                                                            Confirm
                                                        </button>
                                                    </div>
                                                </form>
                                            </div>
                                            <form method="dialog" className="modal-backdrop">
                                                <button>close</button>
                                            </form>
                                        </dialog>
                                        <button className="ml-2 bg-[#5a6680] px-6 py-3 text-lg font-medium text-white"
                                                onClick={() => document.getElementById("my_modal_2").showModal()}
                                        >Add to cart</button>
                                        <dialog id="my_modal_2" className="modal">
                                            <div className="modal-box">
                                                <h3 className="font-bold text-lg">Added!</h3>
                                                <p className="py-4">Successfully added to cart!</p>
                                            </div>
                                            <form method="dialog" className="modal-backdrop">
                                                <button>close</button>
                                            </form>
                                        </dialog>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <ProductsDetail/>
        </>
    );
};

export default ProductsOverview;
