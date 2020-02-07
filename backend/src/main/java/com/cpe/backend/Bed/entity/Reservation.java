package com.cpe.backend.Bed.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.Appointment.entity.Department;
import com.cpe.backend.RegisterPatient.entity.Patient;
//import java.util.Optional;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;;

@Data
@Entity
@NoArgsConstructor
@Table(name="RESERVATION")
public class Reservation {

    @Id
    @SequenceGenerator(name="reservation_seq",sequenceName="reservation_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="reservation_seq")
   
    @Column(name = "RESERVATION_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull Date ReservDate;

    @NotNull
    @Size(min=5,max=240)
    private String note = "";

    @NotNull
    @Pattern(regexp = "[ABCD]\\d{1,9}")
    @Size(max = 2)
    private String zone ="";

    @Getter @Setter
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Patient.class)     
    @JoinColumn(name = "Patient_ID", insertable = true)     
    private Patient patient; 
 
    @Getter @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Department.class)     
    @JoinColumn(name = "Department_ID", insertable = true)     
    private Department department; 

    @Getter @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bed.class)     
    @JoinColumn(name = "Beds_ID", insertable = true)     
    private Bed bed; 
 
    @Getter @Setter
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nurse.class)     
    @JoinColumn(name = "Nurse_ID", insertable = true)     
    private Nurse nurse;

}