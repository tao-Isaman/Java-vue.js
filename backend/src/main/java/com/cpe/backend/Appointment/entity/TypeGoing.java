package com.cpe.backend.Appointment.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="TYPEGOING")
public class TypeGoing {
    @Id
    @SequenceGenerator(name="typegoing_seq",sequenceName="typegoing_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="typegoing_seq")
    @Column(name="TYPEGOILNG_ID",unique = true, nullable = true)
    private @NotNull Long id;
    private @NotNull String typegoingSelect;


    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Appointment> rent;
}
