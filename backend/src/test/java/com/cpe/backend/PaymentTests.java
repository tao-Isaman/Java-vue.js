package com.cpe.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

// import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.Set;
import java.util.Date;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.cpe.backend.Doctororder.entity.DoctorOrder;
import com.cpe.backend.Doctororder.entity.MedicationType;
import com.cpe.backend.Doctororder.entity.Medicine;
import com.cpe.backend.Doctororder.entity.MedicineItem;
import com.cpe.backend.Examination.entity.ExaminationSystem;
import com.cpe.backend.Payment.entity.Payment;
import com.cpe.backend.Payment.entity.PaymentOption;
import com.cpe.backend.Payment.entity.TypeBank;
import com.cpe.backend.Payment.repository.PaymentOptionRepository;
import com.cpe.backend.Payment.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class PaymentTests {

    private Validator validator;

    @Autowired
    private PaymentRepository paymentRepository;
    private PaymentOptionRepository payop;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // @Test
    // void B5907625_testPaymentNameOkwitHNote(){
    //     Payment payment = new Payment();
    //     DoctorOrder doctorOrder = new DoctorOrder();
    //     PaymentOption paymentOption = new PaymentOption();
    //     TypeBank typeBank = new TypeBank();
    //     Date date = new Date();
    //     ExaminationSystem ex = new ExaminationSystem();
    //     MedicineItem md = new MedicineItem();

    //    doctorOrder.setDate(date);
    //    doctorOrder.setReaction("reaction");
    //    doctorOrder.setAllergies("allergies");
    //    doctorOrder.setPayment(payment);
    //    doctorOrder.setMedicineItem();
    //    doctorOrder.setEx(ex);
     


    //     payment.setDoctorOrder(doctorOrder);
    //     payment.setPaymentOption(paymentOption);
    //     payment.setTypeBank(typeBank);
    //     payment.setNote("Note");

        
       
    //     payment = paymentRepository.saveAndFlush(payment);

    //    Optional<Payment> found = paymentRepository.findById(payment.getId());
    //    assertEquals("Note", found.get().getNote());
    // }
    @Test
    void B5907625_testDoctorOrderIsNotNull(){
        Payment payment = new Payment();
        DoctorOrder doctorOrder = new DoctorOrder();
        PaymentOption paymentOption = new PaymentOption();
        TypeBank typeBank = new TypeBank();


        payment.setDoctorOrder(null);
        payment.setPaymentOption(paymentOption);
        payment.setTypeBank(typeBank);
        payment.setNote("abcedf");

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("doctorOrder", v.getPropertyPath().toString());

    }
    @Test
    void B5907625_testNOTeIsNotNull(){
        Payment payment = new Payment();
        DoctorOrder doctorOrder = new DoctorOrder();
        PaymentOption paymentOption = new PaymentOption();
        TypeBank typeBank = new TypeBank();


        payment.setDoctorOrder(doctorOrder);
        payment.setPaymentOption(paymentOption);
        payment.setTypeBank(typeBank);
        payment.setNote(null);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Note", v.getPropertyPath().toString());

    }

    @Test
    void B5907625_testPaymentOptionIsNotNull(){
        Payment payment = new Payment();
        DoctorOrder doctorOrder = new DoctorOrder();
        PaymentOption paymentOption = new PaymentOption();
        TypeBank typeBank = new TypeBank();


        payment.setDoctorOrder(doctorOrder);
        payment.setPaymentOption(null);
        payment.setTypeBank(typeBank);
        payment.setNote("abcdef");

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("paymentOption", v.getPropertyPath().toString());

    }

     @Test
    void B5907625_testPaymEntNotesMustLessThen5(){
        Payment payment = new Payment();
        DoctorOrder doctorOrder = new DoctorOrder();
        PaymentOption paymentOption = new PaymentOption();
        TypeBank typeBank = new TypeBank();

        payment.setDoctorOrder(doctorOrder);
        payment.setPaymentOption(paymentOption);
        payment.setTypeBank(typeBank);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("Note", v.getPropertyPath().toString());
    }

    @Test
    void B5907625_testPaymentNoteMustMoreThen240(){
        Payment payment = new Payment();
        DoctorOrder doctorOrder = new DoctorOrder();
        PaymentOption paymentOption = new PaymentOption();
        TypeBank typeBank = new TypeBank();


        payment.setDoctorOrder(doctorOrder);
        payment.setPaymentOption(paymentOption);
        payment.setTypeBank(typeBank);
        

        String note = "" ;
        int i = 0;
        while(i<241){
            note += "a";
            i++;
        }

        payment.setNote(note);

        Set<ConstraintViolation<Payment>> result = validator.validate(payment);
        assertEquals(1, result.size());

        ConstraintViolation<Payment> v = result.iterator().next();
        assertEquals("size must be between 5 and 240", v.getMessage());
        assertEquals("Note", v.getPropertyPath().toString());
    }
 



   
}


















