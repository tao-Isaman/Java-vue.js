package com.cpe.backend.Payment.repository;
import com.cpe.backend.Payment.entity.TypeBank;
import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource
public interface  TypeBankRepository extends JpaRepository<TypeBank, Long> {
    TypeBank findById(long id);

}