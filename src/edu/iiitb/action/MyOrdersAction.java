package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.database.DBHandlerForMyOrders;
import edu.iiitb.model.MyOrdersModel;

import edu.iiitb.model.User;
import edu.iiitb.model.UserEntry;

public class MyOrdersAction extends ActionSupport implements SessionAware {
	
	private Map<String, Object> session;
	
	ArrayList<MyOrdersModel> Orders;

	public ArrayList<MyOrdersModel> getOrders() {
		return Orders;
	}

	public void setOrders(ArrayList<MyOrdersModel> orders) {
		Orders = orders;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	
	public String execute()
	{
		User user1=(User) session.get("user");
		
		DBHandlerForMyOrders dbHandler = new DBHandlerForMyOrders();
		try {
			Orders = dbHandler.getmyorders(user1.getEmail());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	public String executePastorders()
	{
		User user1=(User) session.get("user");
		
		DBHandlerForMyOrders dbHandler = new DBHandlerForMyOrders();
		try {
			Orders = dbHandler.getmypastorders(user1.getEmail());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	

}