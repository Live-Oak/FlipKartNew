<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Body Browse</title>
<!-- Custom styles for this template -->
<!-- Custom styles for this template -->
<link href="asset/CSS/Index.css" rel="stylesheet">
<link href="asset/CSS/starter-template.css" rel="stylesheet">
<link href="asset/CSS/CompareProducts.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="asset/CSS/bootstrap.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">

<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
<script src="asset/JavaScripts/bootstrap.min.js"></script>
<script src="asset/JavaScripts/jquery-1.9.1.js"></script>
<script src="asset/JavaScripts/jquery-ui.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("#newdivision").hide();
	});
</script>

<script type="text/javascript">
	$(document).ready(function(){
		$(".filters").change(function(event)
			{
			name();
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function(){
		$(".filtersprice").change(function(event)
			{
			name();
		});
	});
</script>

<script type="text/javascript">
function name()
{
	var temp='<b> Showing all the products in the category </b><br><br>';
	var pId = [];
	var price = [];	
	var cnt = $("input[name='checkbox']:checked").length;
	var cnt1 = $("input[name='checkboxprice']:checked").length;
	if(cnt > 0 || cnt1 > 0)
		{
			$("#newdivision").empty();
			$(".filters").each(function() {
				if ($(this).is(':checked')) {
					  pId.push($(this).attr("brandname"));
				   }
			});
			$(".filtersprice").each(function() {
				if ($(this).is(':checked')) {
					 price.push($(this).attr("price"));
					}
			});	
			var categoryId = $("#categoryid").val();
			
			 $.ajax({
				    type: 'POST',
				    contentType : "application/x-www-form-urlencoded; charset=utf-8",
				    data : {
				    	brand : pId.join(","),
				    	price : price.join(","),
				    	category : categoryId,
				    	count : cnt,
				    	countprice : cnt1
				    },
				    url:'getProductDetailcombined',
				    success: function(data){
				    	$.each(data.productinfofilter, function(count, stock) 
				    	{
				    		temp += '<div class="col-md-4"><div class="border"><center>';
				    		if(stock.availableQuantity == 0)
							{
				    			temp += '<a href="#">';
				    			temp += '<img src ="'+stock.image+'" alt="'+stock.productID+'"  height="140px" width="auto" /><br>';
				    			temp += '<img src="asset/Images/outofstock.jpg" alt="outofstock" height="40px">';
				    			temp += '</a>';
							}
				    		else
				    		{
				    			if(stock.availableQuantity >= stock.minimumQuantity)
				    			{
				    				if(stock.offer==0)
				    				{
				    					temp += '<a href="getProductDetail?productID='+stock.productID+'">';
				    					temp += '<img src ="'+stock.image+'" alt="'+stock.productID+'"  height="140px" width="auto" /> <br><br><br>';
						    			temp += '</a>';
				    				}
				    				else
				    				{
				    					temp += '<a href="getProductDetail?productID='+stock.productID+'">';
				    					temp += '<img src ="'+stock.image+'" alt="'+stock.productID+'"  height="140px" width="auto" /> <br>';
						    			temp += '<img src="asset/Images/offer.jpg" alt="offer" height="40px">';
						    			temp += '</a>';
				    				}
				    			}
				    			else
				    			{
				    				temp += '<a href="#">';
					    			temp += '<img src ="'+stock.image+'" alt="'+stock.productID+'"  height="140px" width="auto" />';
					    			temp += '<img src="asset/Images/outofstock.jpg" alt="outofstock" height="40px">';
					    			temp += '</a>';
				    			}
				    		}
				    			
					    	temp += '<hr>';
					    	temp += '<div class="giveMeEllipsis">';
					    	temp += '<a href="getProductDetail?productID=<s:property value="productID"/>">';
					    	temp += '<font size="4" color="black">'+stock.productName+'</font><br></a></div>';
					    	temp += '<hr><div style="height:50px">';
					    	
					    	if(stock.offer == 0)
				    		{
				    				temp += '<font size="5px" color="#76553B"> Rs.'+stock.price+'</font><br>';
				    		}
				    		else
				    		{
				    			temp += '<font color="#848484" size="4">Rs. <strike>'+stock.price+'</strike> '+stock.discount+'% OFF</font><br>';
				    			var a = stock.price;
			    				var b = stock.offer;
			    				var c = a-b;
			    				temp += '<font size="5px" color="#76553B"> Rs.'+c+'</font>';
				    		}
					    	
					    	temp += '</div><hr>';
					    	temp += 'This item has manufacturer warranty of '+stock.warranty+' years.<br>';
					    	temp += '<hr>';
					    	if(stock.availableQuantity == 0)
							{
					    		temp += '<input type="checkbox" disabled >Add To Compare</input>';
							}
					    	else if (stock.availableQuantity < stock.minimumQuantity)
					    	{
					    		temp += '<input type="checkbox" disabled >Add To Compare</input>';
					    	}
					    	else
					    	{
					    		temp += '<input type="checkbox" id="productID" class="addtocompare" name="compare" pid="'+stock.productID+'"/>Add To Compare</input>';  
					    	}
					    	temp += '<hr>';
							temp += '<br><br>';
					    	temp += '</center></div></div>';
					    	
				    	});
				    	$("#newdivision").show();
				    	$("#newdivision").append(temp);
				    	
				    	temp = '';
				    }
				    });
			 $("#dataappended").hide();
		}
	else{
			$("#dataappended").show();
			$("#newdivision").empty();
		}
	}
</script>



<script type="text/javascript">
	var product_id_to_send;
	$("#comparecart").show();
	$(document).ready(function() {
		$.ajax({
			type : 'GET',
			url : 'getProductsFromCartToCompare',
			success : function(data) {
				
				if(data.count>0)
					{
								$("#comparecart").show();
								$("#producttocompare").show();
								
									if (data.count == 1) 
									{
										$("#emptyComparediv").hide();
										$("#compare_button").attr("disabled",true);
										
									}
									if (data.count != 1) 
									{
										$("#compare_button").attr("disabled",false);
									}
									$("#producttocompare").show();
									$("#products_to_compare").empty();
									$.each(data.products,function(count,productcompare) {
											product_id_to_send = productcompare.productId;
											$("#products_to_compare").append("<div style='height:50px;' class='col-md-2' class='border'>"
																	+ "<center>"
																	+ "<div class='remove' pid='"+productcompare.productId+"'><a style='color:black;'>&#215</a></div><br>"
																	+ "<img src='"+productcompare.image+"' height='60px' width='60px' style='float:left' /><br>"
																	+ "<div class='productName'>"
																	+ productcompare.productName
																	+ "</div>"
																	+"</center>"
																	+ "</div>");
										});
								}
					
			}
			});  // 2 ajax
			$("#close_compare").click(function() {
				$.ajax({
					type : 'POST',
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					url : 'removeAllProductFromCompareCart',
					success : function(data) {

						$("#comparecart").hide();
						$('.addtocompare').prop('checked', false);

					}
				});

			});

						

			$(document).on('click','.remove',function(e) {
				var target = e.currentTarget;
				var pId = $(target).attr("pid");
				$.ajax({
						type : 'POST',
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						data : {
								productId : pId,
								},
						url : 'removeProductFromCompareCart',
						success : function(data) {
							$("#producttocompare").show();
							$("#products_to_compare").empty();
							$.each(data.products,function(count,productcompare) {
									product_id_to_send = productcompare.productId;
									$("#products_to_compare").append("<div style='height:50px;' class='col-md-2' class='border'>"
															+ "<center>"
															+ "<div class='remove' pid='"+productcompare.productId+"'><a style='color:black;'>&#215</a></div><br>"
															+ "<img src='"+productcompare.image+"' height='60px' width='60px' style='float:left' /><br>"
															+ "<div class='productName'>"
															+ productcompare.productName
															+ "</div>"
															+"</center>"
															+ "</div>");
									
								});
								if(data.count==0)
									{
										$("#products_to_compare").hide();
										$("#emptyComparediv").show();
										$("#compare_button").attr("disabled",true);
									}		
								if(data.count==1)
									{
									$("#compare_button").attr("disabled",true);
									}
						}
					});
		});
			$(".addtocompare").change(function(event) {
				
				if($(".addtocompare").is(':checked'))
					{
				var pId = $(event.target).attr("pid");
				var categoryId = $("#category").val();
				categoryId = categoryId.trim();
				$.ajax({
						type : 'GET',
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						url : 'getProductToCompare?productId='+ pId+ '&category='+ categoryId,
						success : function(data) {
							if(data.messageCount=="hello")
								{
									alert("No more products can be added for comparison!!");
									$('input:checkbox').removeAttr('checked');
									$("#comparecart").show();
									$("#producttocompare").show();
									$("#products_to_compare").show();
									$("#products_to_compare").empty();
									$("#products_to_compare").show();
									$.each(data.products,function(count,productcompare) {
										product_id_to_send = productcompare.productId;
										$("#products_to_compare").append("<div style='height:50px;' class='col-md-2' class='border'>"
																+ "<center>"
																+ "<div class='remove' pid='"+productcompare.productId+"'><a style='color:black;'>&#215</a></div><br>"
																+ "<img src='"+productcompare.image+"' height='60px' width='60px' style='float:left' /><br>"
																+ "<div class='productName'>"
																+ productcompare.productName
																+ "</div>"
																+"</center>"
																+ "</div>");
									});
								}
							else if(data.messageCategoryMismatch=="yes")
							{
								alert("Category Mismatched !!");
								$('input:checkbox').removeAttr('checked');
								$("#comparecart").show();
								$("#producttocompare").show();
								$("#products_to_compare").show();
								$("#products_to_compare").empty();
								$.each(data.products,function(count,productcompare) {
									product_id_to_send = productcompare.productId;
									$("#products_to_compare").append("<div style='height:50px;' class='col-md-2' class='border'>"
															+ "<center>"
															+ "<div class='remove' pid='"+productcompare.productId+"'><a style='color:black;'>&#215</a></div><br>"
															+ "<img src='"+productcompare.image+"' height='60px' width='60px' style='float:left' /><br>"
															+ "<div class='productName'>"
															+ productcompare.productName
															+ "</div>"
															+"</center>"
															+ "</div>");
								});
							}
							else
							{
							$.ajax({
									type : 'GET',
									url : 'getProductsFromCartToCompare',
									success : function(data) {
										
														$("#comparecart").show();
														$("#producttocompare").show();
														$("#products_to_compare").show();
														if (data.count == undefined|| data.count == 0) 
														{
															
															$("#compare_button").attr("disabled",true);
														}
														else 
														{
															if (data.count == 1) 
															{
																$("#emptyComparediv").hide();
																$("#compare_button").attr("disabled",true);
																
															}
															if (data.count != 1) 
															{
																$("#compare_button").attr("disabled",false);
															}
															$("#producttocompare").show();
															$("#products_to_compare").show();
															$("#products_to_compare").empty();
															alert("hi");
															$.each(data.products,function(count,productcompare) {
																	product_id_to_send = productcompare.productId;
																	$("#products_to_compare").append("<div style='height:50px;' class='col-md-2' class='border'>"
																							+ "<center>"
																							+ "<div class='remove' pid='"+productcompare.productId+"'><a style='color:black;'>&#215</a></div><br>"
																							+ "<img src='"+productcompare.image+"' height='60px' width='60px' style='float:left' /><br>"
																							+ "<div class='productName'> <div class='giveMeEllipsis'>"
																							+ productcompare.productName
																							+ "</div></div>"
																							+"</center>"
																							+ "</div>");
																});
														}
											}
										
									});  // 2 ajax
							}	
						}						// success of first ajax
					});			// 1 ajax
										// else
					} 						// if
	
					else
						{
						}
											
											$("#compare_button").click(
													function() {
														$("#comparecart").hide();
													});
										});
						$('input:checkbox').removeAttr('checked');

					});
</script>





</head>
<body>
	<div class="container">
		<div class="col-md-1 "></div>
		<div class="col-md-3 ">
			<div class="background">
				<br>

				<h4><b>BROWSE</b></h4>
				<a href="getSearchresult?categoryname=<s:property value="linktoitem.get(0).parentCategory"/>"><img src ="asset/Images/down.jpg" height="30px"><font color="black"><s:property value="linktoitem.get(0).parentCategory"/></font></a><br>
				<s:iterator value="linktoitem">
					&nbsp;&nbsp;&nbsp;<a href="getSearchresult?categoryname=<s:property value="Category"/>"><img src ="asset/Images/down.jpg" height="30px"><font color="black"><s:property value="Category"/></font></a><br>

				</s:iterator>
				<hr noshade>
				<h5>
					<b>REFINE</b>
				</h5>
				<hr>
				<h5>Price</h5>
				<hr>
				<form>
					<input type="checkbox" class="filtersprice" name="checkboxprice" id="range" price="1"> Rs. 2000 and Below<br>
					<input type="checkbox" class="filtersprice" name="checkboxprice" id="range" price="2"> Rs. 2001 - Rs. 5000<br>
					<input type="checkbox" class="filtersprice" name="checkboxprice" id="range" price="3"> Rs. 5001 - Rs. 10000<br>
					<input type="checkbox" class="filtersprice" name="checkboxprice" id="range" price="4"> Rs. 10001 - Rs. 18000<br>
					<input type="checkbox" class="filtersprice" name="checkboxprice" id="range" price="5"> Rs. 18001 - Rs. 25000<br>
					<input type="checkbox" class="filtersprice" name="checkboxprice" id="range" price="6"> Rs. 25001 - Rs. 35000<br>
					<input type="checkbox" class="filtersprice" name="checkboxprice" id="range" price="7"> Rs. 35001 and Above
				</form> 
				<hr>
				<h5>Company Name</h5>
				<hr>
				<input type="hidden" id="category"
					value="<s:property value="productinfo.get(2).categoryID"/>">
					<input type="hidden" id="categoryid" value="<s:property value="productinfo.get(0).categoryID"/>">
					<s:iterator value="companyList">
						<input type="checkbox" id="brand" class="filters" name="checkbox"
							brandname="<s:property />">
						<s:property />
						<br>
					</s:iterator>
					<br>
					<br>
					<br>
			</div>
		</div>

		<div class="col-md-8 background" id="dataappended">
			<b> Showing all the products in the category </b><br>
			<br>

			<s:iterator value="productinfo">
				<div class="col-md-4">
					<div class="border">
						<br>
						<center>
							<s:if test="%{availableQuantity == 0}">
								<a href="#"> <img src="<s:property value="image"/>"
									alt="<s:property value="productID"/>" height="140px"
									width="auto"><br> <img
									src="asset/Images/outofstock.jpg" alt="outofstock"
									height="40px">
								</a>
							</s:if>
							<s:if test="%{availableQuantity != 0}">
								<s:if test="%{availableQuantity >= minimumQuantity}">
									<s:if test="%{offer==0}">
										<a
											href="getProductDetail?productID=<s:property value="productID"/>">
											<img src="<s:property value="image"/>"
											alt="<s:property value="productID"/>" height="140px"
											width="auto"> <br>
										<br>
										<br>
										</a>
									</s:if>
									<s:if test="%{offer>0}">
										<a
											href="getProductDetail?productID=<s:property value="productID"/>">
											<img src="<s:property value="image"/>"
											alt="<s:property value="productID"/>" height="140px"
											width="auto"><br> <img
											src="asset/Images/offer.jpg" alt="offer" height="40px">

										</a>
									</s:if>
								</s:if>
								<s:if test="%{availableQuantity < minimumQuantity}">
									<a href="#"> <img src="<s:property value="image"/>"
										alt="<s:property value="productID"/>" height="140px"
										width="auto"><br> <img
										src="asset/Images/outofstock.jpg" alt="outofstock"
										height="40px">
									</a>
								</s:if>
							</s:if>
							<div class="giveMeEllipsis">
								<a
									href="getProductDetail?productID=<s:property value="productID"/>">
									<font size="4" color="black"><s:property
											value="productName" /></font><br>
								</a>
							</div>
							<hr>
							<div style="height:50px">
							<s:if test="%{offer==0}">
								<font size="5px" color="#76553B"> Rs. <s:property
										value="price" /><br><br>
								</font>
							</s:if>
							<s:if test="%{offer>0}">
								<font color="#848484" size="4">Rs. <strike><s:property value="price" /></strike> <s:property value="discount"/>% OFF</font><br>
								<font size="5px" color="#76553B"> Rs. ${price-offer} </font>
							</s:if>
							</div>
							<hr>
							This item has manufacturer warranty of
							<s:property value="warranty" />
							years.<br>
							<hr>

							<input type="checkbox" id="productID" class="addtocompare" name="compare" pid="<s:property value="productID"/> " />Add To Compare</input>

							<hr>

							<br>
							<br>
							<br>
						</center>
					</div>
					<br>
				</div>
			</s:iterator>
		</div>
		<div class="col-md-8" id="newdivision"></div>
	</div>

	<div id="comparecart">
	
			
			<a class="close-reveal-modal" id="close_compare">&#215;</a>
			<div id="emptyComparediv" class="empty-comparison">
				There are no items to compare.<br> <br>
			</div>
			<div id="producttocompare" class="empty-comparison">

				<div id="products_to_compare"></div>
			</div>

			<a href="opencompareproductpage"><input type="button" class="compare_button" value="Compare"
				id="compare_button" /></a>
		

	</div>
	
	<br>
</body>
</html>
