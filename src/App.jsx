import { BrowserRouter, Route, Routes } from "react-router-dom";

import "./App.css";
import RootLayout from "./components/RootLayout";
import Home from "./page/Home";
import Shop from "./components/shop/Shop"
import ProfileStore from "./components/operationcontrol/profilestore/ProfileStore.jsx";
import ViewOrder from "./components/operationcontrol/profilestore/ViewOrder.jsx";
import StoreManagement from "./components/operationcontrol/profilestore/StoreManagement.jsx";
import EditStoreProfile from "./components/operationcontrol/profilestore/EditStoreProfile.jsx";
import ChargeFine from "./components/operationcontrol/profilestore/ChargeFine.jsx";
import CustomerDeliveryInfo from "./components/operationcontrol/profilestore/CustomerDeliveryInfo.jsx";

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
            <Route path="/ProfileStore" element={<ProfileStore />}></Route>
            <Route path="/ViewOrder" element={<ViewOrder />}></Route>
            <Route path="/StoreManagement" element={<StoreManagement />}></Route>
            <Route path="/EditStoreProfile" element={<EditStoreProfile />}></Route>
            <Route path="/ChargeFine" element={<ChargeFine />}></Route>
            <Route path="/CustomerDeliveryInfo" element={<CustomerDeliveryInfo />}></Route>


          </Route>
          {/* RootLayout-end */}

          {/* Login and Register below*/}
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
