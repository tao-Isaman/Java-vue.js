package com.cpe.backend.Discharge.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="SELFCARE")
public class Selfcare {

    @Id
    @SequenceGenerator(name="selfcare_seq",sequenceName="selfcare_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="selfcare_seq")
    @Column(name = "SELFCARE_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;


    @OneToMany(fetch = FetchType.LAZY)
    // mappedBy  = "selfcare"
    private Collection<Discharge> disch;


    public void setName(String name) {
        this.name = name;
	}

}
