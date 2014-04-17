package edu.iiitb.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.getKeywords;

public class autoCompleteAction extends ActionSupport 
{
	String keyword;
	ArrayList<getKeywords> keywordsList;

	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ArrayList<getKeywords> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(ArrayList<getKeywords> keywordsList) {
		this.keywordsList = keywordsList;
	} 
	public String getProduct()
	{
		keywordsList = new ArrayList<getKeywords>();
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		try
		{
			keywordsList = dbHandlerForUser.getKeywordList(keyword);
			//System.out.println("Product recieved count is "+keywordsList.size());
		}
		catch(Exception e)
		{
			System.out.println("Keyword Action "+e);
			return "error";
		}
		return "success";
	}
}
