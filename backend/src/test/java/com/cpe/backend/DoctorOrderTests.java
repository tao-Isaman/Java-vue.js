package com.cpe.backend;

import com.cpe.backend.Doctororder.Model.orderModel;
import com.cpe.backend.Doctororder.repository.DoctorOrderRepository;
import com.cpe.backend.Doctororder.repository.MedicationTypeRepository;
import com.cpe.backend.Doctororder.repository.MedicineItemRepository;
import com.cpe.backend.Doctororder.repository.MedicineRepository;
import com.cpe.backend.Examination.entity.ExaminationSystem;
import com.cpe.backend.Examination.repository.ExaminationRepository;
import com.cpe.backend.Doctororder.entity.DoctorOrder;
import com.cpe.backend.Doctororder.entity.MedicationType;
import com.cpe.backend.Doctororder.entity.Medicine;
import com.cpe.backend.Doctororder.entity.MedicineItem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
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
    private DoctorOrderRepository doctorOrderRepository;
    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private MedicationTypeRepository medicationTypeRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicineItemRepository medicineItemRepository;

    

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void B5915619_testDateNotNull(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(null);
        doctorOrder.setPrescriptionNumber("P1234567890T");
        doctorOrder.setAllergies("Paracetamal");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Date", v.getPropertyPath().toString());

        
    }


    @Test
    public void B5915619_testAllergiesNotNull(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890T");
        doctorOrder.setAllergies(null);
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("allergies", v.getPropertyPath().toString());
        
    }


    @Test
    public void B5915619_testAllergiesWrongPattern(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890T");
        doctorOrder.setAllergies("ไม่มี");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[A-Za-z0-9_]{5,100}\"", v.getMessage());
        assertEquals("allergies", v.getPropertyPath().toString());
        
    }

    

    @Test
    public void B5915619_testReactionNotNull(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890T");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction(null);
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("reaction", v.getPropertyPath().toString());
        
    }

    @Test
    public void B5915619_testReactionWrongPattern(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890T");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("reaction");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[ก-๙]{5,150}\"", v.getMessage());
        assertEquals("reaction", v.getPropertyPath().toString());
        
    }

    


    
    @Test
    public void B5915619_testPrescriptionNumberNotNull(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber(null);
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("prescriptionNumber", v.getPropertyPath().toString());
        
    }
    

    @Test
    public void B5915619_testPrescriptionNumberWrongPattern(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890H");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[(P)]\\d{10}[(T){12}]\"", v.getMessage());
        assertEquals("prescriptionNumber", v.getPropertyPath().toString());
        
    }

    @Test
    public void B5915619_testPrescriptionNumberFirstCharacterP(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("O1234567890T");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[(P)]\\d{10}[(T){12}]\"", v.getMessage());
        assertEquals("prescriptionNumber", v.getPropertyPath().toString());
        
    }

    @Test
    public void B5915619_testPrescriptionNumberLastCharacterU(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890U");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[(P)]\\d{10}[(T){12}]\"", v.getMessage());
        assertEquals("prescriptionNumber", v.getPropertyPath().toString());
        
    }

    @Test
    public void B5915619_testPrescriptionNumberFirstCharacterQ(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("Q1234567890T");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[(P)]\\d{10}[(T){12}]\"", v.getMessage());
        assertEquals("prescriptionNumber", v.getPropertyPath().toString());
        
    }

    @Test
    public void B5915619_testPrescriptionNumberLastCharacterS(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890S");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(ex);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("must match \"[(P)]\\d{10}[(T){12}]\"", v.getMessage());
        assertEquals("prescriptionNumber", v.getPropertyPath().toString());
        
    }
    
    
    @Test
    public void B5915619_testExaminationSystemIdNotNull(){
        ExaminationSystem ex = new ExaminationSystem();
        ex.setSymptom("symtom");
        DoctorOrder doctorOrder = new DoctorOrder();
        doctorOrder.setDate(new Date());
        doctorOrder.setPrescriptionNumber("P1234567890T");
        doctorOrder.setAllergies("allergies");
        doctorOrder.setReaction("ไม่มีอาการ");
        doctorOrder.setEx(null);

        Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
        assertEquals(1, result.size());
        ConstraintViolation<DoctorOrder> v = result.iterator().next();
        assertEquals("examination id not null", v.getMessage());
        assertEquals("ex", v.getPropertyPath().toString());
    }

    
    
    // @Test
    // public void B5915619_testAllergiesLessSize(){
    //     ExaminationSystem ex = new ExaminationSystem();
    //     ex.setSymptom("symtom");
    //     DoctorOrder doctorOrder = new DoctorOrder();
    //     doctorOrder.setDate(new Date());
    //     doctorOrder.setPrescriptionNumber("P1234567890T");
    //     doctorOrder.setAllergies("Para");
    //     doctorOrder.setReaction("ไม่มีอาการ");
    //     doctorOrder.setEx(ex);

    
    //     Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
    //     assertEquals(1, result.size());
    //     ConstraintViolation<DoctorOrder> v = result.iterator().next();
    //     assertEquals("must match \"[A-Za-z0-9_]{5,100}\"", v.getMessage());
    //     assertEquals("allergies", v.getPropertyPath().toString());
        
        
    // }
    
    // @Test
    // public void B5915619_testReactionLessSize(){
    //     ExaminationSystem ex = new ExaminationSystem();
    //     ex.setSymptom("symtom");
    //     DoctorOrder doctorOrder = new DoctorOrder();
    //     doctorOrder.setDate(new Date());
    //     doctorOrder.setPrescriptionNumber("P1234567890T");
    //     doctorOrder.setAllergies("Paracetamal");
    //     doctorOrder.setReaction("ไม่");
    //     doctorOrder.setEx(ex);

    
    //     Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
    //     assertEquals(1, result.size());
    //     ConstraintViolation<DoctorOrder> v = result.iterator().next();
    //     assertEquals("must match \"[A-Za-z0-9_]{5,100}\"", v.getMessage());
    //     assertEquals("allergies", v.getPropertyPath().toString());
        
        
    // }

    // @Test
    // public void B5915619_testReactionOverSize(){
    //     ExaminationSystem ex = new ExaminationSystem();
    //     ex.setSymptom("symtom");
    //     DoctorOrder doctorOrder = new DoctorOrder();
    //     doctorOrder.setDate(new Date());
    //     doctorOrder.setPrescriptionNumber("P1234567890T");
    //     doctorOrder.setAllergies("Paracetamal");
    //     // doctorOrder.setReaction("ไม่มีอาการ");
    //     doctorOrder.setEx(ex);

    //     String reaction = "" ;
    //     int i = 0;
    //     while(i<151){
    //         reaction += "ก";
    //         i++;
    //     }
    //     doctorOrder.setReaction(reaction);

    //     Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
    //     assertEquals(1, result.size());
    //     ConstraintViolation<DoctorOrder> v = result.iterator().next();
    //     assertEquals("must match \"[ก-๙]{5,150}\"", v.getMessage());
    //     assertEquals("reaction", v.getPropertyPath().toString());
        
        
    // }

    // @Test
    // public void B5915619_testAllergiesOverSize(){
    //     ExaminationSystem ex = new ExaminationSystem();
    //     ex.setSymptom("symtom");
    //     DoctorOrder doctorOrder = new DoctorOrder();
    //     doctorOrder.setDate(new Date());
    //     doctorOrder.setPrescriptionNumber("P1234567890T");
    //     // doctorOrder.setAllergies("Paracetamal");
    //     doctorOrder.setReaction("ไม่มีอาการ");
    //     doctorOrder.setEx(ex);

    //     String allergies = "" ;
    //     int i = 0;
    //     while(i<151){
    //         allergies += "a";
    //         i++;
    //     }
    //     doctorOrder.setAllergies(allergies);

    //     Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
    //     assertEquals(1, result.size());
    //     ConstraintViolation<DoctorOrder> v = result.iterator().next();
    //     assertEquals("must match \"[A-Za-z0-9_]{5,100}\"", v.getMessage());
    //     assertEquals("allergies", v.getPropertyPath().toString());
        
        
    // }
    
    // @Test
    // public void B5915619_testInsertDataSuccess(){
    //     ExaminationSystem ex = new ExaminationSystem();
    //     ex.setSymptom("symptom");
    //     Medicine med = new Medicine();
    //     med.setId(1L);
    //     med.setMname("Medicine name");
    //     med.setPrice(20L);
    //     MedicationType mType = new MedicationType();
    //     mType.setId(1L);
    //     mType.setTypeName("TypeName");
    //     MedicineItem medicineItem = new MedicineItem();
    //     medicineItem.setId(1L);
    //     medicineItem.setMedicationType(mType);
    //     medicineItem.setMedicine(med);
    //     // medicineItem.setDoctorOrder(medicineItem);
    //     // medicineItem = medicineItemRepository.save(medicineItem);
    //     DoctorOrder doctorOrder = new DoctorOrder();
    //     doctorOrder.setDate(new Date());
    //     doctorOrder.setPrescriptionNumber("123456789PN");
    //     doctorOrder.setAllergies("allergies");
    //     doctorOrder.setReaction("reaction");
    //     doctorOrder.setEx(ex);
    //     doctorOrder.setMedicineItem(Collection<MedicineItem> );
    //     doctorOrder.setMedicineItem(Collection<MedicineItem> medicineItem);
       
        
    //     try{
    //         Set<Set<ConstraintViolation<DoctorOrder>> result = validator.validate(doctorOrder);
    //     }

    //     doctorOrder = doctorOrderRepository.saveAndFlush(doctorOrder);


    //     try {
    //         entityManager.persist(doctorOrder);
    //         entityManager.flush();
    //     } catch(javax.validation.ConstraintViolationException e) {
    //         System.out.println("Insert data success in##########################"+e);
    //         // fail("Should not pass to this line");
    //     }

        
    // }
    // @Test
    // public void B5915619_testMedicineItemNotNull(){
        
    // }

    // @Test
    // public void B5915619_testDrugAllergies(){
        
    // }

    // @Test
    // public void B5915619_testAllergiesReaction(){
        
    // }

    
    // @Test
    // public void B5915619_testDataSave(){
        
    // }
    // @Test
    // public void B5915619_testDate(){
        
    // }
    
    // @Test
    // public void B5915619_testExaminationSystem(){
        
    // }

}