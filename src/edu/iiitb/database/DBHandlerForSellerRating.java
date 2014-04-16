/**
 * 
 */
package edu.iiitb.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import edu.iiitb.model.SellerRating;
import edu.iiitb.model.SellerReviews;

/**
 * @author paras
 *
 */
public class DBHandlerForSellerRating {

	DBConnectivity db=new DBConnectivity();
	String query;
	
	public void fetchReviewsForSeller(int sellerId,
			ArrayList<SellerReviews> reviews) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = db.createConnection();
		query = "select concat(UC.firstName , ' ' , UC.lastName) name , RR.review from FlipKartDatabase.ReviewNRating RR Inner join FlipKartDatabase.UserCredantials UC "
				+ "on RR.userId = UC.userId where RR.productSellerId = "+sellerId+"";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			SellerReviews model = new SellerReviews();
			model.setUserName(rs.getString(1));
			model.setReview(rs.getString(2));
			reviews.add(model);
		}
		con.close();
	}

	public void fetchRatingForSeller(int sellerId, SellerRating rating) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = db.createConnection();
		Connection con1 = db.createConnection();
		query = "select count(*) , concat(UC.firstName , ' ' , UC.lastName) name , S.description from "
				+ "FlipKartDatabase.ReviewNRating RR Inner join FlipKartDatabase.Seller S"
				+ " on RR.productSellerId = S.sellerId Inner join FlipKartDatabase.UserCredantials UC "
				+ "on S.userId = UC.userId where RR.productSellerId = "+sellerId+"";
		ResultSet rs=db.executeQuery(query, con);
		if(rs.next())
		{
			rating.setTotalReview(rs.getInt(1));
			rating.setSellerName(rs.getString(2));
			rating.setDescription(rs.getString(3));
		}
		con.close();
		query = "select count(*) , rating from FlipKartDatabase.ReviewNRating "
				+ "where productSellerId = "+sellerId+" group by rating";
		ResultSet rs1=db.executeQuery(query, con1);
		while(rs1.next())
		{
			if(rs1.getString(2).equalsIgnoreCase("1"))
				rating.setOneStar((rs1.getInt(1) * 100 )/rating.getTotalReview());
			else if(rs1.getString(2).equalsIgnoreCase("2"))
				rating.setTwoStar((rs1.getInt(1) * 100 )/rating.getTotalReview());
			else if(rs1.getString(2).equalsIgnoreCase("3"))
				rating.setThreeStar((rs1.getInt(1) * 100 )/rating.getTotalReview());
			else if(rs1.getString(2).equalsIgnoreCase("4"))
				rating.setFourStar((rs1.getInt(1) * 100 )/rating.getTotalReview());
			else 
				rating.setFiveStar((rs1.getInt(1) * 100 )/rating.getTotalReview());
		}
		rating.setPositivePercentage(rating.getFiveStar()+rating.getFourStar());
		con1.close();
	}

	public void insertReviewandrating(SellerReviews review, String string) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = db.createConnection();
		query = "insert into FlipKartDatabase.ReviewNRating values(?,?,?,?,?)";
	}
	
	
	
	
}
