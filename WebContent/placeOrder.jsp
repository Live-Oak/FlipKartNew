<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%> <%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
<link href="./asset/CSS/creditDebitCard.css" rel="stylesheet" type="text/css">
<link href="./asset/CSS/verticalTabs.css" rel="stylesheet" type="text/css">
<link href="./asset/CSS/placeOrder.css" rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/jquery-ui.js"></script>


<script>
$(document).ready(function(){
  
	$("#pwd").hide();

});
</script>

<script>
$(document).ready(function(){
  
	$("#panel1").show();

});
</script>


<script>
$(document).ready(function(){
  
	$("#editOrder").hide();

});

</script>

<script>
$(document).ready(function(){
  
	$("#editEmailid").hide();

});
</script>

<script>
$(document).ready(function(){
  
	$("#editAddress").hide();

});
</script>


<script>
$(document).ready(function(){
  $("#show").click(function(){
    $("#pwd").toggle();
  });
});
</script>



<script> 
/*$(document).ready(function(){
  $("#flip1").click(function(){
    $("#panel1").slideDown();
    
  });
});*/
</script>

<script type="text/javascript">
$(document).ready(function(){ 
	 var email = $("#emailInput");
	 if(email.val().length > 0)
		{
		  $("#panel3").slideDown();
		  $("#panel2").slideUp();
		  $("#panel1").slideUp();
		  $("#editAddress").show();
		  $("#editEmailid").show();
		}
});
</script>

<script> 
$(document).ready(function(){
  $("#nextAddressPage").click(function(){
	  var email = $("#emailInput").val();
	  var pass = $("#password").val();
	
	  if(	(email.length > 0)	&&	($("#show").prop('checked') == true) && (pass.length > 0)		)	
	  {
		  $.ajax({
			    type: 'GET',	    
			    url:'check_login_password ?email=' + $("#emailInput").val() + '&password=' + $("#password").val(),
			    success: function(data){
			    	$("#check_email_password").html(data.message);
			    	var status=$("#check_email_password").html();
			    	if(status=="available")
			    		{
			    		$("#check_email_password").html(' ');												
						/* Ajax call here to fetch user address details Start here*/
							 $.ajax({
						    type: 'GET',	    
						    url:'getUserAddressDetails ?email=' + $("#emailInput").val() ,
						    success: function(data){
						    	//alert(data.addressDetails.name);						    	
						    	$("#name").val(data.addressDetails.name);
						    	$("#pin").val(data.addressDetails.pinCode);
						    	$("#address").val(data.addressDetails.addressLine1);
						    	$("#address2").val(data.addressDetails.addressLine2);
						    	$("#phone").val(data.addressDetails.phoneNumber);
						    	$("#city").val(data.addressDetails.city);
						    }});			
						    	
							/* Ajax call here to fetch user address details End here*/	
						  $("#panel3").slideDown();
						  $("#panel2").slideUp();
						  $("#panel1").slideUp();
						  $("#editAddress").show();
						  $("#editEmailid").show();

			    		}
			    	else
			    		{
			    			//alert("Invalid email id or password");
			    			$("#check_email_password").html("Invalid email or password");
			    			$("#check_email_password").css("color", "#ff0000");
			    		}
			     }});	
	  }
	  
	  else
		{
		  $("#password").css("border", "1px solid #ff0000");
		}
	  if( (email.length > 0) &&	($("#show").prop('checked') == false))
	  {
		  $("#panel2").slideDown();
		  $("#panel1").slideUp();		  
		  $("#editEmailid").show();
	  }		
  });
});
</script>

<script> 
/*$(document).ready(function(){
  $("#flip2").click(function(){
    $("#panel2").slideDown();
    

  });
});*/
</script>


<script> 
$(document).ready(function(){
  $("#nextOrderPage").click(function(){
	  var name = $("#name");
	  var pin = $("#pin");
	  var add = $("#address");
	  var phn = $("#phone");
	  var city = $("#city");
	 if(	(name.val().length > 0)  && (pin.val().length > 0)  && (add.val().length > 0)  && (phn.val().length > 0) && (city.val().length > 0) )
	 {
	  $("#panel2").slideUp();
	  $("#panel3").slideDown();
	  $("#editAddress").show();
  	  $("#editEmailid").show();
	}	 
  });
});
</script>

<script> 
$(document).ready(function(){
  $("#nextPaymentPage").click(function(){	 
	
	  /* Ajax Call for place order Starts Here*/
	  	   $.ajax({
	  			    type: 'GET',			    
	  			    url:'placeOrder?email='+$("#emailInput").val()+'&name='+ $("#name").val()+'&pinCode='+$("#pin").val()+'&addressLine1='+$("#address").val()+'&addressLine2='+$("#address2").val()+'&phoneNumber='+$("#phone").val()+'&city='+$("#city").val() ,	
	  			    		success: function(data){
	  			    	/* Success wala kaam here...*/
	  			    	//alert("Order Place : Address tak Ho gaya");
	  			    }});	
	  	  /* Ajax call for place order ends here */
  
    $("#panel4").slideDown();
    $("#panel3").slideUp();
    $("#editOrder").show();
	  $("#editEmailid").show();
	  $("#editAddress").show();
  });
});
</script>
<script> 
$(document).ready(function(){
  $("#makePaymentCreditCard").click(function(){
	  alert($("#cardNumber").val());
	  /* Ajax Call for payment via Credit Card Starts Here*/		  
 	   $.ajax({
 			    type: 'POST',
 			    data : {
 			    	cardNumber : $("#cardNumber").val(),
 			    	expireMonth : $("#expireMonth").val(),
 			    	expireYear : $("#expireYear").val(),
 			    	cvv : $("#cvv").val()
 			    },
 			    url:'verifyCreditCardDetails',	
 			    		success: function(data)
 			    		{
 			    			$("#validDetails").html(data.valid);
 					    	var valid1 = $("#validDetails").html();
 					    	if(valid1 == 1)
 					    	{ 	
 					    		alert ("Credir Card Payment Valdated");
 					    		$("#validDetails").html(' ');   
 					    		$.ajax(
 					    		{
 					 			    type: 'GET',			    
 					 			    url:'makePaymentCreditCard?cardNumber='+$("#cardNumber").val() ,	
 					 			    		success: function(data)
 					 			    		{
 					 			    			alert ("Credir Card Payment bhi ho gayi bhai!!!!");
 					 			    			$.ajax(
 					 	 					    		{
 					 	 					 			    type: 'GET',			    
 					 	 					 			    url:'clearUserCartDetails' ,	
 					 	 					 			    		success: function(data)
 					 	 					 			    		{
 					 	 					 			    			alert ("Credir Card Payment bhi ho gayi bhai!!!!");
 					 					 			    			}
 					 		 			    			});
 					 			    			alert("Cart Bhi clear ho gaya bhai !!!!32334");
				 			    			}
	 			    			});
 					    	}
 					    	else
 					    	{
 					    		alert("Details not Valid");
 					    		$("#validDetails").html("Invalid Card Details");
 				    			$("#validDetails").css("color", "#ff0000"); 					    		   						
 					    	}
 	   					}
 	   		});	
 	  /* Ajax Call for payment via Credit Card Ends Here*/ 
	  
	 // $("#editOrder").hide(); 
	 // $("#editAddress").hide();
	 // $("#editEmailid").hide();
  //  $("#panel4").slideUp();

  });
});
</script>


<script> 
$(document).ready(function(){
  $("#editEmailid").click(function(){
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
$(document).ready(function(){
  $("#editAddress").click(function(){
	  $("#editOrder").hide(); 
	  $("#editEmailid").hide();
	  $("#editAddress").hide();
	  $("#panel2").slideDown();
    $("#panel1").slideUp();
    $("#panel3").slideUp();
    $("#panel4").slideUp();

  });});
  
 </script>
 
 
  <script> 
  $(document).ready(function(){
    $("#editOrder").click(function(){
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
    $( "#receipt" ).dialog();
  });
  </script>

<script>
  $(function() {
    $( "#tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
    
    
    
    $( "#tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
  });
  </script>
  <style>
  .ui-tabs-vertical { width: 55em; }
  .ui-tabs-vertical .ui-tabs-nav { padding: .2em .1em .2em .2em; float: left; width: 12em; }
  .ui-tabs-vertical .ui-tabs-nav li { clear: left; width: 100%; border-bottom-width: 1px !important; border-right-width: 0 !important; margin: 0 -1px .2em 0; }
  .ui-tabs-vertical .ui-tabs-nav li a { display:block; }
  .ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active { padding-bottom: 0; padding-right: .1em; border-right-width: 1px; border-right-width: 1px; }
  .ui-tabs-vertical .ui-tabs-panel { padding: 1em; float: right; width: 40em;}
  </style>
  

</head>
<body>
 
<div id="screenSize" class="myscreenSize">
<div id="flip1" align="left">
<label class="mylabel" >1.EMAIL ID</label>
<input type="submit" id="editEmailid" value="Edit Email"  class="mysubmit3" >
</div>

<div id="panel1"><label class="mylabel1"  >Email Address*</label>
<br>
<form name="login_po" id="login_po" method="post" >

<input type ="email" id="emailInput" name="email" required="true" class="mytext1" 
		value='<s:property value="addressDetails.email"/>' >
<br>
<br>
<input id="show" type ="checkbox" name="registeredUser" >
<label class="mylabel1"  >I have a Flipkart account</label>
<br>
<div id="pwd"><br><label class="mylabel1" ></label>
	<br>
	<input  type ="password" id="password" name="password" class="mytext1"><br/>
<label id="check_email_password" width="200px"></label>
</div>
<br>
<button id="nextAddressPage" type="button" value="CONTINUE" align="middle"  class="mysubmit1">
CONTINUE</button>
 <!--  </form> -->

</div>

<div id="flip2">
<label class="mylabel" >2.DELIVERY ADDRESS</label>

<input type="submit" id="editAddress"value="Edit Address"  class="mysubmit3" >
</div>
<div id="panel2"><div>
<div>
<label class="mylabel2"  >Name*</label> 
<input type="text" name="name" id="name" class="mytext2" pattern = "[A-Z a-z]+" required = "true" 
		title="Please provid valid name" placeholder="Please Enter Name" value='<s:property value="addressDetails.name"/>' >
		<br><br><br><br>
<label class="mylabel2"  >Pincode*</label>
<input type="text" name="pincode"  id="pin" class="mytext2" required="true"  pattern="[1-9]{1}[0-9]{5}" 
title="Please provide valid 6 digit pin" placeholder="Please Enter 6 Digit Pincode" value='<s:property	value="addressDetails.pinCode"/>' >
		<br><br><br><br>
<label class="mylabel2"  >Address Line 1*</label>
<input type="text" name="addressLine1" id="address" class="mytext2" required="true" title="Please provide valid City name" 
placeholder="Please Enter Address Line 1" value='<s:property value="addressDetails.addressLine1"/>' >
			<br><br><br><br>
<label class="mylabel2"  >Address Line 2</label>
<input type="text" id="address2" name="addressLine2"  class="mytext2" placeholder="Please Enter Address Line 2" 
value='<s:property value="addressDetails.addressLine2"/>' >
			<br><br><br><br>
<label class="mylabel2"  >City*</label>
<input type="text" name="city" id="city" class="mytext2"	required="true"  pattern = "[A-Z a-z]+"
		title="Please provide valid City name" placeholder="Please Enter City Name" value='<s:property value="addressDetails.city"/>' >		
			<br><br><br><br>
<label class="mylabel2"  >Phone*</label>
<input type="text" name="phone"  required="true" id="phone" class="mytext2" 
		pattern="[7-9]{1}[0-9]{9}" title="Please provide valid Prone number" value='<s:property value="addressDetails.phoneNumber"/>' >
			<br><br><br><br>
</div>
</div>
<input type="submit" id="nextOrderPage"value="SAVE & CONTINUE" align="middle" class="mysubmit2" ></div>

<div id="flip3"><label class="mylabel" >3.ORDER SUMMARY</label>
<input type="submit" id="editOrder"value="Edit Order"  class="mysubmit3" >
</div>
<div id="panel3">
<style>
table
{
	width : auto;
	height : height;
	border : 1px solid #990000;
	margin : auto;
}

table th
{
	width : auto;
	height : auto;
	border : 2px solid #660000; 
	text-align : center;
	font-weight : bold;
	/*background-color : #08298A;*/
	/*color : #ffffff;*/
}
 .last
{
	text-align : right;
	padding-right : 20px;
}

table td
{
	width : 150px;
	height : 80px;
	border : 2px solid #660000; 
	text-align : center;
	/*font-weight : bold;*/
}


</style>
<table>
	<tr>
		<th>&nbsp;</th>
		<th>Item</th>
		<th>Quantity</th>
		<th>Price</th>
		<th>Sub Total</th>
	</tr>
	
	<s:iterator value="cartDetailsList">
	<tr>
		<td id="cardCol1">
			<img src="<s:property value="image"/>" width="100px" height="100px" alt="<s:property value="productName"/>" >
		</td>
		<td id="cardCol2">
			<s:property value="productName"/>
		</td>
		<td id="cardCol3">
			<s:property value="quantity"/>
			<!--<input type="text" name="quantity" id="quantity" value='<s:property value="quantity"/>' style=" width:50px; margin:auto; text-align : center;">-->
		</td>
		<td id="cardCol4">
			<s:property value="price"/>
		</td>
		<td id="cardCol5">
			<s:property value="subTotal"/>	
			
		</td>
	</tr>
	</s:iterator>
	<tr>
	<th class="last" colSpan ="4" > Grand Total</th>
	<th id="grandTotal">  <s:property value="grandTotal"/>		</th>
	</tr>
</table>
<br/>
<button id="nextPaymentPage" name="nextPaymentPage"  align="middle" class="mysubmit2" >CONTINUE</button>
<br/>
</div>


<div id="flip4"><label class="mylabel" >4.PAYMENT METHOD</label>

</div>

<div id="panel4">
	<script>
	$('#tabs')
	.tabs()
	.addClass('ui-tabs-vertical ui-helper-clearfix');
	
	</script>
	

	<div id="tabs">
	    <ul style="text-align:left;">
	        <li>
	            <a href="#COD">Cash On Delivery</a>
	        </li>
	        <li>
	            <a href="#NetBanking">Net Banking</a>
	        </li>
	        <li>
	            <a href="#CreditCard">Credit Card</a>
	        </li>
	        <li>
	            <a href="#DebitCard">Debit Card</a>
	        </li>
	    </ul>
	    <style>
	    .box
	    {
	    	float:left;
	    	width: 100px;
	    	height: 40px;
	    	border: 1px solid #000000;
	    	margin : auto 20px auto 20px;
	    	text-align : center; 
	    	padding-top : 15px;
	    	
	    }
	    </style>
	    <div id="COD"style="width:600px; height:300px;">
	     <script type="text/javascript">
		   //Created / Generates the captcha function    
		    
		   $(document).ready(function(){
			   $("#btnrefresh").click();
		   });
		   function DrawCaptcha()
		    {
		        var a = Math.ceil(Math.random() * 10)+ '';
		        var b = Math.ceil(Math.random() * 10)+ '';       
		        var c = Math.ceil(Math.random() * 10)+ '';  
		        var d = Math.ceil(Math.random() * 10)+ '';  
		        var e = Math.ceil(Math.random() * 10)+ '';  
		        var f = Math.ceil(Math.random() * 10)+ '';  
		        var g = Math.ceil(Math.random() * 10)+ '';  
		        var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' '+ f + ' ' + g;
		        document.getElementById("txtCaptcha").value = code
		    }
		    // Validate the Entered input aganist the generated security code function   
		    function ValidCaptcha(){
		        var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
		        var str2 = removeSpaces(document.getElementById('txtInput').value);
		        if (str1 == str2) 
		        {
		        	$("#login_po").submit();	
		        }
		        else
		        {
		        	var msg = "Incorrect Captch";
		        	$("#txtInput").css("border", "1px solid #ff0000");
		        	$("#txtInput").html('');
		        	$("#txtInput").focus();
		        	return msg;
		        }
		        ;		        
		    }		
		    // Remove the spaces from the entered and generated code
		    function removeSpaces(string)
		    {
		        return string.split(' ').join('');
		    }	
		    </script>
		    <style>
		    .captcha
		    {
		    	width:250px;
			    height:100px;
			    border:1px solid red;
			    /*margin-right:10px;*/
			    float:left;
		    }
		    .btn{
		    	background: white url('asset/Images/refresh.png') left no-repeat; 
		    	 width:auto;
		    	 height:30px;
		    }
		    </style>    		
	    	<div onload="DrawCaptcha();">
				<h3>Cash on Delivery</h3>
				<div class="captcha">
				<br/>
				<input type="text" id="txtCaptcha"	style="background-image:url(1.jpg); width:130px; text-align:center; border:none;
				            font-weight:bold; font-family:Modern" />
				            				
				<input type="submit" name="button1" id ="btnrefresh" class="btn"  value="Refresh" onclick="DrawCaptcha();" >
				
				 <br/>
				<input type="text" id="txtInput"  placeholder="Please Enter Number Above"/>  <br/>  <br/>
				 </div>
				<div class="captcha" style="margin-left:20px;width:300px; text-align:left;">
				
				Verify Order<br/><br/>				 
				 Type the numbers you see in the image on the left.
				 
				 </div>
				 
				 <input id="Button1" type="submit" value="CONFIRM ORDER"  onClick="form.action='placeOrderActionViaCOD';"
				 style="background-color: #FFA500; border: 1px solid #FFA500;color: #FFFFFF; font-size: large;    
				 height: 40px; left: 350px;  margin-top : 20px; position: none; width: 234px;" onclick="alert(ValidCaptcha());"/>
				   
				<!--<input type="submit" id="nextReceiptPage"value="CONFIRM ORDER" class="mysubmit2"  >-->
			</div>
	    </div>
	    <div id="NetBanking" style="height:300px;">
	        <div align="center"><br>
				<div class="box"> <input type="radio" name="bank" value="SBI"> SBI<br> </div>
				<div class="box">  <input type="radio" name="bank" value="ICICI" > ICICI<br> </div>
				<div class="box">  <input type="radio" name="bank" value="HDFC"> HDFC<br> </div>
				  <input type="submit" name="bankLogin"   id="nextReceiptPageIB" value="CONTINUE"   style="background-color: #FFA500; border: 1px solid #FFA500;color: #FFFFFF; font-size: large;    
				 height: 40px; left: 350px;  margin-top : 20px; position: none; width: 234px;" >
				<br>
			</div>
	    </div>
	    <div id="CreditCard" style="height:300px;">	       
		  <section class="credit-card visa gr-visa">
        <div class="logo">Visa</div>
        <form>
            <h2>Payment Details</h2>
			<label id="validDetails" width="300px"></label>
            <ul class="inputs">
                <li>
                    <label>Credit Card Number</label>
                    <input type="text"  id="cardNumber" name="card_number" pattern="[0-9]{13,16}" placeholder="9842 9472 9457 9472" class="full gr-input" required />
                </li>
                <li class="expire last">
                    <label>Expiration</label>
                    <input type="text" id="expireMonth" name="expire_month" placeholder="December (12)" size="10" class="month gr-input" required />
                    <input type="text" id="expireYear" name="expire_year" placeholder="2014" size="10" class="year gr-input" required />
                    <div class="clearfix"></div>
                </li>
                <li class="cvc-code last">
                    <label>CVV Code</label>
                    <input type="text" id="cvv" name="cvc_code" placeholder="174" size="10" class="gr-input" required />
                </li>
                <div class="clearfix"></div>
            </ul>
             
            <ul>
           
           	 <li>
            	<button  id="makePaymentCreditCard"  align="middle" >MAKE PAYMENT </button>
     		</li>
       		</ul>
       </form>
        <div class="watermark">Visa</div>
    </section>	
		 </div>
 		<div id="DebitCard" style="height:300px;">
	      <section class="credit-card visa gr-visa">
        <div class="logo">Visa</div>
      <!--  <form> -->
            <h2>Payment Details</h2>

            <ul class="inputs">
                <li>
                    <label>Debit Card Number</label>
                    <input type="text" name="card_number" pattern="[0-9]{13,16}" placeholder="9842 9472 9457 9472" class="full gr-input" required />
                </li>
                <li class="expire last">
                    <label>Expiration</label>
                    <input type="text" name="expire_month" placeholder="December (12)" size="10" class="month gr-input" required />
                    <input type="text" name="expire_year" placeholder="2014" size="10" class="year gr-input" required />
                    <div class="clearfix"></div>
                </li>
                <li class="cvc-code last">
                    <label>CVC Code</label>
                    <input type="text" name="cvc_code" placeholder="174" size="10" class="gr-input" required />
                </li>
                <div class="clearfix"></div>
            </ul>
             
            <ul>
           
           	 <li>
            	<input type="submit" id="nextReceiptPageDC" value="CONFIRM ORDER" align="middle"  >
     		</li>
       		</ul>
         <!--  </form> -->
        <div class="watermark">Visa</div>
    </section>
  
	    </div>
	</div>
	
</div>
</div>
</body>
</html>