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
<link rel="stylesheet" href="asset/CSS/reveal.css">
<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
<script src="asset/JavaScripts/bootstrap.min.js"></script>
<script src="asset/JavaScripts/jquery.dataTables.min.js"></script>
<script src="asset/JavaScripts/jquery.reveal.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		$("#dropDown").change(function(){
			
			var id = $("#dropDown").val();
			$.ajax({
				
				 type: 'POST',
				    url:'fetchOrderDetails?orderID='+id,
				    success: function(data){
				    	$("#tableData").empty();
				    	$.each(data.orderDeatils, function(count,order) { 
				    	
				    	$("#tableData").append("<tr> <td> "+order.orderPersonName+" </td> <td> <img src="+order.photo+" height=80 width=60 ></td> <td> "+order.prodName+" </td> <td> "+order.quantity+" </td> <td> "+order.totalprice+" </td> </tr>");
				    	
				    	});
				    
				
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
		
		
		<h3>  Order- ID :: 
			<select id="dropDown" name="id">
					<option> --Select-- </option>
				<s:iterator value="orderId">
				<option>	<s:property/>	</option>
				</s:iterator>
			 </select><br> </h3>
			 
			 <br><br><br>
			 
			 <table id="example" class="display" width="100%">
			<thead>
				<tr>
					<th>Order Person</th>
					<th>Product-Image</th>
					<th>Product-Name</th>
					<th>Quantity</th>
					<th>&nbsp;&nbsp;Price</th>
				</tr>
			</thead>

			<tbody id="tableData">
			</tbody>
			</table>
		
		
	</div>
	<div class="col-md-3"></div>
	
	

	<script type="text/javascript">
		$("document").ready(function() {
			$("#example").dataTable();
		});
	</script> 
</body>
</html>