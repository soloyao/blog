//失败消息盒
function dangerNotice(notice) {
	$(".dangerNotice").html(notice);
	$(".dangerNoticeAlert").html(notice);
	var closeNoticeBox = setTimeout(function() {
		$(".dangerNoticeAlert").css("display", "none");
	}, 3000);
}

//成功消息盒
function successNotice(notice) {
	$(".successNotice").html(notice);
	$(".successNoticeAlert").html(notice);
	var closeNoticeBox = setTimout(function() {
		$(".successNoticeAlert").css("display", "none");
	}, 3000);
}

//设置右侧栏箭头动画显示
var sidebar_toggle = $("#sidebar_toggle");
var sidebar_toggle_line_first = $(".sidebar-toggle-line-first");
var sidebar_toggle_line_middle = $(".sidebar-toggle-line-middle");
var sidebar_toggle_line_last = $(".sidebar-toggle-line-last");
sidebar_toggle.mouseenter(function() {
	sidebar_toggle_line_first.animate({
		width: "50%",
		top: "2px",
		left: "0px"
	}, function() {
		sidebar_toggle_line_first.css({
			"transform": "rotate(-45deg)",
			"transition": "transform 0.5",
			"width": "50%",
			"top": "2px",
			"left": "0px"
		});
	});
	sidebar_toggle_line_middle.animate({
		width: "90%",
		top: "0px",
		left: "0px"
	}, function() {
		sidebar_toggle_line_middle.css({
			"transform": "rotate(0deg)",
			"transition": "transform 0.5",
			"width": "90%",
			"top": "0px",
			"left": "0px"
		});
	});
	sidebar_toggle_line_last.animate({
		width: "50%",
		top: "-2px",
		left: "0px"
	}, function() {
		sidebar_toggle_line_last.css({
			"transform": "rotate(45deg)",
			"transition": "transform 0.5, width 0.5",
			"width": "50%",
			"top": "-2px",
			"left": "0px"
		});
	});
});

sidebar_toggle.mouseleave(function() {
	sidebar_toggle_line_first.animate({
		top: "0px",
		left: "0px"
	}, function() {
		sidebar_toggle_line_first.css({
			"transform": "rotateZ(0deg)",
			"transition": "transform 0.5, width: 0.5",
			"width": "100%",
			"top": "0px",
			"left": "0px"
		});
	});
	sidebar_toggle_line_middle.animate({
		top: "0px",
		left: "0px"
	}, function() {
		sidebar_toggle_line_middle.css({
			"transform": "rotateZ(0deg)",
			"transition": "transform 0.5, width 0.5",
			"width": "100%",
			"top": "0px",
			"left": "0px"
		});
	});
	sidebar_toggle_line_last.animate({
		top: "0px",
		left: "0px"
	}, function() {
		sidebar_toggle_line_last.css({
			"transform": "rotateZ(0deg)",
			"transition": "transform 0.5",
			"width": "100%",
			"top": "0px",
			"left": "0px"
		});
	});
});

//获得访客量，除文章显示界面外其他界面访客量通用
var pageName = window.location.pathname + window.location.search;
$.ajax({
	type: "get",
	url: "/getVisitorNumByPageName",
	dataType: "json",
	data: {
		pageName: pageName.substring(1)
	},
	success: function(data) {
		if (data['status'] == 103) {
			$("#totalVisitors").html(0);
			$("#visitorVolume").html(0);
		} else {
			$("#totalVisitors").html(data['data']['totalVisitor']);
			$("#visitorVolume").html(data['data']['pageVisitor']);
		}
	},
	error: function(data) {
		
	}
});