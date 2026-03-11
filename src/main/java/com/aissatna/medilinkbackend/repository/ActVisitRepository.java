package com.aissatna.medilinkbackend.repository;

import com.aissatna.medilinkbackend.model.ActVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActVisitRepository extends JpaRepository<ActVisit, Long> {
}