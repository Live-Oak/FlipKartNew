package edu.iiitb.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.ProductInfo;

public class getProductList  extends ActionSupport 
{

	ArrayList<ProductInfo> productinfofilter;
	private String category;
	private int count, countprice;
	private String brand, price;
	private String[] brandnames;
	private String[] pricelist;
	private String categoryName;
	ArrayList<String> categoryList, categoryListtemp;
	
	public int getCountprice() {
		return countprice;
	}
	public void setCountprice(int countprice) {
		this.countprice = countprice;
	}
	public ArrayList<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList<String> categoryList) {
		this.categoryList = categoryList;
	}
	public ArrayList<String> getCategoryListtemp() {
		return categoryListtemp;
	}
	public void setCategoryListtemp(ArrayList<String> categoryListtemp) {
		this.categoryListtemp = categoryListtemp;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String[] getPricelist() {
		return pricelist;
	}
	public void setPricelist(String[] pricelist) {
		this.pricelist = pricelist;
	}
	public int getCount() {
		return count;
	}
	public String[] getBrandnames() {
		return brandnames;
	}
	public void setBrandnames(String[] brandnames) {
		this.brandnames = brandnames;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getCategory() {
		return category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public ArrayList<ProductInfo> getProductinfofilter() {
		return productinfofilter;
	}
	public void setProductinfofilter(ArrayList<ProductInfo> productinfofilter) {
		this.productinfofilter = productinfofilter;
	}
	
	
	public String getProductDetailcombined()
	{
		categoryList = new ArrayList<String>();
		categoryListtemp = new ArrayList<String>();
		
		if(brand != null)
			brandnames = brand.split(",");
		if(price != null)
			pricelist = price.split(",");
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		try{
			category = dbHandlerForUser.getidonname(categoryName);
			// get id on category
			
			categoryList.add(category);
			// add the first value to list
			
			categoryListtemp = dbHandlerForUser.getCategoryList(category);
			// get the sub category list for the first time
			
			
			if(categoryName.equalsIgnoreCase("Men") || categoryName.equalsIgnoreCase("Women"))
			{
				int count = categoryList.size();
				//System.out.println("Count is " +count);
				for(int i=1; i<count; i++)
				{
					categoryListtemp = dbHandlerForUser.getCategoryListwithcategory(categoryList.get(i), categoryName);
					if(categoryListtemp.size() > 0)
						categoryList.add(categoryListtemp.get(0));
				}
				// getting value for the level where we have to decide the path
				// adding it to the main side again
				
				// get the sub-sub category list if present
				for(int i=count-1; i<categoryList.size(); i++)
				{
					//System.out.println("It is here");
					categoryListtemp = dbHandlerForUser.getCategoryList(categoryList.get(i));
					if(categoryListtemp.size() > 0)
					{
						for(int j=0; j<categoryListtemp.size(); j++)
						{
							// add it to the main list
							categoryList.add(categoryListtemp.get(j));
						}
					}
				}
			}
			else
			{
				for(int i=0; i<categoryListtemp.size(); i++)
				{
					//System.out.println("value in category list is : " + categoryList.get(i));
					categoryList.add(categoryListtemp.get(i));
				}
				// add it to the main list
				for(int i=0; i<categoryList.size()-1; i++)
				{
					categoryListtemp = dbHandlerForUser.getCategoryList(categoryList.get(i+1));
					if(categoryListtemp.size() > 0)	
					{
						for(int j=0; j<categoryListtemp.size(); j++)
						{
							// add it to the main list
							categoryList.add(categoryListtemp.get(j));
						}
					}
				}
			}
			
			productinfofilter = dbHandlerForUser.getproductlistoncategoryfilter(brandnames,pricelist,categoryList,count,countprice);
			for(int i=0; i<productinfofilter.size(); i++)
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				long diff = (productinfofilter.get(i).getOfferValidity().getTime() - date.getTime());
				int diffDays =(int) diff / (24 * 60 * 60 * 1000);
				if (diffDays > 0)
					productinfofilter.get(i).setValid(diffDays);
				else
					productinfofilter.get(i).setValid(0);
				
				int discount = 100 - (((productinfofilter.get(i).getPrice()-productinfofilter.get(i).getOffer())*100)/productinfofilter.get(i).getPrice());
				productinfofilter.get(i).setDiscount(discount);
			}
			// calculating offer valid and discount
		}
		catch(Exception e)
		{
			System.out.println("Error Search Action "+e);
			return "error";
		}
		return "success";
	}
}
