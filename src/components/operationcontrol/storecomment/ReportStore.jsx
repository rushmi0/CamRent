import React from "react";
import Popup from "reactjs-popup";

function ReportStore(porps) {
  return (
    <Popup trigger={<button className="btn m-1">report</button>} modal nested>
      {(close) => (
        <div className="grid grid-cols-2 p-2 rounded bg-gray-200 text-gray-900">
          <ul className="col-span-2 menu menu-vertical rounded text-center border border-gray-300 mb-1 p-0 grid divide-y-2 divide-gray-300">
            <button onClick={() => {}}>
              <li className="text-sm hover:bg-gray-300 p-1">test</li>
            </button>
            <button onClick={() => {}}>
              <li className="text-sm hover:bg-gray-300 p-1">test</li>
            </button>
            <button onClick={() => {}}>
              <li className="text-sm hover:bg-gray-300 p-1">test</li>
            </button>
            <button onClick={() => {}}>
              <li className="text-sm hover:bg-gray-300 p-1">test</li>
            </button>
            <button onClick={() => {}}>
              <li className="text-sm hover:bg-gray-300 p-1">test</li>
            </button>
            <button onClick={() => {}}>
              <li className="text-sm hover:bg-gray-300 p-1">test</li>
            </button>
          </ul>
          <button className="btn btn-error btn-sm" onClick={() => close()}>
            Cancel
          </button>
        </div>
      )}
    </Popup>
  );
}

export default ReportStore;
