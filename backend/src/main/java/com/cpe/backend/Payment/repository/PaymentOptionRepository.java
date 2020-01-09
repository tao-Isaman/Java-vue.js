package com.cpe.backend.Payment.repository;



import com.cpe.backend.Payment.entity.PaymentOption;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource

public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Long> {

    PaymentOption findById(long id);
}