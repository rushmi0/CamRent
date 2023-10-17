import { BrowserRouter, Route, Routes } from "react-router-dom";



import "./App.css";
import RootLayout from "./components/RootLayout";
import Home from "./page/Home";

import Shop from "./page/Shop.jsx";
import Login from "./page/Login";
import Register from "./page/Register";
import ForgotPassword from "./components/authentucation/ForgotPassword";
import CustomerProfile from "./components/operationcontrol/customer/CustomerProfile.jsx";
import EditProfile from "./components/operationcontrol/customer/EditProfile.jsx";
import ProfileStore from "./components/operationcontrol/profilestore/ProfileStore.jsx";
import ViewOrder from "./components/operationcontrol/profilestore/ViewOrder.jsx";
import StoreManagement from "./components/operationcontrol/profilestore/StoreManagement.jsx";
import ChargeFine from "./components/operationcontrol/profilestore/ChargeFine.jsx";
import CustomerDeliveryInfo from "./components/operationcontrol/profilestore/CustomerDeliveryInfo.jsx";
import StoreDashboard from "./components/operationcontrol/profilestore/StoreDashboard.jsx";
import EditStoreProfile from "./components/operationcontrol/profilestore/EditStoreProfile.jsx";
import AddNewProduct from "./components/operationcontrol/productmanage/AddNewProduct.jsx";
import RemoveProduct from "./components/operationcontrol/productmanage/RemoveProduct.jsx";
import CustomerRentHistory from "./components/operationcontrol/customer/CustomerRentHistory.jsx";
import CustomerHistory from "./components/operationcontrol/customer/CustomerHistory.jsx";
import ProductsDetail from "./components/product/ProductsDetail.jsx";
import ProductsOverview from "./components/product/ProductsOverview.jsx";
import Cart from "./components/shop/Cart.jsx";
import ManageProduct from "./components/operationcontrol/productmanage/ManageProduct.jsx";


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
            <Route path="/profile" element={<CustomerProfile/>}></Route>
            <Route path="/editprofile" element={<EditProfile/>}></Route>
            <Route path="/customerrentorders" element={<CustomerRentHistory/>}></Route>
            <Route path="/customerhistory" element={<CustomerHistory/>}></Route>
            <Route path="/productdetail" element={<ProductsOverview/>}></Route>

            {/*ส่วนของเก้า*/}
            <Route path="/profilestore" element={<ProfileStore />}></Route>
            <Route path="/vieworder" element={<ViewOrder />}></Route>
            <Route path="/storemanagement" element={<StoreManagement />}></Route>
            <Route path="/editstoreprofile" element={<EditStoreProfile />}></Route>
            <Route path="/chargefine" element={<ChargeFine />}></Route>
            <Route path="/customerdeliveryinfo" element={<CustomerDeliveryInfo />}></Route>
            <Route path="/storedashboard" element={<StoreDashboard />}></Route>
            <Route path="/Cart" element={<Cart/>}></Route>
            <Route path="/editproduct" element={<ManageProduct/>}></Route>

            {/*ส่วนของนพภูมิ*/}
            <Route path="/addnewproduct" element={<AddNewProduct/>}></Route>
            <Route path="/removeproduct" element={<RemoveProduct/>}></Route>

          </Route>
          {/* RootLayout-end */}

          {/* Login and Register below*/}
          <Route path="/login" element={<Login />}></Route>
          <Route path="/register/user" element={<Register />}></Route>
          <Route path="/recovery" element={<ForgotPassword/>}></Route>

          <Route path="/profile" element={<CustomerProfile/>}></Route>
          <Route path="/editprofile" element={<EditProfile/>}></Route>
          <Route path="/customerrentorders" element={<CustomerRentHistory/>}></Route>
          <Route path="/customerhistory" element={<CustomerHistory/>}></Route>
          <Route path="/productdetail" element={<ProductsOverview/>}></Route>


          {/*ส่วนของเก้า*/}
          <Route path="/profilestore" element={<ProfileStore />}></Route>
          <Route path="/vieworder" element={<ViewOrder />}></Route>
          <Route path="/storemanagement" element={<StoreManagement />}></Route>
          <Route path="/editstoreprofile" element={<EditStoreProfile />}></Route>
          <Route path="/chargefine" element={<ChargeFine />}></Route>
          <Route path="/customerdeliveryinfo" element={<CustomerDeliveryInfo />}></Route>
          <Route path="/storedashboard" element={<StoreDashboard />}></Route>

          {/*ส่วนของนพภูมิ*/}
          <Route path="/addnewproduct" element={<AddNewProduct/>}></Route>
          <Route path="/removeproduct" element={<RemoveProduct/>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;