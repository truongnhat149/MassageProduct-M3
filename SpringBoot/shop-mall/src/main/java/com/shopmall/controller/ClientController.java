package com.shopmall.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shopmall.dto.SearchProductObject;
import com.shopmall.entities.Category;
import com.shopmall.entities.Contact;
import com.shopmall.entities.User;
import com.shopmall.entities.ResponseObject;
import com.shopmall.entities.Product;
import com.shopmall.service.CategoryService;
import com.shopmall.service.ContactService;
import com.shopmall.service.UserService;
import com.shopmall.service.ProductService;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
public class ClientController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ContactService contactService;

	@ModelAttribute("loggedInUser")
	public User loggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.findByEmail(auth.getName());
	}
	
	@ModelAttribute("listCategory")
	public List<Category> listCategory(){
		return categoryService.getAllCategory();
	}

	public User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("loggedInUser");
	}

	@GetMapping
	public String clientPage(Model model) {
		return "client/home";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "client/login";
	}
	
	@GetMapping("/contact")
	public String contactPage() {
		return "client/contact";
	}
	
	@PostMapping("/createContact")
	@ResponseBody
	public ResponseObject createContact(@RequestBody Contact lh)
	{
		lh.setNgayContact(new Date());
		contactService.save(lh);
		return new ResponseObject();
	}

	@GetMapping("/store")
	public String storePage(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "") String range,@RequestParam(defaultValue = "") String brand,@RequestParam(defaultValue = "") String manufactor,@RequestParam(defaultValue = "") String os,@RequestParam(defaultValue = "") String ram,@RequestParam(defaultValue = "") String pin,Model model) {		
		SearchProductObject obj = new SearchProductObject();
		obj.setBrand(brand);
		obj.setUnitPrice(range);
		obj.setManufactor(manufactor);

		Page<Product> list = productService.getProductByBrand(obj,page,12);
		int totalPage = list.getTotalPages();
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("list",list.getContent());
		model.addAttribute("currentPage",page);
		model.addAttribute("range",range);
		model.addAttribute("brand",brand);
		model.addAttribute("manufactor",manufactor);

		List<Integer> pagelist = new ArrayList<Integer>();
		
		//Lap ra danh sach cac trang
		if(page==1 || page ==2 || page == 3 || page == 4)
		{
			for(int i = 2; i <=5 && i<=totalPage; i++)
			{
				pagelist.add(i);
			}
		}else if(page == totalPage)
		{
			for(int i = totalPage; i >= totalPage - 3 && i> 1; i--)
			{
				pagelist.add(i);
			}
			Collections.sort(pagelist);
		}else
		{
			for(int i = page; i <= page + 2 && i<= totalPage; i++)
			{
				pagelist.add(i);
			}
			for(int i = page-1; i >= page - 2 && i> 1; i--)
			{
				pagelist.add(i);
			}
			Collections.sort(pagelist);
		}
		model.addAttribute("pageList",pagelist);
			
		//Lay cac danh muc va hang san xuat tim thay
		Set<String> hangsx = new HashSet<String>();
		Iterable<Product> dum = productService.getProductByNameCategory(brand);
		for(Product sp: dum)
		{
			hangsx.add(sp.getManufacturer().getNameManufacturer());
			if(brand.equals("Massage")){
				
			}
			
		}
		model.addAttribute("hangsx",hangsx);
		return "client/store";
	}

	// @GetMapping("/sp")
	@GetMapping("/products")
	public String detailspPage(@RequestParam int id, Model model) {
		Product sp = productService.getProductById(id);
		model.addAttribute("sp", sp);
		return "client/detailsp";
	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@GetMapping("/shipping")
	public String shippingPage(Model model) {

		return "client/shipping";
	}

	@GetMapping("/guarantee")
	public String guaranteePage(Model model) {

		return "client/guarantee";
	}

}
