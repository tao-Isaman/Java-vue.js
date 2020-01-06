package com.cpe.backend.Appointment.repository;

import com.cpe.backend.Appointment.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
