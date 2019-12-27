var deleteArticleId = "";

$(".superAdminList .superAdminClick").click(function() {
	var flag = $(this).attr("class").substring(16);
	$("#statistics,#articleManagement,#articleThumbsUp,#articleCategories,#friendLink,#rewardManagement,#userFeedback,#privateWord").css("display", "none");
	$("#" + flag).css("display", "block");
});

//点击文章管理
$(".superAdminList .articleManagement").click(function() {
	getArticleManagement(1);
});

//渲染文章管理文章
function renderArticleManagement(data) {
	var articleManagementTable = $(".articleManagementTable");
	articleManagementTable.empty();
	var table = $("<table class='table am-table am-table-bd am-table-striped admin-content-table am-animation-slide-right'></table>");
	table.append($("<thead>" +
			"<tr><th>文章标题</th><th>发布时间</th><th>文章分类</th><th>浏览量</th><th>操作</th></tr>" +
			"</thead>"));
	var tables = $("<tbody class='tables'></tbody>");
	data['result'].map(function(item) {
		tables.append($("<tr id='a" + item['id'] + "'><td><a href='article/" + item['articleId'] + "'>" + item['articleTitle'] + "</a></td>" +
				"<td>" + item['publishDate'] + "</td><td>" + item['articleCategories'] + "</td><td><span class='am-badge am-badge-success'>" + item['visitorNum'] + "</span></td>" +
				"<td>" +
				"<div class='am-dropdown' data-am-dropdown>" +
				"<button class='articleManagementBtn articleEditor am-btn am-btn-secondary'>编辑</button>" +
				"<button class='articleDeleteBtn articleDelete am-btn am-btn-danger'>删除</button>" +
				"</div>" +
				"</td></tr>"));
	});
	table.append(tables);
	articleManagementTable.append(table);
	articleManagementTable.append($("<div class='my-row' id='page-father'>" +
			"<div id='articleManagementPagination'>" +
			"<ul class='am-pagination am-pagination-centered'></ul>" +
			"</div>" +
			"</div>"));
	$(".articleManagementBtn").click(function() {
		var $this = $(this);
		var id = $this.parent().parent().parent().attr("id").substring(1);
		window.location.replace("/editor?id=" + id);
	});
	$(".articleDeleteBtn").click(function() {
		var $this = $(this);
		deleteArticleId = $this.parent().parent().parent().attr("id").substring(1);
		$("#deleteAlter").modal("open");
	});
}

//删除文章
$(".sureArticleDeleteBtn").click(function() {
	$.ajax({
		type: "get",
		url: "/deleteArticle",
		dataType: "json",
		data: {
			id: deleteArticleId
		},
		success: function(data) {
			if (data['status'] == 101) {
				$.get("/toLogin", function(data, status, xhr) {
					window.location.replace("/login");
				});
			} else if (data['status'] == 201) {
				dangerNotice("删除文章失败");
			} else {
				successNotice("删除文章成功");
				getArticleManagement(1);
			}
		},
		error: function() {
			alert("删除失败");
		}
	});
});

//获取文章管理文章
function getArticleManagement(currentPage) {
	$.ajax({
		type: "post",
		url: "/getArticleManagement",
		dataType: "json",
		data: {
			rows: 10,
			pageNum: currentPage
		},
		success: function(data) {
			if (data['data'] == 101) {
				$.get("/toLogin", function(data, status, xhr) {
					window.location.replace("/login");
				});
			} else {
				renderArticleManagement(data['data']);
				scrollTo(0, 0);
				
				$("#articleManagementPagination").paging({
					rows: data['data']['pageInfo']['pageSize'],
					pageNum: data['data']['pageInfo']['pageNum'],
					pages: data['data']['pageInfo']['pages'],
					total: data['data']['pageInfo']['total'],
					callback: function(currentPage) {
						getArticleManagement(currentPage);
					}
				});
			}
		},
		error: function() {
			alert("获取文章信息失败");
		}
	});
}
