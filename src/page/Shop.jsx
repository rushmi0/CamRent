import React, { useState, useEffect } from "react";
import ShopBanner from "../components/shop/ShopBanner";
import ShopList from "../components/shop/ShopList";
import ShopStyleOne from "../components/shop/ShopStyleOne";
import Pagination from "../components/shop/Pagination";
import axios from "axios";

function Shop() {
  const [data, setData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);

  const [postsPerPage] = useState(8);

  const loadDataFunction = async () => {
    try {
      await axios
        .get("http://127.0.0.1:8080/api/v1/product")
        .then((res) => {
          setData(res.data);
        }) //get data from fakestoreapi to data
        .catch(function (error) {
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
          } else if (error.request) {
            // The request was made but no response was received
            // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
            // http.ClientRequest in node.js
            console.log(error.request);
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log("Error", error.message);
          }
          console.log(error.config);
        });
      setIsLoading(true);
    } catch (err) {
      console.log(err);
    }
  };
  useEffect(() => {
    loadDataFunction();
  }, []);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const indexOfLastPost = currentPage * postsPerPage;

  const indexOfFirstPost = indexOfLastPost - postsPerPage;

  const currentPosts = data.slice(indexOfFirstPost, indexOfLastPost);

  return (
    <>
      <ShopBanner />
      <ShopList />
      <div className="container mx-auto">
        {isLoading && (
          <>
            <div className="m-5 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4  gap-8 mb-[2rem]">
              {/* sent id, category, image, price from data to ShopListOne Components */}
              {currentPosts.map((val) => (
                <ShopStyleOne
                  id={val.id}
                  prod_name={val.name}
                  img={val.img1}
                  price={val.price}
                />
              ))}
              {/* end */}
            </div>

            <Pagination
              postsPerPage={postsPerPage}
              totalPosts={data.length}
              paginate={paginate}
            />
          </>
        )}
        {!isLoading && (
          <>
            <div className="flex flex-col justify-center items-center mb-5">
              <h1 className="text-2xl">Loading</h1>
              <span className="loading loading-spinner loading-lg"></span>
            </div>
          </>
        )}
      </div>
    </>
  );
}

export default Shop;