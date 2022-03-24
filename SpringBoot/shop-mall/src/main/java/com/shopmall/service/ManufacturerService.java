package com.shopmall.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shopmall.entities.Manufacturer;

public interface ManufacturerService {

	List<Manufacturer> getALlHangSX();
	
	Page<Manufacturer> getALlHangSX(int page, int size);

	Manufacturer getHSXById(long id);

	Manufacturer save(Manufacturer h);

	Manufacturer update(Manufacturer h);

	void deleteById(long id);
}
