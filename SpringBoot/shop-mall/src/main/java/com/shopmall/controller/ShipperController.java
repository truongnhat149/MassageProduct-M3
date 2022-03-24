package com.shopmall.controller;

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

import com.shopmall.entities.User;
import com.shopmall.service.UserService;

@Controller
@RequestMapping("/shipper")
@SessionAttributes("loggedInUser")
public class ShipperController {
	
	
	@Autowired
	private UserService UserService;
	

	@ModelAttribute("loggedInUser")
	public User loggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserService.findByEmail(auth.getName());
	}
	
	
	@GetMapping(value= {"", "/order"})
	public String shipperPage(Model model) {
		return "shipper/quanLyorder";
	}
	
	@GetMapping("/profile")
	public String profilePage(Model model, HttpServletRequest request) {
		model.addAttribute("user", getSessionUser(request));
		System.out.println(getSessionUser(request).toString());
		return "shipper/profile";
	}
	
	@PostMapping("/profile/update")
	public String updateUser(@ModelAttribute User nd, HttpServletRequest request) {
		User currentUser = getSessionUser(request);
		currentUser.setAddress(nd.getAddress());
		currentUser.setFullname(nd.getFullname());
		currentUser.setphone(nd.getphone());
		UserService.updateUser(currentUser);
		return "redirect:/shipper/profile";
	}
	
	public User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("loggedInUser");
	}

}
