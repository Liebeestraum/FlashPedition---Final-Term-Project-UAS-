package com.example.flashpedition.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.flashpedition.entity.Logistik;
import com.example.flashpedition.entity.Pengiriman;

public interface PengirimanRepository extends JpaRepository<Pengiriman, Long> {

    // Buyer tracking by resi, returns the concrete Logistik shipment
    @Query("select l from Logistik l where l.resi = :resi")
    Optional<Logistik> findLogistikByResi(@Param("resi") String resi);

    // For history, orders, and report – only Logistik shipments
    @Query("select l from Logistik l")
    List<Logistik> findAllLogistik();
}
