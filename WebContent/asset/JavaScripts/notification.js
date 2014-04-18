	$(document).ready(function(){
		 $('[rel=popover]').popover({
			    html:true,
			    placement:'bottom',
			    content:function(){
			        return $($(this).data('contentwrapper')).html();
			    }
			});
	});
	
	
	
	
	
	
	
	$(document).ready(function(){
		
		$("#notification_button").click(function()
				{
			$.ajax({
			    type: 'POST',	    
			    url:'check_notification',
			    success: function(data){
			    	
			    	var status=data.message;
			 
			    	if(status=="available")
			    		{
			    		 $.each(data.orders, function(count, notification)
			    				{
							    		$("#noti").append(" <div class='displayData'>Your Flipkart order# <strong> "+notification.oredrNo+" </strong> has been <strong> "+notification.status+" </strong> </div> <div class='displayDays' > ("+notification.days_ago+" days ago) </div><br>");	
			    	
			    				}); 
			    		                $("#noti").append(" <div> <a href='Notifications' style='color:blue'> See All </a> </div>")
			    		}
			    	else
			    		{
			    			$("#get_notification").html("<div class='no-notifications' >You have no Notifications </div>");
			    		}
			    }
			});	
		});


	});
	