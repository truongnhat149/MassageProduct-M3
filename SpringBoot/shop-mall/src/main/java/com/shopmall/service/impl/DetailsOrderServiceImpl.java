package com.shopmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopmall.entities.DetailsOrder;
import com.shopmall.repository.CartIndexRepository;
import com.shopmall.repository.DetailsOrderRepository;
import com.shopmall.service.DetailsOrderService;

@Service
public class DetailsOrderServiceImpl implements DetailsOrderService{
	
	@Autowired
	private DetailsOrderRepository repo;
	
	@Override
	public List<DetailsOrder> save(List<DetailsOrder> list)
	{	
		return repo.saveAll(list);
	}
}
