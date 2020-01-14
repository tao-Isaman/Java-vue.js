package com.cpe.backend.RegisterPatient.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;;



@Entity  
@Data
@NoArgsConstructor
@Table(name = "PATIENT")
public class Patient { // ผู้ป่วย
      
   
  
   
  @Id
  @Column(name = "NATIONAL_ID", unique = true, nullable = true)
  @NotNull
  @Min(1000000000000L)
  @Digits(integer = 13 , fraction = 1)
  private  Long nationalID;  


  @NotNull
  @Size(min = 5 ,max = 30)
  private String name = "";


  @NotNull
  @Size(min = 5 ,max = 240)
  private String Address = "";



  @NotNull
  @Size(min = 5 ,max = 240)
  private String initialSsym = "";
  
  @NotNull
  @Max(300)
  @Min(30)
  private int hight ;


  @NotNull
  @Max(300)
  @Min(1)
  private int weigth ;
  
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Benefit.class)
  @JoinColumn(name = "BENEFIT_ID", insertable = true)
  private Benefit benefit ;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
  @JoinColumn(name = "PROVINCE_ID", insertable = true)
  private Province province ;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
  @JoinColumn(name = "GENDER_ID", insertable = true)
  private Gender gender;

  // @OneToMany(fetch = FetchType.LAZY)
  // // mappedBy  = "rentPatient"
  // private Collection<Appointment> rent;


  //benefit
  //Province
  //Gender




      
}