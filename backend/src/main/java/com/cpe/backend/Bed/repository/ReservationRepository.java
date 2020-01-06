package com.cpe.backend.Bed.repository;

import java.util.Collection;

import com.cpe.backend.Bed.entity.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findById(long id);
    @Query(value ="SELECT b.BED_ID,b.Price,b.TYPE,r.RESERV_DATE,r.PATIENT_ID,r.RESERVATION_ID,p.name FROM BED as b  INNER JOIN RESERVATION as r inner join PATIENT as p on b.BED_ID = r.BEDS_ID and p.NATIONAL_ID = r.PATIENT_ID  where PATIENT_ID =:nationalID",
     nativeQuery = true)
     Collection<Object[]> findByPatientID(@Param("nationalID") long nationalID);

    // @Query(value ="SELECT m.MEDICINE_ID,m.NAME,m.Price    FROM MEDICINE as m INNER JOIN MEDICINEITEM as i on m.MEDICINE_ID  = i.MEDICINES_ID  where i.DOC_ORDER_ID =  :Dr",
    // nativeQuery = true)
    // Collection<Object[]> findByDocterOrder(@Param("Dr") long id);


}