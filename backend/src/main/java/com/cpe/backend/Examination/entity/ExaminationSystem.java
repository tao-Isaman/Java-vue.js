package com.cpe.backend.Examination.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

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
    @NotNull
    private Date checkDate;


    @Max(200)
    @Min(1)
    @NotNull
    private int pulse;

    @NotNull
    @Pattern(regexp = "\\d{2}[D]\\d{3}[S]")
    private String pressure;

    @NotNull
    @Size(min = 5 ,max = 250)
    private String symptom;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Doctor.class)
    @JoinColumn(name = "Doctor_ID", insertable = true)
    @NotNull
    private Doctor checkBy ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Duration.class)
    @JoinColumn(name = "Duration_ID", insertable = true)
    @NotNull
    private Duration duration ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Patient.class)
    @JoinColumn(name = "Patient_ID", insertable = true)

    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = State.class)
    @JoinColumn(name = "State_ID", insertable = true)
    @NotNull
    private State state;

    /*public void setCheckdate(String x){


        this.checkDate = x;
    }*/





}