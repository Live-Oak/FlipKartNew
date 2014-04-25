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
public class FetchStockInfo {

	ArrayList<ViewStock> stockInfo;
	String stockType;
	/**
	 * @return the stockInfo
	 */
	public ArrayList<ViewStock> getStockInfo() {
		return stockInfo;
	}

	/**
	 * @param stockInfo the stockInfo to set
	 */
	public void setStockInfo(ArrayList<ViewStock> stockInfo) {
		this.stockInfo = stockInfo;
	}
	
	

	/**
	 * @return the stockType
	 */
	public String getStockType() {
		return stockType;
	}

	/**
	 * @param stockType the stockType to set
	 */
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String execute()
	{
		stockInfo = new ArrayList<ViewStock>();
		ArrayList<String> productId = new ArrayList<String>();
		DBHandlerForAdmin dbHandler = new DBHandlerForAdmin();
		try {
			if(stockType == null)
				stockType = "all";
			dbHandler.fetchProductID(productId);
			for(int i=0;i<productId.size();i++)
				dbHandler.fetchStockInfoForProduct(stockInfo, Integer.parseInt(productId.get(i)) , stockType);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception at execute() of FetchStockInfo.java ... ");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
}
