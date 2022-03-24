package com.shopmall.api.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopmall.dto.SearchOrderObject;
import com.shopmall.entities.DetailsOrder;
import com.shopmall.entities.Order;
import com.shopmall.entities.Product;
import com.shopmall.service.OrderService;
import com.shopmall.service.UserService;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	// lấy danh sách đơn hàng theo search object
	@GetMapping("/all")
	public Page<Order> getOrderByFilter(@RequestParam(defaultValue = "1") int page, @RequestParam String status,
			@RequestParam String tuNgay, @RequestParam String denNgay) throws ParseException {

		SearchOrderObject object = new SearchOrderObject();
		object.setDenNgay(denNgay);
		object.setStatusDon(status);
		object.setTuNgay(tuNgay);
		Page<Order> listOrder = orderService.getAllOrderByFilter(object, page);
		return listOrder;
	}

	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable long id) {
		return orderService.findById(id);
	}

	// phân công đơn hàng
	@PostMapping("/assign")
	public void phanCongOrder(@RequestParam("shipper") String emailShipper,
			@RequestParam("orderId") long OrderId) {
		Order dh = orderService.findById(OrderId);
		dh.setStatusOrder("Đang giao");
		dh.setShipper(userService.findByEmail(emailShipper));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			String dateStr = format.format(new Date());
			Date date = format.parse(dateStr);
			dh.setNgayGiaoHang(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		orderService.save(dh);
	}

	// xác nhận hoàn thành đơn hàng
	@PostMapping("/update")
	public void xacNhanHoanThanhDon(@RequestParam("orderId") long OrderId,
			@RequestParam("ghiChu") String ghiChuAdmin) {
		Order dh = orderService.findById(OrderId);
		
		for(DetailsOrder ct : dh.getListDetails()) {
			Product sp = ct.getProduct();
			sp.setsellingUnit(sp.getsellingUnit() + ct.getSoLuongNhanHang());
			sp.setDonViKho(sp.getDonViKho() - ct.getSoLuongNhanHang() );
		}
		dh.setStatusOrder("Hoàn thành");
		String ghiChu = dh.getGhiChu();
		if (!ghiChuAdmin.equals("")) {
			ghiChu += "<br> Ghi chú admin:\n" + ghiChuAdmin;
		}
		dh.setGhiChu(ghiChu);
		orderService.save(dh);
	}

	// xác nhận hoàn thành đơn hàng
	@PostMapping("/cancel")
	public void huyOrderAdmin(@RequestParam("OrderId") long OrderId) {
		Order dh = orderService.findById(OrderId);
		dh.setStatusOrder("Đã bị hủy");
		orderService.save(dh);
	}

	// lấy dữ liệu làm báo cáo thống kê
	@GetMapping("/report")
	public List<Object> test() {
		return orderService.layOrderTheoThangVaNam();
	}
}
