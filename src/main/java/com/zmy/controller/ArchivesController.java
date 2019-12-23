package com.zmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.constant.CodeType;
import com.zmy.service.ArchiveService;
import com.zmy.service.ArticleService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

/**
 * @ClassName:ArchivesController
 * @Description:归档
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月23日 下午3:21:22
 */
@RestController
public class ArchivesController {
	@Autowired ArchiveService archiveService;
	@Autowired ArticleService articleService;
	
	/**
	 * 获得所有归档日期及每个归档日期的文章数目
	 * @return
	 */
	@GetMapping("/findArchiveNameAndArticleNum")
	public String findArchiveNameAndArticleNum() {
		try {
			DataMap data = archiveService.findArchiveNameAndArticleNum();
			return JsonResult.build(data).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
	
	@GetMapping("getArchiveArticle")
	public String getArchiveArticle(@RequestParam("archive") String archive,
			@RequestParam("rows") String rows,
			@RequestParam("pageNum") String pageNum) {
		try {
			DataMap data = articleService.findArticleByArchive(archive, Integer.parseInt(rows), Integer.parseInt(pageNum));
			return JsonResult.build(data).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
}
