//网站最后更新时间（版本更新需更改）
var siteLastUpdateTime = "2019年12月02日19点";
//网站开始时间
var siteBeginRunningTime = "2019-12-20 20:00:00";
//广告上下滚动
function getStyle(obj, name) {
	if (obj.currentStyle) {
		return obj.currentStyle[name];
	} else {
		return getComputedStyle(obj, false)[name];
	}
}
function startMove(obj, json, doEnd) {
	clearInterval(obj.timer);
	obj.timer = setInterval(function() {
		var oStop = true;
		for (var attr in json) {
			var cur = 0;
			if (attr == "opacity") {
				cur = Math.round(parseFloat(getStyle(obj, attr)) * 100);
			} else {
				cur = parseInt(parseInt(getStyle(obj, attr)));
			}
			var speed = (json[attr] - cur) / 6;
			speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
			if (cur != json[attr]) {
				oStop = false;
			}
			if (attr == "opacity") {
				obj.style.filter = 'alpha(opacity:' + (speed + cur) + ")";
				obj.style.opacity = (speed + cur) / 100;
			} else {
				obj.style[attr] = speed + cur + "px";
			}
		}
		if (oStop) {
			clearInterval(obj.timer);
			if (doEnd) doEnd();
		}
	}, 30);
}
window.onload = function() {
	var oDiv = document.getElementsByClassName("roll")[0];
	var oUl = oDiv.getElementsByTagName("ul")[0];
	var aLi = oUl.getElementsByTagName("li");
	var now = 0;
	for (var i = 0; i < aLi.length; i++) {
		aLi[i].index = i;
	}
	function next() {
		now++;
		if (now == aLi.length) {
			now = 0;
		}
		startMove(oUl, {top: -26 * now});
	}
	//设置广播滚动时间
	var timer = setInterval(next, 3000);
	oDiv.onmouseover = function() {
		clearInterval(timer);
	};
	oDiv.onmouseout = function() {
		timer = setInterval(next, 3000);
	};
}

//填充文章
function renderArticle(data) {
	$(".articles").empty();
	var articles = $(".articles");
	data.map(function(item, index) {
		if (index != data.length - 1) {
			var center = $("<div class='center'>" +
					"<header class='article-header'>" +
					"<h1 iteprop='name'><a class='article-title' href='" + item['thisArticleUrl'] + "' target='_blank'>" + item['articleTitle'] + "</a></h1>" +
					"<div class='article-meta row'>" +
					"<span class='articleType am-badge am-badge-success'>" + item['articleType'] + "</span>" +
					"<div class='articlePublishDate'><i class='am-icon-calendar'><a class='linkColor' href='/archives?archive=" + item['publishDate'] + "'> " + item['publishDate'] + "</a></i></div>" +
					"<div class='originalAuthor'><i class='am-icon-user'> " + item['originalAuthor'] + "</i></div>" +
					"<div class='categories'>" +
					"<i class='am-icon-folder'><a class='linkColor' href='/categories?category=" + item['articleCategories'] + "'> " + item['articleCategories'] + "</a></i>" +
					"</div>" +
					"</div>" +
					"</header>" +
					"<div class='article-entry'>" + item['articleTabloid'] + "</div>" +
					"<div class='read-all'><a href='" + item['thisArticleUrl'] + "' target='_blank'>阅读全文 <i class='am-icon-angle-double-right'></i></a></div>" +
					"<hr>" +
					"<div class='article-tags'></div>" +
					"</div>");
			articles.append(center);
			var articleTags = $(".article-tags");
			for (var i = 0; i < item['articleTags'].length; i++) {
				var articleTag = $("<i class='am-icon-tag'><a class='tag' href='/tags?tag=" + item['articleTags'][i] + "'> " + item['articleTags'][i] + "</a></i>");
				articleTags.eq(index).append(articleTag);
			}
		}
	});
}

//填充最新评论
function renderNewComment(data) {
	var newComment = $(".new-comment");
	newComment.empty();
	var listNews = $("<div data-am-widget='list_news' class='am-list-news am-list-news-default'></div>");
	var newCommentTitle = $("<div class='am-list-news-hd am-cf'>" +
			"<a class='newComments'><h2 style='color:#10101;'>最新评论</h2></a>" +
			"</div>");
	listNews.append(newCommentTitle);
	var amListNewsBd = $("<div class='am-list-news-bd'></div>");
	var ul = $("<ul class='fiveNewComments am-list'></ul>");
	data['result'].map(function(item, index) {
		var li = $("<li class='am-g am-list-item-dated'>" +
				"<a class='newCommentTitle' target='_blank' href='/article/" + item['articleId'] + "' class='am-list-item-hd' style='padding-bottom:5px;" +
						" title='" + item['articleTitle'] + "''>" + item['articleTitle'] + "</a>" +
				"<span class='am-list-date'>" + item['commentDate'] + "</span>" +
				"<div class='new-comment-content' style='margin-bottom:5px;'>" + item['answerer'] + "：" + item['commentContent'] + "</div>" +
				"</li>");
		ul.append(li);
	});
	amListNewsBd.append(ul);
	listNews.append(amListNewsBd);
	newComment.append(listNews);
	newComment.append($("<div class='my-row' id='page-father'><div class='newCommentPagination'></div></div>"));
}

//填充最新留言
function renderNewLeaveWord(data) {
	var newLeaveWord = $(".new-leaveWord");
	newLeaveWord.empty();
	var listNews = $("<div data-am-widget='list_news' class='am-list-news am-list-news-default'></div>");
	var newLeaveWordTitle = $("<div class='am-list-news-hd am-cf'>" +
			"<a class='newLeaveWord'><h2 style='color:#110101;'>最新留言</h2></a>" +
			"</div>");
	listNews.append(newLeaveWordTitle);
	var amListNewsBd = $("<div class='am-list-news-bd'></div>");
	var ul = $("<ul class='fiveNewComments am-list'></ul>");
	data['result'].map(function(item, index) {
		var li = $("<li class='am-g am-list-item-dated'>" +
				"<a class='newLeaveWordTitle' href='/" + item['pagePath'] + "' title='" + item['leaveWordContent'] + "'>" + item['answerer'] + "：" + item['leaveWordContent'] + "</a>\n" +
				"<span class='am-list-date'>" + item['leaveWordDate'] + "</span>" +
				"</li>");
		ul.append(li);
	});
	amListNewsBd.append(ul);
	listNews.append(amListNewsBd);
	newLeaveWord.append(listNews);
	newLeaveWord.append($("<div class='my-row' id='page-father'><div class='newLeaveWordPagination'></div></div>"));
}

//添加标签云
function renderTagsCloud(data) {
	var tagCloud = $(".tag-cloud");
	tagCloud.empty();
	tagCloud.append($("<h3 class='widget-title'>标签云</h3>"));
	var widgetTagCloud = $("<div class='widget-tag-cloud'></div>");
	data['result'].map(function(item, index) {
		widgetTagCloud.append($("<a href='tags?tag=" + item['tagName'] + "' style='font-size:" + item['tagSize'] + "px'>" + item['tagName'] + "</a>"));
	});
	tagCloud.append(widgetTagCloud);
}

//首页文章分页请求
function ajaxFirst(currentPage) {
	//加载时请求
	$.ajax({
		type: "post",
		url: "/myArticles",
		dataType: "json",
		data: {
			rows: "10",
			pageNum: currentPage
		},
		success: function(data) {
			if (data['status'] == 103) {
				dangerNotice("服务器异常，获取文章信息失败");
			} else {
				renderArticle(data['data']);
				scrollTo(0, 0);
				
				var length = data['data'].length;
				$("#pagination").paging({
					rows: data['data'][length - 1]['pageSize'],
					pageNum: data['data'][length - 1]['pageNum'],
					pages: data['data'][length - 1]['pages'],
					total: data['data'][length - 1]['total'],
					callback: function(currentPage) {
						ajaxFirst(currentPage);
					}
				});
			}
		},
		error: function() {
		}
	});
}

//首页最新评论分页请求
function newCommentAjax(currentPage) {
	$.ajax({
		type: "get",
		url: "/newComment",
		dataType: "json",
		data: {
			rows: "5",
			pageNum: currentPage
		},
		success: function(data) {
			renderNewComment(data['data']);
			$(".newCommentPagination").paging({
				rows: data['data']['pageInfo']['pageSize'],
				pageNum: data['data']['pageInfo']['pageNum'],
				pages: data['data']['pageInfo']['pages'],
				total: data['data']['pageInfo']['total'],
				flag: 0,
				callback: function(currentPage) {
					newCommentAjax(currentPage);
				}
			});
		},
		error: function() {
		}
	});
}

//首页最新留言分页请求
function newLeaveWordAjax(currentPage) {
	$.ajax({
		type: "get",
		url: "/newLeaveWord",
		dataType: "json",
		data: {
			rows: "5",
			pageNum: currentPage,
		},
		success: function(data) {
			renderNewLeaveWord(data['data']);
			$(".newLeaveWordPagination").paging({
				rows: data['data']['pageInfo']['pageSize'],
				pageNum: data['data']['pageInfo']['pageNum'],
				pages: data['data']['pageInfo']['pages'],
				total: data['data']['pageInfo']['total'],
				flag: 0,
				callback: function(currentPage) {
					newLeaveWordAjax(currentPage);
				}
			});
		},
		error: function() {
		}
	});
}

//标签云请求
function tagsCloudAjax() {
	$.ajax({
		type: "get",
		url: "/findTagsCloud",
		dataType: "json",
		data: null,
		success: function(data) {
			if (data['data']['result'].length == 0) {
				var tagCloud = $(".tag-cloud");
				tagCloud.empty();
				tagCloud.append($("<h3 class='widget-title'>标签云</h3>"));
				var widgetTagCloud = $("<div class='widget-tag-cloud'><span>暂无标签</span></div>");
				tagCloud.append(widgetTagCloud);
				$("#right").append(tagCloud);
			} else {
				renderTagsCloud(data['data']);
			}
		},
		error: function() {
		}
	});
}

//网站信息请求
function siteInfoAjax() {
	$.ajax({
		type: "get",
		url: "/getSiteInfo",
		dataType: "json",
		data: null,
		success: function(data) {
			var siteInfo = $(".site-info");
			siteInfo.empty();
			siteInfo.append("<h5 class='site-title'><i class='am-icon-info site-icon'></i><strong style='margin-left:15px;'>网站信息</strong></h5>");
			var siteDefault = $("<ul class='site-default'></ul>");
			siteDefault.append("<li><i class='am-icon-file site-default-icon'></i><span class='site-default-word'>文章总数</span>：" + data['data']['articleNum'] + "篇</li>");
			siteDefault.append("<li><i class='am-icon-tags site-default-icon'></i><span class='site-default-word'>标签总数</span>：" + data['data']['tagsNum'] + "个</li>");
			siteDefault.append("<li><i class='am-icon-comments-o site-default-icon'></i><span class='site-default-word'>留言总数</span>：" + data['data']['leaveWordNum'] + "条</li>");
			siteDefault.append("<li><i class='am-icon-commenting-o site-default-icon'></i><span class='site-default-word'>评论总数</span>：" + data['data']['commentNum'] + "条</li>");
			siteDefault.append("<li><i class='am-icon-pencil-square site-default-icon'></i><span class='site-default-word'>网站最后更新</span>：<span class='siteUpdateTime'>" + siteLastUpdateTime + "</span></li>");
			siteDefault.append("<li><i class='am-icon-calendar site-default-icon'></i><span class='site-default-word'>网站运行天数</span>：<span class='siteRunningTime'></span></li>");
			siteInfo.append(siteDefault);
		},
		error: function() {
		}
	});
}

//网站运行时间
//beginTime为建站时间的时间戳
function siteRunningTime(time) {
	var theTime;
	var strTime = "";
	if (time >= 86400) {
		theTime = parseInt(time / 86400);
		strTime += theTime + "天";
		time -= theTime * 86400;
	}
	if (time >= 3600) {
		theTime = parseInt(time / 3600);
		strTime += theTime + "时";
		time -= theTime * 3600;
	}
	if (time >= 60) {
		theTime = parseInt(time / 60);
		strTime += theTime + "分";
		time -= theTime * 60;
	}
	strTime += time + "秒";
	$(".siteRunningTime").html(strTime);
}

var nowDate = new Date().getTime();
//网站开始运行日期
var oldDate = new Date(siteBeginRunningTime.replace(/-/g, '/'));
var time = oldDate.getTime();
var theTime = parseInt((nowDate - time) / 1000);
setInterval(function() {
	siteRunningTime(theTime);
	theTime++;
}, 1000);

//文章请求
ajaxFirst(1);
//最新评论请求
newCommentAjax(1);
//最新留言请求
newLeaveWordAjax(1);
//标签云请求
tagsCloudAjax();
//网站信息请求
siteInfoAjax();

