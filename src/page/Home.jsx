import React from "react";
//components
import Homepage from "../components/homepage/Homepage";
import Feature from "../components/homepage/Feature";
import Banner from "../components/homepage/Banner";

function Home() {
  return (
    <>
      <Homepage />
      <Banner />
      <Feature/>
    </>
  );
}

export default Home;
