package com.zmy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zmy.pojo.Categories;

@Mapper
public interface CategoryMapper {
	List<String> findCategoriesName();
	int countCategoriesNum();
	List<Categories> findAllCategories();
	int findIsExistByCategoryName(String categoryName);
	void save(Categories categories);
	void deleteCategory(String categoryName);
}
