package com.cpe.backend.Doctororder.repository;

import java.util.Collection;

import com.cpe.backend.Doctororder.entity.DoctorOrder;
import com.cpe.backend.Examination.entity.ExaminationSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource
public interface DoctorOrderRepository extends JpaRepository<DoctorOrder, Long> {
    DoctorOrder findById(long id);

    // DoctorOrder findByExaminationId(ExaminationSystem id);

    @Query(value = "SELECT do.DOCTORORDER_ID,do.date , do.allergies,do.reaction,do.prescription_number,p.name ,p.national_id, e.SYMPTOM , md.Mname , mt.TYPE_NAME,d.Dname  FROM MEDICINEITEM as m INNER JOIN DOCTORORDER as do  INNER JOIN PATIENT as p INNER JOIN  EXAMINATION_SYSTEM as e INNER JOIN MEDICINE as md INNER JOIN  Medication_Type as mt INNER JOIN DOCTOR as d on e.PATIENT_ID = p.NATIONAL_ID and m.MEDICINE_ID = md.MEDICINE_ID and m. MEDICATION_TYPE_ID = mt. MEDICATION_TYPE_ID  and do.EX_EXAMINATION_SYSTEM_ID = e.EXAMINATION_SYSTEM_ID and d.DOCTORS_ID = e.DOCTOR_ID where p.national_id = :national_id"
    ,
     nativeQuery = true)
     Collection<Object[]> findBynNationaId(@Param("national_id") long national_id);

    

    @Query(value ="SELECT *FROM DOCTORORDER WHERE DOCTORORDER .EX_EXAMINATION_SYSTEM_ID  = :Ex"
    ,
     nativeQuery = true)
    DoctorOrder findByExaminationSystemId(@Param("Ex") long ex);

    // SELECT do.DOCTORORDER_ID,do.date , do.allergies,do.reaction,do.prescription_number,p.name , e.SYMPTOM , md.Mname , mt.TYPE_NAME,d.Dname  FROM MEDICINEITEM as m INNER JOIN DOCTORORDER as do  INNER JOIN PATIENT as p INNER JOIN  EXAMINATION_SYSTEM as e INNER JOIN MEDICINE as md INNER JOIN  Medication_Type as mt INNER JOIN DOCTOR as d on do. DOCTORORDER_ID = m.MEDICINEITEM_ID and e.PATIENT_ID = p.NATIONAL_ID and m.MEDICINE_ID = md.MEDICINE_ID and m. MEDICATION_TYPE_ID = mt. MEDICATION_TYPE_ID  and do.EX_EXAMINATION_SYSTEM_ID = e.EXAMINATION_SYSTEM_ID and d.DOCTORS_ID = e.DOCTOR_ID where DOCTORORDER_ID = :id

   
//     _____แบบกำหนด id _____________

//  SELECT do.DOCTORORDER_ID,do.date , p.name , e.SYMPTOM , md.Mname , mt.TYPE_NAME,d.Dname   FROM MEDICINEITEM as m INNER JOIN DOCTORORDER as do  INNER JOIN PATIENT as p INNER JOIN  EXAMINATION_SYSTEM as e INNER JOIN MEDICINE as md INNER JOIN  Medication_Type as mt INNER JOIN DOCTOR as d on do. DOCTORORDER_ID = m.MEDICINE_ITEM and e.PATIENT_ID = p.NATIONAL_ID and m.MEDICINES_ID = md.MEDICINE_ID and m. MEDICATION_TYPE_ID = mt. MEDICATION_TYPE_ID  and do.EX_EXAMINATION_SYSTEM_ID = e.EXAMINATION_SYSTEM_ID and d.DOCTORS_ID = e.DOCTOR_ID where DOCTORORDER_ID = ?
// _____________แบบมาทั้งหมด__________________________
//  SELECT do.DOCTORORDER_ID,do.date , p.name , e.SYMPTOM , md.Mname , mt.TYPE_NAME,d.Dname   FROM MEDICINEITEM as m INNER JOIN DOCTORORDER as do  INNER JOIN PATIENT as p INNER JOIN  EXAMINATION_SYSTEM as e INNER JOIN MEDICINE as md INNER JOIN  Medication_Type as mt INNER JOIN DOCTOR as d on do. DOCTORORDER_ID = m.MEDICINE_ITEM and e.PATIENT_ID = p.NATIONAL_ID and m.MEDICINES_ID = md.MEDICINE_ID and m. MEDICATION_TYPE_ID = mt. MEDICATION_TYPE_ID  and do.EX_EXAMINATION_SYSTEM_ID = e.EXAMINATION_SYSTEM_ID and d.DOCTORS_ID = e.DOCTOR_ID
// _______
    
//     SELECT do.DOCTORORDER_ID,do.date , p.name , e.SYMPTOM , md.name , mt.TYPE_NAME,d.name   FROM MEDICINEITEM as m
// INNER JOIN DOCTORORDER as do  
// INNER JOIN PATIENT as p
// INNER JOIN  EXAMINATION_SYSTEM as e
// INNER JOIN MEDICINE as md
// INNER JOIN  Medication_Type as mt
// INNER JOIN DOCTOR as d
// on do. DOCTORORDER_ID = m.MEDICINE_ITEM and
//  e.PATIENT_ID = p.NATIONAL_ID 
//  and m.MEDICINES_ID = md.MEDICINE_ID 
// and m. MEDICATION_TYPE_ID = mt. MEDICATION_TYPE_ID  
// and do.EX_EXAMINATION_SYSTEM_ID = e.EXAMINATION_SYSTEM_ID
// and d.DOCTOR_ID = e.DOCTOR_ID
// SELECT do.DOCTORORDER_ID,do.date , p.name , e.SYMPTOM , md.name , mt.TYPE_NAME,d.name   FROM MEDICINEITEM as m INNER JOIN DOCTORORDER as do  INNER JOIN PATIENT as p INNER JOIN  EXAMINATION_SYSTEM as e INNER JOIN MEDICINE as md INNER JOIN  Medication_Type as mt INNER JOIN DOCTOR as d on do. DOCTORORDER_ID = m.MEDICINE_ITEM and e.PATIENT_ID = p.NATIONAL_ID and m.MEDICINES_ID = md.MEDICINE_ID and m. MEDICATION_TYPE_ID = mt. MEDICATION_TYPE_ID  and do.EX_EXAMINATION_SYSTEM_ID = e.EXAMINATION_SYSTEM_ID and d.DOCTOR_ID = e.DOCTOR_ID"
   

}