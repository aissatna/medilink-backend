package com.aissatna.medilinkbackend.repository.act;

import com.aissatna.medilinkbackend.model.ActTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActTypesRepository extends JpaRepository<ActTypes, Long> {
}