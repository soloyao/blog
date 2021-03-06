$("#toLogin").click(function() {
	$.get("/toLogin", function(data, status, xhr) {
		window.location.replace("/login");
	});
});

//客官，来说两句吧
$("#commentBtn").click(function() {
	var commentContent = $("#comment").val();
	commentContent = $.trim(commentContent);
	if (commentContent == "") {
		alert("客官，你还没说两句呢！");
	} else {
		$.ajax({
			type: "post",
			url: "/publishComment",
			dataType: "json",
			data: {
				commentContent: commentContent,
				articleId: articleId
			},
			success: function(data) {
				if (data['status'] == 101) {
					$.get("/toLogin", function(data, status, xhr) {
						window.location.replace("/login");
					});
				} else {
					renderComment(data['data']);
				}
			},
			error: function() {
				alert("发表评论失败！");
			}
		});
	}
});