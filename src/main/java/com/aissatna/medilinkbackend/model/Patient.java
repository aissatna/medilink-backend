package com.aissatna.medilinkbackend.model;

import com.aissatna.medilinkbackend.model.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "patients")
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String medicalNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @ManyToOne
    private Cabinet cabinet;

}