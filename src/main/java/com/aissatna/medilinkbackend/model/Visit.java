package com.aissatna.medilinkbackend.model;

import com.aissatna.medilinkbackend.model.enums.VisitStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "visits")
public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private VisitStatusEnum status;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    private User nurse;

    @ManyToOne
    private Patient patient;

    @OneToMany(mappedBy = "visit")
    private List<ActVisit> acts = new ArrayList<>();
}
