//Framework
import React, { useState, useEffect } from "react";
import { useLoaderData } from "react-router-dom";
import axios from "axios";

//Components
import ShopBanner from "../components/shopBanner";
import ShopList from "../components/ShopList";
import ShopListOne from "../components/shopliststyle/ShopListOne";
import Pagination from "../components/Pagination";


// Done!
function Shop() {
  const [data, setData] = useState([]);

  const [currentPage, setCurrentPage] = useState(1);

  const [postsPerPage] = useState(8);

  // fetch data from fakestoreapi using axios.get
  useEffect(() => {
    axios
      .get("https://fakestoreapi.com/products")
      .then((res) => setData(res.data)) //store data from fakestoreapi to data
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
  }, []);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const indexOfLastPost = currentPage * postsPerPage;

  const indexOfFirstPost = indexOfLastPost - postsPerPage;

  const currentPosts = data.slice(indexOfFirstPost, indexOfLastPost);

  return (
    <>
      <div>
        <ShopBanner />
        <div className="container mx-auto">
          <ShopList />
          <div className="m-5 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4  gap-8 mb-[2rem]">
            {/* sent id, category, image, price from data to ShopListOne Components */}
            {currentPosts.map((val) => (
              <ShopListOne
                id={val.id}
                cate={val.category}
                img={val.image}
                price={val.price}
              />
            ))}
            {/* end */}
          </div>

          {/* sent postsPerPage, totalPage, paginate to Pagination components */}

          <Pagination
            postsPerPage={postsPerPage}
            totalPosts={data.length}
            paginate={paginate}
          />
        </div>
      </div>
    </>
  );
}

export default Shop;
