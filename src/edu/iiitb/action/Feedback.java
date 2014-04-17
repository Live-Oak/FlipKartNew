package edu.iiitb.action;


import java.sql.SQLException;

import com.opensymphony.xwork2.ModelDriven;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.FeedbackModel;


public class Feedback implements ModelDriven<FeedbackModel> {

	FeedbackModel feedbck = new FeedbackModel();
	
	
	
	/**
	 * @return the feedbck
	 */
	public FeedbackModel getFeedbck() {
		return feedbck;
	}

	/**
	 * @param feedbck the feedbck to set
	 */
	public void setFeedbck(FeedbackModel feedbck) {
		this.feedbck = feedbck;
	}

	/**
	 * @return the db
	 */

	
	
	public String execute()
	{
		DBHandlerForUser db = new DBHandlerForUser();
		System.out.println("It came here!!");
		try {
			
			db.insertFeedback(feedbck);
			System.out.println("Success");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	@Override
	public FeedbackModel getModel() {
		// TODO Auto-generated method stub
		return feedbck;
	}


	
}
