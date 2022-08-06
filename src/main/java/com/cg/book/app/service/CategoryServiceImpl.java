package com.cg.book.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.book.app.exception.CategoryAlreadyExistsException;
import com.cg.book.app.exception.CategoryNotFoundException;
import com.cg.book.app.model.Category;
import com.cg.book.app.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		Category cat = categoryRepository.findCategoryByCategoryName(category.getCategoryName());
		if (cat != null) {
			String exceptionMessage = "Category already exist in the database.";
			LOG.warn(exceptionMessage);
			throw new CategoryAlreadyExistsException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return categoryRepository.save(category);
		}

	}

    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
        	String exceptionMessage = "Category doesnt exist in database";
        	LOG.warn(exceptionMessage);
        	throw new CategoryNotFoundException(exceptionMessage);
        }else {
        	LOG.info("List returned Succesfully.");
        }
        return categoryList;
    }
	
	@Override
	public Category getcategoryById(int id) {
		Category cat = categoryRepository.findCategoryByCategoryId(id);
		if (cat == null) {
			String exceptionMessage = "Category does not exist in the database.";
			LOG.warn(exceptionMessage);
			throw new CategoryNotFoundException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return cat;
		}
	}

	@Override
	public void deleteCategory(int id) {
		// TODO Auto-generated method stub
		Category cat = categoryRepository.findCategoryByCategoryId(id);
		if (cat != null) {
			categoryRepository.deleteById(id);
		} else {
			throw new CategoryNotFoundException("Category not found");
		}

	}
	
	@Override
	public Category getCategoryByName(String name) {
		Category cat = categoryRepository.findCategoryByCategoryName(name);
		if (cat == null) {
			String exceptionMessage = "Category does not exist in the database.";
			LOG.warn(exceptionMessage);
			throw new CategoryNotFoundException(exceptionMessage);
		} else {
			LOG.info("List returned successfully.");
			return cat;
		}
	}

	
	

}