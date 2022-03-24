package com.shopmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopmall.entities.CartIndex;
import com.shopmall.entities.Cart;
import com.shopmall.entities.Product;
import com.shopmall.repository.CartIndexRepository;
import com.shopmall.service.CartIndexService;

@Service
public class CartIndexServiceImpl implements CartIndexService{
	
	@Autowired
	private CartIndexRepository repo;
	
	@Override
	public CartIndex getCartIndexByProductAndCart(Product sp,Cart g)
	{
		return repo.findByProductAndCart(sp,g);
	}
	
	@Override
	public CartIndex saveCartIndex(CartIndex c)
	{
		return repo.save(c);
	}
	
	@Override
	public void deleteCartIndex(CartIndex c)
	{
		repo.delete(c);
	}
	
	@Override
	public List<CartIndex> getCartIndexByCart(Cart g)
	{
		return repo.findByCart(g);
	}
	
	@Override
	public void deleteAllCartIndex(List<CartIndex> c)
	{
		repo.deleteAll(c);
	}

}
