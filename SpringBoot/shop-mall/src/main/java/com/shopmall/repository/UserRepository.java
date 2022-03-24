package com.shopmall.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmall.entities.User;
import com.shopmall.entities.Role;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

	Page<User> findByRole(Set<Role> role, Pageable of);

	List<User> findByRole(Set<Role> role);
}
