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
@Table(name = "GENDER")
public class Gender {  
      
  @Id 
  @SequenceGenerator(name="GENDER_seq",sequenceName="GENDER_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GENDER_seq") 
  @Column(name = "GENDER_ID", unique = true, nullable = true)
  private Long id;  



  @Getter @Setter
  private String name = "";

  @Getter @Setter
  private String GenderDescription = "";

  @OneToMany(fetch = FetchType.LAZY)
  //mappedBy  = "type"
  private Collection<Patient> patient;

}