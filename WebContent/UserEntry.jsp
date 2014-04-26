<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="asset/JavaScripts/jquery.js"></script>
<script type="text/javascript">
	
$(document).ready(function(){
	
	$("#sellerInfo").hide();
	
	$("#role").change(function(){
		
		var strUser = $("#role").val();
		
		if(strUser == 'Seller')
			{
				$("#sellerInfo").show();
				$('#sellerInfo').prop('required',true);


			}
		
		else
			{
				$("#sellerInfo").hide();
				$('#sellerInfo').prop('required',false);
			}
		
		
	});
});
	

</script>




<sx:head />
</head>
<body>
	<h3 align="center"> <b> Please Fill The Below Details </b> </h3>

 
	<div>
	<form id="user_form" action="enterUserDeatils" method="post">
		<div class="col-md-4"></div>
		<div class="col-md-2">
	<h4>		<br><br>
			
			FirstName :: <br>
			LastName :: <br>
			Password ::	<br>
			Role :: <br> <br>
			DateOfBirth :: <br>
			AddressLine 1 <br> <br> <br> <br>
			AddressLine 2 <br> <br> <br> <br>
			City :: <br>
			Country :: <br>
			PinCode :: <br>
			E-mail :: <br>
			PhoneNumber :: <br><br>
			
	</h4>	</div>
		<div class="col-md-2">
			<br><br>
			
			<input type="text" name="firstName" required="true"  placeholder="Enter FirstName"><br>
			<input type="text" name="lastName" required="true"  placeholder="Enter LastName"><br>
			<input type="password" name="password" required="true"  placeholder="Enter Password"><br>
				<select id="role" name="role">
						<option>--Select--</option>
						<option>Seller</option>
						<option>Admin</option>
						<option>User</option>
					</select>	&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<br>
			 <sx:datetimepicker name="date" displayFormat="dd-MM-yyyy" />	<br>
			<s:textarea name="address1" required="true" cols="20" rows="3"  placeholder="Enter address line 1"></s:textarea> <br>
			<s:textarea name="address2" cols="20" rows="3"  placeholder="Enter address line 2"></s:textarea> <br>
			<s:textfield name="city" required="true" placeholder="City"></s:textfield>  <br>
			<s:textfield name="country" required="true" placeholder="Country"></s:textfield> <br>
			<s:textfield name="pinCode" required="true" placeholder="PinCode"></s:textfield> <br>
			<s:textfield name="email" required="true" placeholder="E-Mail"></s:textfield> <br>
			<s:textfield name="phonenumber" required="true" placeholder="Enter PhoneNumber"> </s:textfield> <br><br>	
			<input type="submit" class="btn btn-primary" value="Submit"/>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <input type="reset" class="btn btn-primary" name="Reset"/>	
		</div>
		
		<div class="col-md-4">
		<br><br><br><br><br>
		
			<s:textarea name="sellerDescription"  id="sellerInfo" cols="20" rows="3"  placeholder="Enter seller description"></s:textarea> <br>			
			
		</div>
		
		
			
				
			
		</form>
	</div>

</body>
</html>