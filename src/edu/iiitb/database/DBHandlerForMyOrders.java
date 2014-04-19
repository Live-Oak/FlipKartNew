package edu.iiitb.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

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
				" Where customerEmail = '" + email + "' ORDER BY O.orderDate desc ";

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
				" Where customerEmail = '" + email + "' ORDER BY O.orderDate desc ";

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


	public void GetOrderdetails(GetOrderDetailsModel god, String email, int orderId) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = db.createConnection();

		String query= "SELECT O.orderId, O.status, O.orderDate, O.deliveryDate, SA.customerName, SA.customerEmail, SA.addressLine1,"+ 
				" SA.addressLine2, SA.pincode, SA.city, SA.customerPhoneNumber, P.paymentType, sum(OD.quantity) as totalItems,"+
				" sum(OD.quantity * OD.price) as totalAmount"+
				" From  FlipKartDatabase.Order as O "+
				" Inner Join FlipKartDatabase.Payment as P "+
				" ON P.orderId = O.orderId "+
				" Inner Join FlipKartDatabase.OrderDescription as OD "+
				" ON O.orderId = OD.orderId "+
				" Inner Join FlipKartDatabase.OrderShipingAddress as SA "+
				" On SA.orderId = O.orderId "+
				" Where O.orderId = " + orderId + " "+
				" Group By O.orderId, O.status, O.orderDate, O.deliveryDate, SA.orderId, SA.customerName, SA.customerEmail, SA.addressLine1, "+ 
				" SA.addressLine2, SA.pincode, SA.city, SA.customerPhoneNumber, P.paymentType ";

		ResultSet rs=db.executeQuery(query, con);
		System.out.println("get order info");

		while(rs.next())
		{
			god.setOrderNo(rs.getInt("orderID"));
			god.setStatus(rs.getString("status"));
			god.setOrder_date(rs.getDate("orderDate"));
			god.setDelievry_date(rs.getDate("deliveryDate"));
			god.setCust_name(rs.getString("customerName"));
			god.setAddress_line1(rs.getString("addressLine1"));
			god.setAddress_line2(rs.getString("addressLine2"));
			god.setPincode(rs.getString("pincode"));
			god.setCity(rs.getString("city"));
			god.setPhone_number(rs.getString("customerPhoneNumber"));
			god.setPaymentType(rs.getString("paymentType"));
			god.setQuantity(rs.getInt("totalItems"));
			god.setTotalprice(Float.parseFloat(rs.getString("totalAmount")));

		}
		db.closeConnection(con);
		System.out.println("finally get order info");


	}


	public boolean stockUpdationAfterCancelOrder(int orderId) throws SQLException 
	{
		int pId  = 0;
		int qty = 0;
		String query1;
		String query = "SELECT productId , quantity from FlipKartDatabase.OrderDescription where orderID = "+orderId ;
		Connection con = db.createConnection();
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			pId = rs.getInt("productId");
			qty = rs.getInt("quantity");
			query1 = "Update FlipKartDatabase.Stock Set availableQuantity = availableQuantity + " + qty + " where productId = " + pId ;
			Statement stmt = (Statement) con.createStatement();
			stmt.executeUpdate(query1);
		}
		//System.out.println("PID : " + pId + "quantity : " + qty);
		con.close();
		
		return true;
	}


	public boolean deleteOrder(int orderId) throws SQLException 
	{System.out.println("order cancel successfully2");
        Connection con = db.createConnection();
		
		String query = "DELETE FROM FlipKartDatabase.Payment Where orderId = " + orderId + " ";
		Statement st=(Statement) con.createStatement();
		st.executeUpdate(query);
		System.out.println("order cancel successfully1");
		
		con.close();
		
		return true;
	}


	public ArrayList<MyOrdersModel> getNotificationData(String email) throws SQLException 
	{
		Connection con = db.createConnection();
		ArrayList<MyOrdersModel> notification = new ArrayList<MyOrdersModel>();
		
		String query = "Select O.orderId, O.orderDate, O.deliveryDate, O.status "+
                       " From FlipKartDatabase.Payment as P "+
                       " Inner Join FlipKartDatabase.Order as O "+
	                   " ON O.orderId = P.orderId "+
                       " Inner Join FlipKartDatabase.OrderShipingAddress as SA "+
	                   " On O.orderId = SA.orderId "+ 
                       " Where SA.customerEmail = '" + email + "' "+
                       " Order By deliveryDate, /*dispatchDate,*/orderDate  desc ";
		
		ResultSet rs=db.executeQuery(query, con);
		Date date1 = new Date();
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal3 = Calendar.getInstance();

		cal1.setTime(date1);
		
		while(rs.next())
		{  

			MyOrdersModel obj = new MyOrdersModel();

			
			Date date2 = rs.getDate("orderDate");
			Date date3 = rs.getDate("deliveryDate");
			
			
			cal2.setTime(date2);
			cal3.setTime(date3);
			long diff_in_days1 = ( (cal1.getTimeInMillis() - cal2.getTimeInMillis() ) / (24 * 60 * 60 * 1000) );
			long diff_in_days2 = ( (cal1.getTimeInMillis() - cal3.getTimeInMillis() ) / (24 * 60 * 60 * 1000) );

				
				obj.setOredrNo(rs.getInt("orderId"));
			    obj.setStatus(rs.getString("status"));
			    if(rs.getString("status").equals("PLACED") || rs.getString("status").equals("DISPATCHED") )
			    {
			    	obj.setDays_ago(diff_in_days1);
			    }
			    
			    else
			    {
			    	obj.setDays_ago(diff_in_days2);
			    }
				

				notification.add(obj);
				
		}
		db.closeConnection(con);
		System.out.println("helloNotification");
		return notification;
	}


}
