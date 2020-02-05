package com.cpe.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

    // ใส่ข้อมูลปกติแบบถูกทั้งหมด
    @Test
    void B5915064_testExaminationwithCorrect(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        examinationRepository.saveAndFlush(examinationSystem);

        Optional<ExaminationSystem> check = examinationRepository.findById(examinationSystem.getId());
        assertEquals(90, check.get().getPulse());
        assertEquals(140, check.get().getPressureh());
        assertEquals(80, check.get().getPressurel());
        assertEquals("อาการ", check.get().getSymptom());
        assertEquals(duration, check.get().getDuration());
        assertEquals(state, check.get().getState());
        assertEquals(doctor, check.get().getCheckBy());
        assertEquals(examinationSystem.getCheckDate(), check.get().getCheckDate());
    }

    // Duration ป้องกันใส่ค่า Null
    @Test
    void B5915064_testDurationNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();

        Doctor doctor = doctorRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(null);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("duration", v.getPropertyPath().toString());
    }

    // State ป้องกันใส่ค่า Null

    @Test
    void B5915064_testStateNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Duration duration = durationRepository.findById(1);
        Doctor doctor = doctorRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(null);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("state", v.getPropertyPath().toString());
    }

    // Doctor ป้องกันใส่ค่า Null

    @Test
    void B5915064_testDoctorNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(null);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("checkBy", v.getPropertyPath().toString());
    }

    // Symptom ป้องกันใส่ค่า Null

    @Test
    void B5915064_testSymptomNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom(null);
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    // ใส่ pattern เฉพาะตัวอักษรภาษาไทย
    @Test
    void B5915064_testSymptomPattern(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must match \"[ก-๙]*\"", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    // Sizeอยู่ระหว่าง 5-250
    @Test
    void B5915064_testSymptomMustBeMoreThan5orLess250(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor docter = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อากา");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(docter);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 5 and 250", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    // ชีพจรต้องไม่น้อยกว่า1
    @Test
    void B5915064_testPulseMustLessThen1(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(0);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("pulse", v.getPropertyPath().toString());
    }

    // ชีพจรต้องไม่มากกว่า 200
    @Test
    void B5915064_testPulseMustMoreThen200(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(201);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("pulse", v.getPropertyPath().toString());
    }

    // ความดันH ต้องไม่น้อยกว่า1
    @Test
    void B5915064_testPressurehMustLessThen1(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(0);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("pressureh", v.getPropertyPath().toString());
    }

    // ความดันH ต้องไม่มากกว่า300
    @Test
    void B5915064_testPressurehMustMoreThen300(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(301);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be less than or equal to 300", v.getMessage());
        assertEquals("pressureh", v.getPropertyPath().toString());
    }

    // ความดันL ต้องไม่น้อยกว่า1
    @Test
    void B5915064_testPressurelMustLessThen1(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(0);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("pressurel", v.getPropertyPath().toString());
    }

    // ความดันL ต้องไม่มากว่า300
    @Test
    void B5915064_testPressurelMustMoreThen300(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(301);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be less than or equal to 300", v.getMessage());
        assertEquals("pressurel", v.getPropertyPath().toString());
    }

    // pulse ห้าม Null
    @Test
    void B5915064_testPulseNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Duration duration = durationRepository.findById(1);
        Doctor doctor = doctorRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(null);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pulse", v.getPropertyPath().toString());
    }

    // pressurel ห้าม Null
    @Test
    void B5915064_testPressurelNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(140);
        examinationSystem.setPressurel(null);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pressurel", v.getPropertyPath().toString());
    }

    // pressureh ห้าม Null
    @Test
    void B5915064_testPressurehNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor doctor = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = stateRepository.findById(1);
        Date date = new Date();

        examinationSystem.setPulse(90);
        examinationSystem.setPressureh(null);
        examinationSystem.setPressurel(80);
        examinationSystem.setSymptom("อาการ");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckBy(doctor);
        examinationSystem.setCheckDate(date);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pressureh", v.getPropertyPath().toString());
    }

}