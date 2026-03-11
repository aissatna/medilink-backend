package com.aissatna.medilinkbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Columns;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "acts_visits")
public class ActVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Visit visit;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private ActCatalog act;

    @Column(nullable = false)
    private int quantity = 1;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal priceAtTime;

    private String notes;
}
