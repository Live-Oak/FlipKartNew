<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="edu.iiitb.model.UserEntry"%>
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
		<li style="font-weight: bold; padding: 4px 0 4px 6px" class="padding2">Personal Information</li>
		<li class="padding2"><a class="padding3" href="ChangePassword">Change Password</a></li>
		<li class="padding2"><a class="padding3" href="Addresses">Addresses</a></li>
		<li class="padding2"><a class="padding3" href="UpdateEmail">Update Email</a></li>
		<li class="padding2"><a class="padding3" href="DeactivateAccount">Deactivate Account</a></li>
		<li class="padding2"><a class="padding3" href="Notifications">Notifications</a></li>
		
		</ul>
		</div>
		<div class="col-md-6">
			<h3> Personal Information</h3>
			<br>
			
			<form>
			<center>
	<s:if test="hasActionMessages()">
		<div class="welcome">
      		<s:actionmessage/>
  		 </div>
	
	</s:if>
	<br>
		
</center>
			<table style="width:400px">
			<tr> <td style="text-align:left">First Name </td> 
			<td> <input type="text" class="textbox" name="firstname" value="<s:property value="user.firstName"/>"> </td> </tr>
			<tr> <td style="text-align:left">Last Name </td> 
			<td> <input type="text" class="textbox" name="lastname" value="<s:property value="user.lastName"/>"> </td> </tr>
			<tr> <td style="text-align:left">Mobile Number </td> 
			<td> <input type="text" id="phone" class="textbox" name="mobilenumber" value="<s:property value="user.Phonenumber"/>" onkeypress="return IsNumber(event);" ondrop="return false;" onpaste="return false;"><br> 
			<span id="error_phone" style="color: Red; display: none">*Input digits(0-9)</span>
			    <script type="text/javascript">
        			var specialKeys = new Array();
        			specialKeys.push(8); //Backspace
        			function IsNumber(e) 
				{
        		    	var keyCode = e.which ? e.which : e.keyCode
            			var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
            			document.getElementById("error_phone").style.display = ret ? "none" : "inline";
            			return ret;
        			}
			    </script>
			    </td> </tr>
			
			
			<tr> <td style="text-align:left">Landline Number </td> 
			<td> <input type="text" class="textbox" name="landlinenumber"> </td> </tr>
			 <tr> <td style="text-align:left">Gender </td> 
			<td> <select id="gender" name="gender">
				        <option> <s:property value="user.gender"/> </option>
						<option value="Female">Female</option>
						<option value="Male">Male</option>
						
						
		    </select>	&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td> </tr>
			<tr> <td></td>
			<td><input type="submit" class="btn btn-primary" value="SAVE CHANGES" onClick="form.action='UpdatePersonalInfo';"/>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 	
		    </td></tr>
		    </table>
		    </form>
		</div>
		
		
		</div>


</body>
</html>
