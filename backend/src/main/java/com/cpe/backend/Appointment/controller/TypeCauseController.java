package com.cpe.backend.Appointment.controller;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import com.cpe.backend.Appointment.entity.TypeCause;
import com.cpe.backend.Appointment.repository.TypeCauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TypeCauseController {
    @Autowired
    private final TypeCauseRepository typeCauseRepository;

    public TypeCauseController(TypeCauseRepository typeCauseRepository) {
        this.typeCauseRepository = typeCauseRepository;
    }
    @GetMapping("/typecause")
    public Collection<TypeCause> TypeCause() {
        return typeCauseRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/typecause/{id}")
    public Optional<TypeCause> TypeCause(@PathVariable Long id) {
        Optional<TypeCause> typeCause = typeCauseRepository.findById(id);
        return typeCause;
    }
}
