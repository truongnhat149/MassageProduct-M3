package com.shopmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.shopmall.entities.Order;
import com.shopmall.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {
// public String path = "select DATE_FORMAT(dh.date_of_receipt, '%m') as month,DATE_FORMAT(dh.date_of_receipt, '%Y') as year, sum(ct.quantity * ct.unit_price) as total from order dh, order_details ct where dh.id = ct.id and dh.status = " + " created " + " group by DATE_FORMAT(dh.date_of_receipt, '%Y%m')order by year";
	public List<Order> findByStatusOrderAndShipper(String status, User shipper);

	@Query(value = "select DATE_FORMAT(dh.date_of_receipt, '%m') as month, "
	+ " DATE_FORMAT(dh.date_of_receipt, '%Y') as year, sum(ct.quantity_received * ct.unit_price) as total "
	+ " from order dh, order_details ct"
	+ " where dh.id = ct.order.id and dh.status ='Hoàn thành'"
	+ " group by DATE_FORMAT(dh.date_of_receipt, '%Y%m')"
	+ " order by year asc",nativeQuery = true )
	public List<Object> layOrderTheoThangVaNam();
	
	public List<Order> findByNguoiDat(User ng);
	
	public int countByStatusOrder(String statusOrder);
	
}
