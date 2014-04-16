	$(document).ready(function(){
		 $('[rel=popover]').popover({
			    html:true,
			    placement:'bottom',
			    content:function(){
			        return $($(this).data('contentwrapper')).html();
			    }
			});
	});