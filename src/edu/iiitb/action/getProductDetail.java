package edu.iiitb.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.ProductInfo;

public class getProductDetail  extends ActionSupport 
{
	ArrayList<ProductInfo> productinfo;
	ArrayList<String> description;
	
	public ArrayList<String> getDescription() {
		return description;
	}
	public void setDescription(ArrayList<String> Description) {
		description = Description;
	}

	private int productID;
	public ArrayList<ProductInfo> getProductinfo() {
		return productinfo;
	}
	public void setProductinfo(ArrayList<ProductInfo> productinfo) {
		this.productinfo = productinfo;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	public String execute()
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		try
		{
			productinfo = dbHandlerForUser.getproductinfo(productID);
			String[] temp = productinfo.get(0).getDescription().split(",");
			description = new ArrayList<String>();
			int i = 0;
			while(temp.length > i){
				description.add(temp[i]);
				i++;
			}
			
			for(i=0; i<productinfo.size(); i++)
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				long diff = (productinfo.get(i).getOfferValidity().getTime() - date.getTime());
				int diffDays =(int) diff / (24 * 60 * 60 * 1000);
				if (diffDays > 0)
					productinfo.get(i).setValid(diffDays);
				else
					productinfo.get(i).setValid(0);
				
				int discount = 100 - (((productinfo.get(i).getPrice()-productinfo.get(i).getOffer())*100)/productinfo.get(i).getPrice());
				productinfo.get(i).setDiscount(discount);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error Search Action "+e);
			return "error";
		}
		return "success";
	}
}
