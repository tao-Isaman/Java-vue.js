package com.cpe.backend.Discharge.repository;

import com.cpe.backend.Discharge.entity.Sensorium;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SensoriumRepository extends JpaRepository<Sensorium, Long> {
	Sensorium findById(long id);
}