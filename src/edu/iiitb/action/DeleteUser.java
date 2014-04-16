/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForAdmin;
import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.User;
import edu.iiitb.model.UserEntry;

/**
 * @author paras
 *
 */
public class DeleteUser extends ActionSupport implements SessionAware {
	
	String id;
	private Map<String, Object> session;
	
	

	public Map<String, Object> getSession() {
		return session;
	}



	public void setSession(Map<String, Object> session) {
		this.session = session;
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	public String execute()
	{
		// This array is used because in the dropDown list userId and Role are seprated by a '_'
		
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try {
			String[] idRole=getId().split("_");
			dbHandler.deleteUserFromDB(Integer.parseInt(idRole[0]));
			addActionMessage("User Has Been Deleted Successfully");
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
	}
	
	public String execute_deactivateAccount()
	{
		User user1=(User) session.get("user");
		DBHandlerForUser dbHandler = new DBHandlerForUser();
		
		try {
			dbHandler.deactivateAccount(user1.getEmail());
			addActionMessage("Account Has Been Deactivated Successfully");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		return "success";
		
	}

}
