package com.example.flashpedition.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Paket extends Pengiriman {
    @NotNull
    private String fragile;
    private double berat;
    private double ukuran;
    private String status;

    // Full parameter constructor (no pembayaran per UML)
    public Paket(String resi,
                 Supplier supplier,
                 Penerima penerima,
                 String kurir,
                 Alamat alamatAsal,
                 Alamat alamatTujuan,
                 double berat,
                 double ukuran,
                 String status,
                 String fragile) {
        super(resi, supplier, penerima, kurir, alamatAsal, alamatTujuan);
        this.berat = berat;
        this.ukuran = ukuran;
        this.status = status;
        this.fragile = fragile;
    }

    // Single-arg constructor (fragile only) per UML
    public Paket(String fragile) {
        super();
        this.fragile = fragile;
        this.berat = 0.0;
        this.ukuran = 0.0;
        this.status = "";
    }

    // Default constructor
    public Paket() {
        super();
        this.fragile = "";
        this.berat = 0.0;
        this.ukuran = 0.0;
        this.status = "";
    }

    // Implement abstract methods from Pengiriman
    @Override
    public double berat() {
        return berat;
    }

    @Override
    public double ukuran() {
        return ukuran;
    }

    @Override
    public String status() {
        return status;
    }

    // Setters (void) and Getters per UML (remove id setter; inherited id managed by JPA)
    public void setFragile(String fragile) {
        this.fragile = fragile;
    }
    public String getFragile() {
        return this.fragile;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }
    public double getBerat() {
        return this.berat;
    }

    public void setUkuran(double ukuran) {
        this.ukuran = ukuran;
    }
    public double getUkuran() {
        return this.ukuran;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
}
