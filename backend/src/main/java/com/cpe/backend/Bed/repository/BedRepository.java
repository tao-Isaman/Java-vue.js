package com.cpe.backend.Bed.repository;

import com.cpe.backend.Bed.entity.Bed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BedRepository extends JpaRepository<Bed, Long> {
	Bed findById(long id);
}