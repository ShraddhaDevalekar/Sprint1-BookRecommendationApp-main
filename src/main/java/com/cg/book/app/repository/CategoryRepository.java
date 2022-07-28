package com.cg.book.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.book.app.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Category findCategoryByCategoryId(int id);

}

