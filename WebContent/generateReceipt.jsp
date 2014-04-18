<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Order Receipt</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap core CSS -->
<link href="asset/CSS/bootstrap.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">

<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
<script src="asset/JavaScripts/jquery-ui.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$.ajax(
						{
							type : 'GET',
							url : 'generateReceipt',
							success : function(data) {
								var transactionId = 0;
								var grandTotal = 0;
								var query = "<h3>Order Reciept</h3><br/>"
											+ "<table class='table table-bordered' style=' width : 600px; height : auto;'>"
											+ "<tr> <th>Item</th> <th>Quantity</th> <th>Price</th> <th>Sub Total</th> </tr>";
								$.each(data.buyedProductList,function(count,buyedProduct) 
								{
									transactionId = buyedProduct.transactionId;
									grandTotal += parseInt(buyedProduct.subTotal);
									query += "<tr> "
											+ "<td> "
											+ buyedProduct.productName
											+ "</td>"
											+ "<td> "
											+ buyedProduct.quantity
											+ "</td>"
											+ "<td> "
											+ buyedProduct.price
											+ "</td>"
											+ "<td> "
											+ buyedProduct.subTotal
											+ "</td>"
											+ "</tr>";
							});
							query += "<tr>"
									+ " <th class='last' colSpan ='3' > Grand Total</th>"
									+ "<th id='grandTotal'> "
									+ grandTotal
									+ "</th> "
									+ "</tr>"
									+ "<tr> <th colspan='4'> Your Transaction Id for above Order ( Order Id : "
									+ data.orderId
									+ " ) is : "
									+ transactionId + "</th></tr>"
									+ "</table>"
									+ "<a href='Start_page'> "
									+ "<button class='btn btn-info'> Continue Shopping </button>"
									+ "</a>";
							$("#generateReceipt").append(query);
						}
					});
				});
</script>
</head>
<body>
	
	<div id="generateReceipt"  style=" width:600px; height:auto ; margin : auto; ">		
		
	</div>
</body>
</html>