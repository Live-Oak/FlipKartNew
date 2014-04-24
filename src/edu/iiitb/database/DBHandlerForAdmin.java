/**
 * 
 */
package edu.iiitb.database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import edu.iiitb.model.Advertizement;
import edu.iiitb.model.CategoryModel;
import edu.iiitb.model.FeedbackModel;
import edu.iiitb.model.MyOrdersModel;
import edu.iiitb.model.ProductInfo;
import edu.iiitb.model.UserEntry;
import edu.iiitb.model.ViewRequestSeller;
import edu.iiitb.model.ViewStock;

/**
 * @author paras
 *
 */
public class DBHandlerForAdmin {

	/**
	 * Write All DB query for admin inside this class
	 * @throws SQLException 
	 *
	 */
	DBConnectivity db=new DBConnectivity();
	Connection con = db.createConnection();
	
	public boolean chkForEmailIDAlreadyExists(String email) throws SQLException
	{
		String query="select email from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			if(rs.getString("email").equals(email))
				return true;
		}
		return false;
	}
	
	public boolean registerUserinDB(UserEntry user) throws SQLException
	{	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String date = sdf.format(new java.util.Date());
		String[] splitedDate=user.getDate().split("T");
		String query="INSERT INTO UserCredantials(`firstName`,`lastName`,`password`,`role`,`dateOfBirth`,`addressLine1`,`addressLine2`,`city`,`country`,`pinCode`,`email`,`phoneNumber`,`dateOfRegistration`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prep =con.prepareStatement(query);
		prep.setString(1, user.getFirstName());
		prep.setString(2, user.getLastName());
		prep.setString(3, user.getPassword());
		prep.setString(4, user.getRole());
		prep.setString(5, splitedDate[0]);
		prep.setString(6, user.getAddress1());
		prep.setString(7, user.getAddress2());
		prep.setString(8, user.getCity());
		prep.setString(9, user.getCountry());
		prep.setInt(10, user.getPinCode());
		prep.setString(11, user.getEmail());
		prep.setString(12, user.getPhonenumber());
		prep.setString(13, date );
		prep.execute();
		
		if(user.getRole().equals("Seller"))
		{
			try
			{
				int id = fetchUserIDIntForm(user.getEmail());
				String queryForSeller="INSERT INTO Seller(`userId`,`description`) VALUES(?,?)";
				PreparedStatement prepForSeller =con.prepareStatement(queryForSeller);
				prepForSeller.setInt(1, id);
				prepForSeller.setString(2, user.getSellerDescription());
				prepForSeller.execute();
			} catch(Exception e)
			{
				System.out.println("Exception at fetching id for Role seller");
			}
			
		}
	//	db.closeConnection(con);
		return true;
	}
	
	public void fetchUserIdWithRole(ArrayList<String> user) throws SQLException
	{
		String query = "select userId , role from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			user.add(Integer.toString(rs.getInt("userId"))+"_"+rs.getString("role"));
		}
	//	db.closeConnection(con);
	}
	
	public void fetchUserID(ArrayList<String> userID) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "select userId from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			userID.add(Integer.toString(rs.getInt("userId")));
		}
	//	db.closeConnection(con);
	}
	
	public int fetchUserIDIntForm(String email) throws SQLException
	{
		String query = "select userId , email from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			if(rs.getString("email").equals(email))
				return rs.getInt("userId");
		}
		return 0;
	}
	
	public void deleteUserFromDB(int id) throws SQLException
	{
		try
		{
			String query = "DELETE FROM UserCredantials WHERE  userId = "+id+"";
			Statement st=(Statement) con.createStatement();
			st.executeUpdate(query);
		} catch(SQLException e)
		{
			String query1 = "DELETE FROM Seller WHERE  userId = "+id+"";
			Statement st1=(Statement) con.createStatement();
			st1.executeUpdate(query1);
			String query2 = "DELETE FROM UserCredantials WHERE  userId = "+id+"";
			Statement st2=(Statement) con.createStatement();
			st2.executeUpdate(query2);
		}
	//	db.closeConnection(con);
		
	}
	
	public boolean chkForCategoryIDAlreadyExists(String categoryId) throws SQLException
	{
		String query="select categoryId from Category";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			if(rs.getString("categoryId").equals(categoryId))
				return true;
		}
		return false;
	}

	public void addCategoryinDB(CategoryModel categoryInfo) throws SQLException {
		// TODO Auto-generated method stub
		String query="INSERT INTO Category(`categoryId`,`categoryName`,`image`) VALUES(?,?,?)";
		PreparedStatement prep =con.prepareStatement(query);
		prep.setString(1, categoryInfo.getCategoryId());
		prep.setString(2, categoryInfo.getCategoryName());
		prep.setString(3, categoryInfo.getCategoryImage());
		prep.execute();
	}

	public void fetchCategoryID(ArrayList<String> categoryId) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "select categoryId from Category";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			categoryId.add(rs.getString("categoryId"));
		}
	}

	public void fetchSubCategoryId(ArrayList<String> subCategoryId,
			String categoryId) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select categoryId from Category";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			if(!rs.getString("categoryId").equals(categoryId))
				subCategoryId.add(rs.getString("categoryId"));
		}
	}

	public void insetCategoryRelationship(String id, String id2) throws SQLException {
		// TODO Auto-generated method stub
		
		String query="INSERT INTO CategoryRelation(`categoryId`,`subCategoryId`) VALUES(?,?)";
		PreparedStatement prep =con.prepareStatement(query);
		prep.setString(1, id);
		prep.setString(2, id2);
		prep.execute();
		
	}
	
	public boolean chkForProductIDAlreadyExists(int id) throws SQLException
	{
		String query="select productId from ProductInfo";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			if(rs.getInt("productId")==id)
				return true;
		}
		return false;
	}
	
	public ArrayList<Integer> getAllCategoryIDs() throws SQLException
	{
		ArrayList<Integer> categoryList = new ArrayList<Integer>();
		String query = "Select categoryId from Category";
		ResultSet rs = db.executeQuery(query, con);
		while(rs.next())
		{
			categoryList.add(rs.getInt(1));
		}
		return categoryList;
	}
	
	public void registerProduct(ProductInfo prod) throws SQLException
	{
		
		String[] splitedDate=prod.getLastOfferDate().split("T");
		String query = "Insert into ProductInfo(`productId`,`productName`,`price`,`image`,`offer`" +
				",`categoryId`,`keywords`,`description`,`brand`,`warranty`,`offerValidity`) " +
				" values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmnt = con.prepareStatement(query);
		stmnt.setInt(1, prod.getProductID());
		stmnt.setString(2, prod.getProductName());
		stmnt.setFloat(3, prod.getPrice());
		stmnt.setString(4, prod.getImage());
		stmnt.setInt(5, prod.getOffer());
		stmnt.setString(6,prod.getCategoryID());
		stmnt.setString(7,prod.getKeywords());
		stmnt.setString(8,prod.getDescription());
		stmnt.setString(9,prod.getBrand());
		stmnt.setInt(10,prod.getWarranty());
		stmnt.setString(11, splitedDate[0]);
		stmnt.execute();	
		// Update Stock table
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String date = sdf.format(new java.util.Date());
		String query1 = "INSERT INTO Stock(`productId`,`availableQuantity`,`minimumQuantity`,`maximumQuantity`,`sellerId`,`stockUpdateDate`) VALUES(?,?,?,?,?,?)";
		PreparedStatement stmnt1 = con.prepareStatement(query1);
		stmnt1.setInt(1, prod.getProductID());
		stmnt1.setInt(2, 5);
		stmnt1.setInt(3, prod.getMinimumQuantity());
		stmnt1.setInt(4, 1000);
		stmnt1.setInt(5, Integer.parseInt(prod.getSellerID()));
		stmnt1.setString(6, date);
		stmnt1.execute();
		System.out.println("Executed");
	}
	
	public void deleteProduct(int productId) throws SQLException
	{
		String query = "DELETE FROM ProductInfo where productId = "+productId+"";
		Statement st=(Statement) con.createStatement();
		st.executeUpdate(query);
		System.out.println("ProductInfo rows Deleted");
	}
	
	public void addAdvertisement(Advertizement adv) throws SQLException
	{
		 java.util.Date date= new java.util.Date();
		 Timestamp time =  new Timestamp(date.getTime());
		String query = "Insert into Advertizement values(?,?,?,?,?)";
		PreparedStatement stmnt = con.prepareStatement(query);
		stmnt.setString(1,adv.getProductID());
		stmnt.setString(2, adv.getPhoto());
		stmnt.setTimestamp(3, time);
		stmnt.setString(4, adv.getCaption());
		stmnt.setString(5, adv.getAdvType());
		stmnt.execute();
	}

	public void fetchProductID(ArrayList<String> productId) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "select productId from ProductInfo";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			productId.add(rs.getString("productId"));
		}
		
	}

	public void fetchCategoryName(ArrayList<String> categoryName) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "select categoryName from Category";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			categoryName.add(rs.getString("categoryName"));
		}
		
	}

	public boolean chkForCategoryNameAlreadyExists(String categoryName) throws SQLException {
		// TODO Auto-generated method stub
		String query="select categoryName from Category";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			if(rs.getString("categoryName").equals(categoryName))
				return true;
		}
		return false;
	}

	public void fetchProductName(ArrayList<String> pName) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select productName from ProductInfo";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			pName.add(rs.getString("productName"));
		}
	}

	public void viewUserData(ArrayList<UserEntry> user) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			UserEntry entry = new UserEntry();
			entry.setUserId(rs.getInt("userId"));
			entry.setFirstName(rs.getString("firstName"));
			entry.setLastName(rs.getString("lastName"));
			entry.setRole(rs.getString("role"));
			entry.setEmail(rs.getString("email"));
			entry.setPhonenumber(rs.getString("phoneNumber"));
			entry.setCity(rs.getString("city"));
			user.add(entry);
		}
	}

	public void viewProductData(ArrayList<ProductInfo> product) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from ProductInfo";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			ProductInfo entry = new ProductInfo();
			entry.setProductID(rs.getInt("productId"));
			entry.setProductName(rs.getString("productName"));
			entry.setCategoryID(rs.getString("categoryId"));
			entry.setDescription(rs.getString("description"));
			entry.setPrice(rs.getInt("price"));
			product.add(entry);
		}
	}

	public void viewCategoryData(ArrayList<CategoryModel> category) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from Category";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			CategoryModel entry = new CategoryModel();
			entry.setCategoryId(rs.getString("categoryId"));
			entry.setCategoryName(rs.getString("categoryName"));
			category.add(entry);
		}
	}

	public void viewSubCategoryData(ArrayList<CategoryModel> category) throws SQLException{
		// TODO Auto-generated method stub
		String query = "select * from Category";
		ArrayList<String> id = new ArrayList<String>();
		fetchDistinctSubCategoryID(id);
		ResultSet rs=db.executeQuery(query, con);
			while(rs.next())
			{
				if(id.contains(rs.getString("categoryId")))
				{
					CategoryModel entry = new CategoryModel();
					entry.setCategoryId(rs.getString("categoryId"));
					entry.setCategoryName(rs.getString("categoryName"));
					category.add(entry);
				}
			}
		
	}

	private void fetchDistinctSubCategoryID(ArrayList<String> id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT distinct(subCategoryId) FROM CategoryRelation";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			id.add(rs.getString(1));
		}
	}

	public void fetchIdForGivenRole(ArrayList<String> ID, String string) throws SQLException {
		// TODO Auto-generated method stub
		String query;
		if(string.equals("Seller"))
		{
			query = "SELECT sellerId FROM Seller";
			ResultSet rs=db.executeQuery(query, con);
			while(rs.next())
			{
				System.out.println("Paras");
				ID.add(Integer.toString(rs.getInt(1)));
			}
			
		}
		else if(string.equals("User") || string.equals("Admin"))
		{
			query = "select userId , role from UserCredantials";
			ResultSet rs=db.executeQuery(query, con);
			while(rs.next())
			{
				if(rs.getString(2).equals(string))
					ID.add(Integer.toString(rs.getInt(1)));
			}
		}
		
	}
	
	public void fetchSellerIdWithRole(ArrayList<String> ID) throws SQLException
	{
		String query = "select u.firstName , u.lastName , s.sellerId from UserCredantials as u , Seller as s WHERE  u.userId = s.userId";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
				String idName = Integer.toString(rs.getInt(3))+"_"+rs.getString(1)+" "+rs.getString(2);
				ID.add(idName);
		}
	}
	
	public void fetchStockInfoForProduct(ArrayList<ViewStock> stock , int productId , String stockType) throws SQLException
	{
		String query;
		if(stockType.equalsIgnoreCase("in"))
			query = "select sk.productId , sk.availableQuantity , sk.minimumQuantity , sk.sellerId ,"
					+ "uc.firstName , uc.lastName , pd.productName , pd.image from Stock as sk , Seller as sl , UserCredantials as uc , ProductInfo as pd "
					+ " where sk.productId = pd.productId and sk.sellerId = sl.sellerId and sl.userId = uc.userId and sk.productId = "+productId+" and "
							+ "sk.availableQuantity >= sk.minimumQuantity";
		
		else if(stockType.equalsIgnoreCase("out"))
			query = "select sk.productId , sk.availableQuantity , sk.minimumQuantity , sk.sellerId ,"
					+ "uc.firstName , uc.lastName , pd.productName , pd.image from Stock as sk , Seller as sl , UserCredantials as uc , ProductInfo as pd "
					+ " where sk.productId = pd.productId and sk.sellerId = sl.sellerId and sl.userId = uc.userId and sk.productId = "+productId+" and "
							+ "sk.availableQuantity < sk.minimumQuantity";
		
		else
			query = "select sk.productId , sk.availableQuantity , sk.minimumQuantity , sk.sellerId ,"
					+ "uc.firstName , uc.lastName , pd.productName , pd.image from Stock as sk , Seller as sl , UserCredantials as uc , ProductInfo as pd "
					+ " where sk.productId = pd.productId and sk.sellerId = sl.sellerId and sl.userId = uc.userId and sk.productId = "+productId+"";
			
		
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			ViewStock model = new ViewStock();
			model.setProductId(rs.getInt(1));
			model.setAvailableQty(rs.getInt(2));
			model.setMinimumQty(rs.getInt(3));
			model.setSellerId(rs.getInt(4));
			model.setSellerName(rs.getString(5)+" "+rs.getString(6));
			model.setProductName(rs.getString(7));
			model.setProductImagePath(rs.getString(8));
			if(stockType.equalsIgnoreCase("in"))
				model.setStatusImage("asset/Images/safe2.jpg");
			else
				model.setStatusImage("asset/Images/danger.jpg");
			stock.add(model);
		}
	}

	public void updateMinimumQuantityOfProduct(int sellerId , int productId , int minQty) throws SQLException
	{
		String query="update Stock set minimumQuantity = '" + minQty + "' where productId = '" + productId + "' and sellerId ='" + sellerId+"'";
		Statement st=(Statement) con.createStatement();
		st.executeUpdate(query);
		System.out.println("Product Minimum Quantity updated");
	}
	
	public void insertOrderProductForStock(ViewStock order) throws SQLException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String date = sdf.format(new java.util.Date());
		String query = "insert into OrderStock (`productId`,`sellerId`,`orderQuantity`,`orderDate`) values (?,?,?,?)";
		PreparedStatement stmnt = con.prepareStatement(query);
		stmnt.setInt(1,order.getProductId());
		stmnt.setInt(2, order.getSellerId());
		stmnt.setInt(3,order.getOrderQty());
		stmnt.setString(4, date);
		stmnt.execute();
	}
	
	public void fetchPurchaseProductRequestForAdmin(ArrayList<ViewRequestSeller> requests) throws SQLException
	{
		String query = "select uc.userId , uc.firstName , uc.lastName , pd.productId , pd.productName ,"
				+ " sum(os.orderQuantity) , os.sellerId , os.productId , pd.image from OrderStock as os , Seller as sl , "
				+ "UserCredantials as uc , ProductInfo as pd where sl.sellerId = os.sellerId and sl.userId = uc.userId "
				+ "and os.productId = pd.productId group by pd.productId , os.sellerId";
		
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			ViewRequestSeller model = new ViewRequestSeller();
			model.setCustomerId(rs.getInt(7));
			model.setProductId(rs.getInt(4));
			model.setCustomerName(rs.getString(2)+" "+rs.getString(3));
			model.setProductName(rs.getString(5));
			model.setOrderQty(rs.getInt(6));
			model.setProductImage(rs.getString(9));
			requests.add(model);
		}
		
	}

	public void deleteProductPurchaseEntry(int productId, int customerId) throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM OrderStock where productId = " + productId + " and sellerId =" + customerId+"";
		Statement st=(Statement) con.createStatement();
		st.executeUpdate(query);
		System.out.println("OrderStock rows Deleted");
	}

	public void updateProductQuantity(int productId, int purchaseQty , int sellerId) throws SQLException {
		// TODO Auto-generated method stub
		int originalProductQty = fetchProductAvailableQuantity(productId);
		purchaseQty = originalProductQty+purchaseQty;
		String query="update Stock set availableQuantity = " + purchaseQty + " where productId = " + productId + " and sellerId =" + sellerId+"";
		Statement st=(Statement) con.createStatement();
		st.executeUpdate(query);
		System.out.println("Product Available Quantity updated");
	}

	public int fetchProductAvailableQuantity(int productId) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select availableQuantity from Stock where productId = '"+productId+"'";
		ResultSet rs=db.executeQuery(query, con);
		if(rs.next())
			return rs.getInt(1);
		return 0;
	}

	public void updateKeywordForProduct(int productID, String[] split) throws SQLException {
		// TODO Auto-generated method stub
		for(int i=0;i<split.length;i++)
		{
			String query = "Insert into Keywords(`productId`,`keyword`) values(?,?)";
			PreparedStatement stmnt = con.prepareStatement(query);
			stmnt.setInt(1, productID);
			stmnt.setString(2, split[i]);
			stmnt.execute();
		}
	}

	public void fetchAllPurchasedOrderID(ArrayList<Integer> orderId) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select O.orderId from FlipKartDatabase.Order O Inner Join FlipKartDatabase.Payment P on O.orderId = P.orderId";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			orderId.add(rs.getInt(1));
		}
	//	db.closeConnection(con);
	}

	public void fetchOrderDetails(int orderID,
			ArrayList<MyOrdersModel> orderDeatils) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select  PI.productName , OD.quantity , PI.price , PI.image , OSA.customerName from FlipKartDatabase.OrderDescription OD Inner Join FlipKartDatabase.ProductInfo PI on OD.productId = PI.productId Inner Join FlipKartDatabase.OrderShipingAddress OSA on OSA.orderId = OD.orderId where OD.orderId = "+orderID+"";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			MyOrdersModel model = new MyOrdersModel();
			model.setOrderPersonName(rs.getString(5));
			model.setProdName(rs.getString(1));
			model.setQuantity(rs.getInt(2));
			model.setPrice(rs.getFloat(3));
			model.setPhoto(rs.getString(4));
			model.setTotalprice(rs.getFloat(3) * rs.getInt(2));
			orderDeatils.add(model);
		}
	//	db.closeConnection(con);
	}

	

	public void confirmPurchaseOrder(int orderID, String orderStatus) throws SQLException {
		// TODO Auto-generated method stub
		if(orderStatus.equalsIgnoreCase("DISPATCHED"))
		{
			String query="update FlipKartDatabase.Order set status = '" + orderStatus + "' where orderId = " + orderID + "";
			Statement st=(Statement) con.createStatement();
			st.executeUpdate(query);
			con.close();
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String date = sdf.format(new java.util.Date());
			String query = " Update FlipKartDatabase.Order "
						+  " Set status = '" + orderStatus + "' ,"
						+  " deliveryDate = '"+date+"' "
						+  " Where orderId = " + orderID + "";
			
			Statement st=(Statement) con.createStatement();
			st.executeUpdate(query);
			con.close();
		}
	}

	public void viewFeedbackData(ArrayList<FeedbackModel> feedback) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from FlipKartDatabase.Feedback";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			FeedbackModel model = new FeedbackModel();
			model.setEmail(rs.getString("email"));
			model.setMobileNumber(rs.getString("mobileNumber"));
			model.setCategory(rs.getString("category"));
			model.setMessage(rs.getString("message"));
			feedback.add(model);
		}
		con.close();
	}
	
	
}
