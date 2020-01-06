package com.cpe.backend.Examination.repository;

import com.cpe.backend.Examination.entity.ExaminationSystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ExaminationRepository extends JpaRepository<ExaminationSystem, Long> {
    ExaminationSystem findById(long id);
}