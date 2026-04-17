package com.aissatna.medilinkbackend.model;

import com.aissatna.medilinkbackend.model.enums.VisiModeEnum;
import com.aissatna.medilinkbackend.model.enums.VisitStatusEnum;
import com.aissatna.medilinkbackend.model.VisitSupplement;
import com.aissatna.medilinkbackend.model.ActVisit;
import com.aissatna.medilinkbackend.model.Patient;
import com.aissatna.medilinkbackend.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "visits")
public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String meetingLink;
    private Instant validatedAt;

    @Enumerated(EnumType.STRING)
    private VisitStatusEnum status;
    @Enumerated(EnumType.STRING)
    private VisiModeEnum mode;


    @ManyToOne
    private User nurse;

    @ManyToOne
    private Patient patient;

    @OneToMany(mappedBy = "visit")
    private List<ActVisit> acts = new ArrayList<>();

    @OneToMany(mappedBy = "visit")
    private Set<VisitSupplement> supplements = new HashSet<>();
}
