package com.zmy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zmy.constant.CodeType;
import com.zmy.service.ArticleService;
import com.zmy.service.CommentService;
import com.zmy.service.LeaveMessageService;
import com.zmy.service.TagService;
import com.zmy.util.DataMap;
import com.zmy.util.JsonResult;

/**
 * @ClassName:IndexController
 * @Description:首页请求接收
 * @author:zmy(343722243@qq.com)
 * @date:2019年12月22日 下午1:51:44
 */
@RestController
public class IndexController {
	@Autowired ArticleService articleService;
	@Autowired CommentService commentService;
	@Autowired LeaveMessageService leaveMessageService;
	@Autowired TagService tagService;
	
	
	
	/**
	 * 分页获取当前页文章
	 * @param rows
	 * @param pageNum
	 * @return
	 */
	@PostMapping("/myArticles")
	public String myArticles(@RequestParam("rows") String rows,
			@RequestParam("pageNum") String pageNum) {
		try {
			DataMap data = articleService.findAllArticles(rows, pageNum);
			return JsonResult.build(data).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
	
	/**
	 * 获得最新评论
	 * @param rows
	 * @param pageNum
	 * @return
	 */
	@GetMapping("/newComment")
	public String newComment(@RequestParam("rows") String rows,
			@RequestParam("pageNum") String pageNum) {
		try {
			DataMap data = commentService.findFiveNewComment(Integer.parseInt(rows), Integer.parseInt(pageNum));
			return JsonResult.build(data).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
	
	/**
	 * 获得最新留言
	 * @param rows
	 * @param pageNum
	 * @return
	 */
	@GetMapping("/newLeaveWord")
	public String newLeaveWord(@RequestParam("rows") String rows,
			@RequestParam("pageNum") String pageNum) {
		try {
			DataMap data = leaveMessageService.findFiveNewLeaveMessage(Integer.parseInt(rows), Integer.parseInt(pageNum));
			return JsonResult.build(data).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
	
	/**
	 * 获得所有标签云
	 * @return
	 */
	@GetMapping("/findTagsCloud")
	public String findTagsCloud() {
		try {
			DataMap data = tagService.findTagsCloud();
			return JsonResult.build(data).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
	
	@GetMapping("/getSiteInfo")
	public String getSiteInfo() {
		try {
			Map<String, Integer> dataMap = new HashMap<String, Integer>();
			dataMap.put("articleNum", articleService.countArticle());
			dataMap.put("tagsNum", tagService.countTagsNum());
			dataMap.put("commentNum", commentService.commentNum());
			dataMap.put("leaveWordNum", leaveMessageService.leaveMessageNum());
			return JsonResult.success().data(dataMap).toJSON();
		} catch (Exception e) {
			return JsonResult.fail(CodeType.SERVER_EXCEPTION).toJSON();
		}
	}
}
