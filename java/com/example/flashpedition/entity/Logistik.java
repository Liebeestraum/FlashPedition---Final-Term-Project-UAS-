package com.example.flashpedition.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
public class Logistik extends Pengiriman {

    @NotNull
    private double volume;

    // Parameterized constructor (matches UML: includes volume plus inherited shipment data, no pembayaran)
    public Logistik(String resi,
                    Supplier supplier,
                    Penerima penerima,
                    String kurir,
                    Alamat alamatAsal,
                    Alamat alamatTujuan,
                    double volume) {
        super(resi, supplier, penerima, kurir, alamatAsal, alamatTujuan);
        this.volume = volume;
    }

    // Default constructor
    public Logistik() {
        super();
        this.volume = 0.0;
    }

    // Setter (void per UML) and Getter for volume
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public double getVolume() {
        return this.volume;
    }

    // Implement abstract methods from Pengiriman using volume as proxy for weight & size
    @Override
    public double berat() {
        return volume;
    }

    @Override
    public double ukuran() {
        return volume;
    }

    @Override
    public String status() {
        // Simple status string; adjust later if a lifecycle is introduced
        return "LOGISTIK";
    }
}
