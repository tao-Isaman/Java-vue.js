package com.cpe.backend.Payment.controller;

import com.cpe.backend.Doctororder.entity.DoctorOrder;
// import com.cpe.backend.entity.DoctorOrder;
// import com.cpe.backend.entity.Patient;
import com.cpe.backend.Payment.entity.Payment;
// import com.cpe.backend.entity.PaymentOption;
// import com.cpe.backend.entity.TypeBank;
// import com.cpe.backend.repository.PatientRepository;
import com.cpe.backend.Payment.repository.PaymentOptionRepository;
import com.cpe.backend.Doctororder.repository.DoctorOrderRepository;
import com.cpe.backend.Payment.repository.PaymentOptionRepository;
import com.cpe.backend.Payment.repository.TypeBankRepository;
import com.cpe.backend.Payment.repository.PaymentRepository;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// import java.io.IOException;
// import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
// import java.util.List;
import java.util.Map;
import java.util.Optional;
// import java.util.stream.Collector;
import java.util.stream.Collectors;


// import java.net.URLDecoder;

@CrossOrigin(origins = "*")
@RestController
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private DoctorOrderRepository doctorOrderRepository;
    @Autowired
    private PaymentOptionRepository paymentOptionRepository;
    

    @Autowired
    private TypeBankRepository typeBankRepository;

    PaymentController(PaymentRepository paymentRepository){
    this.paymentRepository = paymentRepository;
    }
    
    @GetMapping("/payment")
    public Collection<Payment> payments(){
        return paymentRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/payment/{id}")
    public Optional<Payment> payments(@PathVariable Long id) {
        Optional<Payment> payments = paymentRepository.findById(id);
        return payments;
    }
    @RequestMapping(value = "/paymentPost",method = RequestMethod.POST)
    public Payment newPayment(@RequestBody Map<String, String> body) {


        Payment newPayment = new Payment();
        Payment errorPayment = new Payment();
   
        
         DoctorOrder DoctorOrder = doctorOrderRepository.findById(Long.valueOf(body.get("doctorOrder")).longValue());
         if(DoctorOrder.getPayment() != null){
            return errorPayment ;
         }else {
            newPayment.setDoctorOrder(doctorOrderRepository.findById(Long.valueOf(body.get("doctorOrder")).longValue()));
			newPayment.setPaymentOption(paymentOptionRepository.findById(Long.valueOf(body.get("paymentOption")).longValue()));
            newPayment.setTypeBank(typeBankRepository.findById(Long.valueOf(body.get("typeBank")).longValue()));
            newPayment.setNote(body.get("note"));
            newPayment.setDate(new Date());
            // newPatient.setAddress(body.get("address"));
            return paymentRepository.save(newPayment);

          }
        // // TypeBank typeBank = typeBankRepository.findById(Long.valueOf(body.get("typeBank")).longValue());
        // // TypeCash typeCash = typeCashRepository.findById(Long.valueOf(body.get("typeCash")).longValue());
        // PaymentOption PaymentOption = paymentOptionRepository.findById(Long.valueOf(body.get("paymentOption")).longValue());
        
        
        // // newPayment.setPname(body.get("patient"));
        // newPayment.setDoctorOrder(DoctorOrder);
        // newPayment.setPaymentOption(PaymentOption);
        // newPayment.setTypeBank(typeBank);
        // newPayment.setTypeCash(typeCash);		
     
    }
    

}
