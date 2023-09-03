import React from "react";
import { motion } from "framer-motion";

// Done!
function Pagination({ postsPerPage, totalPosts, paginate }) {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++) {
    pageNumbers.push(i);
  }

  return (
    <nav className="Page navigation mb-5 flex justify-center">
      <ul className="pagination">
        <li className="page-item">
          <a href="#" className="page-link" onClick={() => paginate(1)}>
            First
          </a>
        </li>
        {pageNumbers.map((number) => (
          <>
            <li key={number} className="page-item">
              <a
                onClick={() => paginate(number)}
                href="#"
                className="page-link"
              >
                {number}
              </a>
            </li>
          </>
        ))}
        <li className="page-item">
          <a href="#" className="page-link" onClick={() => paginate(pageNumbers.length)}>
            Last
          </a>
        </li>
      </ul>
    </nav>
  );
}

export default Pagination;
