package com.shopmall.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shopmall.entities.CartIndex;
import com.shopmall.entities.DetailsOrder;
import com.shopmall.entities.Order;
import com.shopmall.entities.Cart;
import com.shopmall.entities.User;
import com.shopmall.entities.Product;
import com.shopmall.service.CartIndexService;
import com.shopmall.service.DetailsOrderService;
import com.shopmall.service.OrderService;
import com.shopmall.service.CartService;
import com.shopmall.service.UserService;
import com.shopmall.service.ProductService;

@Controller
@SessionAttributes("loggedInUser")
public class CheckOutController {
	
	@Autowired
	private ProductService ProductService;
	@Autowired
	private UserService UserService;
	@Autowired
	private CartService CartService;
	@Autowired
	private CartIndexService CartIndexService;
	@Autowired
	private OrderService OrderService;
	@Autowired
	private DetailsOrderService DetailsOrderService;

	@ModelAttribute("loggedInUser")
	public User loggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return UserService.findByEmail(auth.getName());
	}
	
	public User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("loggedInUser");
	}
	
	@GetMapping("/checkout")
	public String checkoutPage(HttpServletRequest res,Model model) {
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
		
		model.addAttribute("cart",listsp);
		model.addAttribute("quanity",quanity);
		model.addAttribute("user", currentUser);
		model.addAttribute("order", new Order());
		
		return "client/checkout";
	}
	
	@PostMapping(value="/thankyou")
	public String thankyouPage(@ModelAttribute("order") Order Order ,HttpServletRequest req,HttpServletResponse response ,Model model){
		Order.setorderDate(new Date());
		Order.setStatusOrder("Đang chờ giao");

		User currentUser = getSessionUser(req);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<Long,String> quanity = new HashMap<Long,String>();
		List<Product> listsp = new ArrayList<Product>();
		List<DetailsOrder> listDetailDH = new ArrayList<DetailsOrder>();
	
		if(auth == null || auth.getPrincipal() == "anonymousUser")     //Lay tu cookie
		{
			Order d = OrderService.save(Order);
			Cookie cl[] = req.getCookies();		
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
			for(Product sp: listsp)
			{
				DetailsOrder detailDH = new DetailsOrder();
				detailDH.setProduct(sp);
				detailDH.setSoLuongDat(Integer.parseInt(quanity.get(sp.getId())));
				detailDH.setUnitPrice(Integer.parseInt(quanity.get(sp.getId()))*sp.getUnitPrice());
				detailDH.setOrder(d);
				listDetailDH.add(detailDH);
			}
		}else     //Lay tu database
		{
			Order.setNguoiDat(currentUser);
			Order d = OrderService.save(Order);
			Cart g = CartService.getCartByUser(currentUser);
			List<CartIndex> listchimuc = CartIndexService.getCartIndexByCart(g);
			for(CartIndex c: listchimuc)
			{			
				DetailsOrder detailDH = new DetailsOrder();
				detailDH.setProduct(c.getProduct());
				detailDH.setUnitPrice(c.getSo_luong()*c.getProduct().getUnitPrice());	
				detailDH.setSoLuongDat(c.getSo_luong());
				detailDH.setOrder(d);
				listDetailDH.add(detailDH);		
				
				listsp.add(c.getProduct());
				quanity.put(c.getProduct().getId(), Integer.toString(c.getSo_luong()));
			}
			
		}					
			
		DetailsOrderService.save(listDetailDH);
		
		cleanUpAfterCheckOut(req,response);
		model.addAttribute("order",Order);
		model.addAttribute("cart",listsp);
		model.addAttribute("quanity",quanity);
		return "client/thankYou";
	}
	
	public void cleanUpAfterCheckOut(HttpServletRequest request, HttpServletResponse response)
	{
		User currentUser = getSessionUser(request);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null || auth.getPrincipal() == "anonymousUser")    //Su dung cookie de luu
		{
			Cookie clientCookies[] = request.getCookies();
			for(int i=0;i<clientCookies.length;i++)
			{
				if(clientCookies[i].getName().matches("[0-9]+"))
				{						
					clientCookies[i].setMaxAge(0);
					clientCookies[i].setPath("/shopmall");
					response.addCookie(clientCookies[i]);
				}
			}
		}else //Su dung database de luu
		{
			Cart g = CartService.getCartByUser(currentUser);
			List<CartIndex> c = CartIndexService.getCartIndexByCart(g);
			CartIndexService.deleteAllCartIndex(c);
		}
	}
	
	
	
}
