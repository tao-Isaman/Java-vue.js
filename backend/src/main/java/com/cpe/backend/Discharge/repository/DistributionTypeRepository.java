package com.cpe.backend.Discharge.repository;

import com.cpe.backend.Discharge.entity.DistributionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DistributionTypeRepository extends JpaRepository<DistributionType, Long> {
	DistributionType findById(long id);
}