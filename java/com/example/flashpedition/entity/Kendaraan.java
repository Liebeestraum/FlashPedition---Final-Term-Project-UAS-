package com.example.flashpedition.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Kendaraan {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    // Variable initialisations
    @NotNull
    @Size (max = 10)
    @Column(name = "plat_nomor")
    private String platNomor;
    private String merk;
    private String model;

    // Constructor
    public Kendaraan(String pn, String mrk, String mdl) {
        this.platNomor = pn;
        this.merk = mrk;
        this.model = mdl;
    }

    public Kendaraan() {
        this.platNomor = "";
        this.merk = "";
        this.model = "";
    }

    // Setter and Getter
    public Long setId(Long id) {
        return this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public String setPlatNomor(String pn) {
        return this.platNomor = pn;
    }
    public String getPlatNomor() {
        return this.platNomor;
    }

    public String setMerk(String mrk) {
        return this.merk = mrk;
    }
    public String getMerk() {
        return this.merk;
    }

    public String setModel(String mdl) {
        return this.model = mdl;
    }
    public String getModel() {
        return this.model;
    }
}
