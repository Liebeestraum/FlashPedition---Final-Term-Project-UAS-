package com.example.flashpedition.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Variable initialisations
    @NotNull
    @Size (max = 50)
    private String nama_depan;

    @Size (max = 50)
    private String nama_tengah;

    @NotNull
    @Size (max = 50)
    private String nama_belakang;

    @NotNull
    private String tempat_lahir;

    private String tanggal_lahir;

    @Size (max = 30)
    private String jenis_kelamin;

    @Size (max = 15)
    private String nomor_telepon;

    private String email;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "alamat_id")
    private Alamat alamat;

    // Constructor
    public Person(String nd, String nt, String nb, String tmpt, String tgl, 
    String jk, String notlp, String e, Alamat alamat) {
        this.nama_depan = nd;
        this.nama_tengah = nt;
        this.nama_belakang = nb;
        this.tempat_lahir = tmpt;
        this.tanggal_lahir = tgl;
        this.jenis_kelamin = jk;
        this.nomor_telepon = notlp;
        this.email = e;
        this.alamat = alamat;
    }

    // Legacy compatibility constructor (wrap raw string into minimal Alamat)
    public Person(String nd, String nt, String nb, String tmpt, String tgl, 
    String jk, String notlp, String e, String alamatStr) {
        this(nd, nt, nb, tmpt, tgl, jk, notlp, e, buildAlamat(alamatStr));
    }

    public Person() {
        this.nama_depan = "";
        this.nama_tengah = "";
        this.nama_belakang = "";
        this.tempat_lahir = "";
        this.tanggal_lahir = "";
        this.jenis_kelamin = "";
        this.nomor_telepon = "";
        this.email = "";
    this.alamat = new Alamat();
    }

    // Setter and Getter
    public Long setId(Long id) {
        return this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public String setNamaDepan(String nd) {
        return this.nama_depan = nd;
    }
    public String getNamaDepan() {
        return this.nama_depan;
    }

    public String setNamaTengah(String nt) {
        return this.nama_tengah = nt;
    }
    public String getNamaTengah() {
        return this.nama_tengah;
    }

    public String setNamaBelakang(String nb) {
        return this.nama_belakang = nb;
    }
    public String getNamaBelakang() {
        return this.nama_belakang;
    }

    public String setTempatLahir(String tmpt) {
        return this.tempat_lahir = tmpt;
    }
    public String getTempatLahir() {
        return this.tempat_lahir;
    }

    public String setTanggalLahir(String tgl) {
        return this.tanggal_lahir = tgl;
    }
    public String getTanggalLahir() {
        return this.tanggal_lahir;
    }

    public String setJenisKelamin(String jk) {
        return this.jenis_kelamin = jk;
    }
    public String getJenisKelamin() {
        return this.jenis_kelamin;
    }

    public String setNomorTelepon(String notlp) {
        return this.nomor_telepon = notlp;
    }
    public String getNomorTelepon() {
        return this.nomor_telepon;
    }

    public String setEmail(String e) {
        return this.email = e;
    }
    public String getEmail() {
        return this.email;
    }

    public Alamat setAlamat(Alamat a) {
        return this.alamat = a;
    }
    public Alamat getAlamat() {
        return this.alamat;
    }
    // Legacy helper for code still expecting String
    public String getAlamatString() {
        if (alamat != null) {
            return alamat.toString();
        } else {
            return "";
        }
    }

    // Helper to convert legacy String into Alamat using if-else
    private static Alamat buildAlamat(String alamatStr) {
        Alamat alamatObj;
        if (alamatStr == null) {
            alamatObj = new Alamat();
        } else {
            alamatObj = new Alamat(alamatStr, "", "", "", "", "");
        }
        return alamatObj;
    }

}