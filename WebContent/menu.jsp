<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="edu.iiitb.model.MyOrdersModel"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="description" content="Blueprint: Horizontal Drop-Down Menu" />
	<meta name="keywords" content="horizontal menu, microsoft menu, drop-down menu, mega menu, javascript, jquery, simple menu" />
	<title>Menu</title>
	
	<link rel="icon" type="/favicon.png" href="asset/Images/flipkartlogo.png">
	<!-- Custom styles for this template -->
	<link rel="stylesheet" href="asset/CSS/jquery-ui.css">
	<link href="asset/CSS/starter-template.css" rel="stylesheet">
	<link rel="stylesheet" href="asset/CSS/reveal.css">	
	<link href="asset/CSS/notification.css" rel="stylesheet">
	<link href="asset/CSS/feedback.css" rel="stylesheet">
	
	<!-- Bootstrap core CSS -->
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
	<link href="asset/CSS/cart.css" rel="stylesheet">
	<link href="asset/CSS/dropdown.css" rel="stylesheet">
	<link href="asset/CSS/revolving.css" rel="stylesheet">
	<link href="asset/CSS/Index.css" rel="stylesheet">	
	
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	<script src="asset/JavaScripts/jquery-ui.js"></script>
	<script src="asset/JavaScripts/drophover.js"></script>
	<script src="asset/JavaScripts/jquery.reveal.js"></script>
	<script src="asset/JavaScripts/cart.js"></script>
	<script src="asset/JavaScripts/notification.js"></script>
	<script src="asset/JavaScripts/jquery-feedback.js"></script>
	
	
	<style type="text/css">
	  #funkystyling {
    background: white url(asset/Images/search.jpg) left no-repeat;
    padding-left: 27px;
	}
    </style>
    
<script type="text/javascript">
<!--
// Form validation code will come here.
function validate()
{
 
   if( document.form_signup.password.value != document.form_signup.reenter_password.value )
   {
     alert( "Paaswords entered do not match!!" );
     document.form_signup.password.focus() ;
     return false;
   }
}

</script>
    
<script type="text/javascript">
$(document).ready(function(){
	$("#floatingBarsG").hide();
	$("#login_button").click(function()
			{
		$.ajax({
		    type: 'POST',	    
		    url:'check_login_password ?email=' + $("#emaill").val() + '&password=' + $("#passwordl").val(),
		    success: function(data){
		    	
		    	$("#check_email_password").html(data.message);
		    	var status=$("#check_email_password").html();
		    	
		    	if(status=="available")
		    		{
		    		$("#floatingBarsG").show();
		    		$("#form_login").submit();		    			
		    		}
		    	else
		    		{
		    			$("#check_email_password").html("Invalid email or password");
		    		}
		     }});	
	});
});


</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#forgot_password_button").click(function()
			{
		$.ajax({
		    type: 'POST',	    
		    url:'forgot_password_validation ?email=' + $("#email_forgot").val() ,
		    success: function(data){
		    	$("#check_email_forgot").html(data.message);
		    	var status=$("#check_email_forgot").html();
		    	if(status=="available")
		    		{
					$("#forgot_password_form").submit();		    			
		    		}
		    	else
		    		{
		    			$("#check_email_forgot").html("Invalid email Id");
		    		}
		     }});	
	});
});


</script>

<script type="text/javascript">
$(document).ready(function(){
	$("#create_account").click(function()
			{
    	
		var status=$("#notify").html();
		if(status=="Enjoy Flipkart User Experience")
		{
			
			$("#form_signup").submit();
		}
		else
		{
			$("#notify").html("Please check Email id availabilty");
		}
			
	});
	
});

</script>
<script type="text/javascript">
$(document).ready(function(){
	$("p").click(function()
			{
		if($("#email").val()=="")
			{
					$("#notify").html("Empty email id not permitted");
			}
		else
			{
				$.ajax({
				    type: 'POST',	    
				    url:'useravailable?email='+$("#email").val(),
				    success: function(data){
				    	$("#notify").html(data.message);
				     }});
			}
	});
});

</script>

<script type="text/javascript">
	$(document).ready(function(){
		var temp="";
		var index=0;
			$.ajax({
			    type: 'POST',
			    url:'getCatagories',
			    success: function(data){
			    	$.each(data.categoryModel[0], function(count, stock) {
			    		temp = '<li class="dropdown-submenu"><a href="getSearchresult?categoryname='+stock.categoryName+'">'+stock.categoryName+'</a><ul class="dropdown-menu">';
			    		$.each(data.subcategoryModel[index], function(count, stock1) {
							    temp += '<li><a href="getSearchresult?categoryname='+stock1.categoryName+'">'+stock1.categoryName+'</a></li><li class="divider" role="presentation"></li>';
						 });
			    		index = index + 1;
						 $("#electronics").append(temp + '</ul></li><li class="divider" role="presentation"></li>');   	 
			    		 $(".dropdown-submenu").find(".dropdown-menu").hide();
			    		 $(".dropdown-submenu").hover(function(e){
			 			 var currentTrarget = e.currentTarget;
			 			 $(".dropdown-submenu").find(".dropdown-menu").hide();
			 			 $(currentTrarget).find(".dropdown-menu").show();
			    	});
			    	});
			    	
					$.each(data.categoryModel[1], function(count, stock) {
			    		
			    	temp = '<li class="dropdown-submenu"><a href="getSearchresult?categoryname=Men '+stock.categoryName+'">'+stock.categoryName+'</a><ul class="dropdown-menu">';
			    	 $.each(data.subcategoryModel[index], function(count, stock1) {
					  	temp += '<li><a href="getSearchresult?categoryname='+stock1.categoryName+'">'+stock1.categoryName+'</a></li><li class="divider" role="presentation"></li>';
					    });
			    	index = index + 1;
					 $("#men").append(temp + '</ul></li><li class="divider" role="presentation"></li>');   	 
			   		 $(".dropdown-submenu").find(".dropdown-menu").hide();
	    			 $(".dropdown-submenu").hover(function(e){
			 		 var currentTrarget = e.currentTarget;
			 		 $(".dropdown-submenu").find(".dropdown-menu").hide();
			 		 $(currentTrarget).find(".dropdown-menu").show();
			    	});
			    	});
					

					$.each(data.categoryModel[2], function(count, stock) {
			    		
			    	temp = '<li class="dropdown-submenu"><a href="getSearchresult?categoryname=Women '+stock.categoryName+'">'+stock.categoryName+'</a><ul class="dropdown-menu">';

			    	 $.each(data.subcategoryModel[index], function(count, stock1) {
				    	temp += '<li><a href="getSearchresult?categoryname='+stock1.categoryName+'">'+stock1.categoryName+'</a></li><li class="divider" role="presentation"></li>';
				    });
			    	 index = index + 1;

					 $("#women").append(temp + '</ul></li><li class="divider" role="presentation"></li>');   	 
			   		 $(".dropdown-submenu").find(".dropdown-menu").hide();
	    			 $(".dropdown-submenu").hover(function(e){
			 		 var currentTrarget = e.currentTarget;
			 		 $(".dropdown-submenu").find(".dropdown-menu").hide();
			 		 $(currentTrarget).find(".dropdown-menu").show();
			    	});
			    	});
					

					$.each(data.categoryModel[3], function(count, stock) {
			    		
			    	temp = '<li class="dropdown-submenu"><a href="getSearchresult?categoryname='+stock.categoryName+'">'+stock.categoryName+'</a><ul class="dropdown-menu">';

	    			 $.each(data.subcategoryModel[index], function(count, stock1) {
				    	temp += '<li><a href="getSearchresult?categoryname='+stock1.categoryName+'">'+stock1.categoryName+'</a></li><li class="divider" role="presentation"></li>';
				    });
	    			 index = index + 1;

					 $("#baby").append(temp + '</ul></li><li class="divider" role="presentation"></li>');   	 
			   		 $(".dropdown-submenu").find(".dropdown-menu").hide();
	    			 $(".dropdown-submenu").hover(function(e){
			 		 var currentTrarget = e.currentTarget;
			 		 $(".dropdown-submenu").find(".dropdown-menu").hide();
			 		 $(currentTrarget).find(".dropdown-menu").show();
			    	});
			    	});
					

					$.each(data.categoryModel[4], function(count, stock) {
			    		
			    	temp = '<li class="dropdown-submenu"><a href="getSearchresult?categoryname='+stock.categoryName+'">'+stock.categoryName+'</a><ul class="dropdown-menu">';

			    	 $.each(data.subcategoryModel[index], function(count, stock1) {
					  	temp += '<li><a href="getSearchresult?categoryname='+stock1.categoryName+'">'+stock1.categoryName+'</a></li><li class="divider" role="presentation"></li>';
					 });
			    	 index = index + 1;
					 $("#books").append(temp + '</ul></li><li class="divider" role="presentation"></li>');   	 
			   		 $(".dropdown-submenu").find(".dropdown-menu").hide();
	    			 $(".dropdown-submenu").hover(function(e){
			 		 var currentTrarget = e.currentTarget;
			 		 $(".dropdown-submenu").find(".dropdown-menu").hide();
			 		 $(currentTrarget).find(".dropdown-menu").show();
			    	});
			    	});


					$.each(data.categoryModel[5], function(count, stock) {
			    		
			    	temp = '<li class="dropdown-submenu"><a href="getSearchresult?categoryname='+stock.categoryName+'">'+stock.categoryName+'</a><ul class="dropdown-menu">';

			    	 $.each(data.subcategoryModel[index], function(count, stock1) {
					  	temp += '<li><a href="getSearchresult?categoryname='+stock1.categoryName+'">'+stock1.categoryName+'</a></li><li class="divider" role="presentation"></li>';
					 });
			    	 index = index + 1;
					 $("#home").append(temp + '</ul></li><li class="divider" role="presentation"></li>');   	 
			   		 $(".dropdown-submenu").find(".dropdown-menu").hide();
	    			 $(".dropdown-submenu").hover(function(e){
			 		 var currentTrarget = e.currentTarget;
			 		 $(".dropdown-submenu").find(".dropdown-menu").hide();
			 		 $(currentTrarget).find(".dropdown-menu").show();
			    	});
			    	});
					

					$.each(data.categoryModel[6], function(count, stock) {
			    		
			    	temp = '<li class="dropdown-submenu"><a href="getSearchresult?categoryname='+stock.categoryName+'">'+stock.categoryName+'</a><ul class="dropdown-menu">';

			    	 $.each(data.subcategoryModel[index], function(count, stock1) {
					   	temp += '<li><a href="getSearchresult?categoryname='+stock1.categoryName+'">'+stock1.categoryName+'</a></li><li class="divider" role="presentation"></li>';
					 });
			    	 index = index + 1;
					 $("#more").append(temp + '</ul></li><li class="divider" role="presentation"></li>');   	 
			   		 $(".dropdown-submenu").find(".dropdown-menu").hide();
	    			 $(".dropdown-submenu").hover(function(e){
			 		 var currentTrarget = e.currentTarget;
			 		 $(".dropdown-submenu").find(".dropdown-menu").hide();
			 		 $(currentTrarget).find(".dropdown-menu").show();
			    	});
			    	});
			    }});
			});	
</script> 

<script type="text/javascript">
$(document).ready(function(){
	
	$("#get_orderinfo_button").click(function()
			{
		$.ajax({
		    type: 'POST',	    
		    url:'check_orderinfo ?Email=' + $("#email_id").val() + '&OrderId=' + $("#order_id").val(),
		    success: function(data){
		    	
		    	var status=data.message;
		 
		    	if(status=="available")
		    		{
					$("#form_orderinfo").submit();		    			
		    		}
		    	else
		    		{
		    			$("#check_orderdetails").html("Invalid Email or OrderId");
		    		}
		     }});	
	});
});


</script>  

<script type="text/javascript">
$(document).ready(function(){
	$("#button").click(function()
			{
			var search = $("#search").val();
			window.location="Browseresult?keyword=" + search;
			});
});
</script>

<script>
$(document).ready(function(){
	$("#search").keyup(function(){
		debugger;
		var keywordToCheck=$("#search").val();
		$.ajax({
			
			url: 'autoComplete?keyword='+keywordToCheck,
			type:'GET',
			success: function(data){
				$("#searchoption").empty();
				$.each(data.keywordsList,function(index,value){
					var temp= '<a href ="getProductDetail?productID='+value.productId+'"><li><img src='+value.productImage+' height="50px" width="auto" style="margin-left:1%;"> &nbsp;&nbsp;<font color="#000000">'+value.productName+'</font></li> </a>';
					
					$("#searchoption").append(temp);
				});
				$("#searchForm").addClass("open");
			}
		});
	});
	
	$("#search").click(function(){
		$("#searchoption").toggle();
	});
});
</script>

</head>

<body>
 <%@ page import="com.opensymphony.xwork2.ActionContext,com.opensymphony.xwork2.util.ValueStack,javax.servlet.http.HttpSession" %>
 
<%@ page import="edu.iiitb.model.*" %>
    <script type="text/javascript">
		
	</script>
	
<!-- The first layer with logo and search -->

	<div class="navbar-fixed-top" id="container">
				<div class="col-md-1"></div>
				
				<div class="col-md-2">
						&nbsp;&nbsp;&nbsp;
						<a href="index.jsp" >	 <img style="padding-top:10px" alt="flipkart" src="asset/Images/flipkart.png" height="50px" width="160px"> 
							<br>&nbsp;&nbsp;&nbsp;&nbsp; <font color="white" size="1.5px">&nbsp;&nbsp;&nbsp;&nbsp;The Online Megastore</font> </a>
				</div>
				
				<div class="col-md-5">	
						<br>
						<div class="searchForm" id="searchForm">
						  <input style="margin-top:5px" id="search" type="text" name="keyword" class="form-control" id="funkystyling" placeholder=" Search for a product category or brand"> 
						  <ul class="dropdown-menu" id="searchoption" style="width:580px; z-index:1000">
						  
						  </ul>
						</div>	
				</div>
				
				<div class="col-md-3">
					
					<div class="col-md-6">
						
						<!-- First track order -->
								 <%
									if (session.getAttribute("user") == null) {
								%>
								&nbsp;&nbsp;<a href="#" class="big-link Close"
									data-reveal-id="myOrderModal">Track Order</a>
			
			
								<%
									} else {
								%>
			
								&nbsp;&nbsp;<a href="MyOrders"
									class="big-link Close">Track Order</a>
								<%
									}
								%> 
							
			<!-- Second is notification track order -->
			
			<!-- Notification div starts here -->		
							
			<% 
				if(session.getAttribute("user") == null) 
				{ 
			%>
	
			<img src="asset/Images/bell.JPG" height="25px" data-contentwrapper=".mycontent"  rel="popover" data-title=""> 
		 
		 	
		 		<div class="mycontent hide" >
		 	
		 			<div class="notification-popup-header">Notifications</div>
		 				<br>
  					<div class="no-notifications">Get notifications on order's placed!</div>
  						<br>
   					<div class="whatrnotif1">What are Notifications??</div>
						<br>
   					<div class="whatrnotif2">
   							Notifications are timely alerts on your order status!!
   		 				<br>	
   		 					<strong><b>Sign-in</b></strong> now to start receiving notifications. 
   			  		</div>
   					 
				</div>
			
			<% 
				}
				else 
				{ 
			%>
		
			<img id="notification_button" src="asset/Images/bell.JPG" height="25px" data-contentwrapper=".mycontent"  rel="popover" data-title="">

		 	<div class="mycontent hide">
		 	
		 			<div  class="notification-popup-header">Notifications</div>
						<br>		 			
  					<div id="get_notification">  
  				  			<div id="noti"></div>
   					</div>
   					 		 
			</div>	
			<%
				}
			%>
			
			<!-- Notification div ends here -->		
			
					
			
						<button  id="button" type="button" class="btn btn-warning" style="width:140px;">SEARCH</button>
					</div>	
						
					<div class="col-md-6">
						<!-- Third is Signup -->	
								<%
									if (session.getAttribute("user") == null) {
								%>
								<a href="#" class="big-link Close" data-reveal-id="myModal1">Signup</a>&nbsp;
								<%
									} else {
								%>
								<%
									Login l = new Login();
								%>
			
								<li style="list-style-type: none" class="dropdown"><a href="#"
									class="dropdown-toggle" data-hover="dropdown"
									data-toggle="dropdown">Hi <%=session.getValue("fname")%>!<span
										class="caret"></span></a>
									<ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
										role="menu">
			
										<li><a href="Personal-info">Account</a></li>
										<li class="divider" role="presentation"></li>
										<li><a href="MyOrders">Orders</a></li>
										<li class="divider" role="presentation"></li>
										<li><a href="reviewandrating">Review & rating</a></li>
										<li class="divider" role="presentation"></li>
										<li><a href="logout">Logout</a></li>
			
									</ul></li>
								<%
									}
								%>
	
					
						<%
							if(session.getAttribute("user") == null)
							{
						%>
						&nbsp;&nbsp;<a href="#" class="big-link Close" data-reveal-id="myModal">Login</a><br>	
						<%
							}else
							{
						%>
							
						<%
							}
						%> 
						<a data-reveal-id="cartModel"><button style="margin-top:5px" id="cartButton" type="submit" class="btn btn-primary" style="width:130px;"> <img src="asset/Images/cart.png" alt="cart" height="20px" width="30px;" style="float:left;"> <div id="productCount" style="float:left;">&nbsp; CART (0) </div> </button></a>
					</div>
				</div>
				<div class="col-md-1"></div>
	</div>			
		
<!-- The second layer with menu and dropdown -->
	<div class="navbar navbar-inverse">
		<div class="col-md-2"></div>
		
		<div class="col-md-10">
			<div class="container">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					</button>
				</div>
				
				<form action="Start_page" method="post" enctype="multipart/form-data">
				
				<div class="navbar-collapse collapse navbar-ex1-collapse">
					<ul class="nav nav-pills">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">ELECTRONICS<span class="caret"></span></a>
							<ul id="electronics" class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">MEN<span class="caret"></span></a>
							<ul id="men" class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">WOMEN<span class="caret"></span></a>
							<ul id="women" class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">BABY & KIDS<span class="caret"></span></a>
							<ul id="baby" class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">BOOKS & MEDIA<span class="caret"></span></a>
							<ul id="books" class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">HOME & KITCHEN<span class="caret"></span></a>
							<ul id="home" class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">MORE STORE<span class="caret"></span></a>
							<ul id="more" class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
							</ul>
						</li>
					</ul>
				</div>
				
				</form>
			</div>
		</div>
	</div> 

<div id="myModal" class="reveal-modal">
		
		        <h2 align="center">Login</h2>
		      	<hr>

		<br>
		<form id="form_login" action="loginAction" method="post">
			<input type="email" class="textbox" id="emaill" name="email" placeholder="Email Adderess" required autofocus><br><br>
			<input type="password" class="textbox" name="password" id="passwordl" placeholder="Enter Password" required><br><br><br>
			<label id="check_email_password"></label>
			<button type="button" id="login_button" class="css_button">LOGIN</button>
		</form>
<br>

								<div id="floatingBarsG">
								<div class="blockG" id="rotateG_01">
								</div>
								<div class="blockG" id="rotateG_02">
								</div>
								<div class="blockG" id="rotateG_03">
								</div>
								<div class="blockG" id="rotateG_04">
								</div>
								<div class="blockG" id="rotateG_05">
								</div>
								<div class="blockG" id="rotateG_06">
								</div>
								<div class="blockG" id="rotateG_07">
								</div>
								<div class="blockG" id="rotateG_08">
								</div>
								</div>
		<div class="forgot_password">
		    <p>FORGOT YOUR PASSWORD?</p>
				<form class="forgot_password_form" id="forgot_password_form" action="forgotpassword">
	           			<input type="email" class="textbox" id="email_forgot" name="email" size="18" placeholder="E-mail address" />
	            		<label id="check_email_forgot"></label>
	            		<button type="button" id="forgot_password_button" class="css_button" >SUBMIT</button>
	    			</form>
		</div>
<a class="Signup big-link Close" data-reveal-id="myModal1">New User?</a>	
<a class="close_button Close">&#215;</a>		
</div>

<div id="myModal1" class="reveal-modal">
		
		        <h2 align="center">Sign Up</h2>
		      	<hr>
		<br>
		<form id="form_signup" name="form_signup" action="signmeup" onsubmit="return(validate());" method="post">
			<input type="text" class="textbox" name="firstName" placeholder="Enter First Name" required><br>
			<label id="firstNameRequired"></label>
			<input type="text" class="textbox" name="lastName" placeholder="Enter Last Name" required><br>
			<label id="lastNameRequired"></label>
			<input type="text" id="DOB" name="date" class="textbox" placeholder="Enter Date of Birth"  required><br><br>
			<label id="DOBRequired"></label>
			<input type="password" class="textbox" name="password" placeholder="Enter Password" required><br>
			<label id="passwordRequired"></label>
			<input type="password" class="textbox" name="reenter_password" placeholder="Re-Enter Password" required><br><br>
		    <label id="reenter_passwordRequired"></label>    
			<textarea rows="2" cols="18" name="address1" class="textbox" placeholder="Enter Address 1" required></textarea><br>
			<label id="address1Required"></label>    
			<textarea rows="2" cols="18" name="address2" class="textbox" placeholder="Enter Address 1" required></textarea><br>
			<label id="address2Required"></label>    
			<input type="text" id="city" name="city" class="textbox" placeholder="Enter City"  required><br>
			<label id="cityRequired"></label>    
			<input type="text" id="country" name="country" class="textbox" placeholder="Enter Country"  required><br>
			<label id="countryRequired"></label>    
			<input type="text" id="pincode" name="pincode" class="textbox" placeholder="Enter Pincode"  required onkeypress="return IsNumeric(event);" ondrop="return false;" onpaste="return false;">
			<label id="pincodeRequired"></label>    
			<span id="error" style="color: Red; display: none">*Input digits(0-9)</span>
			    <script type="text/javascript">
        			var specialKeys = new Array();
        			specialKeys.push(8); //Backspace
        			function IsNumeric(e) 
				{
        		    	var keyCode = e.which ? e.which : e.keyCode
            			var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys.indexOf(keyCode) != -1);
            			document.getElementById("error").style.display = ret ? "none" : "inline";
            			return ret;
        			}
			    </script>
			<br><br>
			<input type="email" id="email" name="email" class="textbox" placeholder="Enter e-mail"  required><br>
			<p id="checking">Check for availability</p>
			<label id="notify"></label>
			<input type="text" id="phone" name="phonenumber" class="textbox" placeholder="Enter Phone no"  required="true" onkeypress="return IsNumber(event);" ondrop="return false;" onpaste="return false;"><br><br>
			<label id="phoneRequired"></label>    
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
			<button type="submit" id="create_account">SIGN UP NOW!</button>
			<button type="reset" id="create_account">RESET!</button><br>
			<a href="#" class="Signup big-link Close" data-reveal-id="myModal">Already a user?</a>	
		</form>
			<a class="close_button Close">&#215;</a>
	</div>
	
	
	<div id="cartModel" class="cart-revealmodel" style="top: 100px; opacity: 1; visibility: hidden;z-index: 2001;">
		<div id="cartHeader" class="cart-header">CART(0)</div><div id="notification" style=" color:red;margin-left:20px;display:none"></div>
		<div class="cartContainer">
			<center>
			<div id="emptyCart" class="empty-cart">
				There are no items in this cart.<br><br>
				<button id="continueShopping" type="button" class="btn btn-primary Close" > CONTINUE SHOPPING </button>
			</div>
			<div id="productInfo" class="empty-cart">
				<div style="margin-bottom:50px;">
					<div style='float:left;width:80px;'>IMAGE</div>
		    		<div style='float:left;margin-left:20px;width:200px;'>ITEM</div>
		    		<div style='float:left;margin-left:20px;width:50px;'> QTY </div>
		    		<div style='float:left;margin-left:20px;width:120px;'>PRICE</div>
		    		<div style='float:left;margin-left:20px;width:120px;'>SUB-TOTAL</div>
		    		<div style='float:left;margin-left:20px;'></div>
				</div>
				<div id="list">
				
				
				</div>
				
			</div>
			</center>
		</div>
		<a class="close-reveal-modal Close">×</a>
		<div style="height:50px;">
			<div style="" id="totalCost" class="totalcost">Total Cost : 0</div>
		</div>
		<button id="continueShoppingBottom" type="button" class="btn btn-primary Close" style="float:left"> CONTINUE SHOPPING </button>
		<a href="placeOrderUserSessionCheck"><button id="placeOrder" type="button" class="btn btn-primary" style="float:right;" 
		>PLACE ORDER</button></a>
	</div>
  	<div class="reveal-modal-bg" style="display: none; cursor: pointer;z-index: 2000;"></div>
  	
  	
<div id="myOrderModal" class="cart-revealmodel" style="top: 100px; opacity: 1; visibility: hidden;z-index: 2001;">
    <div class="cart-header">Track Your Order</div>
    <div style = "margin-left: 20px; margin-top: 10px;" class ="col-md-6"> 
    <strong> Track using order id </strong>
    <br><br>
    
    <form id="form_orderinfo" name="form_orderinfo" action="Get_OrderInfo"  method="post">
    <table style="width:300px; height:150px;">
			<tr> <td style="text-align:left;">Email ID </td>
			<td> <input type="email" id="email_id" name="Email" class="textbox"   required ></td> </tr>
		
			<tr> <td style="text-align:left;">Order ID </td>
			<td> <input type="text" id="order_id" name="OrderId" class="textbox"  required ></td> </tr>
			
			<tr> <td> <label id="check_orderdetails"></label> </td>
			<td><input style=" text-align:center;padding:10px;" type="button" id="get_orderinfo_button" class="btn btn-primary" value="View Order Status" >&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 	
		    </td></tr>
			</table>
			</form>
			</div>
	
	<div style = "margin-top: 10px; border-left: 1px dotted;" class ="col-md-5" >
	   <strong> Login and do more!</strong>
	   <br><br>
	   <ul style="margin-left: 30px;" >
          <li >Track individual Orders</li>
          <li >View your entire Order history</li>
          <li >Cancel individual Orders</li>
          <li >Conveniently review products and sellers</li>
      </ul>
      <br>
      
      <a href="#" data-reveal-id="myModal" ><input type="submit" class="btn btn-primary Close" value="Login" ></a>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
	
	</div>	
		
		<a class="close-reveal-modal Close">×</a>
  </div>
  	
  	
  	<!-- Div for Feedback  -->
  	<input id="butt" class="btnFeedback" type="image" src="asset/Images/suggest-post.png" alt="Feedback"/>
	<div id = "Thankyou">
  		
		<div class="thankyouStyle">Thank you for your feedback 
			<img height="20px" width="20px" alt="image-logo" src="asset/Images/smiley.jpg">
  		</div>
		<br>
  		<input class="closebuttonStyle" type="button" value="Close" id="closeFeedback"><br><br><br>
  		<img style="margin-left: 300px;" alt="image-logo" src="asset/Images/flipkartLogoFeedback.png">
  		
	</div>
  
	<div  id="slideout_inner">
	
		<h3><label class="label1">Tell us What you Think.</label></h3>
		<p style="font-size: 12px;color: #555555;margin-left: 15px;margin-right: 15px;">Love us / have suggestions / ideas / feature requests?
		Tell us how we can improve our website.</p>
		<hr style="border-style: solid none;border-width: 1px 0;">
		<label class="label2">Email<span style="color:red;"> *</span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<% 
			if(session.getValue("email")==null)
			{ 
		%>
		<input id="emailText" value="" type="email" pattern="[^ @]*@[^ @]*" autocomplete="off" style="border-color:black" name="email" required="true" size="30">
		<%
			}
			else 
			{
		%>
		<input id="emailText" value="<%= session.getValue("email")%>" type="email" pattern="[^ @]*@[^ @]*" autocomplete="off" style="border-color:black" name="email" required="true" size="30">
		<% 
			} 
		%>
	
		<br><br>
		<label class="label2">Mobile Number<span style="color:red;"> *</span></label>&nbsp;&nbsp;&nbsp;
		<input id="mobileText" type="tel" pattern="[0-9]{10,10}" maxlength="10"  autocomplete="off" style="border-color:black" name="mobileNumber" required="true"  size="22">
		<br><br>
		<label class="label2">Category<span style="color:red;"> *</span></label>
		<select class="label2" id="dropbox" name="category">
		<option>Improve this page</option>
		<option>Suggest new features/ideas</option>
		<option>Shopping Experience</option>
		<option>I love Flipkart</option>
		<option>Others - General Feedback</option>
		<option>Flipkart offers zone feedback</option>
		</select>
		<br><br>
		<label class="label2">Message<span style="color:red;"> *</span></label>
		<br>
		<s:textarea id="messageText" cssClass="" cssStyle="margin-left:15px;border-color:black" name="message" 
		required="true" rows="4" cols="52"></s:textarea>
		<br><br>
		<input  class="feedbackSubmit" type="button" value="Submit" id="feedbackSub">
		<img style="margin-left: 145px" alt="image-logo" src="asset/Images/flipkartLogoFeedback.png">
		<hr style="border-style: solid none;border-width: 1px 0;">
		<div id="msg" style="color: red;text-align: center;"></div>
		
	</div>
  	
  	
		
<script type="text/javascript">
$.ajax({
    type: 'GET',
    url:'getProductsFromCart',
    success: function(data){
    	if( data.count != undefined && data.count != 0  )
		{
    		$("#productCount").html("CART ("+data.count+")");
		}
    }
    });

</script>


		
</body>
</html>
