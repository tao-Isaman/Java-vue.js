package com.cpe.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.RegisterPatient.entity.Patient;
import com.cpe.backend.RegisterPatient.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class PatientTests {

    private Validator validator;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    //ทดสอบว่า PatientID มี 13 หลักจริงหรือไ่
    @Test
    void testPatientIdOKWith13Digits() {
        Patient patient = new Patient();
        patient.setNationalID(1234567890123L);

        patient = patientRepository.saveAndFlush(patient);

        Optional<Patient> found = patientRepository.findById(patient.getNationalID());
       assertEquals(1234567890123L, found.get().getNationalID());
        
    }
    @Test
    void testPatientIdIsNotNull(){
        Patient patient = new Patient();
        patient.setNationalID(null);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nationalID", v.getPropertyPath().toString());

    }

    @Test
    void testPatientIdIs14Digits(){
        Patient patient = new Patient();
        patient.setNationalID(12345678901234L); //14 digits

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("numeric value out of bounds (<13 digits>.<1 digits> expected)", v.getMessage());
        assertEquals("nationalID", v.getPropertyPath().toString());

    }

    @Test
    void testPatientIdNotLessThen13Digits() {
        Patient patient = new Patient();
        patient.setNationalID(123456789012L); //12 digits

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1000000000000", v.getMessage());
        assertEquals("nationalID", v.getPropertyPath().toString());
        
    }

   

    

}


















