package com.aissatna.medilinkbackend.repository;

import com.aissatna.medilinkbackend.model.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Long> {
}