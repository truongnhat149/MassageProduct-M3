package com.massageshop.service;

import java.util.List;

import com.massageshop.entities.VaiTro;

public interface VaiTroService {

	VaiTro findByTenVaiTro(String tenVaiTro);
	List<VaiTro> findAllVaiTro();
}
