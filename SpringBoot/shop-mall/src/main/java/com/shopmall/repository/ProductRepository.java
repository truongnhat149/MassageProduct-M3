package com.shopmall.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import com.shopmall.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>{

	
	List<Product> findFirst12ByCategoryNameCategoryContainingIgnoreCaseOrderByIdDesc(String dm);
	List<Product> findByIdIn(Set<Long> idList);
}
