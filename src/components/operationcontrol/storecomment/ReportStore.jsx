import React from "react";
import Popup from "reactjs-popup";

function ReportStore() {
  return (
    <Popup trigger={<button className="btn">report</button>} model nested>
      {(close) => <div></div>}
    </Popup>
  );
}

export default ReportStore;
