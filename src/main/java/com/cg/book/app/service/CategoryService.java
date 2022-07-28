package com.cg.book.app.service;

import java.util.List;

import com.cg.book.app.model.Category;


public interface CategoryService {

	Category addCategory(Category category);
	
	List<Category> getAllCategory();
	
	Category getcategoryById(int id);

}