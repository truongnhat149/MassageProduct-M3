package com.shopmall.service;

import java.util.List;

import com.shopmall.entities.CartIndex;
import com.shopmall.entities.Cart;
import com.shopmall.entities.Product;

public interface CartIndexService{
	
	List<CartIndex> getCartIndexByCart(Cart g);
	
	CartIndex getCartIndexByProductAndCart(Product sp,Cart g);
	
	CartIndex saveCartIndex(CartIndex c);
	
	void deleteCartIndex(CartIndex c);
	
	void deleteAllCartIndex(List<CartIndex> c);
	
}
