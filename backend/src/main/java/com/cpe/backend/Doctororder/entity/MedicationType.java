package com.cpe.backend.Doctororder.entity;

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
@Setter @Getter
@NoArgsConstructor
@Table(name="MedicationType")
public class MedicationType{
    @Id
    @SequenceGenerator(name="medicationType_seq",sequenceName = "medicationType_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "medicationType_seq")
    @Column(name="medicationType_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String TypeName;

}

