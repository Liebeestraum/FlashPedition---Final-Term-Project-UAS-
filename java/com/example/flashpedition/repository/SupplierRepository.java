package com.example.flashpedition.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flashpedition.entity.Supplier;
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	Optional<Supplier> findByUsernameAndPassword(String username, String password);
}
