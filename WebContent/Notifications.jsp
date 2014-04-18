<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="edu.iiitb.model.UserEntry"%>
<%@page import="edu.iiitb.model.MyOrdersModel"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Personal information</title>
	<link rel="icon" type="/favicon.png" href="asset/Images/flipkartlogo.png">
	<!-- Custom styles for this template -->
	<link href="asset/CSS/Index.css" rel="stylesheet">
	<link rel="stylesheet" href="asset/CSS/jquery-ui.css">
	<link href="asset/CSS/starter-template.css" rel="stylesheet">
<!-- 	<link rel="stylesheet" href="asset/CSS/login.css"> -->
<link rel="stylesheet" href="asset/CSS/reveal.css">	
<link rel="stylesheet" href="asset/CSS/notification.css">	
	
	<!-- Bootstrap core CSS -->
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	<script src="asset/JavaScripts/drophover.js"></script>
	<script src="asset/JavaScripts/jquery-ui.js"></script> 
	<script src="asset/JavaScripts/jquery.reveal.js"></script>
<style type="text/css">

li.padding {color: #848484;
    padding: 1cm 0 2px 4px;
    font-size: 15px;
    font-weight: bold;}
 li.padding2:hover {
 color: #2271B2;
    text-decoration: none;
    background-color: #F4F4F4;}
  a.padding3{
  padding: 4px 0 4px 6px;
    display: block;
    cursor: pointer;
    color: #666666;
    }
    .notification-read {
    background-color: #F6F6F6;
}
.line, .lastUnit {
    overflow: hidden;
}
.rpadding10 {
    padding-right: 10px;
}
.bpadding10 {
    padding-bottom: 10px;
}
.lpadding15 {
    padding-left: 15px;
}
.tpadding10 {
    padding-top: 10px;
}
.lastUnit {
    float: none;
    width: auto;
}
.notification-text {
    color: #000000;
    float: left;
}
.tpadding5 {
    padding-top: 5px;
}
.notification-timestamp {
    color: #808080;
    float: right;
    font: 13px arial;
}
    
 
</style>



</head>

<body>

<%@ page import="com.opensymphony.xwork2.ActionContext,com.opensymphony.xwork2.util.ValueStack,javax.servlet.http.HttpSession" %>

<div class="col-md-1"></div>
<div class="col-md-10" style="background-color: #FFFFFF">
       
	
		<div class="col-md-3">
		
		<ul style="list-style-type:none">
		<li style="background-color: #014A72; color: #FFFFFF; font-size: 16px; font-weight: bold; padding: 1px 5px 1px 9px;"><h4>My Account</h4></li>
		<li class="padding"><h4>ORDERS</h4></li>
		<li class="padding2"><a class="padding3" href="MyOrders">My Orders</a></li>
		<li class="padding"><h4>SETTINGS</h4></li>
		<li class="padding2"><a class="padding3" href="Personal-info">Personal Information</a></li>
		<li class="padding2"><a class="padding3" href="ChangePassword">Change Password</a></li>
		<li class="padding2"><a class="padding3" href="Addresses">Addresses</a></li>
		<li class="padding2"><a class="padding3" href="UpdateEmail">Update Email</a></li>
		<li class="padding2"><a class="padding3" href="DeactivateAccount">Deactivate Account</a></li>
		<li style="font-weight: bold; padding: 4px 0 4px 6px" class="padding2">Notifications</li>
		
		</ul>
		</div>
		<div class="col-md-6">
			<h3> Notifications</h3>
			<br>
			
			<s:iterator value="Orders">
			<div class="line tpadding10 bpadding10 lpadding15 rpadding10 notification-content notification-read ">
		    <div class="lastUnit">
            <span class="notification-text">Your Flipkart order# <strong> <s:property value="oredrNo"/> </strong> has been <strong> <s:property value="status"/> </strong>.</span>
            </div>
            <div class="line tpadding5">
            <div class="notification-timestamp lastUnit"><s:property value="days_ago"/> days ago</div>
            </div>
            </div>
            </s:iterator>
			
			<%-- 
			<table class="table">
			<s:iterator value="Orders">
			<tr><td>Your Flipkart order <strong> <s:property value="oredrNo"/> </strong> has been <strong> <s:property value="status"/> </strong> <br> <s:property value="days_ago"/> </td></tr><hr>
			
			</s:iterator>
			</table>
			--%>
			
		</div>
		</div>

</body>
</html>