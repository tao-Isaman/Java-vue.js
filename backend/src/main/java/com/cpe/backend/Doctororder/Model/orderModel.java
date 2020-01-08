package com.cpe.backend.Doctororder.Model;
import lombok.*;

// model for request body 
@Data
public  class orderModel {
    private Long examinationId;
    private Long[] medicationTypeIds;
    private Long[] medicineIds;

    private String allergies;
    private String reaction;

}