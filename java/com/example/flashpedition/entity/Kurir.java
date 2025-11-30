package com.example.flashpedition.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Kurir {
    // Variable initialisations
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size (max = 10)
    private String idKurir;
    private String status;

    @OneToOne
    @JoinColumn (name = "kurir")
    private Kendaraan kendaraan; // Association to Kendaraan

    // Constructor
    // Preferred constructor using association
    public Kurir(String id, String s, Kendaraan k) {
        this.idKurir = id;
        this.status = s;
        this.kendaraan = k;
    }

    // Backward-compatible constructor using String (e.g., plat nomor)
    public Kurir(String id, String s, String platNomor) {
        this.idKurir = id;
        this.status = s;
        this.kendaraan = new Kendaraan();
        this.kendaraan.setPlatNomor(platNomor);
    }

    public Kurir() {
    this.idKurir = "";
    this.status = "";
    this.kendaraan = new Kendaraan();
    }

    // Setter and Getter
    public Long setId(Long id) {
        return this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public String setIdKurir(String id) {
        return this.idKurir = id;
    }
    public String getIdKurir() {
        return this.idKurir;
    }

    public String setStatus(String s) {
        return this.status = s;
    }
    public String getStatus() {
        return this.status;
    }

    public Kendaraan setKendaraan(Kendaraan k) {
        return this.kendaraan = k;
    }
    public Kendaraan getKendaraan() {
        return this.kendaraan;
    }

    // Legacy helpers for code still using String representation (plat nomor)
    public String setKendaraanPlat(String platNomor) {
        if (this.kendaraan == null) {
            this.kendaraan = new Kendaraan();
        }
        return this.kendaraan.setPlatNomor(platNomor);
    }
    public String getKendaraanString() {
        if (this.kendaraan != null) {
            return this.kendaraan.getPlatNomor();
        } else {
            return "";
        }
    }
}
