package com.cpe.backend.Doctororder.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpe.backend.Doctororder.Model.orderModel;
import com.cpe.backend.Doctororder.entity.DoctorOrder;
import com.cpe.backend.Doctororder.entity.MedicineItem;
import com.cpe.backend.Doctororder.repository.DoctorOrderRepository;
import com.cpe.backend.Doctororder.repository.MedicationTypeRepository;
import com.cpe.backend.Doctororder.repository.MedicineItemRepository;
import com.cpe.backend.Doctororder.repository.MedicineRepository;
import com.cpe.backend.Examination.entity.ExaminationSystem;
import com.cpe.backend.Examination.repository.ExaminationRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins =  "http://localhost:8080")
@RestController
public class DoctorOrderController {

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

    DoctorOrderController(DoctorOrderRepository doctorOrderRepository) {
        this.doctorOrderRepository = doctorOrderRepository;
    }

    @GetMapping("/DoctorOrder")
    public Collection<DoctorOrder> doctororder() {
        return doctorOrderRepository.findAll().stream().collect(Collectors.toList());
    }

    // // ดึงตาราง
    // @GetMapping("/Order1")
    // public Optional<DoctorOrder> doctororders(@PathVariable Long id) {
    //     Optional<DoctorOrder> doctororders = doctorOrderRepository.findById(id);
    //     return doctororders;
    // }

    // // ดึงตาราง
    // @GetMapping("/ordertable/{id}")
    // public Collection<Object[]> getData(@PathVariable Long id){
    //     System.out.println(id);
    //     Collection<Object[]> data =  doctorOrderRepository.findByDoctorID(id);
    //     System.out.println(data);

    //     return data;
    // }
    
    // @GetMapping("/ordertable/{id}/{dAllergies}/{rAllergies}")
    // public Collection<Object[]> getData(@PathVariable Long id, @PathVariable String rAllergies, @PathVariable String dAllergies){
    //     System.out.println(id);
    //     System.out.println(rAllergies);
    //     System.out.println(dAllergies);
    //     Collection<Object[]> data =  doctorOrderRepository.findByDoctorID(id);
    //     System.out.println(data);

    //     return data;
    // }

    @PostMapping("/postDoctor")
    public DoctorOrder index(@RequestBody orderModel body) {
        // @RequestBody Map<String, String> p
        // @PathVariable String allergies, @PathVariable String reaction,
        DoctorOrder newdocOrder = new DoctorOrder();
        DoctorOrder errorDoctorOrder = new DoctorOrder();
        
        ExaminationSystem ex = examinationRepository.findById(body.getExaminationId()).get();
        System.out.println(ex);

        DoctorOrder doc = doctorOrderRepository.findByExaminationSystemId(Long.valueOf(body.getExaminationId()).longValue());
         System.out.println(Long.valueOf(body.getExaminationId()).longValue());
         System.out.println("========");
         System.out.println(doc);
         //System.out.println(doc.getEx());
         System.out.println("========");
         
         if(doc == null){
            newdocOrder.setEx(ex);
            newdocOrder.setDate(new Date());
            newdocOrder.setAllergies(body.getAllergies());
            newdocOrder.setReaction(body.getReaction());
            
            System.out.println("check2");

            for(int i = 0 ; i< body.getMedicationTypeIds().length ; i++){
                MedicineItem medI = new MedicineItem();
                medI.setMedicine(medicineRepository.findById(Long.valueOf(body.getMedicineIds()[i]).longValue()));
                medI.setMedicationType(medicationTypeRepository.findById(Long.valueOf(body.getMedicationTypeIds()[i]).longValue()));
                medI.setDoctorOrder(newdocOrder);
                medicineItemRepository.save(medI);
            }


            DoctorOrder docOrderNew = doctorOrderRepository.save(newdocOrder);
            return docOrderNew;

         }else{
            if(doc.getEx() != null){

                System.out.println("check1");
                return errorDoctorOrder;
            }else{
                newdocOrder.setEx(ex);
                newdocOrder.setDate(new Date());
                newdocOrder.setAllergies(body.getAllergies());
                newdocOrder.setReaction(body.getReaction());
                

                System.out.println("check2");

                for(int i = 0 ; i< body.getMedicationTypeIds().length ; i++){
                    MedicineItem medI = new MedicineItem();
                    medI.setMedicine(medicineRepository.findById(Long.valueOf(body.getMedicineIds()[i]).longValue()));
                    medI.setMedicationType(medicationTypeRepository.findById(Long.valueOf(body.getMedicationTypeIds()[i]).longValue()));
                    medI.setDoctorOrder(newdocOrder);
                    medicineItemRepository.save(medI);
                }
                System.out.println(body.getAllergies());

                System.out.println(doc);

                DoctorOrder docOrderNew = doctorOrderRepository.save(newdocOrder);

                return docOrderNew;
            }
        }
        
    }

    
    // @GetMapping("/ordertable/")
    // public Collection<Object[]> getData(@PathVariable Long id){
    //     System.out.println(id);
    //     Collection<Object[]> data =  doctorOrderRepository.findByDoctorID(id);
    //     System.out.println(data);

    //     return data;
    // }

}