<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
	<script src="asset/JavaScripts/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<sx:head />
</head>
<body>
<center>
	

<h1 align="center">REGISTER NEW PRODUCT/ITEM</h1>
<hr>
<i><h3 align="center"> Please Fill The Below Details:-</h3> </i>
	<div align="center">
	<form action="insertProduct" method="post" enctype="multipart/form-data">
		<label>Product ID</label><s:textfield cssErrorStyle="color:red;" name ="productID" required="true" placeholder="Product ID"></s:textfield><br>
		<label>Product Name</label><s:textfield name ="productName" required="true" placeholder="Product Name"></s:textfield><br>
		<label>Product Price - Rs.</label><s:textfield name ="price" required="true" placeholder="only numerical value"></s:textfield><br>
		<label>Any Offers on the product</label><s:textfield name ="offer" required="true" placeholder="only numerical value"></s:textfield><br>
		<label>Offer End Date </label>	<sx:datetimepicker name="lastOfferDate" displayFormat="dd-MM-yyyy" />	<br>
		<label>Product Image</label><input type="file" name ="myFile" required="true" placeholder="Select image to be uploaded" /><br>
		<label>Category ID</label>
						
						<select id="dropDown" name="categoryID">
						
						<s:iterator value="categoryId">
						<option><s:property/></option>		
						</s:iterator>		
						</select><br>	
		<label>Seller ID</label>
						
						<select id="dropDown" name="sellerID">
						
						<s:iterator value="sellerId">
						<option><s:property/></option>		
						</s:iterator>		
						</select><br>	
		<label>MinimumQuantity</label ><s:textfield name ="minimumQuantity" required="true" placeholder="minimumQuantity"></s:textfield><br>							
		<label>Keywords</label ><s:textfield name ="keywords" required="true" placeholder="keywords"></s:textfield><br>
		<label>Description about the product</label><s:textfield name ="description" required="true" placeholder="Describe the Item/Product"></s:textfield><br>
		<label>Brand Name</label><s:textfield name ="brand" required="true" placeholder="Brand"></s:textfield><br>
		<label>Warranty/Gurantee(in terms of years or months)</label><s:textfield name ="warranty" required="true" placeholder="Any Warranty/Gurantee"></s:textfield><br>
		
		<input type="submit" value="Submit"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
			<input type="reset" name="Reset"/>
			<br><br>
			<font color ="red">${errorMessage}</font>
			
</form>
</div>
</center>
</body>
</html>