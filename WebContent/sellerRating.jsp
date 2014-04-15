<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	
	
	
	<link href="asset/CSS/bootstrap.css" rel="stylesheet">
<link href="asset/CSS/Index.css" rel="stylesheet">
<link rel="stylesheet" href="asset/CSS/jquery-ui.css">
<link href="asset/CSS/starter-template.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="asset/CSS/bootstrap-theme.min.css" rel="stylesheet">
<link rel="stylesheet" href="asset/CSS/reveal.css">
	
	
	
	<script src="asset/JavaScripts/jquery-2.0.3.js"></script>
	<script src="asset/JavaScripts/bootstrap.min.js"></script>
	<script src="asset/JavaScripts/jquery.dataTables.min.js"></script>
</head>
<body>
	<br><br><br>
		<div class="col-md-2"></div>
		<div class="col-md-9">
			<div class="background">
			<br>
			<h2>Seller Name</h2> <br>
			 Description :
			<hr>
			 <h3>Review and Ratings</h3>
		
			 <div class="col-md-4">
			 <br>
			 <h1>x% Positive</h1>
			 <!-- Calculated percentage positive review -->
			 </div>
			 <div class="col-md-6">
			
			 
			         
      				<label>5 Star</label>
				      <div class="progress">
				        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 90%;"><span class="sr-only">60% Complete</span></div>
				      </div>
				      <label>4 Star</label>
				      <div class="progress">
				        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 40%"><span class="sr-only">40% Complete (success)</span></div>
				      </div>
				      <label>3 Star</label>
				      <div class="progress">
				        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%"><span class="sr-only">20% Complete</span></div>
				      </div>
				      <label>2 Star</label>
				      <div class="progress">
				        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%"><span class="sr-only">60% Complete (warning)</span></div>
				      </div>
				      <label>1 Star</label>
				      <div class="progress">
				        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%"><span class="sr-only">80% Complete (danger)</span></div>
				      </div>

			 </div>
			 <div class="col-md-2"></div>
			 <br><br> <br><br> <br><br> <br><br> <br><br> <br><br><br><br> <br><br><br><br>
			<hr>
			 <table id="example" class="display" width="100%">
					<thead>
						<tr>
							<th>User</th>
							<th>Review</th>
							
						</tr>
					</thead>
			</table>
		 <hr>
		</div>
	</div>
	
	
	
	
	
	<script type="text/javascript">
		$("document").ready(function() {
			$("#example").dataTable();
		});
	</script>
</body>
</html>