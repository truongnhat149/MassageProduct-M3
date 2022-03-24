package com.shopmall;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shopmall.entities.Category;
import com.shopmall.entities.Order;
import com.shopmall.entities.User;
import com.shopmall.entities.Role;
import com.shopmall.repository.OrderRepository;
import com.shopmall.repository.UserRepository;
import com.shopmall.service.CategoryService;
import com.shopmall.service.UserService;
import com.shopmall.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopmallApplicationTests {

	@Autowired
	private CategoryService dmService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository ndRepo;

	@Autowired
	private OrderRepository dhRepo;
	
	@Autowired
	private ProductService spService;


//	@Test
//	public void getALlCategoryTest() {
//		System.out.println(dhRepo.test().size());
//	}

	@Test
	public void getALlCategoryTest() {
		System.out.println(spService.getProductByNameProductForAdmin("asus",0,10).getContent().size());
	}

}
