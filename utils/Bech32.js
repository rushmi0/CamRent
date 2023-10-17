import React, { useState, useEffect } from "react";
import ShopBanner from "../components/shop/ShopBanner";
import ShopStyleOne from "../components/shop/ShopStyleOne";
import Pagination from "../components/shop/Pagination";
import axios from "axios";

function Shop() {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage] = useState(8);

    useEffect(() => {
        const loadDataFunction = async () => {
            try {
                const response = await axios.get("http://127.0.0.1:8080/api/v1/product");
                setData(response.data);
                setIsLoading(false);
            } catch (error) {
                console.error("Error fetching data:", error);
                setIsLoading(false);
            }
        };

        loadDataFunction();
    }, []);

    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    const currentPosts = data.slice(indexOfFirstPost, indexOfLastPost);

    return (
        <>
            <ShopBanner />
            <div className="container mx-auto">
                {isLoading ? (
                    <div className="flex flex-col justify-center items-center mb-5">
                        <h1 className="text-2xl">Loading</h1>
                        <span className="loading loading-spinner loading-lg"></span>
                    </div>
                ) : (
                    <>
                        <div className="m-5 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-8 mb-[2rem]">
                            {currentPosts.map((val) => (
                                <ShopStyleOne
                                    key={val.productID}
                                    id={val.productID}
                                    prod_name={val.productName}
                                    img={val.image1}
                                    price={val.price}
                                />
                            ))}
                        </div>
                        <Pagination
                            postsPerPage={postsPerPage}
                            totalPosts={data.length}
                            paginate={paginate}
                        />
                    </>
                )}
            </div>
        </>
    );
}

export default Shop;
