/**
 * 
 */
package edu.iiitb.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import edu.iiitb.model.CartModel;
import edu.iiitb.model.CartProduct;
import edu.iiitb.model.ProductInfo;

/**
 * @author PrashantN
 *
 */
public class DBHandlerForCart {
	
	public static DBConnectivity db=new DBConnectivity();
	
	
	public static void addToCart(int uid,int productId,int quantity) throws Exception
	{
		Connection con = db.createConnection();
		String query="INSERT IGNORE INTO Cart(userId,productId,quantity) VALUES (?,?,?);";
		PreparedStatement prep =con.prepareStatement(query);	
		prep.setInt(1,uid);
		prep.setInt(2,productId);
		prep.setInt(3,quantity);
		prep.execute();
		db.closeConnection(con);
	}
	
	public static ArrayList<CartModel> getProducts(int uid) throws SQLException
	{
		Connection con = db.createConnection();
		ArrayList<CartModel> products = new ArrayList<CartModel>(); 
		String query = "select p.productId,p.productName,p.image,p.price,p.offer,c.quantity from Cart c inner join ProductInfo p where c.userId = "+uid+" and c.productId = p.productID;";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			products.add(new CartModel(rs.getInt("productId"),rs.getString("productName"),rs.getString("image"),rs.getInt("price") - rs.getInt("offer"),rs.getInt("quantity")));
		}
		db.closeConnection(con);
		return products;
	}
	
	public static ArrayList<CartModel> getProductsFromCart(ArrayList<CartProduct> cartProducts) throws SQLException
	{
		Connection con = db.createConnection();
		ArrayList<CartModel> products = new ArrayList<CartModel>(); 
		for(CartProduct p : cartProducts)
		{
			String query = "select productId,productName,image,price,offer from  ProductInfo where productID = "+p.getProductId()+";";
			ResultSet rs=db.executeQuery(query, con);
			while(rs.next())
			{
				products.add(new CartModel(rs.getInt("productId"),rs.getString("productName"),rs.getString("image"),rs.getInt("price")-rs.getInt("offer"),p.getQuantity()));
			}
		}
		
		db.closeConnection(con);
		return products;
	}

	public static void removeFromCart(int uid, int productId) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = db.createConnection();
		String query="DELETE FROM Cart where userId = "+uid+" and productId = "+productId+";";
		PreparedStatement prep =con.prepareStatement(query);	
		prep.execute();
		db.closeConnection(con);
	}

	public static void updateToCart(int userId, int productId, int quantity) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = db.createConnection();
		String query="UPDATE Cart SET quantity = ? WHERE userId = ? AND productId = ? ;";
		PreparedStatement prep =con.prepareStatement(query);	
		prep.setInt(1,quantity);
		prep.setInt(2,userId);
		prep.setInt(3,productId);
		prep.execute();
		db.closeConnection(con);
	}
	

}
