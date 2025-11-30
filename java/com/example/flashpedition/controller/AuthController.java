package com.example.flashpedition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flashpedition.entity.Person;
import com.example.flashpedition.entity.Supplier;
import com.example.flashpedition.repository.PersonRepository;
import com.example.flashpedition.repository.SupplierRepository;

@Controller
public class AuthController {

    private final PersonRepository personRepository;
    private final SupplierRepository supplierRepository;

    public AuthController(PersonRepository personRepository, SupplierRepository supplierRepository) {
        this.personRepository = personRepository;
        this.supplierRepository = supplierRepository;
    }

    // Buyer registration: maps register_buyer.html to Person entity
    @PostMapping("/register/buyer")
    public String registerBuyer(@RequestParam String namaDepan,
                                @RequestParam(required = false) String namaTengah,
                                @RequestParam String namaBelakang,
                                @RequestParam String tempatLahir,
                                @RequestParam String tanggalLahir,
                                @RequestParam String jenisKelamin,
                                @RequestParam String nomorTelepon,
                                @RequestParam String email) {

        Person person = new Person();
        person.setNamaDepan(namaDepan);
        person.setNamaTengah(namaTengah != null ? namaTengah : "");
        person.setNamaBelakang(namaBelakang);
        person.setTempatLahir(tempatLahir);
        person.setTanggalLahir(tanggalLahir);
        person.setJenisKelamin(jenisKelamin);
        person.setNomorTelepon(nomorTelepon);
        person.setEmail(email);

        personRepository.save(person);
        return "redirect:/login/buyer";
    }

    // Simple buyer login by email only
    @PostMapping("/auth/buyer")
    public String authBuyer(@RequestParam String email, Model model) {
        return personRepository.findByEmail(email)
                .map(p -> "redirect:/menu_buyer")
                .orElseGet(() -> {
                    model.addAttribute("loginError", true);
                    return "login_buyer";
                });
    }

    // Seller/Supplier registration: map forms to Supplier entity / supplier table
    @PostMapping("/register/seller")
    public String registerSeller(@RequestParam String idSupplier,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String jenisPengiriman) {

        Supplier supplier = new Supplier();
        supplier.setIdSupplier(idSupplier);
        supplier.setUsername(username);
        supplier.setPassword(password);
        supplier.setJenisPengiriman(jenisPengiriman);

        supplierRepository.save(supplier);
        return "redirect:/login/seller";
    }

    @PostMapping("/register/supplier")
    public String registerSupplier(@RequestParam String idSupplier,
                                   @RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String jenisPengiriman) {

        Supplier supplier = new Supplier();
        supplier.setIdSupplier(idSupplier);
        supplier.setUsername(username);
        supplier.setPassword(password);
        supplier.setJenisPengiriman(jenisPengiriman);

        supplierRepository.save(supplier);
        return "redirect:/login/supplier";
    }

    // Simple login checks for seller/supplier against supplier table
    @PostMapping("/auth/seller")
    public String authSeller(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        return supplierRepository.findByUsernameAndPassword(username, password)
                .map(s -> "redirect:/menu_seller")
                .orElseGet(() -> {
                    model.addAttribute("loginError", true);
                    return "login_seller";
                });
    }

    @PostMapping("/auth/supplier")
    public String authSupplier(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        return supplierRepository.findByUsernameAndPassword(username, password)
                .map(s -> "redirect:/menu_supplier")
                .orElseGet(() -> {
                    model.addAttribute("loginError", true);
                    return "login_supplier";
                });
    }
}
