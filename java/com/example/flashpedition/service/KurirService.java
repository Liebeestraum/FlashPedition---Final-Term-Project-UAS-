package com.example.flashpedition.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flashpedition.entity.Kurir;
import com.example.flashpedition.repository.KurirRepository;

import java.util.List;

@Service
public class KurirService {
    @Autowired
    private KurirRepository kurirRepository;
    
    public List<Kurir> getAllKurir() {
        return kurirRepository.findAll();
    }
    public Kurir addKurir(Kurir obj){
        Long id = null;
        obj.setId(id);
        return kurirRepository.save(obj);
    }
    public Kurir getKurirById(long id){
        return kurirRepository.findById(id).orElse(null);
    }
    public Kurir updateKurir(long id, Kurir obj){
        obj.setId(id);
        return kurirRepository.save(obj);
    }
    public void deleteKurir(long id){
        kurirRepository.deleteById(id);
    }
}