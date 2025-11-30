package com.example.flashpedition.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.flashpedition.entity.Barang;
import com.example.flashpedition.repository.BarangRepository;

@Service
public class BarangService {
    @Autowired
    private BarangRepository barangRepository;

    public List<Barang> getAllBarang() {
        return barangRepository.findAll();
    }
}
