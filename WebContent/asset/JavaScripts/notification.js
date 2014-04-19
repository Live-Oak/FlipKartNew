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
			    			 		if(count <= 2)
			    			 		{
							    		$("#noti").append(" <div class='displayData'>Your Flipkart order# <strong> "+notification.oredrNo+" </strong> has been <strong> "+notification.status+" </strong> </div> <div class='displayDays' > ("+notification.days_ago+" days ago) </div><br>");	
			    			 		}			
			    				}); 
			    		                $("#noti").append(" <br><div> <a href='Notifications' style='color:blue;'><center> See All </center></a> </div>");
			    		}
			    	else
			    		{
			    			$("#get_notification").html("<div class='no-notifications' >Woops, you have no active notifications!! </div><br><div class='whatrnotif1'>What are Notifications??</div><br><div class='whatrnotif2'>Notifications are timely alerts on your order status!!</div>");
			    		}
			    }
			});	
		});


	});
	