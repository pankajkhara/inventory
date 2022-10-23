package com.product.storage;

public class Item {
	private Integer id;
	private String brandName;
	private Integer quantity;
	private Double price;
	private String size;
	private Boolean instock = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		this.instock = (quantity > 0 )? true:false;
	}

	public Boolean getInstock() {
		return instock;
	}

	public void setInstock(Boolean instock) {
		this.instock = instock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Item() {
		super();
	}

	public Item(String brandName, String size, Integer quantity, Double price) {
		super();
		this.brandName = brandName;
		this.quantity = quantity;
		this.price = price;
		this.size = size;
		this.instock = (quantity > 0 )? true:false;
	}

	public Item(Integer id, String brandName, String size, Integer quantity, Double price) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.quantity = quantity;
		this.price = price;
		this.size = size;
		this.instock = (quantity > 0 )? true:false;
	}

	@Override
	public String toString() {
		return "Item [brandName=" + brandName + ", quantity=" + quantity + ", " + "price=" + price + ", size=" + size
				+ "]";
	}

}
