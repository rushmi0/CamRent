//library
import { useState } from "react";
import { BrowserRouter, Form, Route, Routes } from "react-router-dom";

//css
import "./App.css";

//components
import Home from "./page/Home";
import RootLayout from "./components/RootLayout";
import Shop from "./page/Shop";


function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          {/* RootLayout contain navigation bar as a component to use in everypage*/}
          <Route element={<RootLayout />}>
            {/* every elements inside RootLayout will show Navigation Bar on the top of the page */}

            <Route index element={<Home />}></Route> 
            <Route path="/shop" element={<Shop />}></Route>

          </Route>
          {/* RootLayout-end */}
          
          {/* Login and Register below*/}

          
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
