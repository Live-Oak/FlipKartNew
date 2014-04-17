package edu.iiitb.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Map<String,Object> session;
	@Override
	public void setSession(Map<String, Object> session) 
	{
		// TODO Auto-generated method stub
		this.session=session;
	}
	
	public String execute()
	{
		HttpServletResponse response=null;
		 response=ServletActionContext.getResponse();
		session.remove("user");
		session.remove("email");
		return "success";
	}

}
