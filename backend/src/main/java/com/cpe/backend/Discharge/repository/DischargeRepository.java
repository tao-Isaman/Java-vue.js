package com.cpe.backend.Discharge.repository;

import com.cpe.backend.Discharge.entity.Discharge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DischargeRepository extends JpaRepository<Discharge, Long> {
}