package com.cpe.backend.RegisterPatient.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpe.backend.RegisterPatient.entity.Benefit;
import com.cpe.backend.RegisterPatient.repository.BenefitRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class BenefitController {

    @Autowired
    private final BenefitRepository benefitRepository;

    public BenefitController(BenefitRepository benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    @GetMapping("/benefit")
    public Collection<Benefit> benefits() {
        return benefitRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/benefit/{id}")
    public Optional<Benefit> benefits(@PathVariable Long id) {
        Optional<Benefit> benefit = benefitRepository.findById(id);
        return benefit;
    }



}