package com.example.flashpedition.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pembayaran {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size (max = 10)
    @Column(name = "id_pembayaran")
    private String idPembayaran;

    private double jumlah;
    private String metode;
    private String status;
    private String tanggal_bayar;
    // Association back to Pengiriman (bidirectional link)
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "resi")
    private Pengiriman pengiriman;

    // Constructor
    public Pembayaran(String id, double j, String m, String s, String tb) {
        this.idPembayaran = id;
        this.jumlah = j;
        this.metode = m;
        this.status = s;
        this.tanggal_bayar = tb;
    }

    public Pembayaran() {
        this.idPembayaran = "";
        this.jumlah = 0.0;
        this.metode = "";
        this.status = "";
        this.tanggal_bayar = "";
        this.pengiriman = null;
    }

    // Setter and Getter
    public Long setId(Long id) {
        return this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public String setIdPembayaran(String id) {
        return this.idPembayaran = id;
    }
    public String getIdPembayaran() {
        return this.idPembayaran;
    }

    public double setJumlah(double j) {
        return this.jumlah = j;
    }
    public double getJumlah() {
        return this.jumlah;
    }

    public String setMetode(String m) {
        return this.metode = m;
    }
    public String getMetode() {
        return this.metode;
    }

    public String setStatus(String s) {
        return this.status = s;
    }
    public String getStatus() {
        return this.status;
    }

    public String setTanggalBayar(String tb) {
        return this.tanggal_bayar = tb;
    }
    public String getTanggalBayar() {
        return this.tanggal_bayar;
    }

    // Association accessors
    public Pengiriman setPengiriman(Pengiriman p) {
        this.pengiriman = p;
        return this.pengiriman;
    }
    public Pengiriman getPengiriman() {
        return this.pengiriman;
    }
}
