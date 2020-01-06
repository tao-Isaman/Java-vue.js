package com.cpe.backend.Examination.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import com.cpe.backend.Examination.entity.Doctor;
import com.cpe.backend.RegisterPatient.entity.Patient;;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Examination_SYSTEM")
public class ExaminationSystem {//ข้อวินิจฉัย

    @Id
    @SequenceGenerator(name="examination_system_seq",sequenceName="examination_system_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="examination_system_seq")
    @Column(name = "Examination_System_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="CHECK_DATE")
    private @NonNull String checkDate;

    private int pulse;
    private String pressure = "";
    private String symptom ="";

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Doctor.class)
    @JoinColumn(name = "Doctor_ID", insertable = true)
    private Doctor checkBy ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Duration.class)
    @JoinColumn(name = "Duration_ID", insertable = true)
    private Duration duration ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Patient.class)
    @JoinColumn(name = "Patient_ID", insertable = true)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = State.class)
    @JoinColumn(name = "State_ID", insertable = true)
    private State state;

    /*public void setCheckdate(String x){


        this.checkDate = x;
    }*/





}