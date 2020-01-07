package com.cpe.backend.Discharge.repository;

import com.cpe.backend.Discharge.entity.Selfcare;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SelfcareRepository extends JpaRepository<Selfcare, Long> {
    Selfcare findById(long id);
}