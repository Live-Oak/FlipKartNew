package edu.iiitb.action;
import java.sql.SQLException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.SignupModel;
import edu.iiitb.util.SendMailSSL;


public class SignupAction extends ActionSupport implements ModelDriven<SignupModel>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		String message;
		SignupModel userData = new SignupModel();
		DBHandlerForUser dbHandler = new DBHandlerForUser();
		public void setMessage(String message)
		{
			this.message=message;
		}
		public String getMessage()
		{
			return message;
		}
		
		public String execute() throws SQLException
		{
			if(dbHandler.chkForEmailIDAlreadyExists(userData.getEmail()))
			{
				message = "Email already present";
				
			}
			try {
				System.out.println(userData.getPincode());
				System.out.println("Gender is: "+userData.getGender());
				dbHandler.SignupUserinDB(userData);
				SendMailSSL.sendEmail(userData.getEmail(),userData.getFirstName());
				return "success";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error execute() of SignupUserAction.java ");
				e.printStackTrace();
				return "error";
			}
		
		}

		
		/**
		 * @return the userData
		 */
		public SignupModel getUserData() {
			return userData;
		}

		/**
		 * @param userData the userData to set
		 */
		public void setUserData(SignupModel userData) {
			this.userData = userData;
		}

		@Override
		public SignupModel getModel() {
			// TODO Auto-generated method stub
			return userData;
		}

	}


