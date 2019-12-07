package com.cpe.backend.Bed.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="NURSE")
public class Nurse {
    @Id
    @SequenceGenerator(name="NURSE_SEQ",sequenceName="NURSE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NURSE_SEQ")
   
    @Column(name="NURSE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "createdBy"
    private Collection<Reservation> reservation;

	public void setName(String name) {
        this.name = name;
	}

}