package com.example.flashpedition.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flashpedition.entity.Penerima;
import com.example.flashpedition.repository.PenerimaRepository;

import java.util.List;

@Service
public class PenerimaService {
    @Autowired
    private PenerimaRepository penerimaRepository;
    
    public List<Penerima> getAllPenerima() {
        return penerimaRepository.findAll();
    }
    public Penerima addPenerima(Penerima obj){
        Long id = null;
        obj.setId(id);
        return penerimaRepository.save(obj);
    }
    public Penerima getPenerimaById(long id){
        return penerimaRepository.findById(id).orElse(null);
    }
    public Penerima updatePenerima(long id, Penerima obj){
        obj.setId(id);
        return penerimaRepository.save(obj);
    }
    public void deletePenerima(long id){
        penerimaRepository.deleteById(id);
    }
}