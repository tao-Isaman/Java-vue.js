
package com.cpe.backend.RegisterPatient.repository;




import com.cpe.backend.RegisterPatient.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  


@RepositoryRestResource
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findById(long id);
}

