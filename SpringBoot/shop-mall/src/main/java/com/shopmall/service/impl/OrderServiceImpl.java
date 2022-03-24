package com.shopmall.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shopmall.dto.SearchOrderObject;
import com.shopmall.entities.Order;
import com.shopmall.entities.User;
import com.shopmall.entities.QOrder;
import com.shopmall.repository.OrderRepository;
import com.shopmall.service.OrderService;
import com.querydsl.core.BooleanBuilder;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public Page<Order> getAllOrderByFilter(SearchOrderObject object, int page) throws ParseException {
		BooleanBuilder builder = new BooleanBuilder();

		String StatusDon = object.getStatusDon();
		String tuNgay = object.getTuNgay();
		String denNgay = object.getDenNgay();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

		if (!StatusDon.equals("")) {
			builder.and(QOrder.order.statusOrder.eq(StatusDon));
		}

		if (!tuNgay.equals("") && tuNgay != null) {
			if (StatusDon.equals("") || StatusDon.equals("Đang chờ giao") || StatusDon.equals("Đã hủy")) {
				builder.and(QOrder.order.orderDate.goe(formatDate.parse(tuNgay)));
			} else if (StatusDon.equals("Đang giao")) {
				builder.and(QOrder.order.ngayGiaoHang.goe(formatDate.parse(tuNgay)));
			} else { // hoàn thành
				builder.and(QOrder.order.ngayNhanHang.goe(formatDate.parse(tuNgay)));
			}
		}

		if (!denNgay.equals("") && denNgay != null) {
			if (StatusDon.equals("") || StatusDon.equals("Đang chờ giao") || StatusDon.equals("Đã hủy")) {
				builder.and(QOrder.order.orderDate.loe(formatDate.parse(denNgay)));
			} else if (StatusDon.equals("Đang giao")) {
				builder.and(QOrder.order.ngayGiaoHang.loe(formatDate.parse(denNgay)));
			} else { // hoàn thành
				builder.and(QOrder.order.ngayNhanHang.loe(formatDate.parse(denNgay)));
			}
		}

		return orderRepo.findAll(builder, PageRequest.of(page - 1, 6));
	}

	@Override
	public Order update(Order dh) {
		return orderRepo.save(dh);
	}

	@Override
	public Order findById(long id) {
		return orderRepo.findById(id).get();
	}

	@Override
	public List<Order> findByStatusOrderAndShipper(String Status, User shipper) {
		return orderRepo.findByStatusOrderAndShipper(Status, shipper);
	}

	@Override
	public Order save(Order dh) {
		return orderRepo.save(dh);
	}

	@Override
	public List<Object> layOrderTheoThangVaNam() {
		return orderRepo.layOrderTheoThangVaNam();
	}
	
	@Override
	public List<Order> getOrderByUser(User ng) {
		return orderRepo.findByNguoiDat(ng);
	}

	@Override
	public Page<Order> findOrderByShipper(SearchOrderObject object, int page, int size, User shipper) throws ParseException {
		BooleanBuilder builder = new BooleanBuilder();

		String StatusDon = object.getStatusDon();
		String tuNgay = object.getTuNgay();
		String denNgay = object.getDenNgay();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		
		builder.and(QOrder.order.shipper.eq(shipper));

		if (!StatusDon.equals("")) {
			builder.and(QOrder.order.statusOrder.eq(StatusDon));
		}

		if (!tuNgay.equals("") && tuNgay != null) {
			if (StatusDon.equals("Đang giao")) {
				builder.and(QOrder.order.ngayGiaoHang.goe(formatDate.parse(tuNgay)));
			} else { // hoàn thành
				builder.and(QOrder.order.ngayNhanHang.goe(formatDate.parse(tuNgay)));
			}
		}

		if (!denNgay.equals("") && denNgay != null) {
			if (StatusDon.equals("Đang giao")) {
				builder.and(QOrder.order.ngayGiaoHang.loe(formatDate.parse(denNgay)));
			} else { // hoàn thành
				builder.and(QOrder.order.ngayNhanHang.loe(formatDate.parse(denNgay)));
			}
		}

		return orderRepo.findAll(builder, PageRequest.of(page - 1, size));
	}

	@Override
	public int countByStatusOrder(String StatusOrder) {
		return orderRepo.countByStatusOrder(StatusOrder);
	}

}
