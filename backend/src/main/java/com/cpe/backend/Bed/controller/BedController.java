package com.cpe.backend.Bed.controller;

import com.cpe.backend.Bed.entity.Bed;
import com.cpe.backend.Bed.repository.BedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BedController {

    @Autowired
    private final BedRepository BedRepository;

    public BedController(BedRepository BedRepository) {
        this.BedRepository = BedRepository;
    }

    @GetMapping("/bed")
    public Collection<Bed> beds() {
        return BedRepository.findAll().stream().collect(Collectors.toList());
    }

}