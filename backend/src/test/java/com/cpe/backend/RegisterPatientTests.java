package com.cpe.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;
//s
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.RegisterPatient.entity.Patient;
import com.cpe.backend.RegisterPatient.repository.PatientRepository;
import com.cpe.backend.RegisterPatient.entity.Benefit;
import com.cpe.backend.RegisterPatient.entity.Province;
import com.cpe.backend.RegisterPatient.entity.Gender;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class RegisterPatientTests {

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
    void B5907519_testPatientIdOKWith13Digits() {
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L);
        patient.setName("isaman");
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        patient = patientRepository.saveAndFlush(patient);

       Optional<Patient> found = patientRepository.findById(patient.getNationalID());
       assertEquals(1234567890123L, found.get().getNationalID());
        
    }

    //ทดสอบว่า NationalID ห้ามเป็น not null
    @Test
    void B5907519_testPatientIdIsNotNull(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(null);
        patient.setName("isaman");
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nationalID", v.getPropertyPath().toString());

    }

    //ทดสอบว่า NationalID ห้ามเป็น 14 หลัก
    @Test
    void B5907519_testPatientIdIs14Digits(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(12345678901234L); //14 digits
        patient.setName("isaman");
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("numeric value out of bounds (<13 digits>.<1 digits> expected)", v.getMessage());
        assertEquals("nationalID", v.getPropertyPath().toString());

    }

    //ทดสอบว่า NationalID ห้ามเป็น น้อยกว่า 13 หลัก
    @Test
    void B5907519_testPatientIdNotLessThen13Digits() {
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(123456789012L); //12 digits
        patient.setName("isaman");
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);
        Set<ConstraintViolation<Patient>> result = validator.validate(patient);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1000000000000", v.getMessage());
        assertEquals("nationalID", v.getPropertyPath().toString());
        
    }

    //ทดสอบว่า PataientNane ห้ามเป็น not null
    @Test
    void B5907519_testPataientNanemMustNotBeNull(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    //ทดสอบว่า PataientNane ห้ามน้อยกว่า 5 ตัวอักษร
    @Test
    void B5907519_testPataientNanemMustSizeLessThen5(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("abcd");
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    //ทดสอบว่า PataientNane ต้องไม่เกิน 30 ตัวอักษร
    @Test
    void B5907519_testPataientNanemMustSizeMoreThen30(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("aaaaabbbbbcccccdddddeeeeefffffg");
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    //ทดสอบว่า PataientAddres ห้ามเป็น not null
    @Test
    void B5907519_testPataientAddresMustNotBeNull(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("Address", v.getPropertyPath().toString());
    }

    //ทดสอบว่า PataientAddres ห้ามน้อยกว่า 5 ตัวอักษร
    @Test
    void B5907519_testPataientAddresMustLessThen5(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcd");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("Address", v.getPropertyPath().toString());
    }

    //ทดสอบว่า PataientAddres ห้ามน้มากว่า 240 ตัวอักษร
    @Test
    void B5907519_testPataientAddresMustMoreThen240(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setInitialSsym("initialSsym");
        patient.setWeigth(50);
        patient.setHight(80);

        //วนลูปสร้าง String ความยาว 241 ตัวอักษร
        String addres = "" ;
        int i = 0;
        while(i<241){
            addres += "a";
            i++;
        }
        patient.setAddress(addres);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("Address", v.getPropertyPath().toString());
    }

    //ทดสอบว่า PataientInitialSsy ห้ามเป็น not null
    @Test
    void B5907519_testPataientInitialSsymMustNotBenull(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcdef");
        patient.setWeigth(50);
        patient.setHight(80);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("initialSsym", v.getPropertyPath().toString());
    }

    //ทดสอบว่า PataientInitialSsy ห้ามน้อยกว่า 5 ตัวอักษร
    @Test
    void B5907519_testPataientInitialSsymMustLessthen5(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setInitialSsym("abcd");
        patient.setAddress("abcdef");
        patient.setWeigth(50);
        patient.setHight(80);

        

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("initialSsym", v.getPropertyPath().toString());
    }

//ทดสอบว่า PataientInitialSsy ห้ามน้อยเกิน 240 ตัวอักษร

    @Test
    void B5907519_testPataientInitialSsymMustMorethen240(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcdef");
        patient.setWeigth(50);
        patient.setHight(80);

        //วนลูปสร้าง String 241 ตัวอักษร
        String initialSsym = "" ;
        int i = 0;
        while(i<241){
            initialSsym += "a";
            i++;
        }
        patient.setInitialSsym(initialSsym);

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("initialSsym", v.getPropertyPath().toString());
    }

    @Test
    void B5907519_testPataientWeigthMustNotBeNull(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcdef");
        patient.setHight(80);
        patient.setInitialSsym("initialSsym");

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("weigth", v.getPropertyPath().toString());
    }

    @Test
    void B5907519_testPataientWeigthMustLessThen1(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcdef");
        patient.setHight(80);
        patient.setWeigth(0);
        patient.setInitialSsym("initialSsym");

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("weigth", v.getPropertyPath().toString());
    }

    @Test
    void B5907519_testPataientWeigthMustMoreThen300(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcdef");
        patient.setHight(80);
        patient.setWeigth(301);
        patient.setInitialSsym("initialSsym");

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must be less than or equal to 300", v.getMessage());
        assertEquals("weigth", v.getPropertyPath().toString());
    }

    @Test
    void B5907519_testPataientHighthMustNotBeNull(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcdef");
        patient.setWeigth(80);
        patient.setInitialSsym("initialSsym");

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 30", v.getMessage());
        assertEquals("hight", v.getPropertyPath().toString());
    }

    @Test
    void B5907519_testPataientHighthMustLessThen30(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcdef");
        patient.setWeigth(80);
        patient.setHight(29);
        patient.setInitialSsym("initialSsym");

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 30", v.getMessage());
        assertEquals("hight", v.getPropertyPath().toString());
    }

    @Test
    void B5907519_testPataientHighthMustMoreThen300(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setName("isaman");
        patient.setAddress("abcdef");
        patient.setWeigth(80);
        patient.setHight(301);
        patient.setInitialSsym("initialSsym");

        Set<ConstraintViolation<Patient>> result = validator.validate(patient);
        assertEquals(1, result.size());

        ConstraintViolation<Patient> v = result.iterator().next();
        assertEquals("must be less than or equal to 300", v.getMessage());
        assertEquals("hight", v.getPropertyPath().toString());
    }

    @Test
    void B5907519_testPataientNameOkWithSameSize(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setName("isaman");
        patient.setWeigth(50);
        patient.setHight(80);

        patient = patientRepository.saveAndFlush(patient);

       Optional<Patient> found = patientRepository.findById(patient.getNationalID());
       assertEquals("isaman", found.get().getName());
    }

    @Test
    void B5907519_testPataientAddressOkWithSameSize(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setName("isaman");
        patient.setWeigth(50);
        patient.setHight(80);

        patient = patientRepository.saveAndFlush(patient);

       Optional<Patient> found = patientRepository.findById(patient.getNationalID());
       assertEquals("Address", found.get().getAddress());
    }

    @Test
    void B5907519_testPataientInitialSsymOkWithSameSize(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setName("isaman");
        patient.setWeigth(50);
        patient.setHight(80);

        patient = patientRepository.saveAndFlush(patient);

       Optional<Patient> found = patientRepository.findById(patient.getNationalID());
       assertEquals("initialSsym", found.get().getInitialSsym());
    }

    @Test
    void B5907519_testPataientWeigthOkWithSameSize(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setName("isaman");
        patient.setWeigth(50);
        patient.setHight(80);

        patient = patientRepository.saveAndFlush(patient);

       Optional<Patient> found = patientRepository.findById(patient.getNationalID());
       assertEquals(50, found.get().getWeigth());
    }

        
    @Test
    void B5907519_testPataientHightOkWithSameSize(){
        Benefit benefit = new Benefit();
        benefit.setId(1L);
        Province province = new Province();
        province.setId(1L);
        Gender gender = new Gender();
        gender.setId(1L);
        Patient patient = new Patient();
        patient.setBenefit(benefit);
        patient.setProvince(province);
        patient.setGender(gender);
        patient.setNationalID(1234567890123L); //13 digits
        patient.setAddress("Address");
        patient.setInitialSsym("initialSsym");
        patient.setName("isaman");
        patient.setWeigth(50);
        patient.setHight(80);

        patient = patientRepository.saveAndFlush(patient);

       Optional<Patient> found = patientRepository.findById(patient.getNationalID());
       assertEquals(80, found.get().getHight());
    }
   
   

    

}


















