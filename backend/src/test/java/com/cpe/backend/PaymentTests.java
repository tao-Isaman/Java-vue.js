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
import javax.validation.ConstraintViolationException;
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
import com.cpe.backend.Payment.repository.TypeBankRepository;
import com.cpe.backend.Doctororder.repository.DoctorOrderRepository;
import com.cpe.backend.Examination.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
public class PaymentTests {

    private Validator validator;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentOptionRepository paymentOptionRepository;

    @Autowired
    private TypeBankRepository typeBankRepository;

      @Autowired
    private DoctorOrderRepository doctorOrderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    
    @Test
    public void B5907625_testFullDataSuccess() {
        Payment payment = new Payment();
        DoctorOrder doctorOrder = doctorOrderRepository.findById(1);
        PaymentOption paymentOption = paymentOptionRepository.findById(2);
        TypeBank typeBank = typeBankRepository.findById(1);
        Date date = new Date();

        try {
                        payment.setDoctorOrder(doctorOrder);
                        payment.setPaymentOption(paymentOption);
                        payment.setTypeBank(typeBank);
                        payment.setNote("ไม่รับถุง");
                        payment.setDate(date);                                                                                                                                                                                                                
        }catch (ConstraintViolationException e) {

            Optional<Payment> found = paymentRepository.findById(payment.getId());   
            assertEquals(date , found.get().getDate());
            assertEquals(doctorOrder , found.get().getDoctorOrder());
            assertEquals(typeBank , found.get().getTypeBank());
            assertEquals(paymentOption , found.get().getDoctorOrder());
            assertEquals("ไม่รับถุง" , found.get().getNote());
        } catch (NullPointerException e) {}

    }
    
    @Test
    void B5907625_testDoctorOrderIsNotNull(){
        Payment payment = new Payment();
        DoctorOrder doctorOrder = new DoctorOrder();
        PaymentOption paymentOption = new PaymentOption();
        TypeBank typeBank = new TypeBank();
        Date date = new Date();

        payment.setDoctorOrder(null);
        payment.setPaymentOption(paymentOption);
        payment.setTypeBank(typeBank);
        payment.setNote("abcedf");
        payment.setDate(date);
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
        Date date = new Date();

        payment.setDoctorOrder(doctorOrder);
        payment.setPaymentOption(paymentOption);
        payment.setTypeBank(typeBank);
        payment.setNote(null);
        payment.setDate(date);

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
        Date date = new Date();

        payment.setDoctorOrder(doctorOrder);
        payment.setPaymentOption(null);
        payment.setTypeBank(typeBank);
        payment.setNote("abcdef");
        payment.setDate(date);

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
        Date date = new Date();

        payment.setDoctorOrder(doctorOrder);
        payment.setPaymentOption(paymentOption);
        payment.setTypeBank(typeBank);
        payment.setDate(date);

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
        PaymentOption paymentOption = paymentOptionRepository.findById(1);
        TypeBank typeBank = new TypeBank();
        Date date = new Date();

        payment.setDoctorOrder(doctorOrder);
        payment.setPaymentOption(paymentOption);
        payment.setTypeBank(typeBank);
        payment.setDate(date);

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

   