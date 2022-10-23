import React, { useState, useEffect } from "react";
import 'bootstrap'
import InForm from "./components/InForm";
import Prod from "./components/Prod";
import logo from "./img/warehouse.svg"
import axios from "axios"
const App = props => {

  const [items, setItems] = useState([]);
  const [item, setItem] = useState({
    brandName: "",
    price: "",
    quantity: 0,
    size: ""
  });

  useEffect(() => {
    getItems()
  }, []);

  const addNewItem = async (prodData) => {

    axios.post("http://localhost:8080/items", prodData)
      .then((response) => {
        alert('Added new product item successfully')
        getItems()
        setItem({
          brandName: "",
          price: "",
          quantity: 0,
          size: ""
        })
      })
      .catch(error => {
        console.log(error.response.data);
      });
  }

  const getItems = async () => {

    axios.get("http://localhost:8080/items")
      .then((response) => {
        console.table(response.data);
        setItems(response.data)
      })
      .catch(error => {
        console.log(error.response.data);
      });
  }

  const getProdData = prodData => {
    console.log('getProdData')
    addNewItem(prodData)
    console.log(prodData)
  };

  return (
    <div className="container">
      <div className="py-1 text-center">
        <img className="d-block mx-auto mb-4" src={logo} alt="" width="72" height="57"></img>
        <h4>Inventory Management</h4>
      </div>
      <div className="row justify-content-center">
        <div className="col-lg-8">
          <InForm getProdData={getProdData} item={item} />
          <Prod items={items} />
        </div>
      </div>
    </div>
  );
};

export default App;