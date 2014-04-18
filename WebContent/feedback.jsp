<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
	
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	<script src="asset/JavaScripts/jquery.dataTables.min.js"></script>
</head>
<body>
	<h1 align="center"> Feedback </h1><br><br><br>
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<table id="example" class="display" width="80%" border="2">
			<thead>
			<tr> 
			    <th> Feedback By </th>
			    <th> MobileNo. </th>
			    <th> Category </th>
			    <th> Message </th>
			</tr>
			</thead>
			<s:iterator value="feedback">
				<tr>
					
					<td>	<s:property value="email"/>		</td>
					<td>	<s:property value="mobileNumber"/>			</td>
					<td>	<s:property value="category"/>		</td>
					<td>	<s:property value="message"/>			</td>
					
				</tr>
			</s:iterator>
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