/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.database.DBHandlerForAdmin;

/**
 * @author paras
 *
 */
public class ComplaintRegister {

	String orderId , orderStatus;
	
	
	
	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}



	public String getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	public String execute()
	{
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try {
			dbHandler.addUserComplaint(orderId , orderStatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of ComplaintRegister.java");
			e.printStackTrace();
			return "error";
		}
		
		
		return "success";
	}
	
}
