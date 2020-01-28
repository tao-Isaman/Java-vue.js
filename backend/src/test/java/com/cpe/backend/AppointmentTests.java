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
import com.cpe.backend.Appointment.repository.DepartmentRepository;
import com.cpe.backend.Appointment.repository.TypeCauseRepository;
import com.cpe.backend.Appointment.repository.TypeGoingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class AppointmentTests {

    private Validator validator;

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TypeCauseRepository typeCauseRepository;
    @Autowired
    private TypeGoingRepository typeGoingRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    // เทส textfill รายละเอียดเพิ่มเติมห้ามว่าง
    @Test
    void B5915101_testCreatAdditionalNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(1234567890123L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional(null);




        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Additional", v.getPropertyPath().toString());

    }
    // เทส textfill รายละเอียดเพิ่มเติม ห้ามใส่่ต่ำกว่า5ตัว
    @Test
    void B5915101_testCreatAdditionalMustSizeLessThen5(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(1234567890123L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("ไมมี");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("Additional", v.getPropertyPath().toString());

    }
    // เทส textfill รายละเอียดเพิ่มเติม ห้ามใส่เกิน20ตัว
    @Test
    void B5915101_testCreatAdditionalMustSizeMoreThen20(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(1234567890123L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("กกกกกกกกกกกกกกกกกกกกก");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("Additional", v.getPropertyPath().toString());

    }
    // เทส combobox สาดหตุที่นัด ห้ามว่าง
    @Test
    void B5915101_testCreatTypeCauseNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(1234567890123L);
        appointment.setCreatTypeCause(null);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("ไม่มีนะ");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("creatTypeCause", v.getPropertyPath().toString());

    }
    // เทส combobox แผนกที่นัด ห้ามว่าง
    @Test
    void B5915101_testCreatDepartmentNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(1234567890123L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(null);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("ไม่มีนะ");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("creatDepartment", v.getPropertyPath().toString());

    }
    // เทส combobox การเดินทางมา ห้ามว่าง
    @Test
    void B5915101_testCreatTypeGoingNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(1234567890123L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(null);
        appointment.setDate(date);
        appointment.setAdditional("ไม่มีนะ");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("creatTypeGoing", v.getPropertyPath().toString());

    }
    // เทส combobox วันที่นัด ห้ามว่าง
    @Test
    void B5915101_testCreatDateNotNull(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(1234567890123L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(null);
        appointment.setAdditional("ไม่มีนะ");



        Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());

    }
    // เทส save
    @Test
    void B5915101_testCreatAppointmentOkWithSameSize(){
        Appointment appointment = new Appointment();
        TypeCause typeCause = typeCauseRepository.findById(1);
        Department department = departmentRepository.findById(1);
        TypeGoing typeGoing = typeGoingRepository.findById(1);
        Date date = new Date();

        appointment.setCreatTypeCause(typeCause);
        appointment.setCreatDepartment(department);
        appointment.setCreatTypeGoing(typeGoing);
       appointment.setDate(date);
        appointment.setAdditional("ไม่มีนะ");



        appointment = appointmentRepository.save(appointment);

        Optional<Appointment> found = appointmentRepository.findById(appointment.getId());
        assertEquals(typeCause, found.get().getCreatTypeCause());
        assertEquals(department, found.get().getCreatDepartment());
        assertEquals(typeGoing, found.get().getCreatTypeGoing());
        assertEquals(date, found.get().getDate());
        assertEquals("ไม่มีนะ", found.get().getAdditional());
    }

    @Test
    void B5915101_testCreatAditionalWrongPattern(){
        Appointment appointment = new Appointment();
        TypeCause t1 = new TypeCause();
        Department d1 = new Department();
        TypeGoing g1 = new TypeGoing();
        Date date = new Date();

        appointment.setId(1234567890123L);
        appointment.setCreatTypeCause(t1);
        appointment.setCreatDepartment(d1);
        appointment.setCreatTypeGoing(g1);
        appointment.setDate(date);
        appointment.setAdditional("aaaaaa");

        final Set<ConstraintViolation<Appointment>> result = validator.validate(appointment);
        assertEquals(1, result.size());
        final ConstraintViolation<Appointment> v = result.iterator().next();
        assertEquals("must match \"[ก-๙]*\"", v.getMessage());
        assertEquals("Additional", v.getPropertyPath().toString());
    }
}