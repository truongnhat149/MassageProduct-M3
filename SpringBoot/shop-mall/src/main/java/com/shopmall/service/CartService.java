package com.shopmall.service;

import com.shopmall.entities.Cart;
import com.shopmall.entities.User;

public interface CartService {
	
	Cart getCartByUser(User n);
	
	Cart save(Cart g);
}
