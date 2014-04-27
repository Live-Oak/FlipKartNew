<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Menu</title>
	<!-- Custom styles for this template -->
	<link href="asset/CSS/Index.css" rel="stylesheet">
	<link href="asset/CSS/starter-template.css" rel="stylesheet">
	
	<!-- Bootstrap core CSS -->
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
	
	<script src="asset/JavaScripts/jquery-1.9.1.js"></script>
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	<script src="asset/JavaScripts/drophover.js"></script>
</head>

<body>
	
	<!-- The first layer with logo and search -->

<div class="navbar-fixed-top">
		<div class="col-md-2"></div>
		<div class="col-md-2">
				<img alt="flipkart" src="asset/Images/flipkart.png" height="45px" width="150px">
				<br> <font color="white" size="1.5px">&nbsp;&nbsp;&nbsp;&nbsp;The Online Megastore</font>
		</div>
		<div class="col-md-2"></div>
		<div class="col-md-2">
			<h3> <font color="#ffffff"> Hello Admin </font></h3>	
		</div>
		<div class="col-md-2"></div>
		<div class="col-md-2">
			<a href="logout">Log out</a>
		</div>
		
	</div>
		
<!-- The second layer with menu and dropdown -->
<div class="navbar navbar-inverse">
		<div class="col-md-1"></div>
		
		<div class="col-md-10">
			<div class="container">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse collapse navbar-ex1-collapse">
					<ul class="nav nav-pills">
						<li><a href="adminhome">Home</a></li>
						
						
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">UserMgnt <span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
								<li><a href="registerUser">Register User</a></li>
								<li><a href="deleteUser">Delete User</a></li>
							</ul>
						</li> 
						
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">Category<span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
								<li><a href="insertCategoryPage">Add Category</a></li>
								<li><a href="insertSubCategoryPage">Add CategoryRelation</a></li>
							</ul>
						</li> 
						
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">Product Mgnt<span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
								<li><a href="addProduct">Add Product</a></li>
								<li><a href="productInfo">Update Product</a></li>
							</ul>
						</li> 
						
						
						
						<li><a href="advertizement">Add Advertizement</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">View DB <span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
								<li><a href="viewUser">Users</a></li>
								<li><a href="viewProduct">Products</a></li>
								<li><a href="viewCategory">Category</a></li>
								<li><a href="viewSubCategory">Sub-Category</a></li>
								<li><a href="viewFeedback">Feed-Back</a></li>
							</ul>
						</li> 
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">StockInfo <span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
								<li><a href="stockData?stockType=in">InStock Products</a></li>
								<li><a href="stockData?stockType=out">OutStock Products</a></li>
							</ul>
						</li> 
						
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">User Order<span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
								<li><a href="adminFetchPurchaseOrderId?status=view">View Orders</a></li>
								<li><a href="adminFetchPurchaseOrderId?status=confirm">Confirm Orders</a></li>
							</ul>
						</li> 
						
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown">Confirmation<span class="caret"></span></a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1" role="menu">
								<li><a href="userSideOkOrder">View ConfirmedOrder</a></li>
								<li><a href="userSideUnOkOrder">UnConfirmedOrder</a></li>
							</ul>
						</li> 
						
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-1"></div>

</body>
</html>