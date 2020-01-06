package com.cpe.backend.Appointment.controller;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import com.cpe.backend.Appointment.entity.TypeGoing;
import com.cpe.backend.Appointment.repository.TypeGoingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TypeGoingController {
    @Autowired
    private final TypeGoingRepository typeGoingRepository;

    public TypeGoingController(TypeGoingRepository typeGoingRepository) {
        this.typeGoingRepository = typeGoingRepository;
    }
    @GetMapping("typegoing")
    public Collection<TypeGoing> typefoing(){return typeGoingRepository.findAll().stream().collect(Collectors.toList());}

    @GetMapping("/typegoing/{id}")
    public Optional<TypeGoing> TypeGoing(@PathVariable Long id) {
        Optional<TypeGoing> typeGoging = typeGoingRepository.findById(id);
        return typeGoging;
    }
}
