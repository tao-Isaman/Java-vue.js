package com.cpe.backend.Appointment.repository;

import com.cpe.backend.Appointment.entity.TypeGoing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TypeGoingRepository extends JpaRepository<TypeGoing, Long> {
    TypeGoing findById(long id);
}
