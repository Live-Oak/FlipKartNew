<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
Welcome <%= request.getParameter("customerName") %>,<br/><br/>

Your Current Balance is:<%= request.getParameter("balance") %><br/><br/>

Payment For FlipKart is: <%=session.getAttribute("grandTotal")%> <br/><br/>

Amount to be debited from Account Number : <%= request.getParameter("accountNumber") %><br/><br/>
<% String address = "onlineBankingPayment?bankName=" + request.getParameter("bankName") +"&accountNumber="+request.getParameter("accountNumber");%>
<a href= "<%= address  %>"><button id="onlineBankingPayment" >Make Payment</button> </a>
</body>
</html>