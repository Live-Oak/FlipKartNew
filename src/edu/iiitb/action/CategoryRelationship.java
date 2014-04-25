/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.database.DBHandlerForAdmin;

/**
 * @author paras
 *
 */
public class CategoryRelationship extends ActionSupport{

	String id,id2;

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

	/**
	 * @return the id2
	 */
	public String getId2() {
		return id2;
	}

	/**
	 * @param id2 the id2 to set
	 */
	public void setId2(String id2) {
		String[] subCatAry = id2.split(" ");
		this.id2 = subCatAry[0];
	}
	
	public String execute()
	{
		String[] idName = id.split("_");
		DBHandlerForAdmin dbhandler = new DBHandlerForAdmin();
		try
		{
			dbhandler.insetCategoryRelationship(idName[0],getId2());
			addActionMessage("Relationship inserted Successfully");
			return "success";
		}catch(SQLException e)
		{
			System.out.println("Exception at execute() of CategoryRelatioship.java");
			e.printStackTrace();
			return "error";
		}
	}
	
	
	
}
