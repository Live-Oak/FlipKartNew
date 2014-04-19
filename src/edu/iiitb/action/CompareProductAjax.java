package edu.iiitb.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.iiitb.database.DBHandlerForUser;

import edu.iiitb.model.ProductInfo;

public class CompareProductAjax 
{
	private String productname;
	private String productId;
	private int count;
	ArrayList<ProductInfo> productInfoAdded=new ArrayList<ProductInfo>();
	private String messagestock;
	private String messageoffer;
	private String messagewarranty;
	public CompareProductAjax(String productname, ArrayList<ProductInfo> productInfoAdded)
	{
		super();
		this.setProductId(productId);
		this.setProductInfoAdded(productInfoAdded);
	}
	public CompareProductAjax() 
	{
		
	} 

	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public String getProductDetail() throws SQLException
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		setProductInfoAdded(dbHandlerForUser.getProductInfoByName(productname));
		if(productInfoAdded.get(0).getOffer()!=0)
		{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		long diff = (productInfoAdded.get(0).getOfferValidity().getTime() - date.getTime());
		int diffDays =(int) Math.ceil(diff / (24.0 * 60.0 * 60.0 * 1000.0));
		productInfoAdded.get(0).setValid(diffDays);
		}
		int discount = 100 - (((productInfoAdded.get(0).getPrice()-productInfoAdded.get(0).getOffer())*100)/productInfoAdded.get(0).getPrice());
		System.out.println("discount of staying"+discount);
		
		if(productInfoAdded.get(0).getMinimumQuantity()>productInfoAdded.get(0).getAvailableQuantity())
		{
			setMessagestock("Out of Stock");
		}
		else
		{
			setMessagestock("In Stock");			
		}
		int offerper;
		offerper=((productInfoAdded.get(0).getPrice()-productInfoAdded.get(0).getOffer())*100)/productInfoAdded.get(0).getPrice();
		if(productInfoAdded.get(0).getDiscount()==0)
		{
			setMessageoffer("No Avalilable Offers");
		}
		else
		{
			
			offerper=100-offerper;
			setMessageoffer(productInfoAdded.get(0).getDiscount()+" % off!!");			
		}
		if(productInfoAdded.get(0).getWarranty()==0)
		{
		setMessagewarranty("No Warranty");
		}
		else if(productInfoAdded.get(0).getWarranty()==1)
		{
			setMessagewarranty(Integer.toString(productInfoAdded.get(0).getWarranty())+" year");
		}
		else
		{
			setMessagewarranty(Integer.toString(productInfoAdded.get(0).getWarranty())+" years");
		}
		count=productInfoAdded.size();
		return "success";
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public ArrayList<ProductInfo> getProductInfoAdded() {
		return productInfoAdded;
	}
	public void setProductInfoAdded(ArrayList<ProductInfo> productInfoAdded) {
		this.productInfoAdded = productInfoAdded;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMessagestock() {
		return messagestock;
	}
	public void setMessagestock(String messagestock) {
		this.messagestock = messagestock;
	}
	public String getMessageoffer() {
		return messageoffer;
	}
	public void setMessageoffer(String messageoffer) {
		this.messageoffer = messageoffer;
	}
	public String getMessagewarranty() {
		return messagewarranty;
	}
	public void setMessagewarranty(String messagewarranty) {
		this.messagewarranty = messagewarranty;
	}
}
