package com.example.flashpedition.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flashpedition.entity.Kendaraan;
import com.example.flashpedition.repository.KendaraanRepository;

import java.util.List;

@Service
public class KendaraanService {
    @Autowired
    private KendaraanRepository kendaraanRepository;
    
    public List<Kendaraan> getAllKendaraan() {
        return kendaraanRepository.findAll();
    }
    public Kendaraan addKendaraan(Kendaraan obj){
        Long id = null;
        obj.setId(id);
        return kendaraanRepository.save(obj);
    }
    public Kendaraan getKendaraanById(long id){
        return kendaraanRepository.findById(id).orElse(null);
    }
    public Kendaraan updateKendaraan(long id, Kendaraan obj){
        obj.setId(id);
        return kendaraanRepository.save(obj);
    }
    public void deleteKendaraan(long id){
        kendaraanRepository.deleteById(id);
    }
}