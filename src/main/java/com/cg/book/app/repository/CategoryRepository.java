package com.cg.book.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.book.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findCategoryByCategoryName(String name);

	public Category findCategoryByCategoryId(int id);

}