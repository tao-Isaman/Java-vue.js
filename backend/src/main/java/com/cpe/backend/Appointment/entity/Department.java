package com.cpe.backend.Appointment.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="DEPARTMENT")
public class Department {
    @Id
    @SequenceGenerator(name="department_seq",sequenceName="department_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="department_seq")
    @Column(name="DEPARTMENT_ID",unique = true, nullable = true)
    private @NotNull Long id;
    @Getter @Setter
    private @NotNull String departmentlSelect;



    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Appointment> rent;
}
