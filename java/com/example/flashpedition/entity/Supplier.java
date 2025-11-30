package com.example.flashpedition.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Supplier extends Person implements Account {
    // Variable initialisations
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size (max = 30)
    private String id_supplier;
    @NotNull
    private String username;
    @NotNull
    @Size (min = 8)
    private String password;

    private String jenis_pengiriman;
    // Association: one Supplier has many Pengiriman
    @OneToMany (mappedBy = "supplier")
    private final java.util.List<Pengiriman> pengirimanList = new java.util.ArrayList<>();

    // Constructor
    public Supplier(String id, String u, String p, String jp) {
        this.id_supplier = id;
        this.username = u;
        this.password = p;
        this.jenis_pengiriman = jp;
    }

    public Supplier() {
        this.id_supplier = "";
        this.username = "";
        this.password = "";
        this.jenis_pengiriman = "";
    }

    // Setter and Getter
    @Override
    public Long setId(Long id) {
        return this.id = id;
    }
    @Override
    public Long getId() {
        return this.id;
    }

    public String setIdSupplier(String id) {
        return this.id_supplier = id;
    }
    public String getIdSupplier() {
        return this.id_supplier;
    }

    public String setUsername(String u) {
        return this.username = u;
    }
    public String getUsername() {
        return this.username;
    }

    public String setPassword(String p) {
        return this.password = p;
    }
    public String getPassword() {
        return this.password;
    }

    public String setJenisPengiriman(String jp) {
        return this.jenis_pengiriman = jp;
    }
    public String getJenisPengiriman() {
        return this.jenis_pengiriman;
    }

    // Association management methods
    public void addPengiriman(Pengiriman p) {
        if (p != null) {
            this.pengirimanList.add(p);
        }
    }

    public boolean removePengiriman(Pengiriman p) {
        return this.pengirimanList.remove(p);
    }

    public java.util.List<Pengiriman> getPengirimanList() {
        return java.util.Collections.unmodifiableList(this.pengirimanList);
    }

    // Account interface implementation
    @Override
    public void sign_up() {
        // For now, just a placeholder implementation
        System.out.println("Supplier sign up: " + username);
    }

    @Override
    public void sign_in() {
        System.out.println("Supplier sign in: " + username);
    }
}
