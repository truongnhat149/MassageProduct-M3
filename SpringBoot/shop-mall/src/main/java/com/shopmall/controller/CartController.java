package com.shopmall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shopmall.entities.CartIndex;
import com.shopmall.entities.Cart;
import com.shopmall.entities.User;
import com.shopmall.entities.Product;
import com.shopmall.service.CartIndexService;
import com.shopmall.service.CartService;
import com.shopmall.service.UserService;
import com.shopmall.service.ProductService;

@Controller
@SessionAttributes("loggedInUser")
public class CartController {
	
	@Autowired
	private ProductService ProductService;
	@Autowired
	private UserService UserService;
	@Autowired
	private CartService CartService;
	@Autowired
	private CartIndexService CartIndexService;
	
	@ModelAttribute("loggedInUser")
	public User loggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserService.findByEmail(auth.getName());
	}
	
	public User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("loggedInUser");
	}
	
	@GetMapping("/cart")
	public String cartPage(HttpServletRequest res,Model model) {
		User currentUser = getSessionUser(res);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<Long,String> quanity = new HashMap<Long,String>();
		List<Product> listsp = new ArrayList<Product>();
		if(auth == null || auth.getPrincipal() == "anonymousUser")     //Lay tu cookie
		{
			Cookie cl[] = res.getCookies();		
			Set<Long> idList = new HashSet<Long>();
			for(int i=0; i< cl.length; i++)
			{
				if(cl[i].getName().matches("[0-9]+"))
				{
					idList.add(Long.parseLong(cl[i].getName()));
					quanity.put(Long.parseLong(cl[i].getName()), cl[i].getValue());  
				}
				
			}
			listsp = ProductService.getAllProductByList(idList);
		}else     //Lay tu database
		{
			Cart g = CartService.getCartByUser(currentUser);
			if(g != null)
			{
				List<CartIndex> listchimuc = CartIndexService.getCartIndexByCart(g);
				for(CartIndex c: listchimuc)
				{
					listsp.add(c.getProduct());
					quanity.put(c.getProduct().getId(), Integer.toString(c.getSo_luong()));
				}
			}
		}
		model.addAttribute("checkEmpty",listsp.size());
		model.addAttribute("cart",listsp);
		model.addAttribute("quanity",quanity);
		
		
		return "client/cart";
	}

}
