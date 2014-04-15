package edu.iiitb.model;

import java.sql.Date;

public class MyOrdersModel {
	
	int oredrNo, quantity;
	
	Date order_date, delievry_date;
	
	String prodName, photo, status;
	
	float price, totalprice;
	
	
	public float getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getOredrNo() {
		return oredrNo;
	}
	public void setOredrNo(int oredrNo) {
		this.oredrNo = oredrNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Date getDelievry_date() {
		return delievry_date;
	}
	public void setDelievry_date(Date delievry_date) {
		this.delievry_date = delievry_date;
	}
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
