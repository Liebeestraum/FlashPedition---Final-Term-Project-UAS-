package com.example.flashpedition.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flashpedition.entity.Pembayaran;
public interface PembayaranRepository extends JpaRepository<Pembayaran, Long>{
	Pembayaran findByIdPembayaran(String idPembayaran);
}
