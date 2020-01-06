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

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

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

    @Getter @Setter
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Patient.class)     
    @JoinColumn(name = "Patient_ID", insertable = true)     
    private Patient patient; 
 
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Department.class)     
    @JoinColumn(name = "Department_ID", insertable = true)     
    private Department department; 

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bed.class)     
    @JoinColumn(name = "Beds_ID", insertable = true)     
    private Bed bed; 
 
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nurse.class)     
    @JoinColumn(name = "Nurse_ID", insertable = true)     
    private Nurse nurse;

	// public void setDepartment(Department department) {
       
    // }

	// public void setBed(Bed bed) {
        
	// }

	// public void setDate(Date date) {
        
	// }

	// public void setPatient(Patient patient) {
        
	// }

	// public void setNurse(Nurse nurse) {
        
	// } 
}