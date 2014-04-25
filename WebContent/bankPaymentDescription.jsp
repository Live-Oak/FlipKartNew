<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="asset/CSS/bootstrap.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function noBack()
{
window.history.forward(1);
}
</script>

</head>
<body onload="noBack()">
<br/><br/>
<div style=" width:600px; height:auto ; margin : auto; ">
	<h3> Welcome to <%= request.getParameter("bankName") %> Bank Online Banking</h3><br><br>
	Welcome <b><%= request.getParameter("customerName")%></b>,
	<br/><br/>
	
	Your Current Balance is : <b><%= request.getParameter("balance") %> </b>
	<br/><br/>
	
	Payment For FlipKart is : <b><%=session.getAttribute("grandTotal")%></b> <br/><br/>
	
	Amount to be debited from Account Number : <b><%= request.getParameter("accountNumber") %></b>
	<br/><br/>
	<% String address = "onlineBankingPayment?bankName=" + request.getParameter("bankName") +"&accountNumber="+request.getParameter("accountNumber");%>
	<a href= "<%= address  %>"><button class="btn btn-info" id="onlineBankingPayment" >Make Payment</button> </a>
	<a href= "Start_page"><button class="btn btn-danger" id="onlineBankingPayment" >Cancel Payment</button> </a>
</div>
</body>
</html>