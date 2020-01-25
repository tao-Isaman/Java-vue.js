package com.cpe.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.cpe.backend.Discharge.entity.Sensorium;
import com.cpe.backend.Discharge.entity.Selfcare;
import com.cpe.backend.Discharge.entity.DistributionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

// import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.Discharge.entity.*;
import com.cpe.backend.Discharge.repository.*;
import com.cpe.backend.RegisterPatient.entity.*;
import com.cpe.backend.RegisterPatient.repository.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class DischargeTests {

    private Validator validator;

    @Autowired
    private DischargeRepository dischargeRepository;
    @Autowired
    private SelfcareRepository selfcareRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private SensoriumRepository sensoriumRepository;
    @Autowired
    private DistributionTypeRepository distributionTypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ทดสอบการบันทึก
   @Test
   void B5904815_testDischargeOkWithSameSize(){
       Discharge discharge = new Discharge();
       Sensorium sensorium = sensoriumRepository.findById(1);
       Selfcare selfcare = selfcareRepository.findById(1);
       DistributionType type = distributionTypeRepository.findById(1);
       Date date = new Date();

       //discharge.setId(1L);
       discharge.setNote("aaaaaa");
       discharge.setLeave(date);
       discharge.setSensorium(sensorium);
       discharge.setSelfcare(selfcare);
       discharge.setDistributionType(type);


       discharge = dischargeRepository.save(discharge);

       Optional<Discharge> found = dischargeRepository.findById(discharge.getId());
       //assertEquals(1L, found.get().getPatient());
       assertEquals("aaaaaa", found.get().getNote());
       assertEquals(sensorium, found.get().getSensorium());
       assertEquals(selfcare, found.get().getSelfcare());
       assertEquals(type, found.get().getType());
   }
   //ตั้งชื่อ testให้สอดคล้องกับสิ่งที่ต้อง test
    //ทดสอบว่า note ห้ามเป็น null
    @Test
    void B5904815_testNoteNotNull(){
        Discharge discharge = new Discharge();
        Sensorium sensorium = new Sensorium();
        Selfcare selfcare = new Selfcare();
        DistributionType type = new DistributionType();
        Date date = new Date();

        discharge.setId(1L);
        discharge.setNote(null);
        discharge.setLeave(date);
        discharge.setSensorium(sensorium);
        discharge.setSelfcare(selfcare);
        discharge.setDistributionType(type);


        Set<ConstraintViolation<Discharge>> result = validator.validate(discharge);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discharge> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
    //ทดสอบว่า Note ห้ามน้อยกว่า 5 ตัวอักษร
   @Test
   void B5904815_testNoteMustSizeLessThen5(){
       Discharge discharge = new Discharge();
       Sensorium sensorium = new Sensorium();
       Selfcare selfcare = new Selfcare();
       DistributionType type = new DistributionType();
       Date date = new Date();

        discharge.setId(1L);
        discharge.setNote("aaa");
        discharge.setLeave(date);
        discharge.setSensorium(sensorium);
        discharge.setSelfcare(selfcare);
        discharge.setDistributionType(type);

       Set<ConstraintViolation<Discharge>> result = validator.validate(discharge);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Discharge> v = result.iterator().next();
       assertEquals("size must be between 5 and 30", v.getMessage());
       assertEquals("note", v.getPropertyPath().toString());

   }
   // ทดสอบว่า note ห้ามเกิน 30 ตัวอักษร
   @Test
   void B5904815_testNoteMustSizeMoreThen30(){
       Discharge discharge = new Discharge();
       Sensorium sensorium = new Sensorium();
       Selfcare selfcare = new Selfcare();
       DistributionType type = new DistributionType();
       Date date = new Date();

        discharge.setId(1L);
        discharge.setNote("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        discharge.setLeave(date);
        discharge.setSensorium(sensorium);
        discharge.setSelfcare(selfcare);
        discharge.setDistributionType(type);

       Set<ConstraintViolation<Discharge>> result = validator.validate(discharge);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Discharge> v = result.iterator().next();
       assertEquals("size must be between 5 and 30", v.getMessage());
       assertEquals("note", v.getPropertyPath().toString());
   }
    // ทดสอบว่า sensorium ห้ามเป็น null
    @Test
    void B5904815_testSensoriumNotNull(){
        Discharge discharge = new Discharge();
        Sensorium sensorium = new Sensorium();
        Selfcare selfcare = new Selfcare();
        DistributionType type = new DistributionType();
        Date date = new Date();
 
        discharge.setId(1L);
         discharge.setNote("aaaaaa");
         discharge.setLeave(date);
         discharge.setSensorium(null);
         discharge.setSelfcare(selfcare);
         discharge.setDistributionType(type);
 
        Set<ConstraintViolation<Discharge>> result = validator.validate(discharge);
 
        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
 
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Discharge> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("sensorium", v.getPropertyPath().toString());
 
    }
    // ทดสอบว่า distribution ห้ามเป็น null
   @Test
   void B5904815_testDistributionTypeNotNull(){
       Discharge discharge = new Discharge();
       Sensorium sensorium = new Sensorium();
       Selfcare selfcare = new Selfcare();
       DistributionType type = new DistributionType();
       Date date = new Date();

        discharge.setId(1L);
        discharge.setNote("aaaaaa");
        discharge.setLeave(date);
        discharge.setSensorium(sensorium);
        discharge.setSelfcare(selfcare);
        discharge.setDistributionType(null);

       Set<ConstraintViolation<Discharge>> result = validator.validate(discharge);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Discharge> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("type", v.getPropertyPath().toString());

   }
}