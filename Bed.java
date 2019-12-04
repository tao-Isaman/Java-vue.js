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
@Table(name="BED")
public class Bed {
    @Id
    @SequenceGenerator(name="BED_SEQ",sequenceName="BED_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BED_SEQ")
   
    @Column(name="Bed_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String type;
    private @NonNull Integer price;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "createdBy"
    private Collection<Reservation> reservation;

    public void setName(String type) {
        this.type = type;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getName(){

        return type;
    }
}
