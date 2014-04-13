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
	ArrayList<CategoryModel> categoryModel1, categoryModel11, categoryModel2, categoryModel3, categoryModel4, categoryModel5, categoryModel6, categoryModel7;
	
	int[] value = new int[4];
	int category;
	String parentcategory;
	
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
	
	public ArrayList<CategoryModel> getCategoryModel4() {
		return categoryModel4;
	}

	public void setCategoryModel4(ArrayList<CategoryModel> categoryModel4) {
		this.categoryModel4 = categoryModel4;
	}

	public ArrayList<CategoryModel> getCategoryModel5() {
		return categoryModel5;
	}

	public void setCategoryModel5(ArrayList<CategoryModel> categoryModel5) {
		this.categoryModel5 = categoryModel5;
	}

	public ArrayList<CategoryModel> getCategoryModel6() {
		return categoryModel6;
	}

	public void setCategoryModel6(ArrayList<CategoryModel> categoryModel6) {
		this.categoryModel6 = categoryModel6;
	}

	public ArrayList<CategoryModel> getCategoryModel7() {
		return categoryModel7;
	}

	public void setCategoryModel7(ArrayList<CategoryModel> categoryModel7) {
		this.categoryModel7 = categoryModel7;
	}

	public ArrayList<CategoryModel> getCategoryModel1() {
		return categoryModel1;
	}

	public void setCategoryModel1(ArrayList<CategoryModel> categoryModel1) {
		this.categoryModel1 = categoryModel1;
	}

	public ArrayList<CategoryModel> getCategoryModel2() {
		return categoryModel2;
	}

	public void setCategoryModel2(ArrayList<CategoryModel> categoryModel2) {
		this.categoryModel2 = categoryModel2;
	}

	public ArrayList<CategoryModel> getCategoryModel3() {
		return categoryModel3;
	}

	public void setCategoryModel3(ArrayList<CategoryModel> categoryModel3) {
		this.categoryModel3 = categoryModel3;
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
			categoryModel2 = dbHandlerForUser.getsubcategorylist(category);
			categoryModel3 = dbHandlerForUser.getsubcategorylist(category);
			categoryModel4 = dbHandlerForUser.getsubcategorylist(category);
			categoryModel5 = dbHandlerForUser.getsubcategorylist(category);
			categoryModel6 = dbHandlerForUser.getsubcategorylist(category);
			categoryModel7 = dbHandlerForUser.getsubcategorylist(category);
		}
		catch(Exception e)
		{
			System.out.println("Exception at getMenuCategory() of StartAction.java");
		}
		return "success";
	}
	
	public String getSubMenuCatagory()
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		try
		{
			System.out.println("parent category in question is : " +parentcategory);
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
	
}
