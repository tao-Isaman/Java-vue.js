package com.cpe.backend.Examination.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.cpe.backend.Examination.entity.ExaminationSystem;
import com.cpe.backend.Examination.entity.Doctor;
import com.cpe.backend.Examination.entity.Duration;
import com.cpe.backend.RegisterPatient.entity.Patient;
import com.cpe.backend.Examination.entity.State;
import com.cpe.backend.Examination.repository.ExaminationRepository;
import com.cpe.backend.Examination.repository.DoctorRepository;
import com.cpe.backend.Examination.repository.DurationRepository;
import com.cpe.backend.RegisterPatient.repository.PatientRepository;
import com.cpe.backend.Examination.repository.StateRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ExaminationController {
    @Autowired
    private final ExaminationRepository examinationRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DurationRepository durationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private StateRepository stateRepository;

    ExaminationController(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    @GetMapping("/examinationSystem")
    public Collection<ExaminationSystem> ExaminationSystems() {
        return examinationRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/examinationSystem/{patient_id}/{pulse}/{pressure}/{symptom}/{duration_id}/{state_id}/{doctor_id}")
    public ExaminationSystem newExaminationSystem(ExaminationSystem newExaminationSystem,
                                      @PathVariable long patient_id,
                                      @PathVariable long duration_id,
                                      @PathVariable long state_id,
                                      @PathVariable long doctor_id,
                                      @PathVariable int pulse,
                                      @PathVariable String pressure,
                                      @PathVariable String symptom,
                                      @RequestBody Map<String, String> body) {

        Patient patient = patientRepository.findById(patient_id);
        Duration duration = durationRepository.findById(duration_id);
        State state = stateRepository.findById(state_id);
        Doctor checkBy = doctorRepository.findById(doctor_id);

        newExaminationSystem.setPatient(patient);
        newExaminationSystem.setPulse(Integer.valueOf(body.get("pulse").toString()));
        newExaminationSystem.setPressure(body.get("pressure"));
        newExaminationSystem.setSymptom(body.get("symptom"));
        newExaminationSystem.setDuration(duration);
        newExaminationSystem.setState(state);
        newExaminationSystem.setCheckDate(body.get("checkDate"));
        newExaminationSystem.setCheckBy(checkBy);

        return examinationRepository.save(newExaminationSystem);

    }
}