package com.cpe.backend.Doctororder.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity  
@Data  
@NoArgsConstructor  
@Table(name = "MEDICINE")
public class Medicine {  
      
  @Id 
  @SequenceGenerator(name="medicine_seq",sequenceName="medicine_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="medicine_seq") 
  @Column(name = "Medicine_ID", unique = true, nullable = true)
  @Getter@Setter
  private @NonNull Long id;  

  @Getter@Setter
  private @NonNull String Mname;

  @Getter@Setter
  private @NonNull Long price;






}