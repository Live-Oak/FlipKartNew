/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edu.iiitb.database.DBHandlerForAdmin;
import edu.iiitb.model.ProductInfo;

/**
 * @author paras
 *
 */
public class AdminUpdateProductMain extends ActionSupport implements ModelDriven<ProductInfo>{

	
	ProductInfo info = new ProductInfo();
	
	
	public ProductInfo getInfo() {
		return info;
	}


	public void setInfo(ProductInfo info) {
		this.info = info;
	}


	@Override
	public ProductInfo getModel() {
		// TODO Auto-generated method stub
		return info;
	}

	public String execute()
	{
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		
		try {
			dbHandler.updateProductInfo(info);
			addActionMessage("Product Updated Successfully");
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of AdminUpdateProductMain.java ");
			e.printStackTrace();
			return "error";
		}
	}
	
}
