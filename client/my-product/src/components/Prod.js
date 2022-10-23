import { useEffect, useState } from "react";
function Prod(props) {

  let tbdata = props.items.map((item, index) => {
    return (
    <tr key={index}>
      <td>{item.brandName}</td>
      <td>{item.size}</td>
      <td>{item.quantity}</td>
      <td>{item.price}</td>
      <td>{item.instock ? "Yes" : "No"}</td>
      <td><i className ="fa-solid fa-pen-to-square"></i></td>
    </tr>
  )
  })
    return ( 
      <div className="Prod">
         <div className="container">
          <table className="table">
            <thead className="table-group-divder">
              <tr>
                <th>Brand</th>              
                <th>Size</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>InStock</th>
                <th style={{width:"30px"}}></th>
              </tr>
            </thead>
            <tbody className="table-group-divder">
                {tbdata}
            </tbody>
          </table>
        </div> 
      </div>
    );
  }

  export default Prod;