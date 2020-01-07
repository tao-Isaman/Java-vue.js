package com.cpe.backend.Discharge.entity;

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
@Table(name="SENSORIUM")
public class Sensorium {
    @Id
    @SequenceGenerator(name="SENSORIUM_SEQ",sequenceName="SENSORIUM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SENSORIUM_SEQ")
    @Column(name="SENSORIUM_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY)
    // mappedBy  = "sensorium"
    private Collection<Discharge> disch;

	public void setName(String name) {
        this.name = name;
	}
}