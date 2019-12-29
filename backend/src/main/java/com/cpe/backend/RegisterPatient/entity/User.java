package com.cpe.backend.RegisterPatient.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity  
@Data  
@NoArgsConstructor  
@Table(name = "User")
public class User {  //สิทธิการรักษา
      
  @Id 
  @Column(name = "User_ID", unique = true, nullable = true)
  private @NonNull String id;  
  private String Password;
  private String Job;

}