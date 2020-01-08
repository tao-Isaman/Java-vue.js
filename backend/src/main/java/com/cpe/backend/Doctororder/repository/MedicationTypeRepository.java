package com.cpe.backend.Doctororder.repository;
import com.cpe.backend.Doctororder.entity.MedicationType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
public interface MedicationTypeRepository extends JpaRepository<MedicationType, Long> {
    MedicationType findById(long id);
    //MedicationType findMedicationTypeName(String medicationTypeName);
}