package com.cg.book.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.book.app.model.Category;
import com.cg.book.app.service.CategoryService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")

public class CategoryController {

	@Autowired
	CategoryService categoryService;

	/********************************************************
	 * Method: getAllCategory
	 * Description: It is used to view all category in categories table
	 * Method: AddCategory
	 * Description:This method is in Admin Controller that is used to Add category in Database.
********************************************************/
	// http://localhost:9999/user/get-all-Category
	@GetMapping("/getAllCategory")
	public List<Category> getAllCategory() {
		List<Category> allCategoryList = (List<Category>) categoryService.getAllCategory();
		return allCategoryList;
	}
	
	/********************************************************
	 * Method: getcategoryById
	 * Description: It is used to view category in categories table by id
	 * *********************************************************/
	
	
	
	@GetMapping("/get-category-by-id/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(name = "id") int id) {

		Category category = categoryService.getcategoryById(id);
		if (category == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(category));
	}
}