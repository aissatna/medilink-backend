package com.aissatna.medilinkbackend.model;


import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "supplement_catalogs")
public class SupplementCatalog implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label;
    private BigDecimal amount;

}
