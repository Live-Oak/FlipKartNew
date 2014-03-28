/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.database.DBHandlerForAdmin;

/**
 * @author paras
 *
 */
public class FetchCategoryDetails {
	
	ArrayList<String> categoryId;
 	ArrayList<String> categryName;
	
	


	/**
	 * @return the categryName
	 */
	public ArrayList<String> getCategryName() {
		return categryName;
	}


	/**
	 * @param categryName the categryName to set
	 */
	public void setCategryName(ArrayList<String> categryName) {
		this.categryName = categryName;
	}


	/**
	 * @return the categoryId
	 */
	public ArrayList<String> getCategoryId() {
		return categoryId;
	}


	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(ArrayList<String> categoryId) {
		this.categoryId = categoryId;
	}


	public String execute()
	{
		categoryId = new ArrayList<String>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		ArrayList<String> cName = new ArrayList<String>();
		ArrayList<String> cId = new ArrayList<String>();
		try {
			dbHandler.fetchCategoryID(cId);
			dbHandler.fetchCategoryName(cName);
			for(int i=0;i<cName.size();i++)
				categoryId.add(cId.get(i)+"_"+cName.get(i));
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of FetchCategoryID.java ");
			e.printStackTrace();
			return "error";
		}

	}
	
	public String fetchCategoryName()
	{
		categryName = new ArrayList<String>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try
		{
			dbHandler.fetchCategoryName(categryName);
			return "success";
		} catch(SQLException e)
		{
			System.out.println("Exception at fetchCategoryName() of FetchCategoryID.java ");
			e.printStackTrace();
			return "error";
		}
	}

}
