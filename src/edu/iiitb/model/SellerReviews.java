/**
 * 
 */
package edu.iiitb.model;

/**
 * @author paras
 *
 */
public class SellerReviews {

	String userName , review , sellerID;
	int rating ;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getSellerID() {
		return sellerID;
	}

	public void setSellerID(String sellerID) {
		String[] split = sellerID.split("_");
		this.sellerID = split[0];
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
}
