package com.cpe.backend.Doctororder.repository;

import java.util.Collection;
import java.util.List;

import com.cpe.backend.Doctororder.entity.Medicine;
import com.cpe.backend.Doctororder.entity.MedicineItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
  
@RepositoryRestResource
public interface MedicineItemRepository extends JpaRepository<MedicineItem, Long> {
    MedicineItem findById(long id);
    

    @Query(value ="SELECT m.MEDICINE_ID,m.NAME,m.Price    FROM MEDICINE as m INNER JOIN MEDICINEITEM as i on m.MEDICINE_ID  = i.MEDICINES_ID  where i.DOC_ORDER_ID =  :Dr",
    nativeQuery = true)
    Collection<Object[]> findByDocterOrder(@Param("Dr") long id);


    // @Query( value = "SELECT * FROM ADMISSION as a INNER JOIN  PATIENT   as  p on a.ADMISSION_ID  = p.ADMISSION_ID where p.FIRST_NAME  = :firstName",
    //         nativeQuery = true)
    // Collection<MedicineItem> findByDocterOrder(@Param("Dr") long id);

    //@Param("firstName") String firstName
    //package com.cpe.backend.entity;
    //SELECT *    FROM MEDICINE as m INNER JOIN MEDICINEITEM as i on m.MEDICINE_ID  = i.MEDICINE_ID  where i.DOC_ORDER_ID = 2
    
}