import { BrowserRouter, Route, Routes } from "react-router-dom";



import "./App.css";
import RootLayout from "./components/RootLayout";
import Home from "./page/Home";

import Shop from "./page/Shop.jsx";
import Login from "./page/Login";
import Register from "./page/Register";
import ForgotPassword from "./components/authentucation/ForgotPassword";


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
          <Route path="/login" element={<Login />}></Route>
          <Route path="/register/user" element={<Register />}></Route>
          <Route path="/recovery" element={<ForgotPassword/>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;