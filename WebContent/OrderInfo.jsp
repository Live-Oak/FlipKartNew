<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="edu.iiitb.model.UserEntry"%>
<%@page import="edu.iiitb.model.GetOrderDetailsModel"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>
<link rel="icon" type="/favicon.png" href="asset/Images/flipkartlogo.png">
	<!-- Custom styles for this template -->
	<link href="asset/CSS/Index.css" rel="stylesheet">
	<link rel="stylesheet" href="asset/CSS/jquery-ui.css">
	<link href="asset/CSS/starter-template.css" rel="stylesheet">
<!-- 	<link rel="stylesheet" href="asset/CSS/login.css"> -->
<link rel="stylesheet" href="asset/CSS/reveal.css">	
	
	<!-- Bootstrap core CSS -->
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	<script src="asset/JavaScripts/drophover.js"></script>
	<script src="asset/JavaScripts/jquery-ui.js"></script> 
	<script src="asset/JavaScripts/jquery.reveal.js"></script>
	
	
<body>

<div class="col-md-2"></div>
<div class="col-md-8" style="background-color: #FFFFFF">
     <div style="border-right: 1px dotted;" class="col-md-5">
     <h2> Order Details </h2>
    <br>
    
    <table style="width:330px; height:150px;" class="table">
			<tr> <td style="text-align:left;">Order ID: </td>
			<td> <strong> <s:property value="GOD.orderNo"/> </strong>
			     <span style="font-size:13px; color:#848484;">(<s:property value="GOD.quantity"/> Item)</span> </td> </tr>
		
			
			
			<tr> <td style="text-align:left;"> Order Date: </td>
			<td> <s:property value="GOD.order_date"/> </td></tr>
			
			<tr> <td style="text-align:left;"> Amount Paid: </td>
			<td><strong>Rs.<s:property value="GOD.totalprice"/> </strong>
			       <span style="font-size:13px; color:#848484;">through <s:property value="GOD.paymentType"/></span> </td></tr>
			
			<tr> <td style="text-align:left;"> Status: </td>
			<th> <s:property value="GOD.status"/></th></tr>
			
			<tr> <td style="text-align:left;"> Delivery Date: </td>
			<td> <s:property value="GOD.delievry_date"/> </td></tr>
			
			<tr><td ></td><td></td></tr>
			
			</table>
			</div>
		
      <div class="col-md-6">
      <br>
      <br>
      <br>
      <h3 style="margin-left: 20px;"> <s:property value="GOD.cust_name"/> 
      <span style="font-size:13px; color:#848484;"> <s:property value="GOD.phone_number"/></span> </h3><br>
      <p style="margin-left: 20px;"> <s:property value="GOD.address_line1"/>, <s:property value="GOD.address_line2"/>, <s:property value="GOD.city"/>-<s:property value="GOD.pincode"/> </p>
      
      
      </div>
</div>
</body>
</html>