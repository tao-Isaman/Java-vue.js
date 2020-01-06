package com.cpe.backend.Examination.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
@Data
@NoArgsConstructor
@Table(name = "DURATION")
public class Duration {

    @Id
    @SequenceGenerator(name="duration_seq",sequenceName="duration_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="duration_seq")
    @Column(name = "Duration_ID", unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY)
    //mappedBy  = "type"
    private Collection<ExaminationSystem> ExaminationSystem;





}