package com.shopmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmall.entities.DetailsOrder;

public interface DetailsOrderRepository extends JpaRepository<DetailsOrder, Long>{
}
