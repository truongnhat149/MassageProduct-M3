package com.shopmall.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shopmall.entities.Category;

public interface CategoryService {

	Page<Category> getAllCategoryForPageable(int page, int size);

	List<Category> getAllCategory();

	Category getCategoryById(long id);

	Category save(Category d);

	Category update(Category d);

	void deleteById(long id);
}
