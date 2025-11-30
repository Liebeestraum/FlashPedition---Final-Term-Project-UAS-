package com.example.flashpedition.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Penerima implements Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    // Variable initialisations
    @NotNull
    @Size(max = 20)
    @Column(name = "id_penerima")
    private String idPenerima;
    // Reverse association: shipments received by this penerima
    @OneToMany (mappedBy = "penerima")
    private List<Pengiriman> pengirimanList;

    // Constructor
    public Penerima(String id) {
        this.idPenerima = id;
        this.pengirimanList = new ArrayList<>();
    }

    public Penerima() {
        this.idPenerima = "";
        this.pengirimanList = new ArrayList<>();
    }

    // Setter and Getter
    public Long setId(Long id) {
        return this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public String setIdPenerima(String id) {
        return this.idPenerima = id;
    }
    public String getIdPenerima() {
        return this.idPenerima;
    }

    // Reverse association API
    public void addPengiriman(Pengiriman p) {
        if (p == null) {
            return;
        }
        if (this.pengirimanList == null) {
            this.pengirimanList = new ArrayList<>();
        }
        boolean exists = false;
        for (Pengiriman it : this.pengirimanList) {
            if (it == p) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            this.pengirimanList.add(p);
        }
    }

    public void removePengiriman(Pengiriman p) {
        if (p == null) {
            return;
        }
        if (this.pengirimanList == null) {
            return;
        }
        this.pengirimanList.remove(p);
    }

    public List<Pengiriman> getPengirimanList() {
        return this.pengirimanList;
    }

    // Account interface implementation
    @Override
    public void sign_up() {
        System.out.println("Penerima sign up: " + idPenerima);
    }

    @Override
    public void sign_in() {
    System.out.println("Penerima sign in: " + idPenerima);
    }
}
