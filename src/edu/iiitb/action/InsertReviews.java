/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ModelDriven;

import edu.iiitb.database.DBHandlerForSellerRating;
import edu.iiitb.model.SellerReviews;
import edu.iiitb.model.User;

/**
 * @author paras
 *
 */
public class InsertReviews implements ModelDriven<SellerReviews> , SessionAware{

	SellerReviews review = new SellerReviews();
	Map session ;
	
	public String execute()
	{
		User user = (User) session.get("user");
		DBHandlerForSellerRating dbHandler = new DBHandlerForSellerRating();
		try {
			dbHandler.insertReviewandrating(review , user.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}

	
	
	public Map getSession() {
		return session;
	}



	public void setSession(Map session) {
		this.session = session;
	}



	public SellerReviews getReview() {
		return review;
	}



	public void setReview(SellerReviews review) {
		this.review = review;
	}



	@Override
	public SellerReviews getModel() {
		// TODO Auto-generated method stub
		return review;
	}
	
}
