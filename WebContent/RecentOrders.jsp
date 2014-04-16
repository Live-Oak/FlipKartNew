<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="edu.iiitb.model.UserEntry"%>
<%@page import="edu.iiitb.model.MyOrdersModel"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>
<link rel="icon" type="/favicon.png" href="asset/Images/flipkartlogo.png">
	<!-- Custom styles for this template -->
	<link href="asset/CSS/Index.css" rel="stylesheet">
	<link rel="stylesheet" href="asset/CSS/jquery-ui.css">
	<link href="asset/CSS/starter-template.css" rel="stylesheet">
<!-- 	<link rel="stylesheet" href="asset/CSS/login.css"> -->
<link rel="stylesheet" href="asset/CSS/reveal.css">	
	
	<!-- Bootstrap core CSS -->
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	<script src="asset/JavaScripts/drophover.js"></script>
	<script src="asset/JavaScripts/jquery-ui.js"></script> 
	<script src="asset/JavaScripts/jquery.reveal.js"></script>

<style type="text/css">
.fk-font-17 {
    font-size: 17px;
}

.line, .lastUnit {
    overflow: hidden;
}

.tmargin20 {
    margin-top: 20px;
}
.bmargin20 {
    margin-bottom: 20px;
} 

#myAccountBtn {
    color: #848484;
}
.fk-inline-block {
    display: inline-block;
}

a {
    color: #666666;
    cursor: pointer;
    text-decoration: none;
}

.myorder-tabs {
    border-bottom: 1px solid #B3B3B3;
    padding: 0 0 0 25px;
}

.myorder-tabs .tab.selected {
    background-color: #F7F5EE;
    border-bottom: 0 none;
    color: #565656;
    padding: 11px 30px 0;
}
.myorder-tabs .tab {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    background-color: #E0DED9;
    border-color: #B3B3B3 #B3B3B3 #CCCAC6;
    border-image: none;
    border-style: solid;
    border-top-left-radius: 2px;
    border-top-right-radius: 2px;
    border-width: 1px 1px 4px;
    color: #999999;
    cursor: pointer;
    font-size: 15px;
    padding: 7px 30px 4px;
    position: relative;
    text-align: center;
    top: 11px;
    vertical-align: bottom;
}
.fk-inline-block {
    display: inline-block;
}


.myorder-tabs .tab.selected .text {
    border-bottom: 4px solid #B3B3B3;
    display: inline-block;
    padding-bottom: 5px;
    vertical-align: bottom;
    padding-border-bottom: 0 0 0 11px;
}

.myorder-tabs #subText {
    padding: 0 0 7px 5px;
}

.fk-smallfont, .fk-font-small, .fksd-smalltext {
    font-size: 11px;
}

.myorder-tabs #past-orders {
    left: -4px;
    position: relative;
}

#order-section {
    color: #565656;
}

#order-section .order {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    box-shadow: 0 0 4px #D3CBB8;
    margin: 0 4px 20px;
}

#order-section .collapsed .order-collapsed {
    display: block;
}
#order-section .order-collapsed {
    background-color: #F2F2F2;
    border-bottom: 0 none;
    border-top: 4px solid #FFFFFF;
    cursor: pointer;
    padding: 12px 15px;
}
.fk-hidden, .hidden {
    display: none;
}

.size1of7 {
    width: 14.2857%;
}
.unit {
    float: left;
}
#order-section .smallText {
    color: #848484;
    font-size: 11px;
}
.size3of5 {
    width: 60%;
}
.size1of6 {
    width: 16.6667%;
}
.text_right {
    text-align: right;
}
.lastUnit {
    float: none;
    width: auto;
}
#order-section .order-expanded {
    background-color: #F9F9F9;
    border-bottom: 1px solid #E6E6E6;
    padding: 12px 15px;
    display: none;
}
.size2of5 {
    width: 40%;
}

#order-section .order-item {
    border-bottom: 1px dotted #CCCCCC;
    margin: 10 0px;
    padding: 10px 0;
}
.size1of8 {
    width: 12.5%;
}
.fk-text-center {
    text-align: center;
}
.size2of7 {
    width: 32.5714%;
}
.tmargin10 {
    margin-top: 10px;
}
.bmargin10 {
    margin-bottom: 10px;
}

#order-section .order-total {
    margin: 10px 15px;
}
.rmargin20 {
    margin-right: 20px;
}
.lastUnit {
    float: none;
    width: auto;
}

</style>

<script> 
$(document).ready(function(){
  $(".toggle-details").click(function(e){
	  var currentTarget = e.currentTarget;
    $(currentTarget).parent().find(".order-expanded").slideToggle("slow");
  });
});
</script>
 


</head>
<body>
      <%@ page import="com.opensymphony.xwork2.ActionContext,com.opensymphony.xwork2.util.ValueStack,javax.servlet.http.HttpSession" %>

<div class="col-md-2"></div>
<div class="col-md-8 ">
<ul class="line ">
<li id="myAccountBtn" class="fk-inline-block">
<a style = "color: #848484" href="Personal-info">My Account</a>
/
</li>
<li class="fk-inline-block">
<strong>My Orders</strong>
</li>
</ul>
<div class="col-md-12 myorder-tabs ">
<ul>
<li id="recent-orders" class="fk-inline-block tab selected">
<span class="text">RECENT ORDERS</span>
<span id="subText" class="text fk-font-small">(Last 2 Months)</span>
</li>
<li id="past-orders" class="fk-inline-block tab"><a href = "MyPastOrders">
<span class="text">PAST ORDERS</span></a>
</li>
</ul>

</div>
<br>

<s:iterator value="Orders">

<div class="col-md-12" id="order-section">
   <div class="fk-inf-scroll-item order physical collapsed ">
      <div class="line order-collapsed fk-hidden toggle-details">
          <div class="unit size1of7">
               <strong>    <s:property value="oredrNo"/>   </strong>
          </div>
          <div class="unit size3of5 smallText"> <s:property value="prodName"/> (Total: <s:property value="quantity"/> item) </div>
          <div class="unit size1of6">
                <span class="smallText">Order Total:</span>
                <strong>   Rs.<s:property value="totalprice"/>  </strong>
          </div>
          <div class="lastUnit text_right">
                <%-- <a class="toggle-details" title="Show order details"></a>  --%>


          </div>
      </div>
    <div class="line order-expanded">
    	<div class="col-md-12">
           <a class="btn btn-primary" href="Get_OrderInfo?OrderId=<s:property value="oredrNo" />" target="_blank"> <s:property value="oredrNo" /> </a>
           <hr>
<div class="col-md-12">
<div class="line js-order-details">
   <div class="line order-item">
        <div class="unit size1of8 fk-text-center">
             <img src="<s:property value="photo" />" alt="Smiley face" width="40" height="60">
        </div>
        <div class="unit size2of7">
              <s:property value="prodName"/>
              <p class="smallText tmargin10"> Qty: <s:property value="quantity"/> </p>
        </div>
        <div class="unit size1of6">
              <div> Rs. <s:property value="price"/> </div>
        </div>
        <div class="unit size2of7">
              <p class="greyText bmargin10"> Delivered on <s:property value="delievry_date"/> </p>
        </div>
        <div class="lastUnit text_right"> </div>
   </div>

   <div class="line order-total">
        <div class="line">
              <div class="unit size2of5">
                    <span class="smallText">Seller:</span>
                    <span class="rmargin20">WS Retail</span>
                    <span class="smallText fk-inline-block">Date:</span>
                    <s:property value="order_date"/> 
              </div> 
              <div class="lastUnit text_right">
                    <span class="smallText">Order Total:</span>
                    <strong>  Rs.<s:property value="totalprice"/>  </strong>
              </div>

        </div>
   </div>

</div>
</div>
</div>

 
  



</div>



</div>
</div>
</s:iterator>
</div>
<div class="col-md-1"></div>




</body>
</html>