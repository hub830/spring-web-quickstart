$(function(){
  
   //defaults
   $.fn.editable.defaults.url = '/post'; 


    $('#comments a').editable({
        url: $(this).attr("data-url"),
        showbuttons: true
    }); 
    
   
});