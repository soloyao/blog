//渲染文章
function articleRender(data) {
	var categoryTimeline = $(".categoryTimeline");
	categoryTimeline.empty();
	var timeLine = $("<div class='timeline timeline-wrap'></div>");
	timeLine.append($("<div class='timeline-row'>" +
			"<span class='node' style='-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;'>" +
			"<i class='am-icon-folder'></i>" +
			"</span>" +
			"<h1 class='title am-animation-slide-top'># " + data['category'] + "</h1>" +
			"</div>"));
	data['result'].map(function(item, index) {
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
		timeLine.append(timelineRowMajor);
	});
	categoryTimeline.append(timeLine);
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
function articleAjax(currentPage, category1) {
	$.ajax({
		type: "get",
		url: "/getCategoryArticle",
		dataType: "json",
		data: {
			category: category1,
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
					articleAjax(currentPage, category1);
				}
			});
		},
		error: function() {
			alert("获取分类文章失败");
		}
	});
}

//请求并渲染分类
function categoryAjax() {
	$.ajax({
		type: "get",
		url: "/findCategoriesNameAndArticleNum",
		dataType: "json",
		data: null,
		success: function(data) {
			var categories = $(".categories");
			categories.empty();
			categories.append($("<div class='categories-title'>" +
					"<h3 style='font-size:20px;'>Categories</h3>" +
					"</div>"));
			var categoriesComment = $("<div class='categories-comment am-animation-slide-top'></div>");
			data['data']['result'].map(function(item, index) {
				categoriesComment.append($("<div class='category'>" +
						"<span><a class='categoryName'>" + item['categoryName'] + "</a>" +
						"<span class='categoryNum'>(" + item['categoryArticleNum'] + ")</span></span>" +
						"</div>"));
			});
			categories.append(categoriesComment);
			$(".categoryName").click(function() {
				var $this = $(this);
				var categoryName = $this.html();
				articleAjax(1 ,categoryName);
			});
		},
		error: function() {
			alert("获取分类信息失败");
		}
	});
}

//左侧分类请求
categoryAjax();
var category = decodeURI(getUrlParam("category"));

//右侧文章及分页请求
articleAjax(1, category);