package edu.iiitb.action;

import java.sql.SQLException;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import edu.iiitb.database.DBHandlerForMyOrders;
import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.User;
import edu.iiitb.model.GetOrderDetailsModel;

public class GetOrderDetails extends ActionSupport implements SessionAware{
	
	private String Email;
    int OrderId;
	String message;
	
	private Map<String, Object> session;
	
	private GetOrderDetailsModel GOD = new GetOrderDetailsModel();
	
	
	public GetOrderDetailsModel getGOD() {
		return GOD;
	}
	public void setGOD(GetOrderDetailsModel gOD) {
		GOD = gOD;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	
	public String Validate() throws SQLException
	{
		DBHandlerForMyOrders dbHandler = new DBHandlerForMyOrders();
		
		try{
		if(dbHandler.chkForEmail_OrderIdExist(getEmail(), getOrderId() ))
		{
			message = "available";
		}
		else
		{
			message = "notavailable";
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "success";
		
				
	}
	
	public String execute()
	{
		
		DBHandlerForMyOrders dbHandler = new DBHandlerForMyOrders();
		
	          try {
				dbHandler.GetOrderdetails(GOD, Email, OrderId);
				System.out.println(GOD.getQuantity());
				System.out.println(GOD.getCity());
				System.out.println(GOD.getCust_name());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		return "success";
	}
	





}
