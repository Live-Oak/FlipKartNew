<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
<title>Place Order</title>
<link href="./asset/CSS/verticalTabs.css" rel="stylesheet"
	type="text/css">
<link href="./asset/CSS/placeOrder.css" rel="stylesheet" type="text/css">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Place Order</title>

<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
<script src="asset/JavaScripts/jquery-ui.js"></script>

<script>
	$(document).ready(function()
	{
		$('#tabs').tabs().addClass('ui-tabs-vertical ui-helper-clearfix');
	});
</script>
<script>
	$(document).ready(function() {

		$("#pwd").hide();

	});
</script>
<script>
	$(document).ready(function() {

		$("#panel1").show();

	});
</script>
<script>
	$(document).ready(function() {

		$("#editOrder").hide();

	});
</script>
<script>
	$(document).ready(function() {

		$("#editEmailid").hide();

	});
</script>
<script>
	$(document).ready(function() {

		$("#editAddress").hide();

	});
</script>
<script>
	$(document).ready(function() {
		$("#show").click(function() {
			$("#pwd").toggle();
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var email = $("#emailInput");
		if (email.val().length > 0)
		{			
			///$("#nextAddressPage").click();
			//$( "#nextOrderPage" ).trigger( "click" );	
			$("#panel3").slideDown();
			$("#panel2").slideUp();
			$("#panel1").slideUp();
			$("#editAddress").show();
			$("#editEmailid").show();
		}
	});
</script>
<script>
	$(document).ready(
			function() 
			{
				$("#nextAddressPage").click(function() 
				{
					var email = $("#emailInput").val();
					var pass = $("#password").val();
					if ((email.length > 0)&& ($("#show").prop('checked') == true)&& (pass.length > 0)) 
					{
						$.ajax(
						{
							type : 'GET',
							url : 'check_login_password ?email='+ $("#emailInput").val()+ '&password='+ $("#password").val(),
							success : function(data) 
							{
								$("#check_email_password").html(data.message);
								var status = $("#check_email_password").html();
								if (status == "available") 
								{
									$("#check_email_password").html(' ');
								/* Ajax call here to fetch user address details Start here*/
									$.ajax(
									{
										type : 'GET',
										url : 'getUserAddressDetails ?email='+ $("#emailInput").val(),
										success : function(data) 
										{						    	
											$("#name").val(data.addressDetails.name);
											$("#pin").val(data.addressDetails.pinCode);
											$("#address").val(data.addressDetails.addressLine1);
											$("#address2").val(data.addressDetails.addressLine2);
											$("#phone").val(data.addressDetails.phoneNumber);
											$("#city").val(data.addressDetails.city);
										}
									});
									/* Ajax call here to fetch user address details End here*/
									$("#panel3").slideDown();
									$("#panel2").slideUp();
									$("#panel1").slideUp();
									$("#editAddress").show();
									$("#editEmailid").show();
								} 
								else 
								{
									$("#check_email_password").html("Invalid email or password");
									$("#check_email_password").css("color","#ff0000");
								}
							}
						});
					}
	
					else 
					{
						$("#password").css("border","1px solid #ff0000");
					}
					if ((email.length > 0)&& ($("#show").prop('checked') == false)) 
					{
						var validEmail =  /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4})(\]?)$/;
						if( validEmail.test(email) )
						{
							$("#panel2").slideDown();
							$("#panel1").slideUp();
							$("#editEmailid").show();
						}
						else
						{
							
							$("#emailInput").html("");
							$("#emailInput").css("border","1px solid #ff0000");		
							$("#emailInput").focus();
						}
					}
					else
					{
						$("#emailInput").html("");
						$("#emailInput").css("border","1px solid #ff0000");		
						$("#emailInput").focus();
					}
				});
		});
</script>
<script>
	$(document).ready(function() 
	{
		$("#nextOrderPage").click(function()
		{			
			var name = $("#name");
			var pin = $("#pin");
			var add = $("#address");
			var phn = $("#phone"); 
			
			var city = $("#city");
			if (		(name.val().length > 0)
					&&  (pin.val().length > 0)
					&&  (add.val().length > 0)
					&&  (phn.val().length > 0)
					&&  (city.val().length > 0)
				) 
			{	
						
				if ( (pin.val().length == 6) && (pin.val() >= 100000) && (pin.val()<= 999999) ) 
					{
						
						if (	(phn.val().length == 10) && (phn.val() >= 7000000000) && (phn.val() <= 9999999999)	)
						 { 
							 $("#panel2").slideUp();
							$("#panel3").slideDown();
							$("#editAddress").show();
							$("#editEmailid").show();						 
						 }
						 else
							{
							 	$("#phone").html("");
								$("#phone").css("border","1px solid #ff0000");		
								$("#phone").focus();
							}
					}
					else
					{
						$("#pin").html("");
						$("#pin").css("border","1px solid #ff0000");				
						$("#pin").focus();	
					};				
				};
		});
	});
</script>

<script>
	$(document).ready(function() 
	{
		$("#makePaymentCreditCard").click(function() 
				{
			/* Ajax Call for payment via Credit Card Starts Here*/
			$.ajax(
			{
				type : 'POST',
				data : 
				{
					cardNumber : $("#cardNumber").val(),
					expireMonth : $("#expireMonth").val(),
					expireYear : $("#expireYear").val(),
					cvv : $("#cvv").val(),
					grandTotal : $("#grandTotalC").val()
				},
				url : 'verifyCreditCardDetails',
				success : function(data) 
				{
					$("#validDetails").html(data.valid);
					var valid1 = $("#validDetails").html();					
					if (valid1 == 1) 
					{
						$("#validDetails").html(' ');
						$.ajax(
						{
							type : 'GET',
							url : 'makePaymentCreditCard?cardNumber='+ $("#cardNumber").val(),
							success : function(data) 
							{
								window.location = "clearUserCartDetails";
							}
						});
					}
					else if(valid1 == 2)
					{
						$("#validDetails").html("Insufficient Account Balance");
						$("#validDetails").css("color","#ff0000");
					}
					else {
							$("#validDetails").html("Invalid Card Details");
							$("#validDetails").css("color","#ff0000");
					}
				}
			});
		});
	});
</script>
<script>
	$(document).ready(function() 
	{
		$("#nextPaymentPage").click(function() 
		{
			/* Ajax Call for place order Starts Here*/
			$.ajax(
			{
				type : 'GET',
				url : 'placeOrder?email='+ $("#emailInput").val() + '&name='+ $("#name").val() + '&pinCode='+ $("#pin").val() + '&addressLine1='
						+ $("#address").val()+ '&addressLine2='+ $("#address2").val()+ '&phoneNumber=' + $("#phone").val()+ '&city=' + $("#city").val(),
				success : function(data) 
				{
				}
			});
			/* Ajax call for place order ends here */
			$("#panel4").slideDown();
			$("#panel3").slideUp();
			$("#editOrder").hide();
			$("#editEmailid").hide();
			$("#editAddress").hide();
		});
	});
</script>
<!-- Bank Login Redirect Here-->
<script>
	$(document).ready(function() 
	{
		$("#bankLogin").click(function() 
		{
			var bankSelected = $('input[name=bank]:checked').val();
			window.open("bankLogin.jsp?bankName=" + bankSelected);
		});
	});
</script>
<script>
	$(document).ready(function() 
	{
		$("#makePaymentDebitCard").click(function() 
		{			
			/* Ajax Call for payment via Credit Card Starts Here*/
			$.ajax(
			{
				type : 'POST',
				data : 
				{
					cardNumber : $("#cardNumberD").val(),
					expireMonth : $("#expireMonthD").val(),
					expireYear : $("#expireYearD").val(),
					cvv : $("#cvvD").val(),
					grandTotal : $("#grandTotalD").val()
				},
				url : 'verifyCreditCardDetails',
				success : function(data) 
				{
					$("#validDetailsD").html(data.valid);
					var valid1 = $("#validDetailsD").html();
					if (valid1 == 1) 
					{																
						$("#validDetailsD").html(' ');
						$.ajax(
						{
							type : 'GET',
							url : 'makePaymentCreditCard?cardNumber='+ $("#cardNumberD").val(),
							success : function(data) 
							{
								$.ajax(
								{
									type : 'GET',
									url : 'clearUserCartDetails',
									success : function(data) 
									{
										window.location = "./generateReceipt.jsp";
									}
								});
							}
						});
					} 														
					else if(valid1==2)
					{																																	$("#validDetailsD").html("Invalid Card Details");
						$("#validDetailsD").html("Insufficient Card Balance");
						$("#validDetailsD").css("color","#ff0000");
					}					
					else
					{
						$("#validDetailsD").html("Invalid Card Details");
						$("#validDetailsD").css("color","#ff0000");
					}
				}
			});
		});
	});
</script>
<script>
	$(document).ready(function() 
	{
		$("#editEmailid").click(function() 
		{
			$("#editOrder").hide();
			$("#editAddress").hide();
			$("#editEmailid").hide();
			$("#panel1").slideDown();
			$("#panel2").slideUp();
			$("#panel3").slideUp();
			$("#panel4").slideUp();

		});
	});
</script>
<script>
	$(document).ready(function() 
	{
		$("#editAddress").click(function()
		{
			$("#editOrder").hide();
			$("#editEmailid").hide();
			$("#editAddress").hide();
			$("#panel2").slideDown();
			$("#panel1").slideUp();
			$("#panel3").slideUp();
			$("#panel4").slideUp();

		});
	});
</script>
<script>
	$(document).ready(function() 
	{
		$("#editOrder").click(function() 
		{
			$("#editOrder").hide();
			$("#editEmailid").hide();
			$("#editAddress").hide();
			$("#panel3").slideDown();
			$("#panel1").slideUp();
			$("#panel2").slideUp();
			$("#panel4").slideUp();

		});
	});
</script>
<script>
	$(function() {
		$("#receipt").dialog();
	});
</script>
<script>
	$(function() 
	{
		$("#tabs").tabs().addClass("ui-tabs-vertical ui-helper-clearfix");
		$("#tabs li").removeClass("ui-corner-top").addClass("ui-corner-left");
	});
</script>
<script type="text/javascript">
	//Created / Generates the captcha function 
	$(document).ready(function() 
	{
		$("#btnrefresh").click();
	});
	function DrawCaptcha() {
		var a = Math.ceil(Math.random() * 10) + '';
		var b = Math.ceil(Math.random() * 10) + '';
		var c = Math.ceil(Math.random() * 10) + '';
		var d = Math.ceil(Math.random() * 10) + '';
		var e = Math.ceil(Math.random() * 10) + '';
		var f = Math.ceil(Math.random() * 10) + '';
		var g = Math.ceil(Math.random() * 10) + '';
		var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d
				+ ' ' + e + ' ' + f + ' ' + g;
		document.getElementById("txtCaptcha").value = code
	}
	// Validate the Entered input aganist the generated security code function   
	function ValidCaptcha() 
	{
		var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
		var str2 = removeSpaces(document.getElementById('captchaInput').value);
		if (str1 == str2) 
		{			
			window.location="cashOnDeliveryOderPayment";
		} 
		else 
		{
			var msg = "Incorrect Captch";
			$("#captchaError").css("color","#ff0000");
			$("#captchaError").html(msg);
			
			$("#captchaInput").css("border","2px solid #ff0000");
			$("#captchaInput").val('');
			$("#captchaInput").focus();
			return msg;
		};
	}
	// Remove the spaces from the entered and generated code
	function removeSpaces(string) 
	{
		return string.split(' ').join('');
	}
</script>


<style>
	.ui-tabs-vertical 
	{
		width: 55em;
	}	
	.ui-tabs-vertical .ui-tabs-nav {
		padding: .2em .1em .2em .2em;
		float: left;
		width: 12em;
	}	
	.ui-tabs-vertical .ui-tabs-nav li {
		clear: left;
		width: 100%;
		border-bottom-width: 1px !important;
		border-right-width: 0 !important;
		margin: 0 -1px .2em 0;
	}	
	.ui-tabs-vertical .ui-tabs-nav li a {
		display: block;
	}	
	.ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active {
		padding-bottom: 0;
		padding-right: .1em;
		border-right-width: 1px;
		border-right-width: 1px;
	}	
	.ui-tabs-vertical .ui-tabs-panel {
		padding: 1em;
		float: right;
		width: 40em;
	}
</style>

<style>
	.box {
		float: left;
		width: 100px;
		height: 40px;
		border: 1px solid #000000;
		margin: auto 20px auto 20px;
		text-align: center;
		padding-top: 15px;
	}
</style>
<style>
	.captcha {
		width: 250px;
		height: 120px;
		border: 1px solid red;
		margin-right :10px;
		float: left;
	}
	
	.btn {
		background: white url('asset/Images/refresh.png') left no-repeat;
		width: auto;
		height: 30px;
	}
</style>
			
			
</head>
<body>
<br><br><br>
	<div id="screenSize" class="myscreenSize">
		<div id="flip1" align="left">
			<label class="mylabel"><font color="#ffffff">&nbsp;&nbsp;1.SIGN IN</font></label> 
			<input type="submit" id="editEmailid" value="Edit Email" class="mysubmit3">
		</div>

		<div id="panel1">
			Email Address* <br><br>
				<input type="email" id="emailInput" name="email" required="true" class="form-control input-sm"  style="width:400px" value='<s:property value="addressDetails.email"/>'>
				<br> <br> 
				<input id="show" type="checkbox" name="registeredUser"> 
				I have a Flipkart account <br>
				<div id="pwd">
					<br> Password <br> <br> 
					<input type="password" id="password" name="password" class="form-control input-sm"  style="width:400px"><br />
					<label id="check_email_password" width="200px"></label>
				</div>
				<br>
				<button id="nextAddressPage" type="button" value="CONTINUE" align="middle" class="mysubmit1">CONTINUE</button>
		</div>

		<div id="flip2">
			<label class="mylabel"><font color="#ffffff">&nbsp;&nbsp;2.DELIVERY ADDRESS</font></label> <input
				type="submit" id="editAddress" value="Edit Address"
				class="mysubmit3">
		</div>
		<div id="panel2">
			<div>
				<div>
					<table class="table table-striped">
						<tr>
							<td>Name*
							</td>
							<td><input type="text" name="name" id="name" class="form-control input-sm"  style="width:400px" pattern="[A-Z a-z]+"required="true" 
						   title="Please provid valid name" placeholder="Please Enter Name"value='<s:property value="addressDetails.name"/>'>
							</td>
						</tr>
						<tr>
							<td>Pincode*
							</td>
							<td><input minlength="6" maxlength ="6" type="text" name="pincode" id="pin" class="form-control input-sm"  style="width:400px" required="true" pattern="[1-9]{1}[0-9]{5}" title="Please provide valid 6 digit pin" 	
					       placeholder="Please Enter 6 Digit Pincode" value='<s:property	value="addressDetails.pinCode"/>'>
							</td>
						</tr>
						<tr>
							<td>Address	Line 1*
							</td>
							<td><input type="text" name="addressLine1" id="address" class="form-control input-sm" style="width:400px" required="true" title="Please provide valid City name"
						placeholder="Please Enter Address Line 1" value='<s:property value="addressDetails.addressLine1"/>'>
							</td>
						</tr>
						<tr>
							<td>Address	Line 2*
							</td>
							<td><input type="text" id="address2" name="addressLine2" class="form-control input-sm" style="width:400px" placeholder="Please Enter Address Line 2"
						value='<s:property value="addressDetails.addressLine2"/>'>
							</td>
						</tr>
						<tr>
							<td>City*
							</td>
							<td><input type="text" name="city" id="city" class="form-control input-sm" style="width:400px" required="true" pattern="[A-Z a-z]+" title="Please provide valid City name"
						placeholder="Please Enter City Name" value='<s:property value="addressDetails.city"/>'> 
							</td>
						</tr>
						<tr>
							<td>Phone*
							</td>
							<td><input type="text" minlength="10" maxlength ="10" name="phone" required="true" id="phone" class="form-control input-sms" style="width:400px" pattern="[7-9]{1}[0-9]{9}"
						title="Please provide valid Prone number" value='<s:property value="addressDetails.phoneNumber"/>'>
							</td>
						</tr>
					</table>
				</div>
		<!--  For Applying Promo code		<input type="text" width="30px"/> 
											<input type="button" />
		-->
			</div>
			<input type="submit" id="nextOrderPage" value="SAVE ADDRESS & CONTINUE" align="middle" class="mysubmit2">
		</div>
		<div id="flip3">
			<label class="mylabel"><font color="#ffffff">&nbsp;&nbsp;3.ORDER SUMMARY</font></label> 
			<input type="submit" id="editOrder" value="Edit Order" class="mysubmit3"> 
		</div>
		<div id="panel3">
			<table class="table table-striped">
				<tr>
					<th>&nbsp;</th>
					<th>Item</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Sub Total</th>
				</tr>

				<s:iterator value="cartDetailsList">
					<tr>
						<td id="cardCol1"><img src="<s:property value="image"/>"
							width="auto" height="100px"
							alt="<s:property value="productName"/>"></td>
						<td id="cardCol2"><s:property value="productName" /></td>
						<td id="cardCol3"><s:property value="quantity" />
						</td>
						<td id="cardCol4"><s:property value="price" /></td>
						<td id="cardCol5"><s:property value="subTotal" /></td>
					</tr>
				</s:iterator>
				<tr>
					<th class="last" colSpan="4">Grand Total</th>
					<th id="grandTotal"><s:property value="grandTotal" /></th>
				</tr>
			</table>
			<br />
			<button id="nextPaymentPage" name="nextPaymentPage" align="middle" class="mysubmit2">CONFIRM  ORDER</button>
			<br />
		</div>
		<div id="flip4">
			<label class="mylabel"><font color="#ffffff">&nbsp;&nbsp;4.PAYMENT METHOD</font></label>
		</div>
		<div class="background" style="background-color:#ffffff">
		<div id="panel4">
			<div id="tabs">
				<div class="span2">
				<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-pills nav-stacked">
					<li><a href="#COD">Cash On Delivery</a></li>
					<li><a href="#NetBanking">Net Banking</a></li>
					<li><a href="#CreditCard">Credit Card</a></li>
					<li><a href="#DebitCard">Debit Card</a></li>
				</ul>
				<div>
				<div class="span5">
				<div id="COD" style="width: 600px; height: 300px;">					
					<h4>Cash on Delivery</h4><br><br>
					<div onload="DrawCaptcha();">
						<div class="captcha">
							<br /> 
							<label id="captchaError"></label><br/>
							<input type="text" id="txtCaptcha" style="background-image: url(1.jpg); width: 130px; text-align: 
									center; border: none; font-weight: bold; font-family: Modern" />
							<input type="submit" name="button1" id="btnrefresh" class="btn"	value="Refresh" onclick="DrawCaptcha();"> 
							<br /> 
							<input type="text" id="captchaInput" class="form-control input-sm" placeholder="Please Enter Number Above" /> <br /> <br />
						</div>
						<div class="captcha" style = "float: left;">
							<p style="padding: 10px;"><br/><b>Verify Order</b><br />
							Type the numbers you see in the image on the left.</p>
						</div>
						<br><br><br>
						<input id="Button1" type="submit" value="CONFIRM ORDER" class="mysubmit1" onclick="ValidCaptcha();" />
					</div>
				</div>
				<div id="NetBanking" style="height: 300px;">
						<br>
						<div class="span4">
							<div class="box">
								<input type="radio" id="bankRadio1" name="bank" value="SBI"> SBI<br>
							</div>
						</div>
						<div class="span4">
							<div class="box">
								<input type="radio" id="bankRadio2" name="bank" value="ICICI"> ICICI<br>
							</div>
						</div>
						<div class="span4">
							<div class="box">
								<input type="radio" id="bankRadio3" name="bank" value="HDFC"> HDFC<br>
							</div>
						</div>
						<button name="bankLogin" id="bankLogin" class="mysubmit1">
						SELECT BANK</button>
						<br>
				</div>
				<div id="CreditCard" style="text-align: left;">
					<section>					
					<!-- <form> --->
					<div>						
						<h2>Credit Card Details</h2>					
					</div>
					<div>
							<label>Card Number</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
							<input type="text" id="cardNumber" name="card_number"  name="card_number" pattern="[0-9]{13,16}"
							placeholder="9842 9472 9457 9472" class="form-control input-sm" required />
					</div><br/>
					<div>
						<label>Expiration</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 														
						 <select class="span3" id="expireMonth" name="expiryMonth" required>
						 	<option selected="selected" >Month</option>				                
			                <option value="01">January</option>
			                <option value="02">Febuary</option>
			                <option value="03">March</option>
			                <option value="04">April</option>
			                <option value="05">May</option>
			                <option value="06">June</option>
			                <option value="07">July</option>
			                <option value="08">August</option>
			                <option value="09">September</option>
			                <option value="10">October</option>
			                <option value="11">November</option>
			                <option value="12">December</option>
		              	</select>	
		              	&nbsp;&nbsp;	           				
						 <select class="span2" id="expireYear" name="expiry_year">
								<option selected="selected" >Year</option>	
				                <option value="2014">2014</option>
				                <option value="2015">2015</option>
				                <option value="2016">2016</option>
				                <option value="2017">2017</option>
				                <option value="2018">2018</option>
				                <option value="2019">2019</option>
				                <option value="2020">2020</option>
				                <option value="2021">2021</option>
				                <option value="2022">2022</option>
				                <option value="2023">2023</option>
			               </select>
			             </div><br/>
			             <div>
			             	<label>CVC Code</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="password" id="cvv" name="cvc_code" placeholder="174" size="10" class="form-control input-sm" required />
						</div><br/>
						<div>
						<center>
							<button id="makePaymentCreditCard" align="middle" class="mysubmit1">MAKE PAYMENT</button>
						</center>
						<input type="hidden" id = "grandTotalC"  value="<%=session.getAttribute("grandTotal")%>" />
					</div><br/>
					<div>
						<center> 
							<label id="validDetails" width="300px"></label>
						</center>
					</div>
					<!-- </form> --->					
					</section>
				</div>			
				<div id="DebitCard" style="text-align: left;">
					<section>					
					<!-- <form> --->
					<div>						
						<h2>Debit Card Details</h2>					
					</div>
					<div>
							<label>Card Number</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
							<input type="text" id="cardNumberD" name="card_number"  name="card_number" pattern="[0-9]{13,16}"
							placeholder="9842 9472 9457 9472" class="form-control input-sm" required />
					</div><br/>
					<div>
						<label>Expiration</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 														
						 <select class="span3" id="expireMonthD" name="expiryMonth" required>
						 	<option selected="selected" >Month</option>				                
			                <option value="01">January</option>
			                <option value="02">Febuary</option>
			                <option value="03">March</option>
			                <option value="04">April</option>
			                <option value="05">May</option>
			                <option value="06">June</option>
			                <option value="07">July</option>
			                <option value="08">August</option>
			                <option value="09">September</option>
			                <option value="10">October</option>
			                <option value="11">November</option>
			                <option value="12">December</option>
		              	</select>	
		              	&nbsp;&nbsp;	           				
						 <select class="span2" id="expireYearD" name="expiry_year">
								<option selected="selected" >Year</option>	
				                <option value="2014">2014</option>
				                <option value="2015">2015</option>
				                <option value="2016">2016</option>
				                <option value="2017">2017</option>
				                <option value="2018">2018</option>
				                <option value="2019">2019</option>
				                <option value="2020">2020</option>
				                <option value="2021">2021</option>
				                <option value="2022">2022</option>
				                <option value="2023">2023</option>
			               </select>
			             </div><br/>
			             <div>
			             	<label>CVC Code</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
							<input type="password" id="cvvD" name="cvc_code" placeholder="174" size="10" class="form-control input-sm" required />
						</div><br/>
						<div>
						<center>
							<button id="makePaymentDebitCard" align="middle" class="mysubmit1">MAKE PAYMENT</button>
						</center>
						<input type="hidden" id = "grandTotalD"  value="<%=session.getAttribute("grandTotal")%>" />
					</div><br/>
					<div>
						<center> 
							<label id="validDetailsD" width="300px"></label>
						</center>
					</div>
					<!-- </form> --->					
					</section>
				</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	
</body>
</html>