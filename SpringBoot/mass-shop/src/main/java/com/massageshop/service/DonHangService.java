package com.massageshop.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.massageshop.dto.SearchDonHangObject;
import com.massageshop.entities.DonHang;
import com.massageshop.entities.NguoiDung;

public interface DonHangService {

	Page<DonHang> getAllDonHangByFilter(SearchDonHangObject object, int page) throws ParseException;

	DonHang update(DonHang dh);
	
	DonHang findById(long id);
	
	Page<DonHang> findDonHangByShipper(SearchDonHangObject object, int page, int size, NguoiDung shipper) throws ParseException;

	DonHang save(DonHang dh);
	
	List<Object> layDonHangTheoThangVaNam();

	List<DonHang> findByTrangThaiDonHangAndShipper(String string, NguoiDung shipper);

	
	List<DonHang> getDonHangByNguoiDung(NguoiDung currentUser);
	
	int countByTrangThaiDonHang(String trangThaiDonHang);
}
