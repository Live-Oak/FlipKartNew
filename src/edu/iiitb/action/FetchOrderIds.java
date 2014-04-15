/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForAdmin;
import edu.iiitb.model.MyOrdersModel;

/**
 * @author paras
 *
 */
public class FetchOrderIds extends ActionSupport {

	ArrayList<Integer> orderId ;
	ArrayList<MyOrdersModel> orderDeatils ;
	String status , orderStatus;
	int orderID ;
	
	
	public ArrayList<Integer> getOrderId() {
		return orderId;
	}

	public void setOrderId(ArrayList<Integer> orderId) {
		this.orderId = orderId;
	}

	public ArrayList<MyOrdersModel> getOrderDeatils() {
		return orderDeatils;
	}

	public void setOrderDeatils(ArrayList<MyOrdersModel> orderDeatils) {
		this.orderDeatils = orderDeatils;
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		orderId = new ArrayList<Integer>();
		try {
			dbHandler.fetchAllPurchasedOrderID(orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of FetchOrderIds.java");
			e.printStackTrace();
			return "error";
		}
		if(status.equalsIgnoreCase("view"))
			return "success";
		else
			return "confirm";
	}
	
	public String orderDeatils()
	{
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		orderDeatils = new ArrayList<MyOrdersModel>();
		try {
			dbHandler.fetchOrderDetails(orderID , orderDeatils);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at orderDeatils() of FetchOrderIds.java");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String confirmOrder()
	{
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		dbHandler.confirmPurchaseOrder(orderID,orderStatus);
		
		addActionMessage("Order Has Been Confirmed");
		return "success";
	}
	
}
