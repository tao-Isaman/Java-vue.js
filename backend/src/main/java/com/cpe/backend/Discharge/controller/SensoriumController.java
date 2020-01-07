package com.cpe.backend.Discharge.controller;

import com.cpe.backend.Discharge.entity.Sensorium;
import com.cpe.backend.Discharge.repository.SensoriumRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SensoriumController {

    @Autowired
    private final SensoriumRepository sensoriumRepository;

    public SensoriumController(SensoriumRepository sensoriumRepository) {
        this.sensoriumRepository = sensoriumRepository;
    }
///////////////////////////////////////////////
    @GetMapping("/sensorium")
    public Collection<Sensorium> Sensorium() {
        return sensoriumRepository.findAll().stream().collect(Collectors.toList());
    }

}