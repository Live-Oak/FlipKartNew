	package edu.iiitb.database;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.json.JSONPopulator;
import org.apache.struts2.json.JSONUtil;

	import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

	import edu.iiitb.model.Advertizement;
import edu.iiitb.model.CartCookie;
import edu.iiitb.model.CartModel;
import edu.iiitb.model.CartProduct;
import edu.iiitb.model.CategoryModel;
import edu.iiitb.model.CompareCartProduct;
import edu.iiitb.model.CompareProductsModel;
import edu.iiitb.model.Linklists;
import edu.iiitb.model.ProductInfo;
import edu.iiitb.model.SignupModel;
import edu.iiitb.model.UserEntry;
import edu.iiitb.model.customerCartDetail;
import edu.iiitb.model.custometAddressDetail;
import freemarker.core.ParseException;


	/**
	 * @author paras
	 *
	 */
public class DBHandlerForUser {

	/**
	 * 
	 */
	DBConnectivity db=new DBConnectivity();
	
	
	public boolean chkForEmailIDAlreadyExists(String email) throws SQLException
	{
		Connection con = db.createConnection();
		String query="select email from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			if(rs.getString("email").equals(email))
			{
				con.close();
				return true;
			}
		}
		return false;
	}
	
	public boolean SignupUserinDB(SignupModel user) throws SQLException
	{	
		Connection con = db.createConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String date = sdf.format(new java.util.Date());
		System.out.println(user.getDate());
		String[] splitedDate=user.getDate().split("T");
		String query="INSERT INTO UserCredantials(`firstName`,`lastName`,`password`,`role`,`dateOfBirth`,`addressLine1`,`addressLine2`,`city`,`country`,`pinCode`,`email`,`phoneNumber`,`dateOfRegistration`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prep =con.prepareStatement(query);
		
		prep.setString(1, user.getFirstName());
		prep.setString(2, user.getLastName());
		prep.setString(3, user.getPassword());
		prep.setString(4, "User");
		prep.setString(5, splitedDate[0]);
		prep.setString(6, user.getAddress1());
		prep.setString(7, user.getAddress2());
		prep.setString(8, user.getCity());
		prep.setString(9, user.getCountry());
		prep.setString(10, user.getPincode());
		prep.setString(11, user.getEmail());
		prep.setString(12, user.getPhonenumber());
		prep.setString(13, date );
		prep.execute();
		
		con.close();
		return true;
	}

	public String chkForEmailID_PasswordAlreadyExists(String email,
			String password) throws SQLException 
	{
		Connection con = db.createConnection();
		String role=null;
		String query="select email,password,role from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		System.out.println(email+password);
		while(rs.next())
		{
			if(rs.getString("email").equals(email)&&rs.getString("password").equals(password))
				role=rs.getString("role");
		}
		System.out.println(role);
		if (role==null)
		{
			System.out.println(role);
			return "invalid";
		}
		con.close();
		return role;
			
	}

	
	public String chkForEmailID(String email) throws SQLException 
	{
		Connection con = db.createConnection();
		String role=null;
		String query="select email,role from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		System.out.println(email);
		while(rs.next())
		{
			if(rs.getString("email").equals(email))
				role=rs.getString("role");
		}
		System.out.println(role);
		if (role==null)
		{
			System.out.println(role);
			return "invalid";
		}
		con.close();
		return role;
			
	}

	
	public String getPasswordformDB(String email) throws SQLException 
	{
		// TODO Auto-generated method stub
		Connection con = db.createConnection();
		String query="select email,password from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		String password=null;
		while(rs.next())
		{
			if(rs.getString("email").equals(email))
			{
				password=rs.getString("password");
		
			}
		}
		con.close();
		return password;
	
	}	
	
	public ArrayList<Advertizement> getadvertizement(String Type, String Limit) throws SQLException, IOException
	{
		Connection con = db.createConnection();
		ArrayList<Advertizement> advertize = new ArrayList<Advertizement>();
		DBConnectivity db=new DBConnectivity();															
		
		String query="SELECT * FROM Advertizement where Advertizement.advertizementType= '" +Type +"' ORDER BY Advertizement.timeStamp desc LIMIT " + Limit;
	
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			Advertizement obj = new Advertizement();
			obj.setProductId(rs.getInt("productId"));
			obj.setTimeStamp(rs.getTimestamp("timeStamp"));
			obj.setPhoto(rs.getString("image"));
			obj.setCaption(rs.getString("caption"));
			
			//System.out.println(rs.getString("caption"));
			//System.out.println(rs.getInt("productID"));
			advertize.add(obj);
			
		}
		db.closeConnection(con);
		return advertize;
	}
	
	public ArrayList<Advertizement> getadvertizementforfront(String Type, String category) throws SQLException, IOException
	{
		//System.out.println("value in handler : " +Type);
		//System.out.println("value in handler : " +category);
		Connection con = db.createConnection();
		ArrayList<Advertizement> advertize = new ArrayList<Advertizement>();
		DBConnectivity db=new DBConnectivity();															
		
		String query="SELECT distinct(Advertizement.productId), Advertizement.image FROM Advertizement, ProductInfo, CategoryRelation as c1, CategoryRelation as c2, CategoryRelation as c3 where Advertizement.advertizementType= '"+Type+"' and Advertizement.productId = ProductInfo.productId and ProductInfo.categoryId = c3.subCategoryId and c1.categoryId = '"+category+"' and c2.categoryId = c1.subCategoryId and c3.categoryId = c2.subCategoryId ORDER BY Advertizement.timeStamp desc LIMIT 3"; 
	
		ResultSet rs=db.executeQuery(query, con);
		
		if (rs.next() == false)
		{
			query = "SELECT distinct(Advertizement.productId), Advertizement.image FROM Advertizement, ProductInfo, CategoryRelation where Advertizement.advertizementType= '"+Type+"' and Advertizement.productId = ProductInfo.productId and ProductInfo.categoryId = CategoryRelation.subCategoryId and CategoryRelation.categoryId = '"+category+"' ORDER BY Advertizement.timeStamp desc LIMIT 3"; 
			rs=db.executeQuery(query, con);
		}
		else
			rs.previous();
		while(rs.next())
		{
			System.out.println("hi");
			Advertizement obj = new Advertizement();
			obj.setProductId(rs.getInt("productId"));
			obj.setPhoto(rs.getString("image"));

			//System.out.println(rs.getInt("productId"));
			//System.out.println(rs.getString("image"));
			advertize.add(obj);
		}
		db.closeConnection(con);
		return advertize;
	}
	
	public ArrayList<CategoryModel> getsubcategorylist(int parentcategoryId) throws SQLException, IOException
	{
		ArrayList<CategoryModel> categoryModel = new ArrayList<CategoryModel>();
		DBConnectivity db=new DBConnectivity();
		Connection con= db.createConnection();																
		
		String query= " SELECT Category.categoryName, Category.categoryId FROM Category, CategoryRelation WHERE Category.categoryId = CategoryRelation.subCategoryId AND CategoryRelation.categoryId =" + parentcategoryId;  
	
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			CategoryModel obj = new CategoryModel();
			obj.setCategoryName(rs.getString("categoryName"));
			obj.setCategoryId(rs.getString("categoryId"));
			categoryModel.add(obj);
		}
		db.closeConnection(con);
		return categoryModel;
	}
	
	public ArrayList<CategoryModel> getsubcategorylistancestor(String parentcategoryId, String ancestorname) throws SQLException, IOException
	{
		ArrayList<CategoryModel> categoryModel = new ArrayList<CategoryModel>();
		DBConnectivity db=new DBConnectivity();
		Connection con= db.createConnection();	
		//System.out.println("In handler");
		//System.out.println(parentcategoryId);
		//System.out.println(ancestorname);
		
		String query= " SELECT Category.categoryName, Category.categoryId FROM Category, CategoryRelation WHERE Category.categoryId = CategoryRelation.subCategoryId AND CategoryRelation.categoryId = '"+ parentcategoryId +"' and Category.categoryName Like '"+ ancestorname+"%'";       
	
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			CategoryModel obj = new CategoryModel();
			obj.setCategoryName(rs.getString("categoryName"));
			obj.setCategoryId(rs.getString("categoryId"));
			categoryModel.add(obj);
		}
		db.closeConnection(con);
		return categoryModel;
	}
	
	public ArrayList<CategoryModel> getsubsubcategorylist(String parentcategoryname) throws SQLException, IOException
	{
		ArrayList<CategoryModel> categoryModel = new ArrayList<CategoryModel>();
		DBConnectivity db=new DBConnectivity();
		Connection con= db.createConnection();																
		
		String query= " SELECT c2.categoryName as subcategory, c2.categoryId as subcategoryid FROM Category as c1, Category as c2, CategoryRelation WHERE c2.categoryId = CategoryRelation.subCategoryId AND c1.categoryName = '" + parentcategoryname + "' AND c1.categoryId = CategoryRelation.categoryId";  
	
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			System.out.println("output sub catgeory is : " + rs.getString("subcategory"));
			CategoryModel obj = new CategoryModel();
			obj.setCategoryName(rs.getString("subcategory"));
			obj.setCategoryId(rs.getString("subcategoryid"));
			categoryModel.add(obj);
		}
		db.closeConnection(con);
		return categoryModel;
	}
	
	public ArrayList<CategoryModel> getsubcategorydeatils(int parentcategoryId) throws SQLException, IOException
	{
		ArrayList<CategoryModel> categoryModel = new ArrayList<CategoryModel>();
		DBConnectivity db=new DBConnectivity();
		Connection con= db.createConnection();																
		
		String query= " SELECT Category.categoryName, Category.categoryId, Category.image FROM Category, CategoryRelation WHERE Category.categoryId = CategoryRelation.subCategoryId AND CategoryRelation.categoryId =" + parentcategoryId;  
	
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			CategoryModel obj = new CategoryModel();
			obj.setCategoryName(rs.getString("categoryName"));
			obj.setCategoryId(rs.getString("categoryId"));
			obj.setCategoryImage(rs.getString("image"));
			//System.out.println(rs.getString("categoryName"));
			categoryModel.add(obj);
		}
		return categoryModel;
	}
		
	
		public String getfName(String email) throws SQLException
		{
			// TODO Auto-generated method stub
			Connection con = db.createConnection();
			String query="select email,firstName from UserCredantials";
			ResultSet rs=db.executeQuery(query, con);
			String fName=null;
			while(rs.next())
			{
				if(rs.getString("email").equals(email))
				{
					fName=rs.getString("firstName");
			
				}
			}
			System.out.println(fName);
			con.close();
			return fName;
		
		}
	public String chkUserId(String email) throws SQLException
	{
		// TODO Auto-generated method stub
		Connection con = db.createConnection();
		String query="select email,userId from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		String userId=null;
		while(rs.next())
		{
			if(rs.getString("email").equals(email))
			{
				userId=rs.getString("userId");
		
			}
		}
		System.out.println(userId);
		con.close();
		return userId;
	}	
	
	public UserEntry getpersonalinfo(String email) throws SQLException
	{
		// TODO Auto-generated method stub
		DBConnectivity db=new DBConnectivity();
		Connection con=db.createConnection();
		String query="select * from UserCredantials";
		ResultSet rs=db.executeQuery(query, con);
		UserEntry user = new UserEntry();
		while(rs.next())
		{
			if(rs.getString("email").equals(email))
			{
		
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setGender(rs.getString("gender"));
		user.setPhonenumber(rs.getString("phoneNumber"));
		user.setCity(rs.getString("city"));
		user.setPinCode(rs.getInt("pinCode"));
		user.setAddress1(rs.getString("addressLine1"));
		user.setAddress2(rs.getString("addressLine2"));
			}
	}
		return user;
	}
	
	public void updatepersonalinfo(UserEntry user) throws SQLException
	{
		DBConnectivity db=new DBConnectivity();
		Connection con=db.createConnection();

		String query1="UPDATE UserCredantials SET firstName =?,lastName =?, gender =?, phoneNumber =? WHERE email=?";
		PreparedStatement prep1 =con.prepareStatement(query1);
		prep1.setString(1, user.getFirstName());
		prep1.setString(2, user.getLastName());
		prep1.setString(3, user.getGender());
		prep1.setString(4, user.getPhonenumber());
		prep1.setString(5, user.getEmail());
		System.out.println("in db handler for user");
		
		prep1.execute();
		
	}
	
	public void updatepassword(UserEntry user) throws SQLException
	{
		DBConnectivity db=new DBConnectivity();
		Connection con=db.createConnection();

		String query1="UPDATE UserCredantials SET password =? WHERE email=?";
		PreparedStatement prep1 =con.prepareStatement(query1);
		prep1.setString(1, user.getPassword());
		prep1.setString(2, user.getEmail());
		System.out.println("update password");
		
		prep1.execute();
		
	}
	
	public void updateaddress(UserEntry user) throws SQLException
	{
		DBConnectivity db=new DBConnectivity();
		Connection con=db.createConnection();

		String query1="UPDATE UserCredantials SET firstName =?,lastName =?,phoneNumber =?,addressLine1 =?,addressLine2 =?,pinCode =?,city =? WHERE email=?";
		PreparedStatement prep1 =con.prepareStatement(query1);
		prep1.setString(1, user.getFirstName());
		prep1.setString(2, user.getLastName());
		prep1.setString(3, user.getPhonenumber());
		prep1.setString(4, user.getAddress1());
		prep1.setString(5, user.getAddress2());
		prep1.setInt(6, user.getPinCode());
		prep1.setString(7, user.getCity());
		prep1.setString(8, user.getEmail());
		System.out.println("update address");
		
		prep1.execute();
		
	}
	
	public void updateemail(UserEntry user) throws SQLException
	{
		DBConnectivity db=new DBConnectivity();
		Connection con=db.createConnection();

		String query1="UPDATE UserCredantials SET email =? WHERE email=?";
		PreparedStatement prep1 =con.prepareStatement(query1);
		prep1.setString(1, user.getNewemail());
		prep1.setString(2, user.getEmail());
		System.out.println("update email");
		
		prep1.execute();
		
	}
	

	
	public ArrayList<ProductInfo> getproductinfo(int Productid) throws SQLException
	{
		
		//System.out.println("ProductId in dbhandler : " +Productid);
		Connection con = db.createConnection();
		ArrayList<ProductInfo> ProductInfo = new ArrayList<ProductInfo>();	
		String query="select * from ProductInfo where ProductInfo.productId = '" + Productid + "'";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			int discount = 100 - (((rs.getInt("price")-rs.getInt("offer"))*100)/rs.getInt("price"));
			
			ProductInfo obj = new ProductInfo();
			obj.setProductID(rs.getInt("productId"));
			obj.setProductName(rs.getString("productName"));
			obj.setPrice(rs.getInt("price"));
			obj.setImage(rs.getString("image"));
			obj.setOffer(rs.getInt("offer"));
			obj.setCategoryID(rs.getString("categoryId"));
			obj.setDescription(rs.getString("description"));
			obj.setBrand(rs.getString("brand"));
			obj.setWarranty(rs.getInt("warranty"));
			obj.setDiscount(discount);
			ProductInfo.add(obj);
		}
		db.closeConnection(con);
		return ProductInfo;
	}
	
	public ArrayList<ProductInfo> getproductlistoncategoryfilter(String[] brand, ArrayList<String> categoryid, int count) throws SQLException
	{
		Connection con = db.createConnection();
		ArrayList<ProductInfo> ProductInfo = new ArrayList<ProductInfo>();	
		String temp="", query="";
		ResultSet rs;
		for(int i=0; i<count; i++)
		{
			for(int j=0; j<categoryid.size(); j++)
			{
				query += "select ProductInfo.productId, ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Category, Stock where ProductInfo.categoryId = Category.categoryId and  ProductInfo.productId = Stock.productId and Category.categoryId = '" + categoryid.get(j) + "' and ProductInfo.brand = '" + brand[i] + "'"; 
				if(i == (count-1) && j == (categoryid.size()-1))
				{
				}
				else
				{
					query +=" UNION ";
				}
			}
		}
		rs=db.executeQuery(query, con);
		while(rs.next())
		{
			//System.out.println("product is : " +rs.getString("productName") );
			ProductInfo obj = new ProductInfo();
			obj.setProductID(rs.getInt("productId"));
			obj.setProductName(rs.getString("productName"));
			obj.setPrice(rs.getInt("price"));
			obj.setImage(rs.getString("image"));
			obj.setOffer(rs.getInt("offer"));
			obj.setCategoryID(rs.getString("categoryId"));
			obj.setDescription(rs.getString("description"));
			obj.setBrand(rs.getString("brand"));
			obj.setWarranty(rs.getInt("warranty"));
			obj.setMinimumQuantity(rs.getInt("minimumQuantity"));
			obj.setAvailableQuantity(rs.getInt("availableQuantity"));
			ProductInfo.add(obj);
		}
		db.closeConnection(con);
		return ProductInfo;
	}
	
	
	public ArrayList<ProductInfo> getproductlistoncategoryfilterprice(String[] price, ArrayList<String> categoryid, int count) throws SQLException
	{
		Connection con = db.createConnection();
		ArrayList<ProductInfo> ProductInfo = new ArrayList<ProductInfo>();	
		String temp="", query="";
		ResultSet rs;
		for(int i=0; i<count; i++)
		{
			for(int j=0; j<categoryid.size(); j++)
			{
				if(price[i].equalsIgnoreCase("1"))
				{
					query += "select distinct(ProductInfo.productId), ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Stock where ProductInfo.categoryId = '" + categoryid.get(j) + "' and  ProductInfo.productId = Stock.productId and ProductInfo.price BETWEEN 0 AND 2000";
				}
				else if(price[i].equalsIgnoreCase("2"))
				{
					query += "select distinct(ProductInfo.productId), ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Stock where ProductInfo.categoryId = '" + categoryid.get(j) + "' and  ProductInfo.productId = Stock.productId and ProductInfo.price BETWEEN 2001 AND 5000";
				}
				else if(price[i].equalsIgnoreCase("3"))
				{
					query += "select distinct(ProductInfo.productId), ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Stock where ProductInfo.categoryId = '" + categoryid.get(j) + "' and  ProductInfo.productId = Stock.productId and ProductInfo.price BETWEEN 5001 AND 10000";
				}
				else if(price[i].equalsIgnoreCase("4"))
				{
					query += "select distinct(ProductInfo.productId), ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Stock where ProductInfo.categoryId = '" + categoryid.get(j) + "' and  ProductInfo.productId = Stock.productId and ProductInfo.price BETWEEN 10001 AND 18000";
				}
				else if(price[i].equalsIgnoreCase("5"))
				{
					query += "select distinct(ProductInfo.productId), ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Stock where ProductInfo.categoryId = '" + categoryid.get(j) + "' and  ProductInfo.productId = Stock.productId and ProductInfo.price BETWEEN 18000 AND 25000";
				}
				else if(price[i].equalsIgnoreCase("6"))
				{
					query += "select distinct(ProductInfo.productId), ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Stock where ProductInfo.categoryId = '" + categoryid.get(j) + "' and  ProductInfo.productId = Stock.productId and ProductInfo.price BETWEEN 25001 AND 35000";       
				}
				else if(price[i].equalsIgnoreCase("7"))
				{
					query += "select distinct(ProductInfo.productId), ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Stock where ProductInfo.categoryId = '" + categoryid.get(j) + "' and  ProductInfo.productId = Stock.productId and ProductInfo.price > 35000";
				}
				
				if(i == (count-1) && j == (categoryid.size()-1))
				{
				}
				else
				{
					query +=" UNION ";
				}
			}
		}
		rs=db.executeQuery(query, con);
		while(rs.next())
		{
			//System.out.println("product is : " +rs.getString("productName") );
			ProductInfo obj = new ProductInfo();
			obj.setProductID(rs.getInt("productId"));
			obj.setProductName(rs.getString("productName"));
			obj.setPrice(rs.getInt("price"));
			obj.setImage(rs.getString("image"));
			obj.setOffer(rs.getInt("offer"));
			obj.setCategoryID(rs.getString("categoryId"));
			obj.setDescription(rs.getString("description"));
			obj.setBrand(rs.getString("brand"));
			obj.setWarranty(rs.getInt("warranty"));
			obj.setMinimumQuantity(rs.getInt("minimumQuantity"));
			obj.setAvailableQuantity(rs.getInt("availableQuantity"));
			ProductInfo.add(obj);
		}
		db.closeConnection(con);
		return ProductInfo;
	}
		
	
	public ArrayList<Linklists> getlinktothecategory(String category) throws SQLException
	{
		System.out.println("Category in question is : " +category);
		ArrayList<Linklists> Listoflink = new ArrayList<Linklists>();
		Connection con = db.createConnection();
		String query="select c1.categoryName as childCategory, c2.categoryName as parentCategory from Category as c1, Category as c2, CategoryRelation where c2.categoryName = '"+category+"' and c2.categoryId = CategoryRelation.subCategoryId and CategoryRelation.categoryId = c1.categoryId";       
		ResultSet rs=db.executeQuery(query, con);
		if(rs.next() == false)
		{
			query="select distinct(c1.categoryName) as childCategory, c2.categoryName as ParentCategory from Category as c1, Category as c2, CategoryRelation where c1.categoryName = '"+category+"' and c1.categoryId = CategoryRelation.categoryId and CategoryRelation.subCategoryId = c2.categoryId";
			rs=db.executeQuery(query, con);
		}
		else
		{
			rs.previous();
		}
		while(rs.next())
		{
			//System.out.println("product is : " +rs.getString("childCategory"));
			Linklists obj = new Linklists();
			obj.setCategory(rs.getString("parentCategory"));
			obj.setParentCategory(rs.getString("childCategory"));
			Listoflink.add(obj);
		}
		db.closeConnection(con);
		return Listoflink;
	}
	
	public String getnameonid(String id) throws SQLException
	{
		//System.out.println("Id in handler" +id);
		Connection con = db.createConnection();
		String name="";
		String query="select categoryName from Category where categoryId = '"+id+"'";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			//System.out.println("name in handler" +rs.getString("categoryName"));
			name = rs.getString("categoryName");
		}
		//System.out.println("Name is : "+name);
		return name;
	}
	
	public String getidonname(String name) throws SQLException
	{
		//System.out.println("name in handler" +name);
		Connection con = db.createConnection();
		String id="";
		String query="select categoryId from Category where  categoryName = '"+name+"'";
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			id = rs.getString("categoryId");
		}
		//System.out.println("Id is : " +id);
		return id;
	}
	
	public ArrayList<String> getCategoryList(String categoryid) throws SQLException
	{
		ArrayList<String> categoryList = new ArrayList<String>();
		Connection con = db.createConnection();
		String query="Select categoryrelation.subCategoryId from category, categoryrelation where category.categoryId = '"+ categoryid +"'and category.categoryId = categoryrelation.categoryId";     
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			categoryList.add(rs.getString("subCategoryId"));
			//System.out.println(rs.getString("subCategoryId"));
		}
		return categoryList;
	}
	
	public ArrayList<String> getCategoryListwithcategory(String categoryid, String Parentcategory) throws SQLException
	{
		ArrayList<String> categoryList = new ArrayList<String>();
		Connection con = db.createConnection();
		String query="Select categoryrelation.subCategoryId from category as c1, category as c2, categoryrelation where c1.categoryId = '"+ categoryid +"' and c1.categoryId = categoryrelation.categoryId and c2.categoryId = categoryrelation.subCategoryId and c2.categoryName Like '"+ Parentcategory+"%'";     

		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			categoryList.add(rs.getString("subCategoryId"));
			//System.out.println(rs.getString("subCategoryId"));
		}
		return categoryList;
	}
	
	public ArrayList<ProductInfo> getproductlistoncategory(ArrayList<String> category) throws SQLException
	{
		Connection con = db.createConnection();
		ArrayList<ProductInfo> ProductInfo = new ArrayList<ProductInfo>();	
		String query = "";
		
		for(int i=0; i<category.size(); i++)
		{
			String categoryid = category.get(i);
			//System.out.println("category id in ques is : "+categoryid);
			query += "select ProductInfo.productId, ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Category, Stock where ProductInfo.categoryId = Category.categoryId and  ProductInfo.productId = Stock.productId and Category.categoryId = '" + categoryid + "'";    
			if(i<(category.size()-1))
				query += " Union ";
		}
		
		ResultSet rs=db.executeQuery(query, con);
			
		while(rs.next())
		{
				
			//System.out.println("product is : " +rs.getString("productName") );
			ProductInfo obj = new ProductInfo();
			obj.setProductID(rs.getInt("productId"));
			obj.setProductName(rs.getString("productName"));
			obj.setPrice(rs.getInt("price"));
			obj.setImage(rs.getString("image"));
			obj.setOffer(rs.getInt("offer"));
			obj.setCategoryID(rs.getString("categoryId"));
			obj.setDescription(rs.getString("description"));
			obj.setBrand(rs.getString("brand"));
			obj.setWarranty(rs.getInt("warranty"));
			obj.setMinimumQuantity(rs.getInt("minimumQuantity"));
			obj.setAvailableQuantity(rs.getInt("availableQuantity"));
			ProductInfo.add(obj);
		}
	
		
		db.closeConnection(con);
		return ProductInfo;
		
	}
	
	public ArrayList<String> getCompanylistoncategory(String category) throws SQLException
	{
		//System.out.println("category in dbhandler : " +category);
		Connection con = db.createConnection();
		ArrayList<String> companyname = new ArrayList<String>();	
		String query="select distinct(ProductInfo.brand) from ProductInfo, Category where Category.categoryId = ProductInfo.categoryId and Category.categoryName = '" + category + "'";       
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			//System.out.println("product is : " +rs.getString("brand") );
			companyname.add(rs.getString("brand"));
		}
		db.closeConnection(con);
		return companyname;
	}
	
	public ArrayList<String> getCompanylistoncategory(ArrayList<String> category) throws SQLException
	{
		//System.out.println("category in dbhandler : " +category);
		Connection con = db.createConnection();
		ArrayList<String> companyname = new ArrayList<String>();	
		String query="";
		for(int i=0; i<category.size(); i++)
		{
			query += "select distinct(ProductInfo.brand) from ProductInfo where ProductInfo.categoryId = '" + category.get(i) + "'";       
			if(i<(category.size()-1))
				query += " union ";
		}
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			//System.out.println("product is : " +rs.getString("brand") );
			companyname.add(rs.getString("brand"));
		}
		db.closeConnection(con);
		return companyname;
	}
	
	public ArrayList<ProductInfo> getproductlist(String keyword) throws SQLException
	{
		Connection con = db.createConnection();
		ArrayList<ProductInfo> ProductInfo = new ArrayList<ProductInfo>();	
		String query="select ProductInfo.productId, ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Keywords, Stock where ProductInfo.productId = Keywords.productId and  ProductInfo.productId = Stock.productId and Keywords.keyword = '" + keyword + "'";       
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			ProductInfo obj = new ProductInfo();
			obj.setProductID(rs.getInt("productId"));
			obj.setProductName(rs.getString("productName"));
			obj.setPrice(rs.getInt("price"));
			obj.setImage(rs.getString("image"));
			obj.setOffer(rs.getInt("offer"));
			obj.setCategoryID(rs.getString("categoryId"));
			obj.setDescription(rs.getString("description"));
			obj.setBrand(rs.getString("brand"));
			obj.setWarranty(rs.getInt("warranty"));
			obj.setMinimumQuantity(rs.getInt("minimumQuantity"));
			obj.setAvailableQuantity(rs.getInt("availableQuantity"));
			ProductInfo.add(obj);
		}
		db.closeConnection(con);
		return ProductInfo;
	}
	
	public String getcategoryId(String keyword) throws SQLException
	{
		//System.out.println("keyword in dbhandler : " +keyword);
		Connection con = db.createConnection();
		String categoryid="";
		String query="select ProductInfo.categoryId from ProductInfo, Keywords, Stock where ProductInfo.productId = Keywords.productId and  ProductInfo.productId = Stock.productId and Keywords.keyword = '" + keyword + "'";       
		ResultSet rs=db.executeQuery(query, con);
		
		rs.next();
		categoryid = (rs.getString("categoryId"));
		db.closeConnection(con);
		return categoryid;
	}
	
	public ArrayList<String> getCompanylist(String keyword) throws SQLException
	{
		//System.out.println("keyword in dbhandler : " +keyword);
		Connection con = db.createConnection();
		ArrayList<String> companyname = new ArrayList<String>();	
		String query="select distinct(ProductInfo.brand) from ProductInfo, Keywords where ProductInfo.productId = Keywords.productId and Keywords.keyword = '" + keyword + "'";       
		ResultSet rs=db.executeQuery(query, con);
		
		while(rs.next())
		{
			//System.out.println("product is : " +rs.getString("brand") );
			companyname.add(rs.getString("brand"));
		}
		db.closeConnection(con);
		return companyname;
	}
	
	public ArrayList<ProductInfo> getproductinfoforcomparison(ArrayList<CompareCartProduct> cartProducts) throws SQLException
	{
		Connection con = db.createConnection();
		ArrayList<ProductInfo> ProductInfo = new ArrayList<ProductInfo>();	
		
		for(CompareCartProduct p : cartProducts)
		{
		System.out.println("db category"+p.getCategory());
		System.out.println("db product"+p.getProductId());
		String query="select ProductInfo.productId, ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Category, Stock where ProductInfo.categoryId = Category.categoryId and  ProductInfo.productId = Stock.productId and Category.categoryId = '" + p.getCategory() + "' and ProductInfo.productId = '" + p.getProductId() + "'" ;       
		ResultSet rs=db.executeQuery(query, con);
		
			
		while(rs.next())
		{
			ProductInfo obj = new ProductInfo();
			//System.out.println("product is : " +rs.getString("productName") );
			obj.setProductID(rs.getInt("productId"));
			obj.setProductName(rs.getString("productName"));
			obj.setPrice(rs.getInt("price"));
			obj.setImage(rs.getString("image"));
			obj.setOffer(rs.getInt("offer"));
			obj.setCategoryID(rs.getString("categoryId"));
			obj.setDescription(rs.getString("description"));
			obj.setBrand(rs.getString("brand"));
			obj.setWarranty(rs.getInt("warranty"));
			obj.setMinimumQuantity(rs.getInt("minimumQuantity"));
			obj.setAvailableQuantity(rs.getInt("availableQuantity"));
			ProductInfo.add(obj);
		}
			
		}
		db.closeConnection(con);
		for(int i=0;i<ProductInfo.size();i++)
		{
			System.out.println("db wala"+ProductInfo.get(i).getImage());
		}
		return ProductInfo;
	}
	
	public ArrayList<ProductInfo> getProductInfoByName(String productname) throws SQLException 
	{
		
		Connection con = db.createConnection();
		ArrayList<ProductInfo> productInfoAdded = new ArrayList<ProductInfo>();	
		String query="select ProductInfo.productId, ProductInfo.productName, ProductInfo.price, ProductInfo.image, ProductInfo.offer, ProductInfo.categoryId, ProductInfo.description, ProductInfo.brand, ProductInfo.warranty, Stock.availableQuantity, Stock.minimumQuantity from ProductInfo, Stock where  ProductInfo.productId = Stock.productId and ProductInfo.productName = '" + productname + "'" ;       
		      
		ResultSet rs=db.executeQuery(query, con);
		System.out.println("hello1");
		ProductInfo obj = new ProductInfo();
		while(rs.next())
		{
			
			obj.setProductID(rs.getInt("productId"));
			obj.setProductName(rs.getString("productName"));
			obj.setPrice(rs.getInt("price"));
			obj.setImage(rs.getString("image"));
			obj.setOffer(rs.getInt("offer"));
			obj.setCategoryID(rs.getString("categoryId"));
			obj.setDescription(rs.getString("description"));
			obj.setBrand(rs.getString("brand"));
			obj.setWarranty(rs.getInt("warranty"));
			obj.setMinimumQuantity(rs.getInt("minimumQuantity"));
			obj.setAvailableQuantity(rs.getInt("availableQuantity"));
			productInfoAdded.add(obj);
		}
		
		db.closeConnection(con);
		System.out.println("hello2");
		return productInfoAdded;		}

	public ArrayList<String>  getproductsforcomparison(ArrayList<CompareCartProduct> cartProducts) throws SQLException 
	{
		Connection con = db.createConnection();
					
		ArrayList<String> categoryproducts = new ArrayList<String>();	
		
		for(CompareCartProduct p : cartProducts)
		{	
			
		String query="select ProductInfo.productId, ProductInfo.productName from FlipKartDatabase.ProductInfo where ProductInfo.categoryId =  " + p.getCategory() ;       
		ResultSet rs=db.executeQuery(query, con);
		System.out.println("hello1");
		while(rs.next())
		{
			categoryproducts.add(rs.getString("productName"));
		}
		break;
		}
		System.out.println("hello2");
		for (String i : categoryproducts)
		System.out.println(i);
		
		db.closeConnection(con);
		
		return categoryproducts;		
	}
	
	public void savePlaceOrderDetails() 
	{
		String status = "PLACED";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		Calendar cal = Calendar.getInstance();
		cal.set(2222, 11, 31);
		java.util.Date deliveryDate = (java.util.Date) cal.getTime();
		Connection con = db.createConnection();
		String query="INSERT INTO  FlipKartDatabase.Order (status, orderDate, deliveryDate) VALUES (?, ?, ?);";
		PreparedStatement prep;
		try {
			prep = con.prepareStatement(query);
			prep.setString(1, status);
			prep.setString(2, sdf.format(new java.util.Date()));
			prep.setString(3, sdf.format((deliveryDate)));		
			prep.execute();		
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int saveOrderAddressDetails( custometAddressDetail addressDetail) throws SQLException
	{
		Connection con = db.createConnection();
		DBConnectivity db = new DBConnectivity();	
		int  orderId = 0;
		String query="Select MAX(orderId) as orderId FROM FlipKartDatabase.Order;";
		ResultSet rs = db.executeQuery(query, con);			
		while(rs.next())
		{
			orderId = Integer.parseInt( rs.getString("orderId"));
		}
		
		Connection con1 = db.createConnection();
		String query1 = "INSERT INTO FlipKartDatabase.OrderShipingAddress (orderID, customerName, customerEmail, addressLine1," +
				" addressLine2,  city, pinCode, customerPhoneNumber) Values (?, ?, ?, ?, ?, ?, ?, ?);" ;
		PreparedStatement prep = con1.prepareStatement(query1);		
		prep.setInt(1, orderId);
		prep.setString(2, addressDetail.getName());
		prep.setString(3, addressDetail.getEmail());
		prep.setString(4, addressDetail.getAddressLine1());
		prep.setString(5, addressDetail.getAddressLine2());
		prep.setString(6, addressDetail.getCity());
		prep.setString(7, addressDetail.getPinCode());
		prep.setString(8, addressDetail.getPhoneNumber());		
		prep.execute();	
		con1.close();	
		return orderId;
		
	}	
	
	
	public void saveUserOrderDescription( customerCartDetail cart) throws SQLException
	{
		DBConnectivity db = new DBConnectivity();
		Connection con = db.createConnection();
		Connection con1 = db.createConnection();
		int  orderId = 0;
		String query="Select MAX(orderId) as orderId FROM FlipKartDatabase.Order;";
		ResultSet rs = db.executeQuery(query, con);			
		while(rs.next())
		{
			orderId = Integer.parseInt( rs.getString("orderId"));
		}		
		con.close();
		String query1 = " INSERT INTO FlipKartDatabase.OrderDescription (orderID, productId, quantity, price) Values (?, ?, ?, ?)" ;
		PreparedStatement prep = con1.prepareStatement(query1);		
		prep.setInt(1, orderId);
		prep.setInt(2, cart.getProductID());
		prep.setInt(3, cart.getQuantity() );
		prep.setFloat(4, Float.parseFloat( cart.getPrice() ) );
		prep.execute();	
		con1.close();			
	}
	
	public void clearUserCart(String email) throws NumberFormatException, SQLException
	{
		DBConnectivity db = new DBConnectivity();
		Connection con = db.createConnection();
		Connection con1 = db.createConnection();
		int  userId = 0;	
		
		String query="SELECT userId FROM FlipKartDatabase.UserCredantials WHERE email = '" + email + "';" ;
		ResultSet rs = db.executeQuery(query, con);			
		while(rs.next())
		{
			userId = Integer.parseInt( rs.getString("userId"));
		}		
		con.close();
		String query1 = " DELETE FROM FlipKartDatabase.Cart WHERE userID = " + userId + ";" ;		
		Statement st=(Statement) con1.createStatement();
		st.executeUpdate(query1);				
		con1.close();			
	}
	
	public custometAddressDetail getUserAddressDetail(String email) throws SQLException
	{
		Connection con = db.createConnection();
		DBConnectivity db = new DBConnectivity();
		custometAddressDetail addressDetails = new custometAddressDetail();	
		String query="SELECT  CONCAT(firstName, ' ', lastName) as name, addressLine1, addressLine2, pinCode,phoneNumber, city FROM `FlipKartDatabase`.`UserCredantials` WHERE email = '" + email + "' " ;
		ResultSet rs = db.executeQuery(query, con);			
		while(rs.next())
		{				
			addressDetails.setName(rs.getString("name"));
			addressDetails.setPhoneNumber(rs.getString("phoneNumber"));
			addressDetails.setEmail(email);				
			addressDetails.setPinCode(rs.getString("pinCode"));
			addressDetails.setAddressLine1(rs.getString("addressLine1"));
			addressDetails.setAddressLine2(rs.getString("addressLine2"));
			addressDetails.setCity(rs.getString("city"));				
		}			
		db.closeConnection(con);
		return addressDetails;
	}
	
	public ArrayList<customerCartDetail>  getCartTableDetail(String email) throws SQLException
	{
		ArrayList<customerCartDetail> cartDetailsList = new ArrayList<customerCartDetail>();
		
		Connection con = db.createConnection();
		DBConnectivity db = new DBConnectivity();	
		
		String query="SELECT P.image as image, P.productName as productName,P.productId  as productId, C.quantity as quantity, (P.price -P.offer) as price FROM FlipKartDatabase.UserCredantials AS U INNER JOIN FlipKartDatabase.Cart AS C  ON C.useriD = U.userId INNER JOIN FlipKartDatabase.ProductInfo AS P    ON P.productId = C.productId WHERE email =  '" + email + "' " ;
		ResultSet rs = db.executeQuery(query, con);			
		while(rs.next())
		{
			customerCartDetail cartDetail = new customerCartDetail();					
			cartDetail.setImage(rs.getString("image"));				
			cartDetail.setProductName(rs.getString("productName"));
			cartDetail.setProductID( Integer.parseInt( rs.getString("productId") ) );
			cartDetail.setQuantity(Integer.parseInt(rs.getString("quantity") ));				
			cartDetail.setPrice(rs.getString("price"));	
			cartDetail.setSubTotal(	Float.toString(Float.parseFloat( cartDetail.getPrice() ) * (  cartDetail.getQuantity()	 )	)  );
			cartDetailsList.add(cartDetail);				
		}		
		db.closeConnection(con);
		return cartDetailsList;
	}

	
	public ArrayList<customerCartDetail>  getCartCokkiesDetail( ArrayList<CartProduct>  cartDetails) throws SQLException
	{
		ArrayList<customerCartDetail> cartDetailsList = new ArrayList<customerCartDetail>();			
		Connection con = db.createConnection();
		
		for(CartProduct p : cartDetails)
		{		
			String query = "SELECT P.image as image, P.productId as productId, P.productName as productName, (P.price - P.offer) as price FROM FlipKartDatabase.ProductInfo    as P WHERE P.productId = "+p.getProductId()+";";
			ResultSet rs=db.executeQuery(query, con);
			while(rs.next())
			{
				customerCartDetail cartDetail = new customerCartDetail();									
				cartDetail.setImage(rs.getString("image"));				
				cartDetail.setProductName(rs.getString("productName"));
				cartDetail.setProductID(Integer.parseInt(rs.getString("productId")));
				cartDetail.setQuantity(p.getQuantity());		
				cartDetail.setPrice(rs.getString("price"));	
				cartDetail.setSubTotal(	Float.toString(Float.parseFloat( cartDetail.getPrice() ) * (  cartDetail.getQuantity()	 )	)  );				
				cartDetailsList.add(cartDetail);
			}
		}
		
		
		db.closeConnection(con);					
		return cartDetailsList;
	}
	
	public String verifyCardDetails(String cardNumber, String expireMonth, String expireYear, String cvv) throws SQLException
	{		
		String bankName = null ;
		Connection con = db.createConnection();
		DBConnectivity db = new DBConnectivity();
		
		String query = "SELECT bankName FROM FlipKartDatabase.BankDetails WHERE creditCardNumber = " +cardNumber + " AND MONTH(expiryDate) = '" + expireMonth + "' AND YEAR(expiryDate) = '" + expireYear +" ' AND cvv = '" + cvv + "';";
		
		ResultSet rs=db.executeQuery(query, con);
		if(rs.next())
		{
			bankName = rs.getString("bankName");
		}
		
		return bankName;
	}

	public void insertOrderPaymentDetails(String orderId, String bankName, String grandTotal) throws SQLException {
		// TODO Auto-generated method stub
		DBConnectivity db = new DBConnectivity();
		Connection con = db.createConnection();
		String query1 = " INSERT INTO  FlipKartDatabase.Payment (orderID, bank, paymentType, amount, paymentDate) VALUES (?, ?, ?, ?, ?);" ;
		PreparedStatement prep = con.prepareStatement(query1);		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		prep.setInt(1, Integer.parseInt(orderId) );
		prep.setString(2, bankName);
		prep.setString(3, "Credit Card" );
		prep.setFloat(4, Float.parseFloat( grandTotal ) );
		prep.setString(5, sdf.format(new java.util.Date()));
		prep.execute();	
		con.close();			
	}	
	
	public void updatePaymentInAccount(String cardNumber, String grandTotal) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("CardNumber : " + cardNumber + " grandTotal : " + grandTotal);
		DBConnectivity db = new DBConnectivity();
		Connection con = db.createConnection();		
		Statement stmt = (Statement) con.createStatement();
		
		String query1 = "UPDATE FlipKartDatabase.BankDetails SET balance = balance - " + grandTotal + " WHERE creditCardNumber = " + cardNumber;
		 int rows = stmt.executeUpdate(query1);
		 System.out.println("Rowa effected by q1 : " + rows);
		String query2 = "UPDATE FlipKartDatabase.BankDetails SET balance = balance + " + grandTotal + " WHERE creditCardNumber = 1";
		rows = stmt.executeUpdate(query2);	
		 System.out.println("Rowa effected by q2 : " + rows);
		con.close();			
	}

	public void updateProductQuantityAfterPurchase(String orderId) throws SQLException {
		// TODO Auto-generated method stub
		int pId  = 0;
		int qty = 0;
		String query1;
		String query = "SELECT productId , quantity from FlipKartDatabase.OrderDescription where orderID = "+orderId ;
		Connection con = db.createConnection();
		DBConnectivity db = new DBConnectivity();
		ResultSet rs=db.executeQuery(query, con);
		while(rs.next())
		{
			pId = rs.getInt("productId");
			qty = rs.getInt("quantity");
			query1 = "Update FlipKartDatabase.Stock Set availableQuantity = availableQuantity - " + qty + " where productId = " + pId ;
			Statement stmt = (Statement) con.createStatement();
			stmt.executeUpdate(query1);
		}
		System.out.println("PID : " + pId + "quantity : " + qty);
	}	

}

