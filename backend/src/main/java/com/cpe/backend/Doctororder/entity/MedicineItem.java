package com.cpe.backend.Doctororder.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
// import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Entity  
@Data  
@NoArgsConstructor  
@Table(name = "MEDICINEITEM")
public class MedicineItem {  
      
  @Id 
  @SequenceGenerator(name="MEDICINEITEM_seq",sequenceName="MEDICINEITEM_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEDICINEITEM_seq") 
  @Column(name = "MEDICINEITEM_ID", unique = true, nullable = true)
  
  @Getter@Setter
  private @NonNull Long id;  

  @Getter@Setter
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Medicine.class)
  @JoinColumn(name = "MedicineId", insertable = true)
  private Medicine medicine;

  @Getter@Setter
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = MedicationType.class)
  @JoinColumn(name = "MedicationTypeId", insertable = true)
  private MedicationType medicationType;

//   @Getter@Setter
//   @ManyToOne(fetch = FetchType.EAGER, targetEntity = DoctorOrder.class)
//   @JoinColumn(name = "DoctorOrder_ID", insertable = true)
//   private DoctorOrder dr;

@Getter @Setter
@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,targetEntity = DoctorOrder.class)
@JoinColumn(name = "DoctorOrderId")
@JsonIgnore
private DoctorOrder doctorOrder ;

  
}



// package com.cpe.backend.Doctororder.entity;

// import lombok.*;

// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;

// import javax.persistence.SequenceGenerator;
// import javax.persistence.Table;
// // import java.util.Collection;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;


// @Entity  
// @Data  
// @NoArgsConstructor  
// @Table(name = "MEDICINEITEM")
// public class MedicineItem {  
      
//   @Id 
//   @SequenceGenerator(name="MEDICINEITEM_seq",sequenceName="MEDICINEITEM_seq")
//   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MEDICINEITEM_seq") 
//   @Column(name = "MEDICINEITEM_ID", unique = true, nullable = true)
  
//   @Getter@Setter
//   private @NonNull Long id;  

//   @Getter@Setter
//   @ManyToOne(fetch = FetchType.EAGER, targetEntity = Medicine.class)
//   @JoinColumn(name = "MedicineId", insertable = true)
//   private Medicine medicine;

//   @Getter@Setter
//   @ManyToOne(fetch = FetchType.EAGER, targetEntity = MedicationType.class)
//   @JoinColumn(name = "MedicationTypeId", insertable = true)
//   private MedicationType medicationType;

// //   @Getter@Setter
// //   @ManyToOne(fetch = FetchType.EAGER, targetEntity = DoctorOrder.class)
// //   @JoinColumn(name = "DoctorOrder_ID", insertable = true)
// //   private DoctorOrder dr;

// @Getter @Setter
// @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,targetEntity = DoctorOrder.class)
// @JoinColumn(name = "DoctorOrderId")
// @JsonIgnore
// private DoctorOrder doctorOrder ;

  


// }