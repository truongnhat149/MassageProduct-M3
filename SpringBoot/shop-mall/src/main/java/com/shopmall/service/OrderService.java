package com.shopmall.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.shopmall.dto.SearchOrderObject;
import com.shopmall.entities.Order;
import com.shopmall.entities.User;

public interface OrderService {

	Page<Order> getAllOrderByFilter(SearchOrderObject object, int page) throws ParseException;

	Order update(Order dh);
	
	Order findById(long id);
	
	Page<Order> findOrderByShipper(SearchOrderObject object, int page, int size, User shipper) throws ParseException;

	Order save(Order dh);
	
	List<Object> layOrderTheoThangVaNam();

	List<Order> findByStatusOrderAndShipper(String string, User shipper);

	
	List<Order> getOrderByUser(User currentUser);
	
	int countByStatusOrder(String StatusOrder);
}
