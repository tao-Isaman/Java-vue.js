package com.cpe.backend.Doctororder.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.Examination.entity.ExaminationSystem;
import com.cpe.backend.Payment.entity.Payment;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name = "DOCTORORDER")
public class DoctorOrder { 

  @Id
  @SequenceGenerator(name = "DOCTORORDER_seq", sequenceName = "DOCTORORDER_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCTORORDER_seq")
  @Column(name = "DOCTORORDER_ID", unique = true, nullable = true)
  private @NonNull Long id;

  @Column(name = "DATE")
  private @NonNull Date Date;

  // @Column(name = "DrugAllergies")
  private @NonNull String allergies = "";

  // @Column(name = "AllergiesReaction")
  private @NonNull String reaction = "";

  @JsonIgnoreProperties("doctorOrder")
  //@JsonBackReference
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "doctorOrder")
  private Payment payment;

  @OneToMany(fetch = FetchType.EAGER , mappedBy="doctorOrder")
  //mappedBy  = "type"
  @JsonManagedReference
  private Collection<MedicineItem> medicineItem;

     @OneToOne(fetch = FetchType.EAGER)
     private ExaminationSystem ex;



}