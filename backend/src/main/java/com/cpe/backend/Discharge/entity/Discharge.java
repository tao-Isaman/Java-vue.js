package com.cpe.backend.Discharge.entity;

import lombok.*;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.RegisterPatient.entity.Patient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

//import com.cpe.backend.entity.Employee;;

@Data
@Entity
@NoArgsConstructor
@Table(name="DISCHARGE")
public class Discharge {

    @Id
    @SequenceGenerator(name="discharge_seq",sequenceName="discharge_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="discharge_seq")
    @Column(name = "DISCHARGE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="LEAVE_DATE")
    private @NonNull Date leave;

    @Column(name="NOTE")
    private @NonNull String note;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Sensorium.class)
    @JoinColumn(name = "SENSORIUM_ID", insertable = true)
    private Sensorium sensorium;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Selfcare.class)
    @JoinColumn(name = "SELFCARE_ID", insertable = true)
    private Selfcare selfcare;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Patient.class)
    @JoinColumn(name = "PATIENT_ID", insertable = true)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DistributionType.class)
    @JoinColumn(name = "DISTRIBUTION_TYPE_ID", insertable = true)
    private DistributionType type;

	public void setPatient(Patient patient) {
        this.patient = patient;
	}

	public void setSensorium(Sensorium sensorium) {
        this.sensorium = sensorium;
	}

	public void setSelfcare(Selfcare selfcare) {
        this.selfcare = selfcare;
	}

	public void setDistributionType(DistributionType type) {
        this.type = type;
	}

	 public void setLeave(Date leave) {
         this.leave = leave;
         }
        
        public void setNote(String note){
        this.note = note;
        }
}