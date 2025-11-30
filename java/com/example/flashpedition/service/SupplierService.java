package com.example.flashpedition.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flashpedition.entity.Supplier;
import com.example.flashpedition.repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }
    public Supplier addSupplier(Supplier obj){
        Long id = null;
        obj.setId(id);
        return supplierRepository.save(obj);
    }
    public Supplier getSupplierById(long id){
        return supplierRepository.findById(id).orElse(null);
    }
    public Supplier updateSupplier(long id, Supplier obj){
        obj.setId(id);
        return supplierRepository.save(obj);
    }
    public void deleteSupplier(long id){
        supplierRepository.deleteById(id);
    }
}