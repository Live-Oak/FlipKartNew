package edu.iiitb.action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

import edu.iiitb.database.DBHandlerForUser;
import edu.iiitb.model.Advertizement;
import edu.iiitb.model.CategoryModel;

public class StartAction extends ActionSupport 
{
	ArrayList<Advertizement> advertizement, advertizementelectronics, advertizementfashion, advertizementbook, dealoftheday, advertizementsidebar;
	ArrayList<CategoryModel> Categoryelectronics, Categorybooks, Categoryfashion;
	ArrayList<CategoryModel> categoryModel1, categoryModel11, categoryModel12;
	int[] value = new int[4];
	int category;
	String parentcategory, ancestorid, ancestorname,parentcategoryid;
	
	public ArrayList<CategoryModel> getCategoryModel12() {
		return categoryModel12;
	}

	public void setCategoryModel12(ArrayList<CategoryModel> categoryModel12) {
		this.categoryModel12 = categoryModel12;
	}
	
	public String getParentcategoryid() {
		return parentcategoryid;
	}

	public void setParentcategoryid(String parentcategoryid) {
		this.parentcategoryid = parentcategoryid;
	}

	public String getAncestorname() {
		return ancestorname;
	}

	public void setAncestorname(String ancestorname) {
		this.ancestorname = ancestorname;
	}

	public String getAncestorid() {
		return ancestorid;
	}

	public void setAncestorid(String ancestorid) {
		this.ancestorid = ancestorid;
	}

	public ArrayList<Advertizement> getAdvertizementsidebar() {
		return advertizementsidebar;
	}

	public void setAdvertizementsidebar(
			ArrayList<Advertizement> advertizementsidebar) {
		this.advertizementsidebar = advertizementsidebar;
	}

	public ArrayList<Advertizement> getDealoftheday() {
		return dealoftheday;
	}

	public void setDealoftheday(ArrayList<Advertizement> dealoftheday) {
		this.dealoftheday = dealoftheday;
	}

	public ArrayList<CategoryModel> getCategoryfashion() {
		return Categoryfashion;
	}

	public void setCategoryfashion(ArrayList<CategoryModel> categoryfashion) {
		Categoryfashion = categoryfashion;
	}

	public ArrayList<CategoryModel> getCategorybooks() {
		return Categorybooks;
	}

	public void setCategorybooks(ArrayList<CategoryModel> categorybooks) {
		Categorybooks = categorybooks;
	}
 
	public int[] getValue() {
		return value;
	}

	public void setValue(int[] value) {
		this.value = value;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
	
	public ArrayList<CategoryModel> getCategoryModel1() {
		return categoryModel1;
	}

	public void setCategoryModel1(ArrayList<CategoryModel> categoryModel1) {
		this.categoryModel1 = categoryModel1;
	}

	public ArrayList<CategoryModel> getCategoryModel11() {
		return categoryModel11;
	}

	public void setCategoryModel11(ArrayList<CategoryModel> categoryModel11) {
		this.categoryModel11 = categoryModel11;
	}

	public ArrayList<Advertizement> getAdvertizement() {
		return advertizement;
	}

	public void setAdvertizement(ArrayList<Advertizement> advertizement) {
		this.advertizement = advertizement;
	}
	
	public ArrayList<Advertizement> getAdvertizementelectronics() {
		return advertizementelectronics;
	}

	public void setAdvertizementelectronics(
			ArrayList<Advertizement> advertizementelectronics) {
		this.advertizementelectronics = advertizementelectronics;
	}

	public ArrayList<Advertizement> getAdvertizementfashion() {
		return advertizementfashion;
	}

	public void setAdvertizementfashion(
			ArrayList<Advertizement> advertizementfashion) {
		this.advertizementfashion = advertizementfashion;
	}

	public String getParentcategory() {
		return parentcategory;
	}

	public void setParentcategory(String parentcategory) {
		this.parentcategory = parentcategory;
	}

	public ArrayList<Advertizement> getAdvertizementbook() {
		return advertizementbook;
	}

	public void setAdvertizementbook(ArrayList<Advertizement> advertizementbook) {
		this.advertizementbook = advertizementbook;
	}

	public ArrayList<CategoryModel> getCategoryelectronics() {
		return Categoryelectronics;
	}

	public void setCategoryelectronics(ArrayList<CategoryModel> categoryelectronics) {
		Categoryelectronics = categoryelectronics;
	}
	
	public String execute()
	{
		
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
	
		try
		{
			advertizement = dbHandlerForUser.getadvertizement("carousel","4");
			for(int i=0; i<advertizement.size(); i++)
			{
				value[i] = advertizement.get(i).getProductId();
			}
			advertizementelectronics = dbHandlerForUser.getadvertizementforfront("general","01");
			advertizementfashion = dbHandlerForUser.getadvertizementforfront("general","02");
			advertizementbook = dbHandlerForUser.getadvertizementforfront("general","03");
			advertizementsidebar= dbHandlerForUser.getadvertizement("sidebar","2");
			dealoftheday= dbHandlerForUser.getadvertizement("dealoftheday","1");
			Categoryelectronics = dbHandlerForUser.getsubcategorydeatils(1);
			Categorybooks = dbHandlerForUser.getsubcategorydeatils(3);
			Categoryfashion = dbHandlerForUser.getsubcategorydeatils(2);
		}
		catch(Exception e)
		{
			System.out.println("Error Start Action "+e);
			return "error";
		}
		return "success";
	}
	
	
	
	public String getMenuCatagory()
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		try
		{
			categoryModel1 = dbHandlerForUser.getsubcategorylist(category);
		}
		catch(Exception e)
		{
			System.out.println("Exception at getMenuCategory() of StartAction.java");
			return "error";
		}
		return "success";
	}

	public String getSubMenuCatagory()
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		try
		{
			//System.out.println("parent category in question is : " +parentcategory);
			if (parentcategory != null)
			{
				categoryModel11 = dbHandlerForUser.getsubsubcategorylist(parentcategory);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception at getMenuCategory() of StartAction.java");
		}
		return "success";
	}
	
	public String getSubMenuCatagorylevel()
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		try
		{
			//System.out.println("id is " +ancestorid);
			//System.out.println("parent category is : "+parentcategory);
			ancestorname = dbHandlerForUser.getnameonid(ancestorid);
			parentcategoryid = dbHandlerForUser.getidonname(parentcategory);
			categoryModel12 = dbHandlerForUser.getsubcategorylistancestor(parentcategoryid, ancestorname);
		}
		catch(Exception e)
		{
			System.out.println("Exception at getMenuCategory() of StartAction.java");
			return "error";
		}
		return "success";
	}
	
}
