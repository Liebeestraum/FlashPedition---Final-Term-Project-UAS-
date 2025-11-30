package com.example.flashpedition.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flashpedition.entity.Pembayaran;
import com.example.flashpedition.repository.PembayaranRepository;

import java.util.List;

@Service
public class PembayaranService {
    @Autowired
    private PembayaranRepository pembayaranRepository;
    
    public List<Pembayaran> getAllPembayaran() {
        return pembayaranRepository.findAll();
    }
    public Pembayaran addPembayaran(Pembayaran obj){
        Long id = null;
        obj.setId(id);
        return pembayaranRepository.save(obj);
    }
    public Pembayaran getPembayaranById(long id){
        return pembayaranRepository.findById(id).orElse(null);
    }
    public Pembayaran updatePembayaran(long id, Pembayaran obj){
        obj.setId(id);
        return pembayaranRepository.save(obj);
    }
    public void deletePembayaran(long id){
        pembayaranRepository.deleteById(id);
    }
}