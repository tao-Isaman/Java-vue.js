package com.cpe.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.cpe.backend.Appointment.entity.Department;
import com.cpe.backend.Appointment.entity.TypeCause;
import com.cpe.backend.Appointment.entity.TypeGoing;
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

import com.cpe.backend.Appointment.entity.Appointment;
import com.cpe.backend.Appointment.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class AppointmentTests {

    private Validator validator;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5915101_testCreatAdditionalNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(123456L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);




        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("Additional", v.getPropertyPath().toString());

    }
    @Test
    void B5915101_testCreatAdditionalMustSizeLessThen5(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(123456L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("aaaa");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("Additional", v.getPropertyPath().toString());

    }
    @Test
    void B5915101_testCreatAdditionalMustSizeMoreThen20(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(123456L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("aaaaaaaaaaaaaaaaaaaaa");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("Additional", v.getPropertyPath().toString());

    }
    @Test
    void B5915101_testCreatTypeCauseNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(123456L);
        appointment.setCreatTypeCause(null);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("aaaaaa");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("creatTypeCause", v.getPropertyPath().toString());

    }

    @Test
    void B5915101_testCreatDepartmentNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(123456L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(null);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("aaaaaa");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("creatDepartment", v.getPropertyPath().toString());

    }
    @Test
    void B5915101_testCreatTypeGoingNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(123456L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(null);
        appointment.setDate(date);
        appointment.setAdditional("aaaaaa");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("creatTypeGoing", v.getPropertyPath().toString());

    }
}