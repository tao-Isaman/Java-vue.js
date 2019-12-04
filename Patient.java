package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;


import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity  
@Data  
@NoArgsConstructor  
@Table(name = "Patient")
public class Patient {  //สิทธิการรักษา
      
  @Id 
  @SequenceGenerator(name="Patient_SEQ",sequenceName="Patient_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Patient_SEQ") 
  @Column(name = "Patient_ID", unique = true, nullable = true)
  private @NonNull Long id;  
  @Getter @Setter
  private  String name ;  
	
	//benefit
	//gender
	//province
	//ส่วนสูง
	//น้ำหนัก
	//อาการ
	//ที่อยู่
	//อื่น ๆ
//
//
 

}