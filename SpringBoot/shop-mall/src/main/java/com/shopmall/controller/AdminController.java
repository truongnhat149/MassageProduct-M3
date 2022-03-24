package com.shopmall.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shopmall.dto.ListWorkDTO;
import com.shopmall.entities.User;
import com.shopmall.entities.Role;
import com.shopmall.service.CategoryService;
import com.shopmall.service.OrderService;
import com.shopmall.service.ManufacturerService;
import com.shopmall.service.ContactService;
import com.shopmall.service.UserService;
import com.shopmall.service.RoleService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("loggedInUser")
public class AdminController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ManufacturerService hangSXService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ContactService contactService;

	@Autowired
	private OrderService orderService;

	@ModelAttribute("loggedInUser")
	public User loggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findByEmail(auth.getName());
	}

	@GetMapping
	public String adminPage(Model model) {
		ListWorkDTO listCongViec = new ListWorkDTO();
		listCongViec.setsoOrderMoi(orderService.countByStatusOrder("Đang chờ giao"));
		listCongViec.setsoOrderChoDuyet(orderService.countByStatusOrder("Chờ duyệt"));
		listCongViec.setSoContactMoi(contactService.countByStatus("Đang chờ trả lời"));
		
		model.addAttribute("listCongViec", listCongViec);
		return "admin/pageAdmin";
	}

	@GetMapping("/category")
	public String managerCategoryPage() {
		return "admin/managerCategory";
	}

	@GetMapping("/brand")
	public String managerbrandPage() {
		return "admin/managerBrand";
	}

	@GetMapping("/contact")
	public String managerContactPage() {
		return "admin/managerContact";
	}
	
	@GetMapping("/products")
	public String managerProductPage(Model model) {
		model.addAttribute("listBrand", hangSXService.getALlHangSX());
		model.addAttribute("listCategory", categoryService.getAllCategory());
		return "admin/managerProduct";
	}

	@GetMapping("/profile")
	public String profilePage(Model model, HttpServletRequest request) {
		model.addAttribute("user", getSessionUser(request));
		return "admin/profile";
	}

	@PostMapping("/profile/update")
	public String updateUser(@ModelAttribute User nd, HttpServletRequest request) {
		User currentUser = getSessionUser(request);
		currentUser.setAddress(nd.getAddress());
		currentUser.setFullname(nd.getFullname());
		currentUser.setphone(nd.getphone());
		userService.updateUser(currentUser);
		return "redirect:/admin/profile";
	}

	@GetMapping("/order")
	public String managerOrderPage(Model model) {
		Set<Role> Role = new HashSet<>();
		// lấy danh sách shipper
		Role.add(roleService.findByNameRole("ROLE_SHIPPER"));
		List<User> shippers = userService.getUserByRole(Role);
		for (User shipper : shippers) {
			shipper.setListOrder(orderService.findByStatusOrderAndShipper("Đang giao", shipper));
		}
		model.addAttribute("allShipper", shippers);
		return "admin/managerOrder";
	}

	@GetMapping("/account")
	public String managerAccountPage(Model model) {
	    model.addAttribute("listRole", roleService.findAllRole());
		return "admin/account";
	}
	
	@GetMapping("/statistical") // thống kê
	public String thongKePage(Model model) {
		return "admin/statistical";
	}
	
	public User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("loggedInUser");
	}
	
	

}
