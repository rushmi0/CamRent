import { BrowserRouter, Route, Routes } from "react-router-dom";

import "./App.css";
import RootLayout from "./components/RootLayout";
import Home from "./page/Home";
import Shop from "./components/shop/Shop";
import ReportStore from "./components/operationcontrol/storecomment/ReportStore";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          {/* RootLayout contain navigation bar as a component to use in everypage*/}
          <Route element={<RootLayout />}>
            <Route path="/test" element={ <ReportStore/> }></Route>
            {/* every elements inside RootLayout will show Navigation Bar on the top of the page */}
          </Route>
          {/* RootLayout-end */}

          {/* Login and Register below*/}
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
