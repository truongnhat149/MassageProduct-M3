package com.shopmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmall.entities.CartIndex;
import com.shopmall.entities.Cart;
import com.shopmall.entities.Product;

public interface CartIndexRepository extends JpaRepository<CartIndex, Long>{
	
	CartIndex findByProductAndCart(Product sp,Cart g);
	
	List<CartIndex> findByCart(Cart g);
}
