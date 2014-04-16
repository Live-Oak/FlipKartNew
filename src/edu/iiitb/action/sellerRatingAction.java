package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.database.DBHandlerForSellerRating;
import edu.iiitb.model.SellerRating;
import edu.iiitb.model.SellerReviews;

public class sellerRatingAction {

	int sellerId ;
	ArrayList<SellerReviews> reviews ;
	SellerRating rating ;
	
	
	public int getSellerId() {
		return sellerId;
	}



	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	

	public ArrayList<SellerReviews> getReviews() {
		return reviews;
	}



	public void setReviews(ArrayList<SellerReviews> reviews) {
		this.reviews = reviews;
	}

	

	public SellerRating getRating() {
		return rating;
	}



	public void setRating(SellerRating rating) {
		this.rating = rating;
	}



	public String execute() {

		reviews = new ArrayList<SellerReviews>();
		rating = new SellerRating();
		
		DBHandlerForSellerRating dbHandler = new DBHandlerForSellerRating();
		
		try {
			dbHandler.fetchReviewsForSeller(sellerId , reviews);
			dbHandler.fetchRatingForSeller(sellerId , rating);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of sellerRatingAction");
			e.printStackTrace();
			return "error";
		}
		
		return "success";

	}
}
