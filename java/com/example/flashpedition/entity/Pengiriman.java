package com.example.flashpedition.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    // Variable declarations
    @NotNull
    @Size(max = 15)
    protected String resi;

    @ManyToOne
    @JoinColumn(name = "id_supplier")
    protected Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "id_penerima")
    protected Penerima penerima;

    protected String kurir;

    @ManyToOne
    @JoinColumn(name = "id_alamat_asal")
    protected Alamat alamat_asal;
    @ManyToOne
    @JoinColumn(name = "id_alamat_tujuan")
    protected Alamat  alamat_tujuan;

    @OneToOne(mappedBy = "pengiriman", cascade = CascadeType.ALL)
    protected Pembayaran pembayaran;

    // Constructor taking object associations
    public Pengiriman(String r, Supplier sply, Penerima pnrm, String kr, Alamat aa, Alamat at,
            Pembayaran pembayaran) {
        this.resi = r;
        this.supplier = sply;
        this.penerima = pnrm;
        this.kurir = kr;
        this.alamat_asal = aa;
        this.alamat_tujuan = at;
        this.pembayaran = pembayaran;
        // Back-reference not set here to avoid leaking 'this' in constructor
    }

    // Overload without pembayaran: initialize with default Pembayaran
    public Pengiriman(String r, Supplier sply, Penerima pnrm, String kr, Alamat aa, Alamat at) {
        this.resi = r;
        this.supplier = sply;
        this.penerima = pnrm;
        this.kurir = kr;
        this.alamat_asal = aa;
        this.alamat_tujuan = at;
        this.pembayaran = null;
        // Back-reference not set here to avoid leaking 'this' in constructor
    }
    // Backward-compatible constructor taking raw strings

    // Overload: raw string addresses + Pembayaran object

    // Overload: raw string addresses without pembayaran

    public Pengiriman() {
        this.resi = "";
        this.supplier = null;
        this.penerima = null;
        this.kurir = "";
        this.alamat_asal = null;
        this.alamat_tujuan = null;
        this.pembayaran = null;
    }

    // Abstract method
    // Abstract attributes to be provided by concrete shipment types
    public abstract double berat();

    public abstract double ukuran();

    public abstract String status();

    // Setter and Getter
    public String setResi(String r) {
        return this.resi = r;
    }

    public String getResi() {
        return this.resi;
    }

    public Supplier setSupplier(Supplier sply) {
        return this.supplier = sply;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    // Penerima association accessors
    public Penerima setPenerimaObj(Penerima pnrm) {
        if (pnrm == null) {
            this.penerima = new Penerima();
        } else {
            this.penerima = pnrm;
        }
        if (this.penerima != null) {
            this.penerima.addPengiriman(this);
        }
        return this.penerima;
    }

    public Penerima getPenerimaObj() {
        return this.penerima;
    }
    // Strictly object-based: legacy string accessors removed

    public String setKurir(String kr) {
        return this.kurir = kr;
    }

    public String getKurir() {
        return this.kurir;
    }

    public Alamat setAlamatAsal(Alamat aa) {
        this.alamat_asal = aa;
        if (this.alamat_asal != null) {
            this.alamat_asal.addPengirimanAsal(this);
        }
        return this.alamat_asal;
    }

    public Alamat getAlamatAsal() {
        return this.alamat_asal;
    }

    public Alamat setAlamatTujuan(Alamat at) {
        this.alamat_tujuan = at;
        if (this.alamat_tujuan != null) {
            this.alamat_tujuan.addPengirimanTujuan(this);
        }
        return this.alamat_tujuan;
    }

    public Alamat getAlamatTujuan() {
        return this.alamat_tujuan;
    }

    public Pembayaran setPembayaranObj(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
        // Back-reference intentionally not set here
        return this.pembayaran;
    }

    public Pembayaran getPembayaranObj() {
        return this.pembayaran;
    }

    // Helper to establish reverse links after fully constructing this object
    public void linkAlamatReverse() {
        if (this.alamat_asal != null) {
            this.alamat_asal.addPengirimanAsal(this);
        }
        if (this.alamat_tujuan != null) {
            this.alamat_tujuan.addPengirimanTujuan(this);
        }
    }

    // Helper to establish reverse link to penerima after construction
    public void linkPenerimaReverse() {
        if (this.penerima != null) {
            this.penerima.addPengiriman(this);
        }
    }

    // Helper to establish reverse link to pembayaran after construction
    public void linkPembayaranReverse() {
        if (this.pembayaran != null) {
            this.pembayaran.setPengiriman(this);
        }
    }
}