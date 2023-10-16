import { BrowserRouter, Route, Routes } from "react-router-dom";

import "./App.css";
import RootLayout from "./components/RootLayout";
import Home from "./page/Home";
import Shop from "./components/shop/Shop"
import ProductsDetail from "./components/product/ProductsDetail.jsx";
import ProductsOverview from "./components/product/ProductsOverview.jsx";
import CustomerProfile from "./components/operationcontrol/customer/CustomerProfile.jsx";
import PersonalProfile from "./components/operationcontrol/customer/CustomerProfile.jsx";

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
            <Route path="/detail" element={<ProductsOverview />}></Route>
            <Route path="/customerprofile" element={<CustomerProfile/>}></Route>

          </Route>
          {/* RootLayout-end */}

          {/* Login and Register below*/}
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
