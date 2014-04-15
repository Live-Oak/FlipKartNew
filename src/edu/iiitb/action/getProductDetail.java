package edu.iiitb.action;

import java.util.ArrayList;

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
			//System.out.println("Productid in action : " +productID);
			productinfo = dbHandlerForUser.getproductinfo(productID);
			String[] temp = productinfo.get(0).getDescription().split(",");
			description = new ArrayList<String>();
			int i = 0;
			while(temp.length > i){
				description.add(temp[i]);
				i++;
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
