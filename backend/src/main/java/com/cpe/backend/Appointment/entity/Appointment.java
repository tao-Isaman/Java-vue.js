package com.cpe.backend.Appointment.entity;

import lombok.*;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import com.cpe.backend.RegisterPatient.entity.Patient;


@Data
@Entity
@NoArgsConstructor
@Table(name="APPOINTMENT")
public class Appointment {
    @Id
    @SequenceGenerator(name="appointment_seq",sequenceName="appointment_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="appointment_seq")
    @Column(name="APPOINTMENT_ID",unique = true, nullable = true)
    
     
    private @NotNull Long id;
    private @NotNull Date date;


    @Size(min = 5,max = 20)
    @Pattern(regexp = "[ก-๙]*")
    @NotNull private String Additional = "";

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeCause.class)
    @JoinColumn(name = "TYPECAUSE_ID", insertable = true)
    private TypeCause creatTypeCause;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Department.class)
    @JoinColumn(name = "DEPARTMENT_ID", insertable = true)
    private Department creatDepartment;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeGoing.class)
    @JoinColumn(name = "TYPEGOING_ID", insertable = true)
    private TypeGoing creatTypeGoing;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Patient.class)
    @JoinColumn(name = "PATINET_ID", insertable = true)
    private Patient creatPatient;



}
