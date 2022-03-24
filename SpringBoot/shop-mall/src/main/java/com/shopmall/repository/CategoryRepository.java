package com.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmall.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
