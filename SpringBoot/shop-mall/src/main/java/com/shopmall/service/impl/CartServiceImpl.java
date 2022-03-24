package com.shopmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopmall.entities.Cart;
import com.shopmall.entities.User;
import com.shopmall.repository.CartRepository;
import com.shopmall.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository repo;
	
	@Override
	public Cart getCartByUser(User n)
	{
		return repo.findByUser(n);
	}
	
	@Override
	public Cart save(Cart g)
	{
		return repo.save(g);
	}

}
