package com.aissatna.medilinkbackend.model.enums;

import com.aissatna.medilinkbackend.model.Visit;
import com.aissatna.medilinkbackend.model.SupplementCatalogs;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "visit_supplements")
public class VisitSupplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private BigDecimal amountAtTime;
    
    @Column(nullable = false)
    private int quantity = 1;
    
    @ManyToOne
    private SupplementCatalog suppCatalog;

    @ManyToOne
    private Visit visit;

}
