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
@Table(name = "PROVINCE")
public class Province {  //จังหวัด
      
  @Id 
  @SequenceGenerator(name="PROVINCE_SEQ",sequenceName="PROVINCE_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROVINCE_SEQ")
  @Column(name="PROVINCE_ID",unique = true, nullable = true)
  private Long id;  


  @Getter @Setter
  private String name ;

  @Getter @Setter
  private String description ;

  @OneToMany(fetch = FetchType.LAZY)
  //mappedBy  = "type"
  private Collection<Patient> patient;

  

  
  //benefit
  //Province
  //Gender




      
}