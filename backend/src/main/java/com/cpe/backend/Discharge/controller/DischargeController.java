package com.cpe.backend.Discharge.controller;

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

import com.cpe.backend.RegisterPatient.entity.Patient;
import com.cpe.backend.Discharge.entity.Sensorium;
import com.cpe.backend.Discharge.entity.DistributionType;
import com.cpe.backend.Discharge.entity.Selfcare;
import com.cpe.backend.Discharge.entity.Discharge;
import com.cpe.backend.RegisterPatient.repository.PatientRepository;
import com.cpe.backend.Discharge.repository.SensoriumRepository;
import com.cpe.backend.Discharge.repository.DistributionTypeRepository;
import com.cpe.backend.Discharge.repository.DischargeRepository;
import com.cpe.backend.Discharge.repository.SelfcareRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DischargeController {
    @Autowired
    private final DischargeRepository dischargeRepository;
    @Autowired
    private SelfcareRepository selfcareRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private SensoriumRepository sensoriumRepository;
    @Autowired
    private DistributionTypeRepository distributionTypeRepository;

    public DischargeController(DischargeRepository dischargeRepository) {
        this.dischargeRepository = dischargeRepository;
    }

    @GetMapping("/discharge")
    public Collection<Discharge> Discharge() {
        return dischargeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/adddischarge/{national_id}/{sensorium_id}/{selfcare_id}/{distributionType}/{note}")
    public Discharge newDischarge(
    @PathVariable long national_id,
    @PathVariable long sensorium_id,
    @PathVariable long selfcare_id,
    @PathVariable long distributionType,
    //@PathVariable Date leave,
    @PathVariable String note) {
   
        Discharge dis = new Discharge();

       

    Patient patient  = patientRepository.findById(national_id);
    dis.setPatient(patient);

    Sensorium sensorium = sensoriumRepository.findById(sensorium_id);
    dis.setSensorium(sensorium);

    Selfcare selfcare = selfcareRepository.findById(selfcare_id);
    dis.setSelfcare(selfcare);

    DistributionType type = distributionTypeRepository.findById(distributionType);
    dis.setDistributionType(type);
    
  
    
    dis.setNote(note);
    dis.setLeave(new Date());

    return dischargeRepository.save(dis); 
    
    }
}