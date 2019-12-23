package com.zmy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zmy.mapper.ArchiveMapper;
import com.zmy.service.ArchiveService;
import com.zmy.service.ArticleService;
import com.zmy.util.DataMap;
import com.zmy.util.TimeUtil;

@Service
public class ArchiveServiceImpl implements ArchiveService {
	@Autowired ArchiveMapper archiveMapper;
	@Autowired ArticleService articleService;
	
	@Override
	public DataMap findArchiveNameAndArticleNum() {
		List<String> archives = archiveMapper.findArchives();
		JSONArray archivesJsonArray = new JSONArray();
		JSONObject archiveJson;
		for (String archiveName : archives) {
			archiveJson = new JSONObject();
			archiveJson.put("archiveName", archiveName);
			archiveName = TimeUtil.timeYearToWhippletree(archiveName);
			archiveJson.put("archiveArticleNum", articleService.countArticleArchiveByArchive(archiveName));
			archivesJsonArray.add(archiveJson);
		}
		JSONObject returnJson = new JSONObject();
		returnJson.put("result", archivesJsonArray);
		return DataMap.success().setData(returnJson);
	}

}
