var flag = 1;
var aCategory = "";

$("#originalAuthorHide").hide();
$(".articleUrlHide").hide();

var fnClose = function(e) {
	e.returnValue = "确定离开当前页面吗？";
};
window.addEventListener("beforeunload", fnClose);

var testEditor;
$(function() {
	testEditor = editormd("my-editormd", {
		width: "100%",
		height: 740,
		syncScrolling: true,
		path: "/static/lib/",
		previewTheme: "dark",
		codeFold: true,
		emoji: true,
		tocm: true,
		text: true,
		flowChart: true,
		sequenceDiagram: true,
		htmlDecode: true,
		imageUpload: true,
		imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp","JPG","JPEG","GIF","PNG","BMP","WEBP"],
		imageUploadURL: "/uploadImage",
		onload: function() {
			
		},
		saveHTMLToTextarea: true,
		toolbarIcons: function() {
			return ["bold","del","italic","quote","|","h1","h2","h3","h4","h5","h6","|","list-ul","list-ol","hr","|","link","image","code",
				"code-block","table","datetime","html-entities","emoji","|","watch","preview","fullscreen","clear","search","|","help","info"]
		}
	});
});

var publishBtn = $(".publishBtn");
var articleTitle = $("#zmy-editor-title");
var articleContent = $("#my-editormd-html-code");
var noticeBoxTitle = $(".notice-box-title");
var noticeBoxContent = $(".notice-box-content");
var noticeBox = $(".notice-box");

publishBtn.click(function() {
	var articleTitleValues = articleTitle.val();
	var articleContentValues = articleContent.val();
	if (articleTitleValues.length == 0) {
		noticeBoxTitle.show();
	} else if (articleContentValues.length == 0) {
		noticeBoxContent.show();
	} else {
		$("#my-alert").modal();
		$.ajax({
			type: "get",
			url: "/findCategoriesName",
			async: false,
			data: null,
			dataType: "json",
			success: function(data) {
				var selectCategories = $("#select-categories");
				selectCategories.empty();
				selectCategories.append($("<option class='cateogriesOption' value='choose'>请选择</option>"));
				for (var i = 0; i < data['data'].length; i++) {
					selectCategories.append($("<option class='categoriesOption' value='" + data['data'][i] + "'>" + data['data'][i] + "</option>"));
				}
				if (aCategory !== "" && aCategory.length > 0) {
					selectCategories.val(aCategory);
				}
			},
			error: function() {
			}
		});
	}
	//定时关闭错误提示框
	var closeNoticeBox = setTimeout(() => {
		noticeBox.hide();
	}, 3000);
});

function publishSuccessRender(data) {
	$("#removeDiv").html("");
	var sec = $("<div id='all'></div>");
	var success = $("<div class='success'></div>");
	var successBox = $("<div class='success-box'></div>");
	var successArticleTitle = $("<div class='successArticleTitle'><span>" + data['articleTitle'] + "</span></div>");
	var successWord = $("<div class='success-word'><p><i class='am-success am-icon-check-square-o' style='color:#5eb95e;'></i> 发布成功</p></div>");
	var successTimeAndUser = $("<div class='success-time-user'>" +
			"<p><i class='am-icon-calendar'></i>&nbsp;" + data['updateDate'] + "&nbsp;&nbsp;&nbsp;&nbsp;<i class='am-icon-user'></i>&nbsp;" + data['author'] + "</p>" +
			"</div>");
	var successBtn = $("<div class='successBtn'>" +
			"<a href='/editor' class='reWriteBtn am-btn am-btn-danger am-round'>写新文章</a>" +
			"<a href='" + data['articleUrl'] + "' class='lookArticleBtn am-btn am-btn-danger am-round'>查看文章</a>" +
			"</div>");
	successBox.append(successArticleTitle);
	successBox.append(successWord);
	successBox.append(successTimeAndUser);
	successBox.append(successBtn);
	success.append(successBox);
	sec.append(success);
	$("#removeDiv").append(sex);
}

//验证是否有权限写博客
$.ajax({
	type: "get",
	url: "/canYouWrite",
	data: null,
	dataType: "json",
	success: function(data) {
		if (data['status'] != 0) {
			var noticeBoxWrite = $("<div class='notice-box-write'>" +
					"<div class='am-alert am-alert-danger'>" +
					"<p>在线写博客功能暂不对外开放，您所写的文章都将发布无效<button class='canYouWrite am-close'>&times;</button></p>" +
					"</div></div>");
			$("#app").append(noticeBoxWrite);
		}
		$(".canYouWrite").click(function() {
			$(".notice-box-write").hide();
		});
	},
	error: function() {
	}
});

//获得草稿文章
$.ajax({
	type: "get",
	url: "/getDraftArticle",
	async: false,
	data: null,
	dataType: "json",
	success: function(data) {
		if (data['status'] == 0) {
			$("#zmy-editor-title").val(data['data']['articleTitle']);
			$("#my-editormd-markdown-doc").html(data['data']['articleContent']);
			$("#select-type").val(data['data']['articleType']);
			$("#select-grade").val(data['data']['articleGrade']);
			$("#originalAuthor").val(data['data']['originalAuthor']);
			$("#articleUrl").val(data['data']['articleUrl']);
			if (data['data']['articleType'] == "转载") {
				$("#originalAuthorHide").show();
				$(".articleUrlHide").show();
			}
			aCategory = data['data']['articleCategories'];
			var tags = data['data']['articleTags'];
			var tag = $(".tag");
			for (var i in tags) {
				tag.append($("<div style='display:inline-block;'><p class='tag-name' contenteditable='true'>" + tags[i] + "</p>" +
						"<i class='am-icon-times removeTag' style='color:#ccc;'></i></div>"));
			}
			var articleId = data['data']['id'];
			if (articleId != 0) {
				$(".surePublishBtn").attr("id", articleId);
			}
		}
	},
	error: function() {
	}
});

//添加标签
var addTagsBtn = $(".addTagsBtn");
$(function() {
	var i = 0;
	var $wrapper = $(".tag");
	var appendPanel = function(index) {
		var panel = $("<div style='display:inline-block;'><p class='tag-name' contenteditable='true'></p>" +
				"<i class='am-icon-times removeTag' style='color:#ccc;'></i></div>");
		$wrapper.append(panel);
		$(".tag-name").click(function() {
			$(this).focus();
		});
	}
	addTagsBtn.on("click", function() {
		if (i >= 4) {
			addTagsBtn.attr("disabled", "disabled");
		}
		var value = $(".tag-name").eq(i - 1).html();
		if (value != "") {
			appendPanel(i);
			i++;
		}
	});
	
	$(".tag").on("click", ".removeTag", function() {
		$(this).parent().remove();
		i--;
		if (i <= 4) {
			addTagsBtn.removeAttr("disabled");
		}
	});
});

//显示文章作者
var articleType = $("#select-type");
$("#select-type").blur(function() {
	if (articleType.val() == "转载") {
		$("#originalAuthorHide").show();
		$(".articleUrlHide").show();
	} else if (articleType.val() == "原创") {
		$("#originalAuthorHide").hide();
		$(".articleUrlHide").hide();
	}
});

//发表博客成功后显示的内容
function publishSuccessPutIn(data) {
	$("#removeDiv").html("");
	var sec = $("<div id='all'></div>");
	var success = $("<div class='success'></div>");
	var successBox = $("<div class='success-box'></div>");
	var successArticleTitle = $("<div class='successArticleTitle'><span>" + data["articleTitle"] + "</span></div>");
	var successWord = $("<div class='success-word'><p><i class='am-success am-icon-square-o' style='color:#5eb95e;'></i> 发布成功</p></div>");
	var successTimeAndUser = $("<div class='success-time-user'>" +
			"<p><i class='am-icon-calendar'></i>&nbsp;" + data["updateDate"] + "&nbsp;&nbsp;&nbsp;&nbsp;<i class='am-icon-user'></i>&nbsp;" + data["author"] + "</p>" +
			"</div>");
	var successBtn = $("<div class='successBtn'>" +
			"<a href='/editor' class='reWriteBtn am-btn am-btn-danger am-round'>写新文章</a>" +
			"<a href='" + data["articleUrl"] + "' class='lookArticleBtn am-btn am-btn-danger am-round'>查看文章</a>" +
			"</div>");
	
	successBox.append(successArticleTitle);
	successBox.append(successWord);
	successBox.append(successTimeAndUser);
	successBox.append(successBtn);
	success.append(successBox);
	sec.append(success);
	$("#removeDiv").append(sec);
}

//发表博客
var surePublishBtn = $(".surePublishBtn");
var articleCategories = $("#select-categories");
var articleGrade = $("#select-grade");
var originalAuthor = $("#originalAuthor");
var articleUrl = $("#articleUrl");
surePublishBtn.click(function() {
	var tagNum = $(".tag").find(".tag-name").length;
	var articleTagsValue = [];
	for (var j = 0; j < tagNum; j++) {
		articleTagsValue[j] = $(".tag-name").eq(j).html();//文章标签
	}
	var articleTypeValue = articleType.val();//文章类型（转载，原创）
	var articleCategoriesValue = articleCategories.val();//文章分类
	var articleGradeValue = articleGrade.val();//文章等级
	var originalAuthorValue = originalAuthor.val();//文章转载原作者
	var articleUrlValue = articleUrl.val();//文章转载原链接
	if (articleTagsValue.length == 0 || articleTagsValue[tagNum - 1] == "") {
		$(".notice-box-tags").show();
	} else if (articleTypeValue == "choose") {
		$(".notice-box-type").show();
	} else if (articleCategoriesValue == "choose") {
		$(".notice-box-categories").show();
	} else if (articleGradeValue == "choose") {
		$(".notice-box-grade").show();
	} else if (articleType.val() == "转载" && originalAuthorValue == "") {
		$(".notice-box-originalAuthor").show();
	} else if (articleType.val() == "转载" && articleUrlValue == "") {
		$(".notice-box-url").show();
	} else {
		$.ajax({
			type: "post",
			url: "/publishArticle",
			traditional: true,
			data: {
				id: $(".surePublishBtn").attr("id"),
				articleTitle: articleTitle.val(),
				articleContent: articleContent.val(),
				articleTagsValue: articleTagsValue,
				articleType: articleTypeValue,
				articleCategories: articleCategoriesValue,
				articleGrade: articleGradeValue,
				originalAuthor: originalAuthorValue,
				articleUrl: articleUrlValue,
				articleHtmlContent: testEditor.getHTML()
			},
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "json",
			success: function(data) {
				if (data['status'] == 101) {
					window.removeEventListener('beforeunload',fnClose);
                    $.get("/toLogin",function(data,status,xhr){
                        window.location.replace("/login");
                    });
				} else if (data['status'] == 205) {
					alert("发布失败了，都叫你不要发布了，不听嘛");
				} else if (data['status'] == 206) {
					alert("服务器异常");
				} else {
					$("#my-alert").modal("close");
					window.removeEventListener('beforeunload',fnClose);
                    publishSuccessPutIn(data['data']);
				}
			},
			error: function() {
				alert("发表博客异常");
			}
		});
	}
	
	//定时关闭错误提示框
	var closeNoticeBox = setTimeout(function() {
		noticeBox.hide();
	}, 3000);
});