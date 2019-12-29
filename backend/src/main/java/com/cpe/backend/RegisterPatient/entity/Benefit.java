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
@Table(name = "BENEFIT")
public class Benefit {  //สิทธิการรักษา
      
  @Id 
  @SequenceGenerator(name="BENEFIT_seq",sequenceName="BENEFIT_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BENEFIT_seq") 
  @Column(name = "BENEFIT_ID", unique = true, nullable = true)
  private @NonNull Long id;  
  private  String name ;  
  private String BeneDescriptstion ;
  private int dicount  ;
  @OneToMany(fetch = FetchType.LAZY)
  //mappedBy  = "type"
  private Collection<Patient> patient;

}