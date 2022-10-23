import React, { useState, useEffect, useRef } from "react";

function InForm(props) {

    const sizeOptions = [
        { value: '', text: '---Choose Size---' },
        { value: '6', text: '6' },
        { value: '7', text: '7' },
        { value: '8', text: '8' },
        { value: '9', text: '9' },
        { value: '10', text: '10' },
    ];

    const [size, setSize] = useState(sizeOptions[0].value);
    const [quantity, setQuantity] = useState(() => { return 1 });
    const [brand, setBrand] = useState("");
    const [price, setPrice] = useState(1);
    const [errmsg, setErrMsg] = useState("");
    const errors = useRef({
        brandErr: false, sizeErr: false
    });
    const [formValid, setFormValid] = useState(true);

    function handleChangeBrand(event) {
        console.log(event.target.value);
        setBrand(event.target.value)
        console.log(brand);
    }

    function handleChangePrice(event) {
        console.log(event.target.value);
        setPrice(event.target.value)
        console.log(price);
    }

    const handleChangeSize = event => {
        console.log(event.target.value);
        setSize(event.target.value);
    };

    const handleChangeQuantity = event => {
        console.log(event.target.value);
        setQuantity(event.target.value);
    };

    useEffect(() => {
        setBrand(props.item.brandName)
        setPrice(props.item.price)
        setSize(props.item.size)
        setQuantity(props.item.quantity)
    }, [props.item]);

    function validateField(fieldName, value) {
        let brandValid = errors.current.brandErr;
        let sizeValid = errors.current.sizeErr;
        console.log(fieldName + " " + value);
        switch (fieldName) {
            case 'brand':
                brandValid = value.length >= 1;
                break;
            case 'size':
                sizeValid = value.length >= 1;
                break;
            default:
                break;
        }
        errors.current = {
            brandErr: brandValid,
            sizeErr: sizeValid
        }
        validateForm()

    }

    function validateForm(prodData) {
        let msg = "";
        if (prodData.brandName.trim().length < 1) {
            msg = msg + "\nBrand name is mandatory field";
        }
        if (prodData.quantity < 1 || quantity % 1 !== 0) {
            msg = msg + "\nQuantity is mandatory field and should be whole number";
        }
        if (prodData.size.trim().length < 1) {
            msg = msg + "\nSize is mandatory field";
        }
        if (msg.length !== 0) {
            setFormValid(false)
            setErrMsg(msg)
        }
        else {
            props.getProdData(prodData)
        }
    }

    return (
        <div className="mt-0 form">
            <label htmlFor="brand" className="form-label">Brand</label>
            <div className="mb-1 input-group">
                <span className="input-group-text">
                    <i className="bi bi-award"></i>
                </span>
                <input type="text" style={{ border: (brand.trim().length > 0) ? "" : "1px solid #ff2600" }} name="brand" id="brand" value={brand} className="form-control" placeholder="Enter Brand Name" onChange={handleChangeBrand} required></input>
            </div>
            <label htmlFor="quantity" className="form-label">Quantity</label>
            <div className="mb-1 input-group">
                <span className="input-group-text">
                    <i className="bi bi-bag-plus"></i>
                </span>
                <input type="number" title="Quantity should be whole number" style={{ border: (quantity > 0 && quantity % 1 === 0) ? "" : "1px solid #ff2600" }} min="1" step="1" name="quantity" id="quantity" value={quantity} className="form-control" onChange={handleChangeQuantity}></input>
            </div>
            <label>Size</label>
            <div className="mb-1 input-group">
                <span className="input-group-text">
                    <i className="bi bi-bag-plus"></i>
                </span>
                <select className="form-select" style={{ border: (size.trim().length > 0) ? "" : "1px solid #ff2600" }} value={size} onChange={handleChangeSize}>
                    {sizeOptions.map(option => (
                        <option key={option.value} value={option.value}>
                            {option.text}
                        </option>
                    ))}
                </select>
            </div>
            <label htmlFor="price" className="form-label">Price (USD)</label>
            <div className="mb-1 input-group">
                <span className="input-group-text">
                    <i className="bi bi-currency-dollar"></i>
                </span>
                <input type="number" style={{ border: (price > 0) ? "" : "1px solid #ff2600" }} name="price" id="price" min="0" value={price} className="form-control" placeholder="Enter Price" onChange={handleChangePrice}></input>
            </div>
            <button className="mt-4 d-block mx-auto mb-4 w-50 btn btn-sm btn-primary " type="submit" onClick={event => {
                let prodData = { brandName: brand, price: price, quantity: quantity, size: size };
                validateForm(prodData);
            }}>
                Add Product
            </button>
            <div className="panel panel-default" style={{ display: !formValid ? "" : "none", backgroundColor: "#f0f0f0", color: "red" }}>
                Please enter mandatory fields
            </div>
        </div>
    );
}

export default InForm;