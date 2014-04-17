<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Order Receipt</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
<script src="asset/JavaScripts/jquery-ui.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$
								.ajax({
									type : 'GET',
									url : 'generateReceipt',
									success : function(data) {
										var transactionId = 0;
										var grandTotal = 0;
										var query = "<table>"
												+ "<tr> <th>Item</th> <th>Quantity</th> <th>Price</th> <th>Sub Total</th> </tr>";
										$
												.each(
														data.buyedProductList,
														function(count,
																buyedProduct) {
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
												+ "<tr> <th colspan='4'> Your order id for above order is : "
												+ data.orderId
												+ "and transaction id is : "
												+ transactionId + "</th></tr>"
												+ "</table>";
										$("#generateReceipt").append(query);
									}
								});
					});
</script>

<style>
table {
	width: auto;
	height: height;
	border: 1px solid #990000;
	margin: auto;
}

table th {
	width: auto;
	height: auto;
	border: 2px solid #660000;
	text-align: center;
	font-weight: bold;
	/*background-color : #08298A;*/
	/*color : #ffffff;*/
}

.last {
	text-align: right;
	padding-right: 20px;
}

table td {
	width: 150px;
	height: 80px;
	border: 2px solid #660000;
	text-align: center;
	/*font-weight : bold;*/
}
</style>
</head>
<body>
	<h2>Order Reciept</h2>
	<div id="generateReceipt"></div>
	<a href="Start_page"> Back</a>
</body>
</html>