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
    void B5915064_testExaminationwithCorrect(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor docter = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = this.stateRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure("90D120S");
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(docter);

        examinationRepository.saveAndFlush(examinationSystem);

        Optional<ExaminationSystem> check = examinationRepository.findById(examinationSystem.getId());
        assertEquals(90, check.get().getPulse());
        assertEquals(examinationSystem.getCheckDate(), check.get().getCheckDate());
        assertEquals("90D120S", check.get().getPressure());
        assertEquals("Something", check.get().getSymptom());
        assertEquals(duration, check.get().getDuration());
        assertEquals(state, check.get().getState());
        assertEquals(docter, check.get().getCheckBy());
    }
    @Test
    void B5915064_testPulseMustBeMoreThan1orLess200(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor docter = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = this.stateRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(0);
        examinationSystem.setPressure("90D120S");
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(docter);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("pulse", v.getPropertyPath().toString());
    }



    @Test
    void B5915064_testPressureNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor docter = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = this.stateRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure(null);
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(docter);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pressure", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testPressurePattern(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor docter = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = this.stateRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure("90");
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(docter);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must match \"\\d{2}[D]\\d{3}[S]\"", v.getMessage());
        assertEquals("pressure", v.getPropertyPath().toString());
    }


    @Test
    void B5915064_testSymptomNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor docter = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = this.stateRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure("90D120S");
        examinationSystem.setSymptom(null);
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(docter);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testSymptomMustBeMoreThan5orLess250(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Doctor docter = doctorRepository.findById(1);
        Duration duration = durationRepository.findById(1);
        State state = this.stateRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure("90D120S");
        examinationSystem.setSymptom("Some");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(docter);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("size must be between 5 and 250", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testDurationNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();

        Doctor docter = doctorRepository.findById(1);
        State state = this.stateRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure("90D120S");
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(null);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(docter);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("duration", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testStateNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Duration duration = durationRepository.findById(1);
        Doctor docter = doctorRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure("90D120S");
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(null);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(docter);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("state", v.getPropertyPath().toString());
    }
    @Test
    void B5915064_testCheckDateNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Duration duration = durationRepository.findById(1);
        Doctor docter = doctorRepository.findById(1);
        State state = this.stateRepository.findById(1);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure("90D120S");
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(null);
        examinationSystem.setCheckBy(docter);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("checkDate", v.getPropertyPath().toString());
    }

    @Test
    void B5915064_testDocterNotNull(){
        ExaminationSystem examinationSystem = new ExaminationSystem();
        Duration duration = durationRepository.findById(1);
        State state = this.stateRepository.findById(1);

        java.util.Date date = new java.util.Date(2020-01-21);

        examinationSystem.setPulse(90);
        examinationSystem.setPressure("90D120S");
        examinationSystem.setSymptom("Something");
        examinationSystem.setDuration(duration);
        examinationSystem.setState(state);
        examinationSystem.setCheckDate(date);
        examinationSystem.setCheckBy(null);

        Set<ConstraintViolation<ExaminationSystem>> result = validator.validate(examinationSystem);
        assertEquals(1, result.size());

        ConstraintViolation<ExaminationSystem> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("checkBy", v.getPropertyPath().toString());
    }


}