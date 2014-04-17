package edu.iiitb.action;

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

public class cardPayment  extends ActionSupport implements SessionAware , ServletRequestAware , ServletResponseAware
{
	private String cardNumber;
	private String expireMonth;
	private String expireYear;
	private String cvv;	
	private String grandTotal;
	private String bankName;
	private String userEmail;
	private String email;
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;
	private Map session;
	private String valid; 
	Date paymentDate = new Date ();
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpireMonth() {
		return expireMonth;
	}
	public void setExpireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
	}
	public String getExpireYear() {
		return expireYear;
	}
	public void setExpireYear(String expireYear) {
		this.expireYear = expireYear;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Map getSession() {
		return session;
	}
	public void setSession(Map session) {
		this.session = session;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Override
	public void setServletResponse(HttpServletResponse servletResponse)
	{
		// TODO Auto-generated method stub
		this.servletResponse = servletResponse;		
	}
	@Override
	public void setServletRequest(HttpServletRequest servletRequest) 
	{
		// TODO Auto-generated method stub
		this.servletRequest = servletRequest;		
	}
	
	public String execute() throws SQLException
	{
		DBHandlerForUser db = new DBHandlerForUser();		
		System.out.println("Paras");
		bankName = db.verifyCardDetails(cardNumber, expireMonth, expireYear, cvv);			
		session.put("bankName", bankName);
		System.out.println("Catch");
		if ( bankName != null)
		{	
			valid = "1";
		}
		return "success";
	}

	public String makeCreditCardPayment() 
	{		

		DBHandlerForUser db = new DBHandlerForUser();		
		String orderId =  String.valueOf(session.get("orderId"));
		String grandTotal = String.valueOf(session.get("grandTotal"));
		String bankName = String.valueOf(session.get("bankName"));		

		try {
			db.insertOrderPaymentDetails(orderId, bankName, grandTotal);	
			db.updatePaymentInAccount(cardNumber, grandTotal);
			db.updateProductQuantityAfterPurchase(orderId);
			db.updateProductQuantityAfterPurchase(orderId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	 	
		return "success";
	}
	public String clearUserCartDetail() throws NumberFormatException, SQLException
	{
		User user = (User) session.get("user");			
		if ( user!=null )			
		{			
			userEmail = (String)user.getEmail();
		}
		if(userEmail != null)
			{ 
			DBHandlerForUser db = new DBHandlerForUser();
				db.clearUserCart(userEmail);
			}
			else
			{
				try {
					String content = null;
					
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
							cookie.getProductList().clear();
							content = JSONUtil.serialize(cookie);
							c.setValue(content);
							c.setMaxAge(60*60*24*2);
							servletResponse.addCookie(c);
							break;
						}
					}				

				} catch (Exception e) {
				e.printStackTrace();
			}
		}			
		return "success";		
	}
}
