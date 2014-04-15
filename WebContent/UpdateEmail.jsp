<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="edu.iiitb.model.UserEntry"%>
<%@page import="edu.iiitb.model.User"%>
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
  td{
    text-align:center;
    padding:10px;
   }
</style>

<script type="text/javascript">
$(document).ready(function(){
	$("p").click(function()
			{
		$.ajax({
		    type: 'POST',	    
		    url:'useravailable?email='+$("#email").val(),
		    success: function(data){
		    	
		    	$("#notify").html(data.message);
		     }});	
	});
});


</script>

<script type="text/javascript">
$(document).ready(function(){
	$("#change_email").click(function()
			{
    	
		var status=$("#notify").html();
		if(status=="Enjoy Flipkart User Experience")
		{
			
			$("#form_change_email").submit();
		}
		else
		{
			$("#notify").html("Please check Email id availabilty");
		}
			
	});
	
});

</script>



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
		<li style="font-weight: bold; padding: 4px 0 4px 6px" class="padding2">Update Email</li>	
		<li class="padding2"><a class="padding3" href="DeactivateAccount">Deactivate Account</a></li>
		
		</ul>
		</div>
		<div class="col-md-6">
			<h3> Update Email</h3>
			<br>
			<p style="font-weight: bold;">Enter the new Email ID that you wish to associate with your Flipkart account.</p>
			<form id="form_change_email" action="UpdateNewEmail" method="post">
			<table style="width:400px">
			<tr> <td style="text-align:left">Email Address </td> 
			<td> <label id="emailid"><% User u = (User) session.getValue("user");
					out.print(u.getEmail());%></label> 
			</td> </tr>
			<tr> <td style="text-align:left">New Email ID </td> 
			<td> <input type="email" id="email" name="newEmail" class="textbox"  required ></td> </tr>
			<tr> <td> </td> <td><p id="checking">Check for availability</p></td>
			<tr><td> </td><td><label id="notify"></label> </td> </tr>
			
			<tr> <td></td>
			<td><input type="button" id="change_email" class="btn btn-primary" value="SAVE CHANGES" />&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 	
		    </td></tr>
		    </table>
		    </form>
		</div>
		
		
			
				
			
	
	</div>


</body>
</html>
