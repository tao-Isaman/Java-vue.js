package com.cpe.backend.RegisterPatient.repository;



import java.util.Optional;

import com.cpe.backend.RegisterPatient.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);
}