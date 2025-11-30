package com.example.flashpedition.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flashpedition.entity.Logistik;
import com.example.flashpedition.repository.LogistikRepository;

import java.util.List;

@Service
public class LogistikService {
    @Autowired
    private LogistikRepository logistikRepository;
    
    public List<Logistik> getAllLogistik() {
        return logistikRepository.findAll();
    }
    public Logistik addLogistik(Logistik obj){
        if (obj == null) {
            return null; // or throw IllegalArgumentException
        }
        // Id is auto-generated; just persist
        return logistikRepository.save(obj);
    }
    public Logistik getLogistikById(long id){
        return logistikRepository.findById(id).orElse(null);
    }
    public Logistik updateLogistik(long id, Logistik obj){
        if (obj == null) {
            return null; // or throw IllegalArgumentException
        }
        Logistik existing = logistikRepository.findById(id).orElse(null);
        if (existing == null) {
            return null; // or throw an exception
        }
        existing.setVolume(obj.getVolume());
        existing.setKurir(obj.getKurir());
        existing.setAlamatAsal(obj.getAlamatAsal());
        existing.setAlamatTujuan(obj.getAlamatTujuan());
        existing.setSupplier(obj.getSupplier());
        existing.setPenerimaObj(obj.getPenerimaObj());
        existing.setResi(obj.getResi());
        return logistikRepository.save(existing);
    }
    public void deleteLogistik(long id){
        logistikRepository.deleteById(id);
    }
}