package com.cpe.backend;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.Optional;
import java.util.Set;
import java.util.Date;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Size;
import com.cpe.backend.Bed.entity.*;
import com.cpe.backend.Appointment.entity.*;
import com.cpe.backend.RegisterPatient.entity.*;

import com.cpe.backend.Bed.repository.*;
import com.cpe.backend.Appointment.repository.*;
import com.cpe.backend.RegisterPatient.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class BedTests {

    private Validator validator;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BedRepository bedRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    //Nurse ต้องไม่เป็นค่า Null
    @Test
    void B5915330_testNurseIsNotNull(){
        Reservation reservation = new Reservation();
        Bed bed = new Bed();
        Department department = new Department();
        java.util.Date reservDate = new java.util.Date(2020-01-21);

        reservation.setNurse(null);
        reservation.setBed(bed);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone("A1");
        reservation.setNote("abcedf");

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Reservation> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nurse", v.getPropertyPath().toString());
    }

    //Bed ต้องไม่เป็นค่า Null
    @Test
    void B5915330_testBedIsNotNull(){
        Reservation reservation = new Reservation();
        Nurse nurse = new Nurse();
        Department department = new Department();
        java.util.Date reservDate = new java.util.Date(2020-01-21);

        reservation.setNurse(nurse);
        reservation.setBed(null);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone("A1");
        reservation.setNote("abcedf");

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Reservation> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bed", v.getPropertyPath().toString());

    }
   
//Department ต้องไม่เป็นค่า Null
    @Test
    void B5915330_testDepartmentIsNotNull(){
        Reservation reservation = new Reservation();
        Nurse nurse = new Nurse();
        Bed bed = new Bed();
        java.util.Date reservDate = new java.util.Date(2020-01-21);

        reservation.setNurse(nurse);
        reservation.setBed(bed);
        reservation.setDepartment(null);
        reservation.setReservDate(reservDate);
        reservation.setZone("A1");
        reservation.setNote("abcedf");

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Reservation> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("department", v.getPropertyPath().toString());

    }
    
    //Note ต้องมีค่าไม่น้อยกว่า 5 ตัวอักษร
    @Test
    void B5915330_testNotesMustNotLessThen5(){
        Reservation reservation = new Reservation();
        Nurse nurse = new Nurse();
        Bed bed = new Bed();
        Department department = new Department();
        java.util.Date reservDate = new java.util.Date(2020-01-21);

        reservation.setNurse(nurse);
        reservation.setBed(bed);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone("A1");
        reservation.setNote("m");

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);
        assertEquals(1, result.size());

        ConstraintViolation<Reservation> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
 
    //Note ต้องมีค่าไม่มากกว่า 240 ตัวอักษร
    @Test
    void B5915330_testNotesMustNotMoreThen240(){
        Reservation reservation = new Reservation();
        Nurse nurse = new Nurse();
        Bed bed = new Bed();
        Department department = new Department();
        java.util.Date reservDate = new java.util.Date(2020-01-21);

        reservation.setNurse(nurse);
        reservation.setBed(bed);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone("A1");
        reservation.setNote("mlolpokijuhygtfrdeskgitjglhop;slsl;dlkoiopoirytueirueiroeirmlolpokijuhygtfrdeskgitjglhop;slsl;dlkoiopoirytueirueiroeirmlolpokijuhygtfrdeskgitjglhop;slsl;dlkoiopoirytueirueiroeirmlolpokijuhygtfrdeskgitjglhop;slsl;dlkoiopoirytueirueiroeirmlolpokijuhygtfrdeskgitjglhop;slsl;dlkoiopoirytueirueiroeir");

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);
        assertEquals(1, result.size());

        ConstraintViolation<Reservation> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
    
//Note ห้าม Null
    @Test
    void B5915330_testNoteMustBeNotNull(){
        Reservation reservation = new Reservation();
        Nurse nurse = new Nurse();
        Bed bed = new Bed();
        Department department = new Department();
        java.util.Date reservDate = new java.util.Date(2020-01-21);

        reservation.setNurse(nurse);
        reservation.setBed(bed);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone("A1");
        reservation.setNote(null);

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);
        assertEquals(1, result.size());

        ConstraintViolation<Reservation> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
    
    //เทสการบันทึก
    @Test
    void B5915330_testReservationitCorrect() {
        Reservation reservation = new Reservation();
        Nurse nurse = nurseRepository.findById(1);
        Bed bed = bedRepository.findById(1);
        Department department = departmentRepository.findById(1);
        java.util.Date reservDate = new java.util.Date(2020-01-21);

        reservation.setNurse(nurse);
        reservation.setBed(bed);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone("A1");
        reservation.setNote("เตียงที่มีขนาดใหญ่พิเศษ");

        reservation =  reservationRepository.save(reservation);

        Optional<Reservation> check = reservationRepository.findById(reservation.getId());
        assertEquals(nurse, check.get().getNurse());
        assertEquals(bed, check.get().getBed());
        assertEquals(department, check.get().getDepartment());
        assertEquals(reservation.getReservDate(), check.get().getReservDate());
        assertEquals("เตียงที่มีขนาดใหญ่พิเศษ", check.get().getNote());
    }
   
    //Zone ต้องตรงตาม Pattern ของตัวอักษร
    @Test
    void B5915330_testZoneFirstCharacterMustNotBeX() {
        Reservation reservation = new Reservation();
        Nurse nurse = new Nurse();
        Bed bed = new Bed();
        Department department = new Department();
        java.util.Date reservDate = new java.util.Date(2020-01-21);
      
        reservation.setNurse(nurse);
        reservation.setBed(bed);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone("X1");
        reservation.setNote("เตียงที่มีขนาดใหญ่พิเศษ");

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);

        assertEquals(1, result.size());
        ConstraintViolation<Reservation> message = result.iterator().next();
        assertEquals("must match \"[ABCD]\\d{1,9}\"",message.getMessage());
        assertEquals("zone",message.getPropertyPath().toString());
    }

    //Zone ต้องตรงตาม Pattern ของตัวเลข
    @Test
    void B5915330_testZoneNumberMustBeOneDigit() {
        Reservation reservation = new Reservation();
        Nurse nurse = new Nurse();
        Bed bed = new Bed();
        Department department = new Department();
        java.util.Date reservDate = new java.util.Date(2020-01-21);
      
        reservation.setNurse(nurse);
        reservation.setBed(bed);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone("A11");
        reservation.setNote("เตียงที่มีขนาดใหญ่พิเศษ");

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);

        assertEquals(1, result.size());
        ConstraintViolation<Reservation> message = result.iterator().next();
        assertEquals("size must be between 0 and 2",message.getMessage());
        assertEquals("zone",message.getPropertyPath().toString());
    }
 
    //zone ห้าม Null
    @Test
    void B5915330_testZoneMustBeNotNull(){
        Reservation reservation = new Reservation();
        Nurse nurse = new Nurse();
        Bed bed = new Bed();
        Department department = new Department();
        java.util.Date reservDate = new java.util.Date(2020-01-21);

        reservation.setNurse(nurse);
        reservation.setBed(bed);
        reservation.setDepartment(department);
        reservation.setReservDate(reservDate);
        reservation.setZone(null);
        reservation.setNote("เตียงขนาดพิเศษ");

        Set<ConstraintViolation<Reservation>> result = validator.validate(reservation);
        assertEquals(1, result.size());

        ConstraintViolation<Reservation> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("zone", v.getPropertyPath().toString());
    }
       
}