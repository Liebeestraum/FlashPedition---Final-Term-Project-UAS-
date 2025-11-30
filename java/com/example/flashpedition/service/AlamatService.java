package com.example.flashpedition.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flashpedition.entity.Alamat;
import com.example.flashpedition.repository.AlamatRepository;

import java.util.List;

@Service
public class AlamatService {
    @Autowired
    private AlamatRepository alamatRepository;
    
    public List<Alamat> getAllAlamat() {
        return alamatRepository.findAll();
    }
    public Alamat addAlamat(Alamat obj){
        Long id = null;
        obj.setId(id);
        return alamatRepository.save(obj);
    }
    public Alamat getAlamatById(long id){
        return alamatRepository.findById(id).orElse(null);
    }
    public Alamat updateAlamat(long id, Alamat obj){
        obj.setId(id);
        return alamatRepository.save(obj);
    }
    public void deleteAlamat(long id){
        alamatRepository.deleteById(id);
    }
}