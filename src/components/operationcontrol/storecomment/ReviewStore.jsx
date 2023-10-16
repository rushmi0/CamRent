import React from "react";
import Popup from "reactjs-popup";

function ReviewStore(props) {
  const [score, setScore] = React.useState(5);
  return (
    <Popup trigger={<button className="btn m-1">review</button>} modal nested>
      {(close) => (
        <div className="bg-gray-200 p-3 rounded text-gray-900 ">
          <h1 className="text-xl mb-2">ให้คะแนนร้านค้า "{props.storename}" </h1>
          <h2 className="text-center text-2xl mb-3"> {score} </h2>
          <div className="grid justify-center">
            <div className="rating rating-lg rating-half mb-3">
              <input type="radio" name="rating-10" className="rating-hidden" />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-1"
                onClick={() => setScore(0.5)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-2"
                onClick={() => setScore(1)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-1"
                onClick={() => setScore(1.5)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-2"
                onClick={() => setScore(2)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-1"
                onClick={() => setScore(2.5)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-2"
                onClick={() => setScore(3)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-1"
                onClick={() => setScore(3.5)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-2"
                onClick={() => setScore(4)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-1"
                onClick={() => setScore(4.5)}
              />
              <input
                type="radio"
                name="rating-10"
                className="bg-orange-400 mask mask-star-2 mask-half-2"
                onClick={() => setScore(5)}
              />
            </div>
          </div>
          <div className="grid grid-cols-2">
            <button
              className="btn btn-error mr-1 btn-sm"
              onClick={() => close()}
            >
              Cancel
            </button>
            <button
              className="btn btn-success btn-sm"
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

export default ReviewStore;

//ref https://www.geeksforgeeks.org/how-to-create-popup-box-in-reactjs/
