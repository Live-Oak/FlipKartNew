package edu.iiitb.action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForUser;

public class cashOnDeliveryOderPayment  extends ActionSupport implements SessionAware , ServletRequestAware , ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;
	private Map session;
	
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
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	
	public String execute() throws SQLException
	{
		DBHandlerForUser db = new DBHandlerForUser();		
		String orderId =  String.valueOf(session.get("orderId"));
		String grandTotal = String.valueOf(session.get("grandTotal"));
		//String bankName = String.valueOf(session.get("bankName"));
		String accountNumber = "1";
		String bankName = "Flipkart";
		String paymentType = "Cash On Delivery";		
		db.insertOrderPaymentDetails(orderId, bankName, grandTotal, paymentType, accountNumber);
		db.updateProductQuantityAfterPurchase(orderId);			
		return "success";
	}

}
