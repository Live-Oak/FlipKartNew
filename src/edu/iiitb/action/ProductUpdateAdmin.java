/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;

import com.opensymphony.xwork2.ModelDriven;

import edu.iiitb.database.DBHandlerForAdmin;
import edu.iiitb.model.ProductInfo;

/**
 * @author paras
 *
 */
public class ProductUpdateAdmin{

	String productID ;
	ProductInfo product ;
	
	public ProductInfo getProduct() {
		return product;
	}



	public void setProduct(ProductInfo product) {
		this.product = product;
	}



	public String getProductID() {
		return productID;
	}



	public void setProductID(String productID) {
		this.productID = productID;
	}



	public String execute()
	{
		product = new ProductInfo();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try
		{
			dbHandler.fetchProductDetailsForUpdate(productID.split("_")[0] , product);
			return "success";
		}catch(SQLException e)
		{
			System.out.println("Exception at execute() of ProductUpdateAdmin.java ");
			e.printStackTrace();
			return "error";
		}
	}
	

}
