package com.example.flashpedition.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flashpedition.entity.Penerima;
public interface PenerimaRepository extends JpaRepository<Penerima, Long> {

    Penerima findByIdPenerima(String id_penerima);
}
