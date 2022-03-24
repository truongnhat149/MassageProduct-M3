package com.shopmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmall.entities.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long>{

}
