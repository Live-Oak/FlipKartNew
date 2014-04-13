/**
 * 
 */
package edu.iiitb.action;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.database.DBHandlerForAdmin;
import edu.iiitb.model.ViewStock;

/**
 * @author paras
 *
 */
public class FetchSpecificStock {

	int productId;
	ArrayList<ViewStock> stock;
	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return the stock
	 */
	public ArrayList<ViewStock> getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(ArrayList<ViewStock> stock) {
		this.stock = stock;
	}
	
	public String execute()
	{
		stock = new ArrayList<ViewStock>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try {
			dbHandler.fetchStockInfoForProduct(stock, getProductId() , "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of FetchSpecificStock.java");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
}
