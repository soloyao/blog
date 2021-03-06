package com.zmy.controller;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.constant.CodeType;
import com.zmy.service.ArticleService;
import com.zmy.service.CategoryService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

/**
 * @ClassName:CategoriesController
 * @Description:分类
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月23日 下午3:21:16
 */
@RestController
public class CategoriesController {
	@Autowired CategoryService categoryService;
	@Autowired ArticleService articleService;
	
	/**
	 * 获得所有分类名以及每个分类名的文章数目
	 * @return
	 */
	@GetMapping("/findCategoriesNameAndArticleNum")
	public String findCategoriesNameAndArticleNum() {
		try {
			DataMap data = categoryService.findCategoriesNameAndArticleNum();
			return JsonResult.build(data).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
	
	/**
	 * 根据分类名称分页获取该分类下的文章
	 * @param category
	 * @param rows
	 * @param pageNum
	 * @return
	 */
	@GetMapping("/getCategoryArticle")
	public String getCategoryArticle(@RequestParam("category") String category,
			@RequestParam("rows") String rows,
			@RequestParam("pageNum") String pageNum) {
		try {
			DataMap data = articleService.findArticleByCategory(category, Integer.parseInt(rows), Integer.parseInt(pageNum));
			return JsonResult.build(data).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
	
}
