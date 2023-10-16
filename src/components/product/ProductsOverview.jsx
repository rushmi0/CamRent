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
                                src="https://images.unsplash.com/photo-1510127034890-ba27508e9f1c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"
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
                                src="https://images.unsplash.com/photo-1494168684379-764b46e4be02?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2046&q=80"
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
                                src="https://images.unsplash.com/photo-1495707902641-75cac588d2e9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80"
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
                                    Nikon Z7
                                </h1>

                                <div className="space-y-6 mt-7">
                                    <p className="text-base text-gray-900">
                                        Nikon Z7
                                        เรือธงใหม่ของค่ายนิคอนที่เป็นกล้องฟูลเฟรมมิลเอลร์เลสรุ่นแรกของค่ายที่มาพร้อมกับ
                                        Z6
                                        ที่มีสเปคเป็นรองกว่าพี่ใหญ่ทั้งจุดโฟกัสและรายละเอียดเล็กๆน้อยรวมถึงราคาที่ห่างกัน
                                        เพื่อเป็นการทำความรู้จักกับ Z7 ให้ดีมากขึ้น
                                        ลองไปดูรายละเอียดกันว่าจุดเด่นจุดแข็งของพี่ใหญ่คนนี้เป็นอย่างไร
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
                            ราคาเฉพาะบอดี้ 170,000 บาท
                          </span>
                                                </li>
                                                <li className="text-gray-400">
                          <span className="text-gray-600">
                            45.7 ล้านพิคเซล เซนเซอร์ฟูลเฟรม CMOS
                          </span>
                                                </li>
                                                <li className="text-gray-400">
                          <span className="text-gray-600">
                            จุดโฟกัส 493 จุด ระบบ Hybrid AF
                          </span>
                                                </li>
                                                <li className="text-gray-400">
                          <span className="text-gray-600">
                            ถ่ายภาพต่อเนื่องได้ 9 fps
                          </span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <div className="flex">
                                        <button className="bg-[#5a6680] px-6 py-3 text-lg font-medium text-white">RENT</button>
                                        <button className="ml-2 bg-[#5a6680] px-6 py-3 text-lg font-medium text-white">Add to cart</button>
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
