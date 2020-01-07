package com.cpe.backend.Discharge.controller;

import com.cpe.backend.Discharge.entity.Selfcare;
import com.cpe.backend.Discharge.repository.SelfcareRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SelfcareController {

    @Autowired
    private final SelfcareRepository selfcareRepository;

    public SelfcareController(SelfcareRepository selfcareRepository) {
        this.selfcareRepository = selfcareRepository;
    }

    @GetMapping("/selfcare")
    public Collection<Selfcare> Selfcare() {
        return selfcareRepository.findAll().stream().collect(Collectors.toList());
    }

}