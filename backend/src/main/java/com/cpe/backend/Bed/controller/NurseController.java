package com.cpe.backend.Bed.controller;

import com.cpe.backend.Bed.entity.Nurse;
import com.cpe.backend.Bed.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class NurseController {

    @Autowired
    private final NurseRepository nurseRepository;

    public NurseController(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @GetMapping("/nurse")
    public Collection<Nurse> nurses() {
        return nurseRepository.findAll().stream().collect(Collectors.toList());
    }

}