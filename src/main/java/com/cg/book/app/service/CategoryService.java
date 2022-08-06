package com.cg.book.app.service;

import java.util.List;


import com.cg.book.app.model.Category;


public interface CategoryService {

	Category getcategoryById(int id);
	
	List<Category> getAllCategory();
	
	Category addCategory(Category category);
	
	Category getCategoryByName(String name);

	void deleteCategory(int id);

	


}