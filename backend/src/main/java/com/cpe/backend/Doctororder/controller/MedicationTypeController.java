package com.cpe.backend.Doctororder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.cpe.backend.Doctororder.repository.MedicationTypeRepository;
import com.cpe.backend.Doctororder.entity.MedicationType;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MedicationTypeController {

    @Autowired
    private final MedicationTypeRepository  medicationTypeRepository;

    public MedicationTypeController(MedicationTypeRepository medicationTypeRepository) {
            this.medicationTypeRepository = medicationTypeRepository;
    }
    
    @GetMapping("/medicationType")
    public Collection<MedicationType> getMedicineTypeTypeAll() {
        return medicationTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/medicationType/{id}/{TypeName}")
    public MedicationType getMedicationById(@PathVariable("id") Long id) {
         return medicationTypeRepository.findById(id).get();
    }
}