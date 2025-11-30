package com.example.flashpedition.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Barang {
    @Id
    @NotNull
    @Size(max = 10)
    private String id_barang;

    @NotNull
    @Size(max = 50)
    private String nama_barang;

    @NotNull
    @Size(max = 30)
    private String jenis_barang;

    @NotNull
    private int jumlah;

    @NotNull
    @Size(max = 20)
    private String satuan;

    // Constructors
    public Barang() {}
    public Barang(String id, String nama, String jenis, int jumlah, String satuan) {
        this.id_barang = id;
        this.nama_barang = nama;
        this.jenis_barang = jenis;
        this.jumlah = jumlah;
        this.satuan = satuan;
    }

    // Getters and setters
    public String getId_barang() { return id_barang; }
    public void setId_barang(String id_barang) { this.id_barang = id_barang; }
    public String getNama_barang() { return nama_barang; }
    public void setNama_barang(String nama_barang) { this.nama_barang = nama_barang; }
    public String getJenis_barang() { return jenis_barang; }
    public void setJenis_barang(String jenis_barang) { this.jenis_barang = jenis_barang; }
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public String getSatuan() { return satuan; }
    public void setSatuan(String satuan) { this.satuan = satuan; }
}
