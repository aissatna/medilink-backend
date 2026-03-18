package com.aissatna.medilinkbackend.repository.visit;

import com.aissatna.medilinkbackend.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}