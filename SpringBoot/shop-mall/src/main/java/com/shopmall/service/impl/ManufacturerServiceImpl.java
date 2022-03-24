package com.shopmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shopmall.entities.Manufacturer;
import com.shopmall.repository.ManufacturerRepository;
import com.shopmall.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerRepository repo;

	@Override
	public List<Manufacturer> getALlHangSX() {
		return repo.findAll();
	}

	@Override
	public Manufacturer getHSXById(long id) {
		return repo.findById(id).get();
	}

	@Override
	public Manufacturer save(Manufacturer h) {
		return repo.save(h);
	}

	@Override
	public Manufacturer update(Manufacturer h) {
		return repo.save(h);
	}

	@Override
	public void deleteById(long id) {
		repo.deleteById(id);
	}

	@Override
	public Page<Manufacturer> getALlHangSX(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

}
