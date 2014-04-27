<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.welcome {
	background-color: #DDFFDD;
	border: 1px solid #009900;
	width: 190px;
}

.welcome li {
	list-style: none;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="asset/CSS/bootstrap.css" rel="stylesheet">
<link href="asset/CSS/Index.css" rel="stylesheet">
<link rel="stylesheet" href="asset/CSS/jquery-ui.css">
<link href="asset/CSS/starter-template.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
<script src="asset/JavaScripts/bootstrap.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$("#dropDown").change(function(){
			
			var id = $("#dropDown").val();
			
			$.ajax({
				
				 type: 'POST',
				    url:'productDetails?productID='+id,
				    success: function(data){
				    	
				    	
				    	$("#price").val(data.product.price);
				    	$("#offerprice").val(data.product.offer);
				    	$("#keywords").val(data.product.keywords);
				    	$("#date").val(data.product.lastOfferDate);
				    }
				
			});
			
		});
	});

</script>

</head>
<body>
	<center>
		<s:if test="hasActionMessages()">
			<div class="welcome">
				<s:actionmessage />
			</div>
		</s:if>

	</center>
	<div class="col-md-3"></div>
	<div class="col-md-6">
		
		 <form action="updateProduct" method="post">
	<center>	<h3>  Product- ID :: </h3>	</center>
			<select id="dropDown" name="productIDUpdate">
					<option> --Select-- </option>
				<s:iterator value="productId">
				<option>	<s:property/>	</option>
				</s:iterator>
			 </select><br> 
			 
			 <br><br><br>
			 <center>
			 <div id="productData">
			
			 Product-Price :	<input type="text" name="price" id="price"> <br>
			 OfferLastDate :	<input type="text" name="lastOfferDate" id="date"> <br>
			 Product Kywrd :	<input type="text" name="keywords" id="keywords"> <br>
			 Offered Price :	<input type="text" name="offer" id="offerprice"> <br> <br>
			 	<input type="submit" value="UpdateEntry"/>
			
			 </div>
			 </center>
		
		 </form>	
	</div>
	<div class="col-md-3"></div>
	

</body>
</html>