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
	$("document").ready(function() {
		$("a").click(function() {

			var productID = $(this).attr("id");

			$.ajax({

				type : 'POST',
				url : 'stockForProduct?productId=' + productID,
				success : function(data) {

					$.each(data.stock, function(count, stock) {

						$("#pId").val(stock.productId);
						$("#sId").val(stock.sellerId);
						$("#pName").val(stock.productName);
						$("#sName").val(stock.sellerName);
						$("#mQty").val(stock.minimumQty);
						$("#aQty").val(stock.availableQty);
						$("#aQty").attr("disabled", "disabled");
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
		
		
		<h3> Order- ID :: 
			<select id="dropDown" name="id">
					<option> --Select-- </option>
				<s:iterator value="orderId">
				<option>	<s:property/>	</option>
				</s:iterator>
			 </select><br> </h3>
		
		
	</div>
	<div class="col-md-3"></div>
	
	
	<%-- <div class="col-md-2"></div>
	<div class="col-md-8">
		<table id="example" class="display" width="100%">
			<thead>
				<tr>
					<th>Product-Image</th>
					<th>Product-Name</th>
					<th>Seller-Name</th>
					<th>Min-Qty</th>
					<th>Avl-Qty</th>
					<th>&nbsp;&nbsp;Status</th>
					<th>&nbsp;&nbsp;&nbsp; Submit</th>
				</tr>
			</thead>

			<tbody>
				<s:iterator value="stockInfo">

					<tr>
						<td><img alt="not found"
							src="<s:property value="productImagePath"/>" height="80"
							width="60"></td>
						<td><s:property value="productName" /></td>
						<td><s:property value="sellerName" /></td>
						<td>&nbsp;&nbsp;&nbsp; <s:property value="minimumQty" />
						</td>
						<td>&nbsp;&nbsp;&nbsp;<s:property value="availableQty" />
						</td>
						<td><img alt="not found"
							src="<s:property value="statusImage"/>" height="65" width="65">
						</td>
						<td><a id="<s:property value="productId"/>"
							class="big-link Close" data-reveal-id="myModal2"> <font
								color="blue">OrderMore </font></a></td>
					</tr>


				</s:iterator>
			</tbody>
		</table>
	</div>

	





	<script type="text/javascript">
		$("document").ready(function() {
			$("#example").dataTable();
		});
	</script> --%>
</body>
</html>