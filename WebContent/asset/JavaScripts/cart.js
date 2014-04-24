$(document).ready(function(){
	$("#buyNow , .buyNow").click(function(e){
		var currentTarget = e.currentTarget;
		var pId = $(currentTarget).parent().find("#productId").attr("pid");
		var qty = 1;
		$.ajax({
		    type: 'POST',
		    contentType: "application/x-www-form-urlencoded; charset=utf-8",
		    data : {
		    	productId : pId,
		    	quantity : qty
		    },
		    url:'addProductToCart',
		    success: function(data){
		    	$("#cartButton").click();
		     }});	
	});
	
	
	$("#cartButton").click(function(){
		
		$("#emptyCart").hide();
    	
    	$.ajax({
		    type: 'GET',
		    url:'getProductsFromCart',
		    success: function(data){
		    	showCart(data);
		     }});	
    	
	});
	
	
	$(document).on('click','.remove',function(e){
		var target = e.currentTarget;
		var pId = $(target).attr("pid");
		
		$.ajax({
		    type: 'POST',
		    contentType: "application/x-www-form-urlencoded; charset=utf-8",
		    data : {
		    	productId : pId,	
		    },
		    url:'removeProductFromCart',
		    success: function(data){
		    	showCart(data);
		     }});	
	});
	
	
	$(document).on('click','.save',function(e){
		var target = e.currentTarget;
		var pId = $(target).attr("pid");
		var qty = $(target).parent().find(".quantityInput").val();
		
		$.ajax({
		    type: 'POST',
		    contentType: "application/x-www-form-urlencoded; charset=utf-8",
		    data : {
		    	productId : pId,
		    	quantity : qty
		    },
		    url:'updateProductQuantity',
		    success: function(data){
		    	showCart(data);
		    	$("#notification").html(data.message).show().delay(2000).hide(1000);
		     }});	
	});

});
	
	
function showCart(data)
{
	if(data.count == 0 || data.count == undefined)
	{
		$("#emptyCart").show();
		$("#continueShoppingBottom").hide();
		$("#placeOrder").hide();
		$("#totalCost").hide();
		$("#list").empty();
		$("#productInfo").hide();
		$("#productCount").html("CART (0)");
		$("#cartHeader").html("CART (0)");
	}
else
	{
		$("#productInfo").show();
		$("#list").empty();
		$("#productCount").html("CART ("+data.count+")");
		$("#cartHeader").html("CART ("+data.count+")");
		var totalcost = 0;
		$.each(data.products, function(count,product) { 
			subtotal = 0;
			subtotal = Number(product.quantity) * Number(product.price);
			totalcost += subtotal;
			$("#list").append("<div style='height:100px;border : 1px solid gray;padding:10px'>"+
					"<img src='"+product.image+"' height='80px' width='80px' style='float:left' />"+
					"<div class='productName'>"+product.productName+"</div>"+
					"<div class='qty'><input type='text' class='quantityInput' value='"+product.quantity+"'></input><a class='save' pid='"+product.productId+"'>save</a></div>"+
					"<div class='price'>"+product.price+"</div>"+
					"<div class='subtotal'>"+subtotal+"</div>"+
					"<div class='remove' pid='"+product.productId+"'><a style='color:black;'>remove</a></div>"+
			"</div>");
		});
		$("#totalCost").html("Total Cost : "+totalcost);
		$(".quantityInput").keyup(function(e){
			
			var value = $(".quantityInput").val();
			if(Number(value) && Number(value) > 0)
			{
				$(this).parent().find(".save").show();
			}
			else
			{
				$(this).parent().find(".save").hide();
			}
			
		});
	}
}

function IsNumeric(e) {
	var keyCode = e.which ? e.which : e.keyCode
	var ret = ((keyCode >= 48 && keyCode <= 57) || specialKeys
			.indexOf(keyCode) != -1);
	document.getElementById("error").style.display = ret ? "none"
			: "inline";
	return ret;
}