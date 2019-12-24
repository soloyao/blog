package com.zmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.constant.CodeType;
import com.zmy.service.ArticleService;
import com.zmy.service.TagService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

@RestController
public class TagsController {
	@Autowired TagService tagService;
	@Autowired ArticleService articleService;
	
	@GetMapping("/getTagArticle")
	public String getTagArticle(@RequestParam("tag") String tag,
			@RequestParam("rows") String rows,
			@RequestParam("pageNum") String pageNum) {
		try {
			if (StringUtils.isEmpty(tag)) {//只是加载标签
				return JsonResult.build(tagService.findTagsCloud()).toJSON();
			} else {//点击标签加载标签下的文章
				DataMap data = articleService.findArticleByTag(tag, Integer.parseInt(rows), Integer.parseInt(pageNum));
				return JsonResult.build(data).toJSON();
			}
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
}
