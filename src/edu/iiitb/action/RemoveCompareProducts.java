package edu.iiitb.action;
import java.sql.SQLException;
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
import javax.servlet.http.Cookie;
import org.apache.struts2.json.JSONPopulator;
import org.apache.struts2.json.JSONUtil;

import edu.iiitb.database.DBHandlerforComparison;
import edu.iiitb.model.CompareCartCookie;
import edu.iiitb.model.CompareCartProduct;

public class RemoveCompareProducts implements ServletResponseAware, ServletRequestAware 
{
	private HttpServletResponse servletResponse;
	private HttpServletRequest servletRequest;
	

	public void setServletRequest(HttpServletRequest servletRequest) {
		// TODO Auto-generated method stub
		this.servletRequest = servletRequest;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		// TODO Auto-generated method stub
		this.servletResponse = servletResponse;
	}

	public String execute()
	{
		System.out.println("i m in remove all products ");
		try {

			String content = null;
			for (Cookie c : servletRequest.getCookies()) {
				if (c.getName().equals("comparecart")) {
					content = c.getValue();
					CompareCartCookie cookie = new CompareCartCookie();
					 JSONPopulator pop = new JSONPopulator();
					Map< ?, ?> map = (Map< ?, ?>)	JSONUtil
							.deserialize(content);
					 pop.populateObject(cookie, map);
//					cookie.getProductList().removeAll(new CompareCartProduct());
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


		return "success";
	}
}
