<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
   
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="asset/CSS/CompareProducts.css" rel="stylesheet">	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Compare Products</title>
<link href="asset/CSS/Index.css" rel="stylesheet">
	<link href="asset/CSS/starter-template.css" rel="stylesheet">
		<link href="asset/CSS/CompareProducts.css" rel="stylesheet">
		<link href="asset/CSS/Index.css" rel="stylesheet">
	
	<!-- Bootstrap core CSS -->
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
	
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	<script src="asset/JavaScripts/jquery-1.9.1.js"></script>
	<script src="asset/JavaScripts/jquery-ui.js"></script>
	<script src="asset/JavaScripts/compareProducts.js"></script>
	
	
<script>
	$(document).ready(function()
	{
	      $("#close").click(function(){
	    	  
	    	  alert("hello");
	    	  $(".show_product").hide();
	    	  $(".show_chose_product").show();
	    	  
	      });
	      
		    
	      $(".dropdown").change(function(event) 
	      	    	{
	      	    	  var nameofproduct;
	      	    	  myparent = $(event.target).parent();
	      	    	  var obj = $(this);
	      	    	  nameofproduct=$(this).val();
	      	    	  
	      	    	    alert(nameofproduct);
	      	    		$.ajax({
	      	    		    type: 'POST',	    
	      	    		    url:'retrieveProduct?productname=' + nameofproduct ,
	      	    		    success: function(data){
	      	    		    				alert(obj.parent());
	      	    		    				$.each(data.productInfoAdded, function(count,productcompare) 
	      	    						    		{ 	
	      	    		    							
	      	    						    			product_id_to_send=productcompare.productId;
	      	    						    			obj.hide();
	      	    						    			obj.parent().html(
	      	    						    					"<img src='"+productcompare.image+"' height='140px' width='160px' /><br><br><br>"+		    												    				
	      	    					    						"<div> <style>'-webkit-line-clamp: 1;'</style> <font size='3' color='black'>"+productcompare.productName+"</font><br></div><hr>"+
	      	    					    						"<strong> <font size='4px' color='#BB0000'>"+productcompare.price+"</strong><hr><font size='2px' color='#BB0000'>"  
	      	    					    						+data.messagestock+"<br><hr><font size='3px' color='#76553B'>"+data.messageoffer+"</font><hr><font size='2px' color='#000000'>"+productcompare.brand+
	      	    					    						"<hr><font size='2px' color='#000000'>"+productcompare.description+"</font><br><br><br><hr><font size='2px' color='#000000'>"+data.messagewarranty+"<hr>");		
	      	    						    			
	      	    						    		});
	      	    		    					

	      	    		   }});	

	      	    	});

	});
	
	
</script>

</head>
<body>
<strong>Compare Products</strong>
	<div class="container">
		<div class="col-md-1 "></div>
		<div class="col-md-2">
					<div class="border">
						<center>
							<br><br><br>
							<p>You can add upto 4 items for comparison</p>	
							<br><br><br><br><br><br>
							<hr>
							<p>Price</p>
							<hr>
							<p>Availability</p>
							<hr>
							<p>Offers</p>
							<hr>
							<p>Brand</p>
							<hr>
							<p>Description</p>
							<br><br><br><br><br><br><br>
							<hr>
							<p>Warranty</p>
							<hr>
							
						</center>						
				 	</div> 
		</div>

<div class="col-md-8 background">
	<%@ page import="edu.iiitb.model.*" %>
	<%! int count1=0;%>
			<%count1=0;%>

	
						
		<s:iterator value="productinfo">
				<div class="col-md-3">
						<div class="border">
						<div class="borderForComparison">
								<br>
								<center>
										<img src="<s:property value="image"/>" alt="<s:property value="productID"/>" height="140px" width="160px" >
										<br><br><br>
																									
								<div class="giveMeEllipsis">
										<font size="3" color="black"><s:property value="productName"/></font><br>
								</div>
												<hr>
													<strong>
														<font size="4px" color="#BB0000">
															Rs. <s:property value="price"/><br>
														</font>
													</strong>	
												<hr>
													<s:if test="%{availableQuantity < minimumQuantity}">
																<font size="2px" color="#BB0000">
																		Out of Stock<br>
																</font>
													</s:if>
													<s:if test="%{availableQuantity > minimumQuantity}">
																<font size="2px" color="#BB0000">
																		In Stock<br>
																</font>
													</s:if>
												<hr>	
													<s:if test="%{offer==0}">
														<font size="3px" color="#76553B">
															No offers Available<br>
														</font>
													</s:if>
													<s:if test="%{offer>0}">
														<font size="3px" color="#76553B">
															Rs. ${offer} off!!
														</font>
													</s:if>
												<hr>	
													
														<font size="2px" color="#000000">
															 <s:property value="brand"/><br>
														</font>
														
												<hr>
													
														<font size="2px" color="#000000">
															 <s:property value="description"/><br>
														</font>
														
												<hr>
											<font size="2px" color="#000000">												
								This item has manufacturer warranty of <s:property value="warranty"/> years.<br>
											</font>
								<hr>
										
								<br><br><br>
							</center>
							</div>
						</div>
						</div>
					
					<%count1=count1+1; %>
			</s:iterator>
										<%
											if(count1==3) {
											%>
											<div class="col-md-3">
												<div class="border">
												<br>
												<div class="show_chose_product">
												<center>
													<strong>Add to compare</strong>
													<br><br><br><br><br><br><br><br><br>
															
															     <select class="dropdown">
															     		<option value="Add Product">Add Product</option>
															     	<s:iterator value="categoryproducts">
																  		<option value="<s:property/>"><s:property/></option>
																  	</s:iterator>
																</select>   
																				
													<br><br><br><br><br><br><br><br><br><br>								
												</center>
												</div>
											</div>
											</div>
											<%}
									
											 else if(count1==2)
											{%>
												<div class="col-md-3">
													<div class="border borderForComparison">
													<br>
													<center>
														<strong>Add to compare</strong>
																 <select class="dropdown">
															     		<option value="Add Product">Add Product</option>
															     	<s:iterator value="categoryproducts">
															     		
																  		<option value="<s:property/>"><s:property/></option>
																  	</s:iterator>
																</select>   
														
													</center>
												</div>
												</div>
												<div class="col-md-3">
													<div class="border borderForComparison">
													<br>
													<center>
														<strong>Add to compare</strong>
																     <select class="dropdown">
																     		<option value="Add Product">Add Product</option>
																     	<s:iterator value="categoryproducts">
																	  		<option value="<s:property/>"><s:property/></option>
																	  	</s:iterator>
																	</select>   
													</center>
												</div>
											</div>	
											<%}%>
										
			</div>
	<div class="col-md-1 "></div>
		
</div>

</body>
</html>



