package com.example.flashpedition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flashpedition.entity.Pengiriman;
import com.example.flashpedition.entity.Paket;
import com.example.flashpedition.entity.Logistik;
import com.example.flashpedition.service.PengirimanService;
import com.example.flashpedition.service.PaketService;
import com.example.flashpedition.service.LogistikService;

@RestController
@RequestMapping("/api")
public class PengirimanController {

    @Autowired
    private PengirimanService pengirimanService;

    @Autowired
    private PaketService paketService;

    @Autowired
    private LogistikService logistikService;

    // -------------------- Pengiriman (abstract, read-only endpoints) --------------------
    @GetMapping("/pengiriman")
    public List<Pengiriman> listPengiriman() {
        return pengirimanService.getAllPengiriman();
    }

    @GetMapping("/pengiriman/{id}")
    public ResponseEntity<Pengiriman> getPengiriman(@PathVariable("id") long id) {
        Pengiriman result = pengirimanService.getPengirimanById(id);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    // -------------------- Paket --------------------
    @GetMapping("/paket")
    public List<Paket> listPaket() {
        return paketService.getAllPaket();
    }

    @GetMapping("/paket/{id}")
    public ResponseEntity<Paket> getPaket(@PathVariable("id") long id) {
        Paket result = paketService.getPaketById(id);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @PostMapping("/paket")
    public ResponseEntity<Paket> createPaket(@RequestBody Paket paket) {
        Paket saved = paketService.addPaket(paket);
        return saved == null ? ResponseEntity.badRequest().build() : ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/paket/{id}")
    public ResponseEntity<Paket> updatePaket(@PathVariable("id") long id, @RequestBody Paket paket) {
        Paket updated = paketService.updatePaket(id, paket);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/paket/{id}")
    public ResponseEntity<Void> deletePaket(@PathVariable("id") long id) {
        Paket existing = paketService.getPaketById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        paketService.deletePaket(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------- Logistik --------------------
    @GetMapping("/logistik")
    public List<Logistik> listLogistik() {
        return logistikService.getAllLogistik();
    }

    @GetMapping("/logistik/{id}")
    public ResponseEntity<Logistik> getLogistik(@PathVariable("id") long id) {
        Logistik result = logistikService.getLogistikById(id);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    @PostMapping("/logistik")
    public ResponseEntity<Logistik> createLogistik(@RequestBody Logistik logistik) {
        Logistik saved = logistikService.addLogistik(logistik);
        return saved == null ? ResponseEntity.badRequest().build() : ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/logistik/{id}")
    public ResponseEntity<Logistik> updateLogistik(@PathVariable("id") long id, @RequestBody Logistik logistik) {
        Logistik updated = logistikService.updateLogistik(id, logistik);
        return updated == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/logistik/{id}")
    public ResponseEntity<Void> deleteLogistik(@PathVariable("id") long id) {
        Logistik existing = logistikService.getLogistikById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        logistikService.deleteLogistik(id);
        return ResponseEntity.noContent().build();
    }
}
