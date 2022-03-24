package com.shopmall.api.shipper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopmall.dto.UpdateOrderShipper;
import com.shopmall.dto.SearchOrderObject;
import com.shopmall.entities.DetailsOrder;
import com.shopmall.entities.Order;
import com.shopmall.entities.User;
import com.shopmall.service.OrderService;
import com.shopmall.service.UserService;

@RestController
@RequestMapping("/api/shipper/order")
public class OrderShipperApi {

	@Autowired
	private OrderService OrderService;

	@Autowired
	private UserService UserService;

	@GetMapping("/all")
	public Page<Order> getOrderByFilter(@RequestParam(defaultValue = "1") int page, @RequestParam String status,
			@RequestParam String tuNgay, @RequestParam String denNgay, @RequestParam long idShipper)
			throws ParseException {

		SearchOrderObject object = new SearchOrderObject();
		object.setDenNgay(denNgay);
		object.setStatusDon(status);
		object.setTuNgay(tuNgay);

		User shipper = UserService.findById(idShipper);
		Page<Order> listOrder = OrderService.findOrderByShipper(object, page, 6, shipper);
		return listOrder;
	}

	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable long id) {
		return OrderService.findById(id);
	}

	@PostMapping("/update")
	public void updateStatusOrder(@RequestBody UpdateOrderShipper UpdateOrderShipper) {
		Order Order = OrderService.findById(UpdateOrderShipper.getIdOrder());

		for (DetailsOrder chiTiet : Order.getListDetails()) {
			for (UpdateOrderShipper.CapNhatChiTietDon chiTietCapNhat : UpdateOrderShipper
					.getDanhSachCapNhatChiTietDon()) {
				if (chiTiet.getId() == chiTietCapNhat.getIdChiTiet()) {
					chiTiet.setSoLuongNhanHang(chiTietCapNhat.getSoLuongNhanHang());
				}
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {

			String dateStr = format.format(new Date());
			Date date = format.parse(dateStr);
			Order.setNgayNhanHang(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Order.setStatusOrder("Chờ duyệt");

		String ghiChu = UpdateOrderShipper.getGhiChuShipper();

		if (!ghiChu.equals("")) {
			Order.setGhiChu("Ghi chú shipper: \n" + UpdateOrderShipper.getGhiChuShipper());
		}
		OrderService.save(Order);

	}
}
