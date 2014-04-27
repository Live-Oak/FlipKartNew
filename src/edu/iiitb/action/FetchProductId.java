package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.database.DBHandlerForAdmin;

public class FetchProductId {

	ArrayList<String> productId;
	
	/**
	 * @return the productId
	 */
	public ArrayList<String> getProductId() {
		return productId;
	}


	/**
	 * @param productId the productId to set
	 */
	public void setProductId(ArrayList<String> productId) {
		this.productId = productId;
	}
	
	public String execute()
	{
		productId = new ArrayList<String>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		
		try {
			dbHandler.fetchProductIDWithName(productId);
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of FetchProductId.java ");
			e.printStackTrace();
			return "error";
		}
	}
	
}
