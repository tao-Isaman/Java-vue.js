package com.cpe.backend.Doctororder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

import com.cpe.backend.Doctororder.repository.MedicineItemRepository;
import com.cpe.backend.Doctororder.repository.MedicineRepository;
import com.cpe.backend.Doctororder.entity.Medicine;
import com.cpe.backend.Doctororder.entity.MedicineItem;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class MedicineItemController {

    @Autowired
    private final MedicineItemRepository medicineItemRepository;
    private final MedicineRepository medicineRepository;

    public MedicineItemController(MedicineItemRepository medicineItemRepository,MedicineRepository medicineRepository) {
        this.medicineItemRepository = medicineItemRepository;
        this.medicineRepository = medicineRepository;
    }

    @GetMapping("/medicineItem")
    public Collection<MedicineItem> getMedicineItemAll() {
        return medicineItemRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/medicineItem/{id}")
    public Collection<Object[]> getMedicineItemById(@PathVariable("id") Long id) {

        System.out.println(medicineItemRepository.findByDocterOrder(id));
        Collection<Object[]> med = medicineItemRepository.findByDocterOrder(id);
        System.out.println(med);


       // medicineItemRepository.findByDocterOrder(id);

        return medicineItemRepository.findByDocterOrder(id) ;
    }
}
   
