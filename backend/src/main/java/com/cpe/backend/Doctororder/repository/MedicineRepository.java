package com.cpe.backend.Doctororder.repository;



import com.cpe.backend.Doctororder.entity.Medicine;


import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Medicine findById(long id);
}