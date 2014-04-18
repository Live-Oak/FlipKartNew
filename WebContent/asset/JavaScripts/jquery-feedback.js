$(document).on('click','#butt',function(){
	$("#slideout_inner").toggleClass("out_inner");
	$("#butt").toggleClass("butt_out");
});



$(document).on('click','#closeFeedback',function(){
	$("#Thankyou").toggleClass("out_inner");
});


$(document).on('click','#feedbackSub',function(){
	
	
		
		var email = $("#emailText").val();
		var mobile = $("#mobileText").val();
		var category = $("#dropbox").val();
		var message = $("#messageText").val();
		if(email!="" && mobile!="" && category!="" && message!="")
			{
		$.ajax({
		    type: 'POST',
		    data : {
		    	email : email,
		    	mobileNumber : mobile,
		    	category : category,
		    	message : message
		    },
		    url:'insertFeedback',
		    success: function(data){
		    	$("#emailText").val("");
				$("#mobileText").val("");
				$("#messageText").val("");
				$("#msg").html("");
		    	$("#slideout_inner").toggleClass("out_inner");
		    	$("#Thankyou").toggleClass("out_inner");
		    	$("#butt").toggleClass("butt_out");
		    }
		    });	
			}
		else
			{
			$("#msg").html("<h3>*Please fill all the required details<h3>");
			}
	});
	

