package com.massageshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.massageshop.entities.VaiTro;

public interface VaiTroRepository extends JpaRepository<VaiTro, Long> {

	VaiTro findByTenVaiTro(String tenVaiTro);
}
