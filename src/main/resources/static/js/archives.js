//渲染文章
function articleRender(data) {
	var categoryTimeline = $(".archiveTimeline");
	categoryTimeline.empty();
	var timeline = $("<div class='timeline timeline-wrap'></div>");
	timeline.append($("<div class='timeline-row'>" +
			"<span class='node' style='-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;'>" +
			"<i class='am-icon-folder'></i>" +
			"</span>" +
			"<h1 class='title am-animation-slide-top'># 很好！&nbsp;目前总计<span class='archivesNum'>" + data['articleNum'] + "</span>篇日志，继续努力。</h1>" +
			"</div>"));
	var strArray = new Array();
	data['result'].map(function(item, index) {
		var year = item["publishDate"].substring(0, 4);
		var month = item["publishDate"].substring(5, 7);
		if (data["showMonth"] == "hide") {
			if ($.inArray(year, strArray) == -1) {
				strArray.push(year);
				timeline.append($("<div class='timeline-row-major'>" +
						"<span class='node am-animation-slide-top'></span>" +
						"<div class='nodeYear am-animation-slide-top'>" + year + "年</div>" +
						"</div>"));
			}
		} else {
			if ($.inArray(year, strArray) == -1) {
				strArray.push(year);
				timeline.append($("<div class='timeline-row-major'>" +
						"<span class='node am-animation-slide-top'></span>" +
						"<span class='nodeYear am-animtaion-slide-top'>" + year + "年&nbsp;" + month + "月</span>" +
						"</div>"));
			}
		}
		var timelineRowMajor = $("<div class='timeline-row-major'></div>");
		timelineRowMajor.append($("<span class='node am-animation-slide-up'></span>"));
		var content = $("<div class='content am-comment-main am-animation-slide-top'></div>");
		content.append($("<header class='am-comment-hd' style='background:#fff;'>" +
				"<div class='contentTitle am-comment-meta'>" +
				"<a href='/article/" + item['articleId'] + "'>" + item['articleTitle'] + "</a>" +
				"</div>" +
				"</header>"));
		var amCommentBd = $("<div class='am-comment-bd'></div>");
		amCommentBd.append($("<i class='am-icon-calendar'> <a href='/archives?archive=" + item['publishDate'] + "'>" + item['publishDate'] + "</a></i>" +
				"<i class='am-icon-folder'> <a href='/categories?category=" + item['articleCategories'] + "'>" + item['articleCategories'] + "</a></i>"));
		var amCommentBdTags = $("<i class='am-comment-bd-tags am-icon-tag'></i>");
		for (var i = 0; i < item['articleTags'].length; i++) {
			var tag = $("<a href='/tags?tag=" + item['articleTags'][i] + "'>" + item['articleTags'][i] + "</a>");
			amCommentBdTags.append(tag);
			if (i != (item['articleTags'].length - 1)) {
				amCommentBdTags.append(",");
			}
		}
		amCommentBd.append(amCommentBdTags);
		content.append(amCommentBd);
		timelineRowMajor.append(content);
		timeline.append(timelineRowMajor);
	});
	categoryTimeline.append(timeline);
	categoryTimeline.append($("<div class='my-row' id='page-father'>" +
			"<div id='pagination'>" +
			"<ul class='am-pagination am-pagination-centered'>" +
			"<li class='am-disabled'><a href='#'>&laquo; 上一页</a></li>" +
			"<li class='am-active'><a href='#'>1</a></li>" +
			"<li><a href='#'>2</a></li>" +
			"<li><a href='#'>3</a></li>" +
			"<li><a href='#'>4</a></li>" +
			"<li><a href='#'>5</a></li>" +
			"<li><a href='#'>下一页&raquo;</a></li>" +
			"</ul>" +
			"</div>" +
			"</div>"));
}

//文章请求
function articleAjax(currentPage, archive1) {
	$.ajax({
		type: "get",
		url: "/getArchiveArticle",
		dataType: "json",
		data: {
			archive: archive1,
			rows: "10",
			pageNum: currentPage
		},
		success: function(data) {
			articleRender(data['data']);
			scrollTo(0, 0);
			
			$("#pagination").paging({
				rows: data['data']['pageInfo']['pageSize'],
				pageNum: data['data']['pageInfo']['pageNum'],
				pages: data['data']['pageInfo']['pages'],
				total: data['data']['pageInfo']['total'],
				callback: function(currentPage) {
					articleAjax(currentPage, archive1);
				}
			});
		},
		error: function() {
			alert("获取归档文章失败");
		}
	});
}

//请求并渲染归档
function archivesAjax() {
	$.ajax({
		type: "get",
		url: "/findArchiveNameAndArticleNum",
		dataType: "json",
		data: null,
		success: function(data) {
			var archives = $(".archives");
			archives.empty();
			archives.append($("<div class='archives-title'>" +
					"<h3 style='font-size:20px;'>Archives</h3>" +
					"</div>"));
			var archivesComment = $("<div class='archives-comment am-animation-slide-top'></div>");
			data['data']['result'].map(function(item, index) {
				archivesComment.append($("<div class='archive'>" +
						"<span><a class='archiveName'>" + item['archiveName'] + "</a></span>" +
						"<span class='archiveNum'>(" + item['archiveArticleNum'] + ")</span>" +
						"</div>"));
			});
			archives.append(archivesComment);
			$(".archiveName").click(function() {
				var $this = $(this);
				var archiveName = $this.html();
				articleAjax(1, archiveName);
			});
		},
		error: function() {
			alert("获取归档信息失败");
		}
	});
}

//左侧归档请求
archivesAjax();
var archive = decodeURI(getUrlParam("archive"));

//右侧文章及分页请求
articleAjax(1, archive);
