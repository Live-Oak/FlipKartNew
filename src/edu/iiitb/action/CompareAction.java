package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.json.JSONPopulator;
import org.apache.struts2.json.JSONUtil;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.database.DBHandlerforComparison;
import edu.iiitb.model.CompareCartCookie;
import edu.iiitb.model.CompareProductsModel;
import edu.iiitb.model.ProductInfo;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.JSONPopulator;
import org.apache.struts2.json.JSONUtil;
import com.opensymphony.xwork2.ActionSupport;
import edu.iiitb.model.CompareCartCookie;
import edu.iiitb.model.CompareCartProduct;
import edu.iiitb.model.CompareProductsModel;
import edu.iiitb.database.DBHandlerforComparison;

public class CompareAction extends ActionSupport implements SessionAware,
ServletResponseAware, ServletRequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int productId;
	private String productname;
	private HttpServletResponse servletResponse;
	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	private HttpServletRequest servletRequest;

	String message;
	private int count;
	ArrayList<Integer> pid= new ArrayList <Integer>(); 
	ArrayList<ProductInfo> productinfo=new ArrayList<ProductInfo>();
	ArrayList<String> description;
	
	public ArrayList<String> getCategoryproducts() {
		return categoryproducts;
	}
	public void setCategoryproducts(ArrayList<String> categoryproducts) {
		this.categoryproducts = categoryproducts;
	}

	ArrayList<String> categoryproducts;
	
	public ArrayList<String> getDescription() {
		return description;
	}
	public void setDescription(ArrayList<String> Description) {
		description = Description;
	}

	ArrayList<String> categoryproductsfiltered;

	public ArrayList<String> getCategoryproductsfiltered() {
		return categoryproductsfiltered;
	}
	public void setCategoryproductsfiltered(ArrayList<String> categoryproductsfiltered) {
		this.categoryproductsfiltered = categoryproductsfiltered;
	}
	public ArrayList<ProductInfo> getProductinfo() {
		return productinfo;
	}
	public void setProductinfo(ArrayList<ProductInfo> productinfo) {
		this.productinfo = productinfo;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductID(int productId) {
		this.productId = productId;
	}
	public String execute()
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();	 
		try {
				String content = null;
				boolean cookieFound = false;
				for (Cookie c : servletRequest.getCookies()) {
					
					if (c.getName().equals("comparecart")) {
						content = c.getValue();
						CompareCartCookie cookie = new CompareCartCookie();
						 JSONPopulator pop = new JSONPopulator();
						Map< ?, ?> map = (Map< ?, ?>)	JSONUtil
								.deserialize(content);
						 pop.populateObject(cookie, map);
						 
						productinfo=dbHandlerForUser.getproductinfoforcomparison(cookie.getProductList()); 
						
						categoryproducts=dbHandlerForUser.getproductsforcomparison(cookie.getProductList());
						
						
						cookieFound = true;
						break;
					}
				}
				if(cookieFound == false)
				{
					productinfo = new ArrayList<ProductInfo>();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		setCount(productinfo.size());		
		ArrayList<String> productStringList= new ArrayList <String>();
		for(ProductInfo p : productinfo)
		{
			productStringList.add(p.getProductName());
		}

		for(ProductInfo p : productinfo)
		{
			for(int i=0;i<categoryproducts.size();i++)
			{
				if(p.getProductName().equals(categoryproducts.get(i)))
				{
					categoryproducts.remove(p.getProductName());
				}
			}
		}
		return "success";	
	}
	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		// TODO Auto-generated method stub
		this.servletRequest = servletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse servletResponse) {
		// TODO Auto-generated method stub
		this.servletResponse = servletResponse;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}