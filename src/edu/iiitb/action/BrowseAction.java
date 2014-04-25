package edu.iiitb.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.Linklists;
import edu.iiitb.model.ProductInfo;

public class BrowseAction extends ActionSupport 
{
	ArrayList<ProductInfo> productinfo;
	String keyword;
	ArrayList<String> companyList;
	ArrayList<Linklists> linktoitem;
	String catid, categoryname ;

	
	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}

	public ArrayList<Linklists> getLinktoitem() {
		return linktoitem;
	}

	public void setLinktoitem(ArrayList<Linklists> linktoitem) {
		this.linktoitem = linktoitem;
	}
	
	public ArrayList<String> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(ArrayList<String> companyList) {
		this.companyList = companyList;
	}


	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ArrayList<ProductInfo> getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(ArrayList<ProductInfo> productinfo) {
		this.productinfo = productinfo;
	}
	
	public String execute()
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		try
		{
			catid = dbHandlerForUser.getcategoryId(keyword);
			// get the id of product
			categoryname = dbHandlerForUser.getnameonid(catid);
			// get the name of product on id
			linktoitem = dbHandlerForUser.getlinktothecategory(categoryname);
			// To get the links for the side results
			productinfo = dbHandlerForUser.getproductlist(keyword);
			// get the list of products
			for(int i=0; i<productinfo.size(); i++)
			{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				long diff = (productinfo.get(i).getOfferValidity().getTime() - date.getTime());
				int diffDays =(int) diff / (24 * 60 * 60 * 1000);
				if (diffDays > 0)
					productinfo.get(i).setValid(diffDays);
				else
					productinfo.get(i).setValid(0);
				
				int discount = 100 - (((productinfo.get(i).getPrice()-productinfo.get(i).getOffer())*100)/productinfo.get(i).getPrice());
				productinfo.get(i).setDiscount(discount);
			}
			// calculating offer valid and discount
			companyList = dbHandlerForUser.getCompanylist(keyword);
			// get the list of company
		}
		catch(Exception e)
		{
			System.out.println("Error Search Action "+e);
			return "error";
		}
		return "success";
	}
}
