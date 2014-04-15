package edu.iiitb.model;

import java.sql.Date;

public class GetOrderDetailsModel {
	
	int orderNo; 
	
	float price, totalprice;
	
	Date order_date, delievry_date;
	
	String status, seller;

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

}
