package edu.iiitb.action;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONPopulator;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForCart;
import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.*;

public class placeOrderAction extends ActionSupport implements SessionAware,
ServletResponseAware, ServletRequestAware 
{
	private static final long serialVersionUID = 1L;

	custometAddressDetail addressDetails;
	private String userEmail;
	private String name;
	private String pinCode;
	private String city;
	private String email;
	private String phoneNumber;
	private String addressLine1;
	private String addressLine2;
	
	private int orderId =0;
	
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;
	
	private Map<String, Object> session;
	
	

	ArrayList<customerCartDetail> cartDetailsList = new ArrayList<customerCartDetail>();
    
	public ArrayList<customerCartDetail> getCartDetailsList() 
	{
		return cartDetailsList;
	}
	public void setCartDetailsList(ArrayList<customerCartDetail> cartDetailsList) 
	{
		this.cartDetailsList = cartDetailsList;
	}
			
	public Map<String, Object> getSession()
	{
		return session;
	}
	public void setSession(Map<String, Object> session) 
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public custometAddressDetail getAddressDetails() {
		return addressDetails;
	}
	public void setAddressDetails(custometAddressDetail addressDetails) {
		this.addressDetails = addressDetails;
	}

	private Map<String, Object> session1;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Map<String, Object> getSession1() {
		return session1;
	}
	public void setSession1(Map<String, Object> session1) {
		this.session1 = session1;
	}
	
	public String execute() throws SQLException, ParseException, freemarker.core.ParseException 
	{		
		addressDetails = new custometAddressDetail();
		addressDetails.setName(name);
		addressDetails.setEmail(email);
		addressDetails.setCity(city);
		addressDetails.setPinCode(pinCode);
		addressDetails.setAddressLine1(addressLine1);
		addressDetails.setAddressLine2(addressLine2);
		addressDetails.setPhoneNumber(phoneNumber);
	
		DBHandlerForUser db = new DBHandlerForUser();
		db.savePlaceOrderDetails();	
		orderId = db.saveOrderAddressDetails(addressDetails);
		session.put("orderId", orderId);
		User user = (User) session.get("user");			
		if ( user!=null )			
		{			
			userEmail = user.getEmail();
			addressDetails.setEmail(user.getEmail());	
			addressDetails = db.getUserAddressDetail(addressDetails.getEmail());
			DBHandlerForUser db2 = new DBHandlerForUser();					
			cartDetailsList = db2.getCartTableDetail(user.getEmail());			
		}	
		else
		{		
			String content = null;
			DBHandlerForUser db3 = new DBHandlerForUser();		
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
						 cartDetailsList = db3.getCartCokkiesDetail(cookie.getProductList());
						break;
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}					
		}
		
		for (customerCartDetail cart : cartDetailsList) 
		{
			db.saveUserOrderDescription(cart);	
		}	
		
		return "success";
	}
	
}
