package com.cpe.backend.Examination.controller;

import com.cpe.backend.Examination.entity.State;
import com.cpe.backend.Examination.repository.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class StateController {

    @Autowired
    private final StateRepository stateRepository;

    public StateController(StateRepository stateRepository) {

        this.stateRepository = stateRepository;
    }

    @GetMapping("/state")
    public Collection<State> States() {
        return stateRepository.findAll().stream().collect(Collectors.toList());
    }

}