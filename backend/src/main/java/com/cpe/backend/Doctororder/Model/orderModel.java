package com.cpe.backend.Doctororder.Model;
import lombok.*;
import java.util.Date;

// model for request body 
@Data
public  class orderModel {
    private Long examinationId;
    private Long[] medicationTypeIds;
    private Long[] medicineIds;
    
    private Date Date;
    private String allergies;
    private String reaction;
    private String prescriptionNumber;

}