package com.example.flashpedition.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flashpedition.entity.Kurir;

public interface KurirRepository extends JpaRepository<Kurir, Long> {
    Optional<Kurir> findByIdKurir(String idKurir);
}
