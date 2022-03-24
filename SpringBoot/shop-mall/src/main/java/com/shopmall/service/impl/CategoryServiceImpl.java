package com.shopmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shopmall.entities.Category;
import com.shopmall.repository.CategoryRepository;
import com.shopmall.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repo;

	@Override
	public Category save(Category d) {
		return repo.save(d);
	}

	@Override
	public Category update(Category d) {
		return repo.save(d);
	}

	@Override
	public void deleteById(long id) {
		repo.deleteById(id);
	}

	@Override
	public Page<Category> getAllCategoryForPageable(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Category getCategoryById(long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Category> getAllCategory() {
		return repo.findAll();
	}

}
