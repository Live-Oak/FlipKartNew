package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.customerCartDetail;

public class getUserCartDetails {
	
	ArrayList<customerCartDetail> cartDetailsList = new ArrayList<customerCartDetail>();
	private String email;
	
	public ArrayList<customerCartDetail> getCartDetailsList() {
		return cartDetailsList;
	}
	public void setCartDetailsList(ArrayList<customerCartDetail> cartDetailsList) {
		this.cartDetailsList = cartDetailsList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String execute() throws SQLException
	{	
		DBHandlerForUser db = new DBHandlerForUser();		
					
		cartDetailsList = db.getCartTableDetail(email);
		return "success";
	}
}
