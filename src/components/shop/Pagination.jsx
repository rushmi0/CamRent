import React from "react";

// Done!
function Pagination({ postsPerPage, totalPosts, paginate }) {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++) {
    pageNumbers.push(i);
  }

  return (
    <nav className="mb-[3rem] mt-[3rem] flex justify-center ">
      <ul className="join">
      <button className="join-item btn bg-[#353535] hover:bg-white hover:text-[#353535] border-0 text-white" onClick={() => paginate(1)}>First</button>
  
        {pageNumbers.map((number) => (
          <>
            <button key={number} className="join-item btn bg-[#353535] hover:bg-white hover:text-[#353535] border-0 text-white" onClick={() => paginate(number)}>
                {number}
            </button>
          </>
        ))}
        <button className="join-item btn bg-[#353535] hover:bg-white hover:text-[#353535] border-0 text-white" onClick={() => paginate(pageNumbers.length)}>Last</button>
      </ul>
    </nav>
  );
}

export default Pagination;