<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
$(document).ready(
		function() {
			$("#login").click(
					function() {
						/* Ajax Call for place order Starts Here*/
						var bank =  $("#bankName").val();		
						$.ajax({							
							type : 'POST',
							url : 'bankLoginAction?bankLoginId='+ $("#bankLoginId").val()+'&bankPassword='+ $("#bankPassword").val() 
									+'&bankName=' + $("#bankName").val()+'&grandTotal='+$("#grandTotal").val(),
							success : function(data)
							{
								$("#validDetails").html(data.valid);								
								var valid = $("#validDetails").html();								
								if (valid == "1") 
								{
									$("#validDetails").html("");
									window.location = "./bankPaymentDescription.jsp?customerName="+data.details.customerName+"&accountNumber="+data.details.accountNumber+"&balance="+data.details.balance+'&bankName='+ $("#bankName").val();									
								}	
								else if (valid == "2") 
								{
									$("#validDetails").html("Insuffiecient Account Balance");	
									$("#validDetails").css("color","#ff0000");
								}	
								else
								{
									$("#validDetails").html("Invalid Login Details");
									$("#validDetails").css("color","#ff0000");
								}	
							}
						});						
					});
		});
</script>
<body>
<br><br><br><br>
 <input type="hidden" id = "bankName"  value="<%= request.getParameter("bankName") %>" /><br/>
 <input type="hidden" id = "grandTotal"  value="<%=session.getAttribute("grandTotal")%>" /><br/>
Welcome to <%= request.getParameter("bankName") %> Bank Online Banking<br><br>

   <div style=" float:left;width:300px; height:170px; border: solid 3px black;  ">
   	<label id="validDetails" width="300px"></label>
   		
   			<br>
			<label>Username*</label>
			<input type="text" id ="bankLoginId" name="bankLoginId" ><br><br>
			<label>Password*</label>
			<input type="password" id="bankPassword" name="bankPassword" ><br><br>
			<button id="login">LOGIN</button>
		
	</div>	
</body>
</html>