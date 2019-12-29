package com.cpe.backend.RegisterPatient.controller;

import com.cpe.backend.RegisterPatient.entity.Benefit;
import com.cpe.backend.RegisterPatient.entity.Gender;
import com.cpe.backend.RegisterPatient.entity.Patient;
import com.cpe.backend.RegisterPatient.entity.Province;
import com.cpe.backend.RegisterPatient.repository.BenefitRepository;
import com.cpe.backend.RegisterPatient.repository.GenderRepository;
import com.cpe.backend.RegisterPatient.repository.PatientRepository;
import com.cpe.backend.RegisterPatient.repository.ProvinceRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import java.net.URLDecoder;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PatientController {
    @Autowired
    private final PatientRepository patientRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private ProvinceRepository provincerRepository;
    @Autowired
    private BenefitRepository benefitRepository;

    PatientController (PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient")
    public Collection<Patient> Patients(){
        
        return patientRepository.findAll().stream().collect(Collectors.toList());

        
    }

    @GetMapping("/patient/{id}")
    public Optional<Patient> Patients(@PathVariable Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient;
    }
    

    @RequestMapping(value = "/patientPost",method = RequestMethod.POST)
    public Patient newPatient(@RequestBody Map<String, String> body) {
        Patient newPatient = new Patient();
        Province Province = provincerRepository.findById(Long.valueOf(body.get("province")).longValue());
        Benefit Benefit = benefitRepository.findById(Long.valueOf(body.get("benefit")).longValue());
        Gender Gender = genderRepository.findById(Long.valueOf(body.get("gender")).longValue());
           newPatient.setNationalID(Long.valueOf(body.get("nationalID").toString()));
           newPatient.setName(body.get("name"));
           newPatient.setAddress(body.get("address"));
           newPatient.setInitialSsym(body.get("initialSsym"));
           newPatient.setHight(Integer.valueOf(body.get("hight").toString()));
           newPatient.setWeigth(Integer.valueOf(body.get("weigth").toString()));
           newPatient.setProvince(Province);
           newPatient.setBenefit(Benefit);
           newPatient.setGender(Gender);
          System.out.println(Province);
          System.out.println(Benefit);
          System.out.println(Gender);
          System.out.println(newPatient);



        
        return patientRepository.save(newPatient) ;
    }
}