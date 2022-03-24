package com.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmall.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByNameRole(String nameRole);
}
