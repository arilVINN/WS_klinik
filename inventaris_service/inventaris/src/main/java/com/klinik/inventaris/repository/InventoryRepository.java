package com.klinik.inventaris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klinik.inventaris.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
