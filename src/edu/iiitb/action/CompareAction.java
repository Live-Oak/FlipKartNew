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
	private ArrayList<String> categoryList, categoryListtemp;
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
	ArrayList<String> categoryproducts;
	private ArrayList<Integer> pidRetrieved= new ArrayList <Integer>();
	public ArrayList<String> getCategoryproducts() {
		return categoryproducts;
	}
	public void setCategoryproducts(ArrayList<String> categoryproducts) {
		this.categoryproducts = categoryproducts;
	}

	
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
	    String  categoryRetrieved=null;
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();	 
		try {
			    String categoryname;
				String content = null;
				boolean cookieFound = false;
				int counter;
				for (Cookie c : servletRequest.getCookies()) {
					
					if (c.getName().equals("comparecart")) {
						content = c.getValue();
						CompareCartCookie cookie = new CompareCartCookie();
						 JSONPopulator pop = new JSONPopulator();
						Map< ?, ?> map = (Map< ?, ?>)	JSONUtil
								.deserialize(content);
						 pop.populateObject(cookie, map);
						categoryRetrieved = dbHandlerForUser.getCategoryIdForRetrieval(cookie.getProductList());
						pidRetrieved = dbHandlerForUser.getProductIdForRetrieval(cookie.getProductList());
						categoryList = new ArrayList<String>();
						categoryListtemp = new ArrayList<String>();
						categoryname = dbHandlerForUser.getnameonid(categoryRetrieved);

						// get id of the product we have
			
						categoryList.add(categoryRetrieved);
						// add it to the main list
			
						categoryListtemp = dbHandlerForUser.getCategoryList(categoryRetrieved);
						// get the sub category list for the first time
			
						for(int i=0; i<categoryListtemp.size(); i++)
						{
							//System.out.println("value in category list is : " + categoryList.get(i));
							categoryList.add(categoryListtemp.get(i));
						}
						// add it to the main list
			
			
						if(categoryname.equalsIgnoreCase("Men") || categoryname.equalsIgnoreCase("Women"))
						{
							int count = categoryList.size();
							for(int i=1; i<count; i++)
							{
								categoryListtemp = dbHandlerForUser.getCategoryListwithcategory(categoryList.get(i), categoryname);
								if(categoryListtemp.size() > 0)
									categoryList.add(categoryListtemp.get(0));
							}
							// getting value for the level where we have to decide the path
							// adding it to the main side again
				
							// get the sub-sub category list if present
							for(int i=count-1; i<categoryList.size(); i++)
							{
								//System.out.println("It is here");
								categoryListtemp = dbHandlerForUser.getCategoryList(categoryList.get(i));
								if(categoryListtemp.size() > 0)
								{
									for(int j=0; j<categoryListtemp.size(); j++)
									{
										// add it to the main list
										categoryList.add(categoryListtemp.get(j));
									}
								}
							}
						}
					else
					{
						// get the sub-sub category list if present
						for(int i=0; i<categoryList.size()-1; i++)
						{
							categoryListtemp = dbHandlerForUser.getCategoryList(categoryList.get(i+1));
							if(categoryListtemp.size() > 0)
							{
								for(int j=0; j<categoryListtemp.size(); j++)
								{
									// add it to the main list
									categoryList.add(categoryListtemp.get(j));
								}
							}
						}
					}
				

								// Function to get me all sub category id
								productinfo = dbHandlerForUser.getproductinfoforcomparison(categoryList,pidRetrieved); 
								categoryproducts=dbHandlerForUser.getproductsforcomparison(categoryList);
								// To get the List of all the product and their details
						System.out.println();
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
		System.out.println("category retrieved is "+categoryRetrieved);
		setCount(productinfo.size());	
		
		for(ProductInfo p : productinfo)
		System.out.println(p.getProductID());
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
					System.out.println("inside");
					System.out.println("removed product"+p.getProductName());
					categoryproducts.remove(p.getProductName());
				}
			}
		}
		for(int i=0;i<categoryproducts.size();i++)
		{
				System.out.println("products in category product"+categoryproducts.get(i));
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
	public ArrayList<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList<String> categoryList) {
		this.categoryList = categoryList;
	}
	public ArrayList<String> getCategoryListtemp() {
		return categoryListtemp;
	}
	public void setCategoryListtemp(ArrayList<String> categoryListtemp) {
		this.categoryListtemp = categoryListtemp;
	}
	public ArrayList<Integer> getPidRetrieved() {
		return pidRetrieved;
	}
	public void setPidRetrieved(ArrayList<Integer> pidRetrieved) {
		this.pidRetrieved = pidRetrieved;
	}
	
}