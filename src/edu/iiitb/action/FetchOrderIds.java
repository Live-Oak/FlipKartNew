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
public class FetchOrderIds {

	ArrayList<Integer> orderId ;
	
	
	
	/**
	 * @return the orderId
	 */
	public ArrayList<Integer> getOrderId() {
		return orderId;
	}



	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(ArrayList<Integer> orderId) {
		this.orderId = orderId;
	}



	public String execute()
	{
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		orderId = new ArrayList<Integer>();
		try {
			dbHandler.fetchAllPurchasedOrderID(orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of FetchOrderIds.java");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	
	
}
