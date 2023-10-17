import React from "react";
import Popup from "reactjs-popup";

function RemoveProduct(props) {
  const [score] = React.useState();
  return (
    <Popup trigger={<button className="btn m-1">Delete product</button>} modal nested>
      {(close) => (
        <div className="bg-gray-200 p-3 rounded text-gray-900 ">
          <h1 className="text-xl mb-2">คุณจะลบสินค้านี้ใช่หรือไม่?{props.storename} </h1>
          <h2 className="text-center text-2xl mb-3"> {score} </h2>
          <div className="grid justify-center">
          </div>
          <div className="grid grid-cols-2">
            <button
              className="btn btn-error hover:bg-red-700 mr-1 btn-sm"
              onClick={() => close()}
            >
              Cancel
            </button>
            <button
              className="btn btn-success hover:bg-green-700 btn-sm"
              onClick={() => {
                close();
              }}
            >
              Confirm
            </button>
          </div>
        </div>
      )}
    </Popup>
  );
}

export default RemoveProduct;