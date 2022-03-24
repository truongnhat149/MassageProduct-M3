package com.massageshop.service;

import com.massageshop.entities.GioHang;
import com.massageshop.entities.NguoiDung;

public interface GioHangService {
	
	GioHang getGioHangByNguoiDung(NguoiDung n);
	
	GioHang save(GioHang g);
}
