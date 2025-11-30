package com.example.flashpedition.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.flashpedition.entity.PermintaanRestock;
import com.example.flashpedition.repository.PermintaanRestockRepository;

@Service
public class PermintaanRestockService {
    private final PermintaanRestockRepository permintaanRestockRepository;

    public PermintaanRestockService(PermintaanRestockRepository permintaanRestockRepository) {
        this.permintaanRestockRepository = permintaanRestockRepository;
    }

    public List<PermintaanRestock> getAll() {
        return permintaanRestockRepository.findAll();
    }

    public @org.springframework.lang.NonNull PermintaanRestock save(@org.springframework.lang.NonNull PermintaanRestock pr) {
        return permintaanRestockRepository.save(pr);
    }
}
