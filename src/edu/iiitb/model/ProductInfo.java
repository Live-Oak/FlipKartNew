package edu.iiitb.model;

import java.io.*;



public class ProductInfo {
	
	
	int productID,offer,warranty,price;
	String productName,categoryID,keywords,description,brand;
	int discount;
	String sellerFName, sellerLName;
	String image;
	File myFile;
	String myFileFileName,myFileContentType,sellerID;
	int minimumQuantity;
	int availableQuantity;
	

	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public String getSellerFName() {
		return sellerFName;
	}
	public void setSellerFName(String sellerFName) {
		this.sellerFName = sellerFName;
	}
	public String getSellerLName() {
		return sellerLName;
	}
	public void setSellerLName(String sellerLName) {
		this.sellerLName = sellerLName;
	}
	public int getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	/**
	 * @return the minimumQuantity
	 */
	public int getMinimumQuantity() {
		return minimumQuantity;
	}
	/**
	 * @param minimumQuantity the minimumQuantity to set
	 */
	public void setMinimumQuantity(int minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
	}
	/**
	 * @return the sellerID
	 */
	public String getSellerID() {
		return sellerID;
	}
	/**
	 * @param sellerID the sellerID to set
	 */
	public void setSellerID(String sellerID) {
		String[] split = sellerID.split("_");
		this.sellerID = split[0];
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the myFile
	 */
	public File getMyFile() {
		return myFile;
	}
	/**
	 * @param myFile the myFile to set
	 */
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	/**
	 * @return the myFileFileName
	 */
	public String getMyFileFileName() {
		return myFileFileName;
	}
	/**
	 * @param myFileFileName the myFileFileName to set
	 */
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	/**
	 * @return the myFileContentType
	 */
	public String getMyFileContentType() {
		return myFileContentType;
	}
	/**
	 * @param myFileContentType the myFileContentType to set
	 */
	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getOffer() {
		return offer;
	}
	public void setOffer(int offer) {
		this.offer = offer;
	}
	public int getWarranty() {
		return warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		String[] idName = categoryID.split("_");
		this.categoryID = idName[0];
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords+",";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
}
