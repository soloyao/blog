//只渲染标签
function renderTagsOnly(data) {
	var allTags = $(".allTags");
	allTags.empty();
	allTags.append($("<div class='allTagsTitle'>" +
			"<h2 style='font-size:20px'>Tags</h2>" +
			"</div>"));
	allTags.append($("<div class='allTagsNum'>" +
			"目前共计<span class='num' style='font-size:20px;'>" + data['tagsNum'] + "</span> 个标签" +
			"</div>"));
	var allTagsCloud = $("<div class='allTagsCloud categories-comment am-animation-slide-top'></div>");
	data['result'].map(function(item, index) {
		allTagsCloud.append($("<a href='/tags?tag=" + item['tagName'] + "' style='font-size:" + item['tagSize'] + "px'>" + item['tagName'] + "</a>"));
	});
	allTags.append(allTagsCloud);
}

//渲染文章
function renderTagsArticle(data) {
	var siteInner = $(".site-inner");
	siteInner.empty();
	var timeLine = $("<div class='timeline timeline-wrap'></div>");
	timeLine.append($("<div class='timeline-row'>" +
			"<span class='node' style='-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;'>" +
			"<i class='am-icon-tag'></i>" +
			"</span>" +
			"<h1 class='title am-animation-slide-top'># " + data['tag'] + "</h1>" +
			"</div>"));
	data['result'].map(function(item, index) {
		var timelineRowMajor = $("<div class='timeline-row-major'></div>");
		timelineRowMajor.append($("<span class='node am-animation-slide-up'></span>"));
		var content = $("<div class='content am-comment-main am-animation-slide-top '></div>");
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
		timeLine.append(timelineRowMajor);
	});
	
	siteInner.append(timeLine);
	siteInner.append($("<div class='my-row' id='page-father'>" +
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

//标签页请求
function tagsAjax(currentPage) {
	$.ajax({
		type: "get",
		url: "/getTagArticle",
		dataType: "json",
		data: {
			tag: tag,
			rows: "10",
			pageNum: currentPage
		},
		success: function(data) {
			if (data['status'] == 301) {//只渲染标签
				renderTagsOnly(data['data']);
			} else if (data['status'] == 302) {//外部点击了标签，渲染文章
				renderTagsArticle(data['data']);
				scrollTo(0, 0);
				
				$("#pagination").paging({
					rows: data['data']['pageInfo']['pageSize'],
					pageNum: data['data']['pageInfo']['pageNum'],
					pages: data['data']['pageInfo']['pages'],
					total: data['data']['pageInfo']['total'],
					callback: function(currentPage) {
						tagsAjax(currentPage);
					}
				});
			}
		},
		error: function() {
			alert("获取标签文章失败");
		}
	});
}

var tag = decodeURI(getUrlParam("tag"));

tagsAjax(1);