package edu.iiitb.action;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForUser;

import java.sql.SQLException;
import java.util.Date;
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

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.CartCookie;
import edu.iiitb.model.User;

public class onlineBankingPaymentAction extends ActionSupport implements SessionAware , ServletRequestAware , ServletResponseAware
{
	private String accountNumber;
	private String bankName;
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;
	private Map session;
	
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		// TODO Auto-generated method stub
		this.servletResponse = servletResponse;		
	}
	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		// TODO Auto-generated method stub
		this.servletRequest = servletRequest;
		
	}	
	public String execute()  throws SQLException
	{
		DBHandlerForUser db = new DBHandlerForUser();		
		String orderId =  String.valueOf(session.get("orderId"));
		String grandTotal = String.valueOf(session.get("grandTotal"));	
		try
		{
			String paymentType = "Internet Banking";
			db.insertOrderPaymentDetails(orderId, bankName, grandTotal, paymentType);				
			db.updatePaymentInAccount(accountNumber, grandTotal);
			db.updateProductQuantityAfterPurchase(orderId);					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	 	
		return "success";
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
