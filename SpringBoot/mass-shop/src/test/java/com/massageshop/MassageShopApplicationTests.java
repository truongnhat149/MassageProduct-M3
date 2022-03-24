package com.massageshop;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.massageshop.entities.DanhMuc;
import com.massageshop.entities.DonHang;
import com.massageshop.entities.NguoiDung;
import com.massageshop.entities.VaiTro;
import com.massageshop.repository.DonHangRepository;
import com.massageshop.repository.NguoiDungRepository;
import com.massageshop.service.DanhMucService;
import com.massageshop.service.NguoiDungService;
import com.massageshop.service.SanPhamService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MassageShopApplicationTests {

	@Autowired
	private DanhMucService dmService;

	@Autowired
	private NguoiDungService nguoiDungService;

	@Autowired
	private NguoiDungRepository ndRepo;

	@Autowired
	private DonHangRepository dhRepo;
	
	@Autowired
	private SanPhamService spService;


//	@Test
//	public void getALlDanhMucTest() {
//		System.out.println(dhRepo.test().size());
//	}

	@Test
	public void getALlDanhMucTest() {
		System.out.println(spService.getSanPhamByTenSanPhamForAdmin("asus",0,10).getContent().size());
	}

}
