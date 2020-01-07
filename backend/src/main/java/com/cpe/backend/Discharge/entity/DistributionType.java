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
@Table(name="DISTRIBUTION_TYPE")
public class DistributionType {
    @Id
    @SequenceGenerator(name="DISTRIBUTION_TYPE_SEQ",sequenceName="DISTRIBUTION_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DISTRIBUTION_TYPE_SEQ")
    @Column(name="DISTRIBUTION_TYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY)
    //mappedBy  = "type"
    private Collection<Discharge> disch;

	public void setName(String name) {
        this.name = name;
	}
}