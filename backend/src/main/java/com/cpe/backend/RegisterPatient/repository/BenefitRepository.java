package com.cpe.backend.RegisterPatient.repository;



import com.cpe.backend.RegisterPatient.entity.Benefit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource
public interface BenefitRepository extends JpaRepository<Benefit, Long> {

    Benefit findById(long id);
}