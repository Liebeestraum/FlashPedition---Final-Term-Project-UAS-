package com.example.flashpedition.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PermintaanRestock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_barang", referencedColumnName = "id_barang")
    private Barang barang;

    private int jumlah;
    private LocalDateTime tanggal;
    private String status; // e.g., "Diajukan", "Diproses", "Selesai"

    public PermintaanRestock() {}

    public PermintaanRestock(Barang barang, int jumlah, LocalDateTime tanggal, String status) {
        this.barang = barang;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
        this.status = status;
    }

    public Long getId() { return id; }
    public Barang getBarang() { return barang; }
    public void setBarang(Barang barang) { this.barang = barang; }
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public LocalDateTime getTanggal() { return tanggal; }
    public void setTanggal(LocalDateTime tanggal) { this.tanggal = tanggal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
