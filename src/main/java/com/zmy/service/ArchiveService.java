package com.zmy.service;

import com.zmy.util.DataMap;

public interface ArchiveService {
	DataMap findArchiveNameAndArticleNum();
	void addArchiveName(String archiveName);
}
