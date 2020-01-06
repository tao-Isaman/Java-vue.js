package com.cpe.backend.Appointment.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="TYPECAUSE")
public class TypeCause {
    @Id
    @SequenceGenerator(name="typecause_seq",sequenceName="typecause_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="typecause_seq")
    @Column(name="TYPECAUSE_ID",unique = true, nullable = true)
    private @NotNull Long id;
    private @NotNull String typecauseSelect;



    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Appointment> rent;
}
