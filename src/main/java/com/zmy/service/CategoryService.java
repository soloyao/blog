package com.zmy.service;

import com.zmy.util.DataMap;

public interface CategoryService {
	DataMap findCategoriesNameAndArticleNum();
	int countCategoriesNum();
	DataMap findCategoriesName();
	DataMap findAllCategories();
	DataMap updateCategory(String categoryName, int type);
}
