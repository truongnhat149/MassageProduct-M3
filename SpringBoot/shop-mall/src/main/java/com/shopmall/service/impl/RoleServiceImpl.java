package com.shopmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopmall.entities.Role;
import com.shopmall.repository.RoleRepository;
import com.shopmall.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	

	@Autowired
	private RoleRepository repo;

	@Override
	public Role findByNameRole(String nameRole) {
		return repo.findByNameRole(nameRole);
	}

	@Override
	public List<Role> findAllRole() {
		return repo.findAll();
	}

}
