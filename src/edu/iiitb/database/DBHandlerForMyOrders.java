package edu.iiitb.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import edu.iiitb.model.MyOrdersModel;

import edu.iiitb.model.GetOrderDetailsModel;
import edu.iiitb.model.ProductInfo;
import edu.iiitb.model.User;
import edu.iiitb.model.UserEntry;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DBHandlerForMyOrders {

	public static DBConnectivity db=new DBConnectivity();

	public ArrayList<MyOrdersModel> getmyorders(String email) throws SQLException 
	{

		Connection con = db.createConnection();
		ArrayList<MyOrdersModel> Orders = new ArrayList<MyOrdersModel>();

		String query="SELECT O.orderId, O.status, O.orderDate, O.deliveryDate, OD.quantity, OD.price, PI.image, PI.productName FROM FlipKartDatabase.Order as O Inner Join FlipKartDatabase.OrderShipingAddress as SA " +
				" ON O.orderId = SA.orderID"+
				" Inner Join  FlipKartDatabase.Payment as P"+
				" ON P.orderId = O.orderId " +
				" Inner Join  FlipKartDatabase.OrderDescription as OD "+
				" On OD.orderId = O.orderId"+
				" Inner Join FlipKartDatabase.ProductInfo as PI "+
				"ON PI.productId = OD.productId"+
				" Where customerEmail = '" + email + "' ";

		ResultSet rs=db.executeQuery(query, con);
		System.out.println("hellomyorders");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//get current date time with Date()
		Date date1 = new Date();
		System.out.println(dateFormat.format(date1)); 


		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);

		while(rs.next())
		{  

			MyOrdersModel obj = new MyOrdersModel();


			Date date2 = rs.getDate("deliveryDate");
			cal2.setTime(date2);
			long diff_in_days = ( (cal1.getTimeInMillis() - cal2.getTimeInMillis() ) / (24 * 60 * 60 * 1000) );

			if(diff_in_days <= 60)
			{
				obj.setDelievry_date(rs.getDate("deliveryDate"));
				obj.setOredrNo(rs.getInt("orderId"));
				obj.setOrder_date(rs.getDate("orderDate"));
				obj.setStatus(rs.getString("status"));
				obj.setPrice(Float.parseFloat(rs.getString("price")));
				obj.setQuantity(rs.getInt("quantity"));
				obj.setTotalprice(obj.getPrice() * obj.getQuantity());
				obj.setPhoto(rs.getString("image"));
				obj.setProdName(rs.getString("productName"));

				Orders.add(obj);
			}	
		}

		db.closeConnection(con);
		System.out.println("hellomyordersagain");
		return Orders;

	}


	public ArrayList<MyOrdersModel> getmypastorders(String email) throws SQLException 
	{

		Connection con = db.createConnection();
		ArrayList<MyOrdersModel> pastOrders = new ArrayList<MyOrdersModel>();

		String query="SELECT O.orderId, O.status, O.orderDate, O.deliveryDate, OD.quantity, OD.price, PI.image, PI.productName FROM FlipKartDatabase.Order as O Inner Join FlipKartDatabase.OrderShipingAddress as SA " +
				" ON O.orderId = SA.orderID"+
				" Inner Join  FlipKartDatabase.Payment as P"+
				" ON P.orderId = O.orderId " +
				" Inner Join  FlipKartDatabase.OrderDescription as OD "+
				" On OD.orderId = O.orderId"+
				" Inner Join FlipKartDatabase.ProductInfo as PI "+
				"ON PI.productId = OD.productId"+
				" Where customerEmail = '" + email + "' ";

		ResultSet rs=db.executeQuery(query, con);
		System.out.println("hellomyorders");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//get current date time with Date()
		Date date1 = new Date();
		System.out.println(dateFormat.format(date1)); 


		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);

		while(rs.next())
		{  

			MyOrdersModel obj = new MyOrdersModel();


			Date date2 = rs.getDate("deliveryDate");
			cal2.setTime(date2);
			long diff_in_days = ( (cal1.getTimeInMillis() - cal2.getTimeInMillis() ) / (24 * 60 * 60 * 1000) );

			if(diff_in_days > 60)
			{
				obj.setDelievry_date(rs.getDate("deliveryDate"));
				obj.setOredrNo(rs.getInt("orderId"));
				obj.setOrder_date(rs.getDate("orderDate"));
				obj.setStatus(rs.getString("status"));
				obj.setPrice(Float.parseFloat(rs.getString("price")));
				obj.setQuantity(rs.getInt("quantity"));
				obj.setTotalprice(obj.getPrice() * obj.getQuantity());
				obj.setPhoto(rs.getString("image"));
				obj.setProdName(rs.getString("productName"));

				pastOrders.add(obj);
			}	
		}

		db.closeConnection(con);
		System.out.println("hellomyordersagain");
		return pastOrders;

	}

	public boolean chkForEmail_OrderIdExist(String email, int orderid) throws SQLException
	{
		Connection con = db.createConnection();
		String query="select orderId,customerEmail from OrderShipingAddress";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			if(rs.getInt("orderId") == orderid && rs.getString("customerEmail").equals(email))
			{
				con.close();
				return true;
			}
		}
		return false;
	}


	public void GetOrderdetails(GetOrderDetailsModel gOD, String email,
			int orderId) {
		// TODO Auto-generated method stub

	}

}
