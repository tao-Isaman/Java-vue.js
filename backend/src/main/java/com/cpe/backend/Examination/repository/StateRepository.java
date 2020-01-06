package com.cpe.backend.Examination.repository;


import com.cpe.backend.Examination.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Long> {
    State findById(long id);
}