package com.cpe.backend.Payment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpe.backend.Payment.entity.TypeBank;
import com.cpe.backend.Payment.repository.*;


import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
public class TypeBankController {
   
   
@Autowired
private final TypeBankRepository typeBankRepository;

public TypeBankController(TypeBankRepository typeBankRepository){
this.typeBankRepository = typeBankRepository;
}

@GetMapping("/typeBank")
public Collection<TypeBank>typeBank(){
    return typeBankRepository.findAll().stream().collect(Collectors.toList());
}

@GetMapping("/typeBank/{id}")
public Optional<TypeBank> typeBank(@PathVariable Long id) {
    Optional<TypeBank> typeBank = typeBankRepository.findById(id);
    return typeBank;
}


}