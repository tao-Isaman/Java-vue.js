package com.cpe.backend.Discharge.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cpe.backend.Discharge.entity.DistributionType;
import com.cpe.backend.Discharge.repository.DistributionTypeRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DistributionTypeController {

    @Autowired
    private final DistributionTypeRepository distributionTypeRepository;

    public DistributionTypeController(DistributionTypeRepository distributionTypeRepository) {
        this.distributionTypeRepository = distributionTypeRepository;
    }

    @GetMapping("/distributionType")
    public Collection<DistributionType> DistributionType() {
        return distributionTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}