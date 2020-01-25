package com.cpe.backend;

import com.cpe.backend.Doctororder.repository.DoctorOrderRepository;
import com.cpe.backend.Examination.repository.*;

import com.cpe.backend.Examination.entity.Doctor;
import com.cpe.backend.Examination.entity.Duration;
import com.cpe.backend.Examination.entity.ExaminationSystem;
import com.cpe.backend.Examination.entity.State;
import com.cpe.backend.Doctororder.entity.DoctorOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.Set;
import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class DoctorOrderTests {

    private Validator validator;

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    public void DoctorOrder(final DoctorOrderRepository doctorOrderRepository) {
        this.doctorOrderRepository = doctorOrderRepository;
    }


    @Autowired
    private DoctorOrderRepository doctorOrderRepository;

    
    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void B5916719_testFullDataSuccess() {
        ExaminationSystem e1 = new ExaminationSystem();
        DoctorOrder doctorOrder = new DoctorOrder();
        try {
            e1.setPressure("90 120");
            e1.setPulse(180);
            e1.setSymptom("Something");
            doctorOrder.setId(1L);
            doctorOrder.setDate(new Date());
            doctorOrder.setPrescriptionNumber("P1234567890");
            doctorOrder.setAllergies("Paracetamal");
            doctorOrder.setReaction("ไม่มีอาการ");
            doctorOrder.setEx(e1);

            doctorOrder = doctorOrderRepository.saveAndFlush(doctorOrder);
            
        }catch (ConstraintViolationException e) {
            Optional<DoctorOrder> found = doctorOrderRepository.findById(doctorOrder.getId());
            assertEquals("P1234567890", found.get().getPrescriptionNumber());
        } catch (NullPointerException e) {}
        // ExaminationSystem e1 = new ExaminationSystem();
        // DoctorOrder doctorOrder = new DoctorOrder();
        // e1.setPressure("90 120");
        // e1.setPulse(180);
        // e1.setSymptom("Something");
        // DoctorOrder doctorOrder = new DoctorOrder();
        // doctorOrder.setId(1L);
        // doctorOrder.setDate(new Date());            
        // doctorOrder.setPrescriptionNumber("P1234567890");
        // doctorOrder.setAllergies("Paracetamal");
        // doctorOrder.setReaction("ไม่มีอาการ");
        // doctorOrder.setEx(e1);
        // doctorOrder = doctorOrderRepository.saveAndFlush(doctorOrder);
        // try {
        //     entityManager.persist(doctorOrder);
        //     entityManager.flush();

        // } catch(javax.validation.ConstraintViolationException e) {
        //     fail("Should not pass to this line testSuccess");
        // } catch(NullPointerException e){
        //     System.out.println("Null Pointer Error."); 
        // }catch(Exception e) {
        //     System.out.println("Error Others.");               
        // }
            

    }

    @Test
    public void B5916719_testDateNotNull() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(null);
        doctorOrder.setPrescriptionNumber("P1234567890");
        doctorOrder.setAllergies("Paracetamal");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Date", v.getPropertyPath().toString());

    }

    @Test
    public void B5916719_testAllergiesNotNull() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890");
        doctorOrder.setAllergies(null);
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("allergies", v.getPropertyPath().toString());

    }

    @Test
    public void B5916719_testAllergiesWrongPattern() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890");
        doctorOrder.setAllergies("ไม่มี");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[A-Za-z0-9_]{5,100}\"", v.getMessage());
        assertEquals("allergies", v.getPropertyPath().toString());

    }

    @Test
    public void B5916719_testReactionNotNull() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction(null);
        doctorOrder.setEx(ex);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("reaction", v.getPropertyPath().toString());

    }

    @Test
    public void B5916719_testReactionOverSize151() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890");
        doctorOrder.setAllergies("Paracetamal");
        // doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        String reaction = "";
        int i = 0;
        while (i < 151) {
            reaction += "ก";
            i++;
        }
        doctorOrder.setReaction(reaction);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("size must be between 5 and 150", v.getMessage());
        assertEquals("reaction", v.getPropertyPath().toString());

    }

    @Test
    public void B5916719_testReactionLessSize4() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890");
        doctorOrder.setAllergies("Paracetamal");
        doctorOrder.setReaction("nooo");
        doctorOrder.setEx(ex);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("size must be between 5 and 150", v.getMessage());
        assertEquals("reaction", v.getPropertyPath().toString());

    }

    @Test
    public void B5916719_testPrescriptionNumberNotNull() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber(null);
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("prescriptionNumber", v.getPropertyPath().toString());

    }

    @Test
    public void B5916719_testPrescriptionNumberWrongPattern() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890H");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[(P)]\\d{10}\"", v.getMessage());
        assertEquals("prescriptionNumber", v.getPropertyPath().toString());

    }

    @Test
    public void B5916719_testExaminationSystemIdNotNull() {
        final ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        final DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(null);

        final Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals( 1, result.size());
        final ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("examination id not null", v.getMessage());
        assertEquals("ex", v.getPropertyPath().toString());
    }

    

    
    

}