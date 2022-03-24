package com.massageshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.massageshop.entities.ChiMucGioHang;
import com.massageshop.entities.GioHang;
import com.massageshop.entities.SanPham;

public interface ChiMucGioHangRepository extends JpaRepository<ChiMucGioHang, Long>{
	
	ChiMucGioHang findBySanPhamAndGioHang(SanPham sp,GioHang g);
	
	List<ChiMucGioHang> findByGioHang(GioHang g);
}
