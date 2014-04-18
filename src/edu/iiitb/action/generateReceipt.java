package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBConnectivity;
import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.User;
import edu.iiitb.model.customerCartDetail;
import edu.iiitb.util.SendMailSSL;

public class generateReceipt extends ActionSupport implements SessionAware 
{
	private static final long serialVersionUID = 1L;
	private int orderId;
	private Map session;
	private float  grandTotal;
	ArrayList<customerCartDetail> buyedProductList = new ArrayList<customerCartDetail> ();
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;
	
	public Map getSession() {
		return session;
	}	
	public ArrayList<customerCartDetail> getBuyedProductList() {
		return buyedProductList;
	}
	public void setBuyedProductList(ArrayList<customerCartDetail> buyedProductList) {
		this.buyedProductList = buyedProductList;
	}
	public float getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	@Override
	public void setSession(Map arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	
	public String execute() throws SQLException
	{	ArrayList<String> receipt = new ArrayList<String>();	
		orderId = (Integer) session.get("orderId");		
		DBHandlerForUser db = new DBHandlerForUser();		
		buyedProductList =  db.generateReceipt(orderId);		
		for (customerCartDetail buyedProduct : buyedProductList)
		{
			grandTotal = ( buyedProduct.getQuantity()  * Integer.parseInt( buyedProduct.getPrice() )  );
		}
		receipt = db.getUserEmailIdForOrder(orderId);
		
		SendMailSSL sm = new SendMailSSL();
		sm.sendMailReceiptGenerated(receipt.get(0), receipt.get(1), receipt.get(2), orderId);
		
		session.remove("orderId");
		session.remove("grandTotal");
		session.remove("bankName");
		return "success";
	}	
	
}
