<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seller Rating</title>
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<link href="asset/CSS/Index.css" rel="stylesheet">
	<link rel="stylesheet" href="asset/CSS/jquery-ui.css">
	<link href="asset/CSS/starter-template.css" rel="stylesheet">
<!-- Bootstrap theme -->
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
	<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
<!--  -->	
	<link rel="stylesheet" href="asset/CSS/reveal.css">
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/jquery-ui.js"></script>
	<script src="asset/JavaScripts/jquery.dataTables.min.js"></script>
<!--For Star Rating  -->
	<script src="asset/JavaScripts/star-rating.min.js" type="text/javascript"></script>
	<script src="asset/JavaScripts/star-rating.js" type="text/javascript"></script>
	<link href="asset/CSS/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="asset/CSS/star-rating.min.css" media="all" rel="stylesheet" type="text/css"/>
	
	
	
	<script>
        $("#input-21").on('rating.change', function (event, value, caption) {
            $('#input-21').attr('disabled', true);
            $('#input-21').rating('refresh');
			alert(value);
        });
        $("#input-21").rating({
            clearButtonBaseClass: 'my-clear',
            clearButtonActiveClass: 'my-clear-active',
        });
    </script>
	

	 
	
	<script type="text/javascript">
	function activaTab(tab){
	    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
	};

	activaTab('aaa');
	
	
	</script>
	
	
</head>
<body>
	<br><br><br>
		<div class="col-md-2"></div>
		<div class="col-md-9">
			<div class="background">
			<br>
			<h2>MY REVIEW AND RATING</h2> <br>
			
			<ul class="nav nav-tabs">
			    <li class="active"><a href="#aaa" data-toggle="tab">Seller Rating</a></li>
			    <!-- <li><a href="#bbb" data-toggle="tab">BBB</a></li>
			    <li><a href="#ccc" data-toggle="tab">CCC</a></li> -->
			</ul>
			<div class="tab-content" id="tabs">
			    <div class="tab-pane active" id="aaa">
			    
			    <div id="ratingForm">
							<form action="insertReview" method="post">		
								 <div><br><br>
								 	<label>Select Seller*</label>	
								 	<select name="sellerID">
							<s:iterator value="sellerId">
								 		<option><s:property/></option>
								 	</s:iterator>
								 	</select>
									<label>Star Rating*</label>
									<select name="rating">
							
								 		<option>1</option>
								 		<option>2</option>
								 		<option>3</option>
								 		<option>4</option>
								 		<option>5</option>
								 	</select>
									
									<!-- <input id="input-21" type="number" class="rating" min="1" max="5" step="1"> -->
								</div>
								<br><br>
								<label>Review*</label>
								<textarea name="review" required="true" rows="5" cols="70" style="border:solid 2px black;"></textarea>
								<br><br>
								<button>SUBMIT</button>
							</form>
				</div>
			    
			    
			    </div>
			    <!-- <div class="tab-pane" id="bbb">...b...</div>
			    <div class="tab-pane" id="ccc">...c..</div> -->
			</div>
			
			 <br><br> <br><br> <br><br> <br><br> <br><br> <br><br><br><br> <br><br><br><br>
		</div>
	</div>
</body>
</html>