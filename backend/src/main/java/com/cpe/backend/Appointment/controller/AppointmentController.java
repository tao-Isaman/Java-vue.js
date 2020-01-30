package com.cpe.backend.Appointment.controller;

import com.cpe.backend.Appointment.entity.*;
import com.cpe.backend.Appointment.repository.*;
import com.cpe.backend.RegisterPatient.entity.Patient;
import com.cpe.backend.RegisterPatient.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AppointmentController {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private TypeCauseRepository typeCauseRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TypeGoingRepository typeGoingRepository;
    @Autowired
    private PatientRepository patientRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    @GetMapping("/appointment")
    public Collection<Appointment> Appointment() {
        return appointmentRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/appointment/{patient_id}/{typecause_id}/{typegoing_id}/{department_id}")
    public Appointment newAppointment(@RequestBody Appointment newAppointment,

                                      @PathVariable long typecause_id,
                                      @PathVariable long department_id,
                                      @PathVariable long patient_id,
                                      @PathVariable long typegoing_id) {

        TypeCause creatTypeCause = typeCauseRepository.findById(typecause_id);
        Department creatDepartment = departmentRepository.findById(department_id);
        TypeGoing creatTypeGoing = typeGoingRepository.findById(typegoing_id);
        Patient creatPatient = patientRepository.findById(patient_id);


        newAppointment.setCreatTypeCause(creatTypeCause);
        newAppointment.setCreatDepartment(creatDepartment);
        newAppointment.setCreatTypeGoing(creatTypeGoing);
        newAppointment.setCreatPatient(creatPatient);

        return appointmentRepository.save(newAppointment);
    }
}
