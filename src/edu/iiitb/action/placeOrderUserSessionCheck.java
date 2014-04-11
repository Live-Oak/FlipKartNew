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
	
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;

	custometAddressDetail addressDetails = new custometAddressDetail();
	private Map session;
	
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
		
	public Map getSession()
	{
		return session;
	}
	public void setSession(Map session) 
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
	public String execute() throws SQLException, JSONException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IntrospectionException
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
			System.out.println("I got lucky here 1 ;)");
			try
			{
				for (Cookie c : servletRequest.getCookies()) 
				{
					System.out.println("I got lucky here 2 ;)");
					if (c.getName().equals("cart")) 
					{
						System.out.println("I got lucky here 3 ;)");
						content = c.getValue();
						CartCookie cookie = new CartCookie();						
						JSONPopulator pop = new JSONPopulator();
						Map< ?, ?> map = (Map< ?, ?>)	JSONUtil
								.deserialize(content);
						 pop.populateObject(cookie, map);		
						
						   
						 cartDetailsList = db.getCartCokkiesDetail(cookie.getProductList());
						 System.out.println("I got lucky here 4 ;)");
						break;
					}					
				}
				for (customerCartDetail cart : cartDetailsList) 
				{						
					grandTotal =  grandTotal + Float.parseFloat( cart.getSubTotal() );					
				}
				System.out.println("Cokies GrandTotal = Rs." + grandTotal);			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		return "success";
	}
	
	
	
}