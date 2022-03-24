package com.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.shopmall.entities.Cart;
import com.shopmall.entities.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	Cart findByUser(User n);
	
}
