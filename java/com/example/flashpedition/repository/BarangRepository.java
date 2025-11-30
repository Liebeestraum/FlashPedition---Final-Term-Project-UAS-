package com.example.flashpedition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.flashpedition.entity.Barang;

public interface BarangRepository extends JpaRepository<Barang, String> {
}
