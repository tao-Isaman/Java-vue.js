package com.cpe.backend.Bed.repository;

import com.cpe.backend.Bed.entity.Nurse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface NurseRepository extends JpaRepository<Nurse, Long> {
	Nurse findById(long id);
}