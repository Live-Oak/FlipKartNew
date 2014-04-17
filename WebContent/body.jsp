<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Body</title>
	<!-- CSS for Feedback designing  -->
	<link href="asset/CSS/feedback.css" rel="stylesheet">
	
	<!-- Script to run Feedback jquery and ajax call  -->
	<script src="asset/JavaScripts/jquery-feedback.js"></script>

</head>
<body>
	<!-- Carousel -->
	
 <form action="Start_page" method="post" enctype="multipart/form-data">
	<div class="col-md-2"></div>
	
	<div class="col-md-7">
					<div id="myCarousel" class="carousel slide">
						<!-- Indicators -->
						
						<div class="carousel-inner">
							<s:iterator value="advertizement">
							<s:if test="%{productId==value[2]}">
								<div class="item active">
									<a href="getProductDetail?productID=<s:property value="productId"/>">
										<img src="<s:property value="photo"/>" alt="slide"
											style="height: 350px; width: 100%; display: block;">
									</a>
								</div>
							</s:if>
							<s:if test="%{productId!=value[2]}">
								<div class="item">
									<a href="getProductDetail?productID=<s:property value="productId"/>">
										<img src="<s:property value="photo"/>" alt="slide"
											style="height: 350px; width: 100%; display: block;">
									</a>
								</div>
							</s:if>
							</s:iterator>
						</div>
						
						 	  <s:iterator value="advertizement">
								<s:if test="%{productId==value[0]}">	
										<button type="button" class="btn slide-one" style="height:70px;font-size:12px;color:#ffffff;background-color:#48494B;width:24%;white-space: normal;border-radius: 0;"><s:property value="caption"/></button>
								</s:if>
								
								<s:if test="%{productId==value[1]}">
										<button type="button" class="btn slide-two" style="height:70px;font-size:12px;color:#ffffff;background-color:#48494B;width:25%;white-space: normal;border-radius: 0;"><s:property value="caption"/></button>
								</s:if>
								
								<s:if test="%{productId==value[2]}">
										<button type="button" class="btn slide-three" style="height:70px;font-size:12px;color:#ffffff;background-color:#48494B;width:24%;white-space: normal;border-radius: 0;"><s:property value="caption"/></button>
								</s:if>
								
								<s:if test="%{productId==value[3]}">
										<button type="button" class="btn slide-four" style="height:70px;font-size:12px;color:#ffffff;background-color:#48494B;width:25%;white-space: normal;border-radius: 0;"><s:property value="caption"/></button>
								</s:if>
							 </s:iterator>
 
					</div>
			
		</div>
		
		<!-- Side of the page -->
	<div class="col-md-2">
			<div class="border">
			<center> 
				  <h5> <b> SHOP.</b> </h5>  
				  <h5> <b> ANYTIME.ANYWHERE. </b> </h5> 
				  <h6> with the Flipkart Mobile App </h6> 
				  <a href="apppage"> 
				  <img src="asset/Images/leftsmall.png" alt="anytime anywhere" height="120px" width="200px">
				  </a>
				  <h6> GET THE APP </h6>
			  </center>
			 </div>
			 <br>
			 <s:iterator value="dealoftheday">
			 	<center>
					<div class="border">
						 <h6> <b> DEAL OF THE DAY </b></h6>
						 <h6> <i> <s:property value="caption"/> </i> </h6>
						<a href="getProductDetail?productID=<s:property value="productId"/>">
							<img src="<s:property value="photo"/>" alt="<s:property value="productId"/>" height="130px" width="140px" >
						</a>
					</div>
			 	</center>
			 </s:iterator>
		</div> 
		
		<div class="col-md-1">
	
			<br><br><br><br><br><br><br><br><br><br><br>
			
			<div id="suggestPost"><a href="#" ></a></div>
	</div>	
			
		<!-- Data on the page -->
		<br>
		<br>
		<div class="col-md-2">
		</div>
		
		<div class="col-md-7">
			
		<div class="container">
				<div class="row">
						ELECTRONICS
				</div>
	<!-- ----------------------------------------------------------------- -->
		 <div class="scroll-pane ui-widget ui-widget-header ui-corner-all">
			  <div id="slider" class="scroll-content">
			  <s:iterator value="Categoryelectronics">
			  	<div class="scroll-content-item ui-widget-header border">
			  	<a href="getSearchresult?categoryname=<s:property value="categoryName"/>">
				    	<img src="<s:property value="categoryImage"/>" alt="<s:property value="categoryName"/>" height="80px" width="90px"><br>
				</a>
				<h6> <s:property value="categoryName"/> </h6>
			   </div>
			  </s:iterator>
			   
			  <div class="scroll-bar-wrap ui-widget-content ui-corner-bottom">
			    <div class="scroll-bar"></div>
			  </div>
			</div>
		</div>
			
			<!-- ------------------------------------------------------------------- -->
			
			 <s:iterator value="advertizementelectronics">
			 	<center>
			 	<div class="col-md-4">
			 		<br>
					<div class="border">
						<a href="getProductDetail?productID=<s:property value="productId"/>">
							<img src="<s:property value="photo"/>" alt="<s:property value="productId"/>" height="230px" width=auto >
						</a>
					</div>
			 	</div>
			 	</center>
			 </s:iterator>
				
				<!-- ------------------------------------------------------------------- -->
			</div>
			
			<div class="container">
				<div class="row">
						FASHION
				</div>
				<br>
				
				<s:iterator value="Categoryfashion">
					<div class="col-md-4">
					  	<div class="border">
						  	<center><h6><s:property value="categoryName"/> <span class="caret"></span></h6>
							  	<a href="getSearchresult?categoryname=<s:property value="categoryName"/>">
								    	<img src="<s:property value="categoryImage"/>" alt="<s:property value="categoryName"/>"><br>
								</a>
							</center>
						</div>
					</div>
			    </s:iterator>
			    
			    <!-- ------------------------------------------------------------------- -->
			
			
			 <s:iterator value="advertizementfashion">
			 	<center>
			 	<div class="col-md-4">
			 		<br>
					<div class="border">
						<a href="getProductDetail?productID=<s:property value="productId"/>">
							<img src="<s:property value="photo"/>" alt="<s:property value="productId"/>" height="230px" width=auto >
						</a>
					</div>
			 	</div>
			 	</center>
			 </s:iterator>
				<!-- ------------------------------------------------------------------- -->
			    
			  </div>
			<div class="container">
				<div class="row">
						BOOKS AND MEDIA
				</div>	
				
					<!-- ----------------------------------------------------------------- -->
	
		 <div class="scroll-pane ui-widget ui-widget-header ui-corner-all">
			  <div id="slider" class="scroll-content">
			   <s:iterator value="Categorybooks">
			  	<div class="scroll-content-item ui-widget-header border">
			  	<a href="getSearchresult?categoryname=<s:property value="categoryName"/>">
				    	<img src="<s:property value="categoryImage"/>" alt="<s:property value="categoryName"/>" height="80px" width="90px"><br>
				</a>
				<h6> <s:property value="categoryName"/> </h6>
			   </div>
			  </s:iterator>
			   
			  <div class="scroll-bar-wrap ui-widget-content ui-corner-bottom">
			    <div class="scroll-bar"></div>
			  </div>
			</div>
		</div>
				<!-- ------------------------------------------------------------------- -->
			
			
			<s:iterator value="advertizementbook">
				<center>
			 	<div class="col-md-4">
			 		<br>
					<div class="border">
						<a href="getProductDetail?productID=<s:property value="productId"/>">
							<img src="<s:property value="photo"/>" alt="<s:property value="productId"/>" height="230px" width=auto >
						</a>
					</div>
			 	</div>
			 	</center>
			 </s:iterator>
				<!-- ------------------------------------------------------------------- -->
				
				
			</div>
			
		</div>
		
		<div class="col-md-2">
			<br><br>
			<s:iterator value="advertizementsidebar">
			 	<center>
					<div class="border">
						 <h6> <i> <s:property value="caption"/> </i> </h6>
						<a href="getProductDetail?productID=<s:property value="productId"/>">
							<img src="<s:property value="photo"/>" alt="<s:property value="productId"/>" height="130px" width="170px" >
						</a>
					</div>
			 	</center>
			 	<br><br>
			 </s:iterator>
			<br>
			<br>
			<br>
		</div>
	</form>
	<br><br><br><br>
	
	
	
		<input id="butt" class="btnFeedback" type="image" src="asset/Images/suggest-post.png" alt="Feedback"/>
	<div id = "Thankyou">
  		
		<div class="thankyouStyle">Thank you for your feedback 
			<img height="20px" width="20px" alt="image-logo" src="asset/Images/smiley.jpg">
  		</div>
		<br>
  		<input class="closebuttonStyle" type="button" value="Close" id="closeFeedback"><br><br><br>
  		<img style="margin-left: 300px;" alt="image-logo" src="asset/Images/flipkartLogoFeedback.png">
  		
	</div>
  
	<div  id="slideout_inner">
	
		<h3><label class="label1">Tell us What you Think.</label></h3>
		<p style="font-size: 12px;color: #555555;margin-left: 15px;margin-right: 15px;">Love us / have suggestions / ideas / feature requests?
		Tell us how we can improve our website.</p>
		<hr style="border-style: solid none;border-width: 1px 0;">
		<label class="label2">Email<span style="color:red;"> *</span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<% 
			if(session.getValue("email")==null)
			{ 
		%>
		<input id="emailText" value="" type="email" pattern="[^ @]*@[^ @]*" autocomplete="off" style="border-color:black" name="email" required="true" size="30">
		<%
			}
			else 
			{
		%>
		<input id="emailText" value="<%= session.getValue("email")%>" type="email" pattern="[^ @]*@[^ @]*" autocomplete="off" style="border-color:black" name="email" required="true" size="30">
		<% 
			} 
		%>
	
		<br><br>
		<label class="label2">Mobile Number<span style="color:red;"> *</span></label>&nbsp;&nbsp;&nbsp;
		<input id="mobileText" type="tel" pattern="[0-9]{10,10}" maxlength="10"  autocomplete="off" style="border-color:black" name="mobileNumber" required="true"  size="22">
		<br><br>
		<label class="label2">Category<span style="color:red;"> *</span></label>
		<select class="label2" id="dropbox" name="category">
		<option>Improve this page</option>
		<option>Suggest new features/ideas</option>
		<option>Shopping Experience</option>
		<option>I love Flipkart</option>
		<option>Others - General Feedback</option>
		<option>Flipkart offers zone feedback</option>
		</select>
		<br><br>
		<label class="label2">Message<span style="color:red;"> *</span></label>
		<br>
		<s:textarea id="messageText" cssClass="" cssStyle="margin-left:15px;border-color:black" name="message" 
		required="true" rows="4" cols="52"></s:textarea>
		<br><br>
		<input  class="feedbackSubmit" type="button" value="Submit" id="feedbackSub">
		<img style="margin-left: 145px" alt="image-logo" src="asset/Images/flipkartLogoFeedback.png">
		<hr style="border-style: solid none;border-width: 1px 0;">
		<div id="msg" style="color: red;text-align: center;"></div>
		
	</div>
	
	
		<script>
   $(function(){
      // Cycles the carousel to a particular frame 
      $(".slide-one").hover(function(){
         $("#myCarousel").carousel(0);
      });
      $(".slide-two").hover(function(){
         $("#myCarousel").carousel(1);
      });
      $(".slide-three").hover(function(){
         $("#myCarousel").carousel(2);
      });
      $(".slide-four").hover(function(){
          $("#myCarousel").carousel(3);
       });
   });
</script>
<style>
  .scroll-pane { overflow: auto; width: 99%; float:left; }
  .scroll-content { width: 1050px; float: left; }
  .scroll-content-item { width: 100px; height: 120px; float: left; margin: 2px; font-size: 3em; line-height: 96px; text-align: center; }
  .scroll-bar-wrap { clear: left; padding: 4px 4px 0 2px; margin: 4px -1px -1px -1px; }
  .scroll-bar-wrap .ui-slider { background: none; border:0; height: 2em; margin: 0 auto;  }
  .scroll-bar-wrap .ui-handle-helper-parent { position: relative; width: 100%; height: 100%; margin: 0 auto; }
  .scroll-bar-wrap .ui-slider-handle { top:.2em; height: 1.5em; }
  .scroll-bar-wrap .ui-slider-handle .ui-icon { margin: -8px auto 0; position: relative; top: 50%; }
 </style>
  <script>
  $(function() {
    //scrollpane parts
    var scrollPane = $( ".scroll-pane" ),
      scrollContent = $( ".scroll-content" );
 
    //build slider
    var scrollbar = $( ".scroll-bar" ).slider({
      slide: function( event, ui ) {
        if ( scrollContent.width() > scrollPane.width() ) {
          scrollContent.css( "margin-left", Math.round(
            ui.value / 100 * ( scrollPane.width() - scrollContent.width() )
          ) + "px" );
        } else {
          scrollContent.css( "margin-left", 0 );
        }
      }
    });
 
    //append icon to handle
    var handleHelper = scrollbar.find( ".ui-slider-handle" )
    .mousedown(function() {
      scrollbar.width( handleHelper.width() );
    })
    .mouseup(function() {
      scrollbar.width( "100%" );
    })
    .append( "<span class='ui-icon ui-icon-grip-dotted-vertical'></span>" )
    .wrap( "<div class='ui-handle-helper-parent'></div>" ).parent();
 
    //change overflow to hidden now that slider handles the scrolling
    scrollPane.css( "overflow", "hidden" );
 
    //size scrollbar and handle proportionally to scroll distance
    function sizeScrollbar() {
      var remainder = scrollContent.width() - scrollPane.width();
      var proportion = remainder / scrollContent.width();
      var handleSize = scrollPane.width() - ( proportion * scrollPane.width() );
      scrollbar.find( ".ui-slider-handle" ).css({
        width: handleSize,
        "margin-left": -handleSize / 2
      });
      handleHelper.width( "" ).width( scrollbar.width() - handleSize );
    }
 
    //reset slider value based on scroll content position
    function resetValue() {
      var remainder = scrollPane.width() - scrollContent.width();
      var leftVal = scrollContent.css( "margin-left" ) === "auto" ? 0 :
        parseInt( scrollContent.css( "margin-left" ) );
      var percentage = Math.round( leftVal / remainder * 100 );
      scrollbar.slider( "value", percentage );
    }
 
    //if the slider is 100% and window gets larger, reveal content
    function reflowContent() {
        var showing = scrollContent.width() + parseInt( scrollContent.css( "margin-left" ), 10 );
        var gap = scrollPane.width() - showing;
        if ( gap > 0 ) {
          scrollContent.css( "margin-left", parseInt( scrollContent.css( "margin-left" ), 10 ) + gap );
        }
    }
 
    //change handle position on window resize
    $( window ).resize(function() {
      resetValue();
      sizeScrollbar();
      reflowContent();
    });
    //init scrollbar size
    setTimeout( sizeScrollbar, 10 );//safari wants a timeout
  });
  </script>
  
</body>
</html>
	
	
</body>
</html>