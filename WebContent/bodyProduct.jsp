<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Body Product</title>
	<!-- Custom styles for this template -->
	<!-- Custom styles for this template -->
	<link href="asset/CSS/Index.css" rel="stylesheet">
	<link href="asset/CSS/starter-template.css" rel="stylesheet">
	
	<!-- Bootstrap core CSS -->
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	
	<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">	
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	

</head>
<body>
	<div class="container">
	<div class="col-md-2 "></div>
	
	
	<div class="col-md-9">
		<div class="background">
		<div class="container">
			<div class="col-md-6">
					<br>
					<center>
						<s:iterator value="productinfo">
								<img src="<s:property value="image"/>" alt="<s:property value="productID"/>"><br>
						</s:iterator>
					</center>
			</div>
	
			<div class="col-md-1"></div>
			<div class="col-md-5">
					<br>
					<s:iterator value="productinfo">
						<font size="5" >
						<b><s:property value="productName"/></b><br>
						</font>
						<hr>
							<s:iterator value="description">
								<s:property/>
							</s:iterator>
						<hr>
						<div class="container">
							<s:if test="%{offer==0}">
								<font size="5" color="#76553B">
									Rs. <s:property value="price"/><br>
								</font>
							</s:if>
							<s:if test="%{offer>0}">
								<font size="5" color="#76553B">
									<font color="#848484" size="4">Rs. <strike><s:property value="price" /></strike> <s:property value="discount"/>% OFF</font><br>
										Rs. ${price-offer} 
								</font>
							</s:if>
						<hr>
						Seller: <a href="sellerRating"> <font color="black" size="4">WS Retail </font> </a>
						<br>
						</div>
						<hr>
						
						<input type="hidden" pid="<s:property value="productID"/>" id="productId"/>
						<s:property value="warranty"/> year manufacturer warranty for Phone and 6 months warranty for in the box accessories <s:property value="brand"/> and Free Transit Insurance. <br>
						<hr>
						
					</s:iterator>
					
					<button type="button" class="btn btn-danger"  id="buyNow">BUY NOW</button>
			</div>
		</div>
		<br>
		<br>
		<hr noshade>
		<s:iterator value="productinfo">
			<b>&nbsp; KEY FEATURES OF <s:property value="productName"/> </b>
		</s:iterator>
		<hr>
		<ul>
			<s:iterator value="description">
				<li><s:property/></li>
			</s:iterator>
		</ul>
		<br>
		<br>
		</div>
	</div>
	
	<div class="col-md-1 "></div>
	</div>
	<br>
</body>
</html>
