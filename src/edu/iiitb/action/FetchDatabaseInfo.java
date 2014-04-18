/**
 * 
 */
package edu.iiitb.action;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.database.DBHandlerForAdmin;
import edu.iiitb.model.CategoryModel;
import edu.iiitb.model.FeedbackModel;
import edu.iiitb.model.ProductInfo;
import edu.iiitb.model.UserEntry;

/**
 * @author paras
 *
 */
public class FetchDatabaseInfo {

	ArrayList<UserEntry> user;
	ArrayList<ProductInfo> product;
	ArrayList<CategoryModel> category;
	ArrayList<FeedbackModel> feedback;
	

	public String execute() {
		
		user = new ArrayList<UserEntry>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try
		{
			dbHandler.viewUserData(user);
		}catch(SQLException e)
		{
			System.out.println("Exception at execute() of FetchUser.java");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String productFetch()
	{
		product = new ArrayList<ProductInfo>();
		
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try
		{
			dbHandler.viewProductData(product);
			
				
		}catch(SQLException e)
		{
			System.out.println("Exception at productFetch() of FetchUser.java");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String categoryFetch()
	{
		category = new ArrayList<CategoryModel>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try
		{
			dbHandler.viewCategoryData(category);
		}catch(SQLException e)
		{
			System.out.println("Exception at categoryFetch() of FetchUser.java");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String subCategoryFetch()
	{
		category = new ArrayList<CategoryModel>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try
		{
			dbHandler.viewSubCategoryData(category);
		}catch(SQLException e)
		{
			System.out.println("Exception at subCategoryFetch() of FetchUser.java");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String feedbackFetch()
	{
		feedback = new ArrayList<FeedbackModel>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try
		{
			dbHandler.viewFeedbackData(feedback);
		}catch(SQLException e)
		{
			System.out.println("Exception at subCategoryFetch() of FetchUser.java");
			e.printStackTrace();
			return "error";
		}
		return "success";
		
	}

	
	/**
	 * @return the user
	 */
	public ArrayList<UserEntry> getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(ArrayList<UserEntry> user) {
		this.user = user;
	}
	
	/**
	 * @return the product
	 */
	public ArrayList<ProductInfo> getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ArrayList<ProductInfo> product) {
		this.product = product;
	}
	
	/**
	 * @return the category
	 */
	public ArrayList<CategoryModel> getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(ArrayList<CategoryModel> category) {
		this.category = category;
	}

	/**
	 * @return the feedback
	 */
	public ArrayList<FeedbackModel> getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(ArrayList<FeedbackModel> feedback) {
		this.feedback = feedback;
	}

	
}
