package com.example.flashpedition.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flashpedition.entity.Kendaraan;

public interface KendaraanRepository extends JpaRepository<Kendaraan, Long> {
	Optional<Kendaraan> findByPlatNomor(String platNomor);
}
