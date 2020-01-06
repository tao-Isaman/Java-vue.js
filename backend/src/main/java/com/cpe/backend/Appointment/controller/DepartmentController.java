package com.cpe.backend.Appointment.controller;

import com.cpe.backend.Appointment.entity.Department;
import com.cpe.backend.Appointment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DepartmentController {
    @Autowired
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @GetMapping("/department")
    public Collection<Department> departments(){return departmentRepository.findAll().stream().collect(Collectors.toList());}

    @GetMapping("/department/{id}")
    public Optional<Department> Departments(@PathVariable Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department;
    }
}
