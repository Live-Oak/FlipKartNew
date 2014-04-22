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
	int[] value = new int[4];
	int category;
	String parentcategoryname;
	ArrayList<String> categoryList, categoryListtemp;
	ArrayList<CategoryModel>[] categoryModel = (ArrayList<CategoryModel>[])new ArrayList[7];
	ArrayList<CategoryModel>[] subcategoryModel = (ArrayList<CategoryModel>[])new ArrayList[50];
	
	public String getParentcategoryname() {
		return parentcategoryname;
	}

	public void setParentcategoryname(String parentcategoryname) {
		this.parentcategoryname = parentcategoryname;
	}

	public ArrayList<CategoryModel>[] getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(ArrayList<CategoryModel>[] categoryModel) {
		this.categoryModel = categoryModel;
	}

	public ArrayList<CategoryModel>[] getSubcategoryModel() {
		return subcategoryModel;
	}

	public void setSubcategoryModel(ArrayList<CategoryModel>[] subcategoryModel) {
		this.subcategoryModel = subcategoryModel;
	}

	public ArrayList<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<String> categoryList) {
		this.categoryList = categoryList;
	}

	public ArrayList<String> getCategoryListtemp() {
		return categoryListtemp;
	}

	public void setCategoryListtemp(ArrayList<String> categoryListtemp) {
		this.categoryListtemp = categoryListtemp;
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
		int counter;
		categoryListtemp = new ArrayList<String>();
		String[] categoryid = {"01", "02", "03"};
		
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
	
		try
		{
			advertizement = dbHandlerForUser.getadvertizement("carousel","4");
			for(int i=0; i<advertizement.size(); i++)
			{
				value[i] = advertizement.get(i).getProductId();
			}
			
		for(int loop=0; loop<3; loop++)
		{
			
			categoryList = new ArrayList<String>();
			// Intialize the new arraylist
			
			categoryList.add(categoryid[loop]);
			// add it to the main list
			
			categoryListtemp = dbHandlerForUser.getCategoryList(categoryid[loop]);
			// get the sub category list for the first time
			
			for(int i=0; i<categoryListtemp.size(); i++)
			{
				//System.out.println("value in category list is : " + categoryList.get(i));
				categoryList.add(categoryListtemp.get(i));
			}
			// add it to the main list

				// get the sub-sub category list if present
				for(int i=0; i<categoryList.size()-1; i++)
				{
					categoryListtemp = dbHandlerForUser.getCategoryList(categoryList.get(i+1));
					if(categoryListtemp.size() > 0)
					{
						for(int j=0; j<categoryListtemp.size(); j++)
						{
							// add it to the main list
							categoryList.add(categoryListtemp.get(j));
						}
					}
				}
			if(loop == 0)
				advertizementelectronics = dbHandlerForUser.getadvertizementforfront("general",categoryList);
			else if(loop == 1)
				advertizementfashion = dbHandlerForUser.getadvertizementforfront("general",categoryList);
			else if(loop == 2)
				advertizementbook = dbHandlerForUser.getadvertizementforfront("general",categoryList);
		}
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
	
	public String getMenu()
	{
		DBHandlerForUser dbHandlerForUser = new DBHandlerForUser();
		int k,j=0,prev=0;
		int [] categoryidlist = {1, 12, 13, 14, 3, 34, 41};
		try
		{
			for(int i=0; i<categoryidlist.length; i++)
			{
				categoryModel[i] = new ArrayList<CategoryModel>();					
				dbHandlerForUser.getsubcategorylist(categoryidlist[i],categoryModel[i]);						
				// This level will calculate value for the first level
				k=0;
				while(j<(categoryModel[i].size()+prev))
				{
					// This level will calculate value for the second level
					subcategoryModel[j] = new ArrayList<CategoryModel>();
					if(categoryidlist[i] == 12)
					{
						// Check for men
						parentcategoryname = dbHandlerForUser.getnameonid("12");
						subcategoryModel[j] = dbHandlerForUser.getsubcategorylistancestor(categoryModel[i].get(k).getCategoryId(), parentcategoryname);
					}
					else if(categoryidlist[i] == 13)
					{
						// Check for women
						parentcategoryname = dbHandlerForUser.getnameonid("13");
						subcategoryModel[j] = dbHandlerForUser.getsubcategorylistancestor(categoryModel[i].get(k).getCategoryId(), parentcategoryname);
					}
					else
					{
						// for any other menu
						subcategoryModel[j] = dbHandlerForUser.getsubsubcategorylist(categoryModel[i].get(k).getCategoryId());
					}
					k++;
					j++;
				}
				prev = j;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception at getMenu() of StartAction.java");
			return "error";
		}
		return "success";
	}
	
}
