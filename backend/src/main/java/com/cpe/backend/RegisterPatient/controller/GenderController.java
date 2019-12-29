package com.cpe.backend.RegisterPatient.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpe.backend.RegisterPatient.entity.Gender;
import com.cpe.backend.RegisterPatient.repository.GenderRepository;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class GenderController {
   
   
@Autowired
private final GenderRepository genderRepository;

public GenderController(GenderRepository genderRepository){
this.genderRepository = genderRepository;
}

@GetMapping("/gender")
public Collection<Gender>  gender(){
    return genderRepository.findAll().stream().collect(Collectors.toList());
}

@GetMapping("/gender/{id}")
public Optional<Gender> gender(@PathVariable Long id) {
    Optional<Gender> gender = genderRepository.findById(id);
    return gender;
}



}