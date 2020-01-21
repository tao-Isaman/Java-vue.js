package com.cpe.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;
import java.util.Date;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.Examination.entity.ExaminationSystem;
import com.cpe.backend.Examination.repository.ExaminationRepository;
import com.cpe.backend.Examination.entity.Doctor;
import com.cpe.backend.Examination.repository.DoctorRepository;
import com.cpe.backend.Examination.entity.Duration;
import com.cpe.backend.Examination.repository.DurationRepository;
import com.cpe.backend.Examination.entity.State;
import com.cpe.backend.Examination.repository.StateRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class ExaminationTests {

    private Validator validator;

    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DurationRepository durationRepository;
    @Autowired
    private StateRepository stateRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test

    @Test
    void B5915064_testPulseMustNotBeNull(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPressure("90 120");
        e1.setSymptom("Something");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("pulse", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testPulseMustLessThen1(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(0);
        e1.setPressure("90 120");
        e1.setSymptom("Something");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("pulse", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testPulseMustMoreThen200(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(201);
        e1.setPressure("90 120");
        e1.setSymptom("Something");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("pulse", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testPressureMustNotBeNull(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setSymptom("Something");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);


        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 2 and 7", v.getMessage());
        assertEquals("pressure", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testPressureMustLessthen2(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setPressure("9");
        e1.setSymptom("Something");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);


        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 2 and 7", v.getMessage());
        assertEquals("pressure", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testPressureMustLessthen7(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setPressure("100 1200");
        e1.setSymptom("Something");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);


        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 2 and 7", v.getMessage());
        assertEquals("pressure", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testSymptomMustNotBeNull(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setPressure("90 120");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);


        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 5 and 250", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testSymptomMustSizeLessThen5(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setPressure("90 120");
        e1.setSymptom("ffff");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);


        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 5 and 250", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testSymptomMustSizeMoreThen250(){
        Doctor d1 = this.doctorRepository.findById(1L);
        Duration dt1 = this.durationRepository.findById(1L);
        State s1 = this.stateRepository.findById(1L);
        Date date = new Date();
        ExaminationSystem e1 = new ExaminationSystem();
        e1.setPulse(90);
        e1.setPressure("90 120");
        e1.setDuration(dt1);
        e1.setState(s1);
        e1.setCheckDate(date);
        e1.setCheckBy(d1);

        String symptom = "" ;
        int i = 0;
        while(i<251){
            symptom += "a";
            i++;
        }
        e1.setSymptom(symptom);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(e1);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 5 and 250", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }



}