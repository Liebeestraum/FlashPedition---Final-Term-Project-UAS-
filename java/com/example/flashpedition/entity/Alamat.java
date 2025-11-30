package com.example.flashpedition.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Alamat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Variable declarations
    @NotNull
    @Size(max = 50)
    private String jalan;

    private String kecamatan;
    private String kelurahan;
    private String kota;
    private String provinsi;

    @NotNull
    @Size(max = 10)
    private String kode_pos;

    // Reverse associations to Pengiriman (mappedBy must match field names in
    // Pengiriman)
    @OneToMany(mappedBy = "alamat_asal")
    private List<Pengiriman> pengirimanAsal;
    @OneToMany(mappedBy = "alamat_tujuan")
    private List<Pengiriman> pengirimanTujuan;

    // Constructor
    public Alamat(String j, String kcmt, String klrh, String kt, String p, String kp) {
        this.jalan = j;
        this.kecamatan = kcmt;
        this.kelurahan = klrh;
        this.kota = kt;
        this.provinsi = p;
        this.kode_pos = kp;
        this.pengirimanAsal = new ArrayList<>();
        this.pengirimanTujuan = new ArrayList<>();
    }

    public Alamat() {
        this.jalan = "";
        this.kecamatan = "";
        this.kelurahan = "";
        this.kota = "";
        this.provinsi = "";
        this.kode_pos = "";
        this.pengirimanAsal = new ArrayList<>();
        this.pengirimanTujuan = new ArrayList<>();
    }

    // Setter and Getter
    public Long setId(Long id) {
        return this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String setJalan(String j) {
        return this.jalan = j;
    }

    public String getJalan() {
        return this.jalan;
    }

    public String setKecamatan(String kcmt) {
        return this.kecamatan = kcmt;
    }

    public String getKecamatan() {
        return this.kecamatan;
    }

    public String setKelurahan(String klrh) {
        return this.kelurahan = klrh;
    }

    public String getKelurahan() {
        return this.kelurahan;
    }

    public String setKota(String kt) {
        return this.kota = kt;
    }

    public String getKota() {
        return this.kota;
    }

    public String setProvinsi(String p) {
        return this.provinsi = p;
    }

    public String getProvinsi() {
        return this.provinsi;
    }

    public String setKodePos(String kp) {
        return this.kode_pos = kp;
    }

    public String getKodePos() {
        return this.kode_pos;
    }

    // Convenience factory
    public static Alamat of(String jalan, String kecamatan, String kelurahan, String kota, String provinsi,
            String kodePos) {
        return new Alamat(jalan, kecamatan, kelurahan, kota, provinsi, kodePos);
    }

    @Override
    public String toString() {
        return jalan + ", " + kelurahan + ", " + kecamatan + ", " + kota + ", " + provinsi + " " + kode_pos;
    }

    // Reverse association API
    public void addPengirimanAsal(Pengiriman p) {
        if (p == null) {
            return;
        }
        if (this.pengirimanAsal == null) {
            this.pengirimanAsal = new ArrayList<>();
        }
        boolean exists = false;
        for (Pengiriman it : this.pengirimanAsal) {
            if (it == p) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            this.pengirimanAsal.add(p);
        }
    }

    public void removePengirimanAsal(Pengiriman p) {
        if (p == null) {
            return;
        }
        if (this.pengirimanAsal == null) {
            return;
        }
        this.pengirimanAsal.remove(p);
    }

    public List<Pengiriman> getPengirimanAsal() {
        return this.pengirimanAsal;
    }

    public void addPengirimanTujuan(Pengiriman p) {
        if (p == null) {
            return;
        }
        if (this.pengirimanTujuan == null) {
            this.pengirimanTujuan = new ArrayList<>();
        }
        boolean exists = false;
        for (Pengiriman it : this.pengirimanTujuan) {
            if (it == p) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            this.pengirimanTujuan.add(p);
        }
    }

    public void removePengirimanTujuan(Pengiriman p) {
        if (p == null) {
            return;
        }
        if (this.pengirimanTujuan == null) {
            return;
        }
        this.pengirimanTujuan.remove(p);
    }

    public List<Pengiriman> getPengirimanTujuan() {
        return this.pengirimanTujuan;
    }
}