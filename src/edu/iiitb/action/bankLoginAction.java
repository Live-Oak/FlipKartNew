package edu.iiitb.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.getUserBankDetails;

public class bankLoginAction
{
	private String bankLoginId;
	private String bankPassword;
	private getUserBankDetails details;
	private String grandTotal;
	private boolean check = false; 
	private String valid = "0";
	
	public getUserBankDetails getDetails() {
		return details;
	}
	public void setDetails(getUserBankDetails details) {
		this.details = details;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}

	private String bankName;

	public String getBankLoginId() {
		return bankLoginId;
	}
	public void setBankLoginId(String bankLoginId) {
		this.bankLoginId = bankLoginId;
	}
	public String getBankPassword() {
		return bankPassword;
	}	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}	
	public String getBankName() {
		return bankName;
	}
	public void setBankPassword(String bankPassword) {
		this.bankPassword = bankPassword;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	public String execute() 
	{
		try {	
				String creditCardNumber = null;
				DBHandlerForUser db = new DBHandlerForUser();
				creditCardNumber = db.validateBankLogin(bankLoginId, bankPassword, bankName);				
				if(creditCardNumber != null)
				{	
					valid = "1";
					String balance=  db.verifyBalanceDetails(creditCardNumber, grandTotal);
					if( ( Float.parseFloat(balance) - Float.parseFloat(grandTotal)) < 0)
						valid="2";
					details = new getUserBankDetails();
					details = db.getUserBankDetail(bankLoginId);								
				return "success";
				}
				else
				{
					return "fail";
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			return "fail";			
		}		
	}
	
}
