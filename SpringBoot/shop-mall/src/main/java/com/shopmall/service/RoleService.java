package com.shopmall.service;

import java.util.List;

import com.shopmall.entities.Role;

public interface RoleService {

	Role findByNameRole(String nameRole);
	List<Role> findAllRole();
}
