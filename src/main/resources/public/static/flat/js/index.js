$(function() {
	$(".del").click(function() {
		var userid=$(this).attr("userid");
		$.ajax({
			url : "/test/del",
			type: "post",
			data: "userid="+userid,
			success : function(data) {
				location.reload()
				
			}
		});
	});

})