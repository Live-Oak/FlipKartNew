package edu.iiitb.action;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONPopulator;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.ActionSupport;
import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.*;

public class placeOrderUserSessionCheck extends ActionSupport implements SessionAware,
ServletResponseAware, ServletRequestAware 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private float grandTotal;
	//Map <String, Object> session1;
	
	/*public Map<String, Object> getSession1() {
		return session1;
	}
	public void setSession1(Map<String, Object> session1) {
		this.session1 = session1;
	}*/
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;

	custometAddressDetail addressDetails = new custometAddressDetail();
	Map <String, Object> session;
	
	ArrayList<customerCartDetail> cartDetailsList = new ArrayList<customerCartDetail>();
	
	public float getGrandTotal() 
	{
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) 
	{
		this.grandTotal = grandTotal;
	}
	
	public ArrayList<customerCartDetail> getCartDetailsList() 
	{
		return cartDetailsList;
	}
	public void setCartDetailsList(ArrayList<customerCartDetail> cartDetailsList) 
	{
		this.cartDetailsList = cartDetailsList;
	}
	public custometAddressDetail getAddressDetails() 
	{
		return addressDetails;
	}
	public void setCustomerAddress(custometAddressDetail addressDetails) 
	{
		this.addressDetails = addressDetails;
	}
		
	public Map<String,Object> getSession()
	{
		return session;
	}
	public void setSession(Map<String,Object> session) 
	{
		this.session = session;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}
	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	public String execute() throws SQLException, JSONException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
	IllegalArgumentException, InstantiationException, IntrospectionException
	{	
		User user;				
		if ( session.containsKey("user") )			
		{
			user = (User) session.get("user");
			DBHandlerForUser db = new DBHandlerForUser();
			addressDetails.setEmail(user.getEmail());	
			addressDetails = db.getUserAddressDetail(addressDetails.getEmail());		
			cartDetailsList = db.getCartTableDetail(addressDetails.getEmail());		
			for (customerCartDetail cart : cartDetailsList) 
			{						
				grandTotal =  grandTotal + Float.parseFloat( cart.getSubTotal() );					
			}
			System.out.println("Table GrandTotal = Rs." + grandTotal);
		}	
		else
		{			
			addressDetails = new custometAddressDetail();
			String content = null;
			DBHandlerForUser db = new DBHandlerForUser();
			
			try
			{
				for (Cookie c : servletRequest.getCookies()) 
				{
					
					if (c.getName().equals("cart")) 
					{
						
						content = c.getValue();
						CartCookie cookie = new CartCookie();						
						JSONPopulator pop = new JSONPopulator();
						Map< ?, ?> map = (Map< ?, ?>)	JSONUtil
								.deserialize(content);
						 pop.populateObject(cookie, map);							   
						 cartDetailsList = db.getCartCokkiesDetail(cookie.getProductList());						 
						break;
					}					
				}
				for (customerCartDetail cart : cartDetailsList) 
				{						
					grandTotal =  grandTotal + Float.parseFloat( cart.getSubTotal() );					
				}
					
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}			
		}
		try
		{
		session.put("grandTotal", grandTotal);
		
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("Session value in grandTotal : " +session.get("grandTotal") );		
		return "success";
	}
	
	
	
	
}