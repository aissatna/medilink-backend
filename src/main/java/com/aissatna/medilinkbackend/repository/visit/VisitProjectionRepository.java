package com.aissatna.medilinkbackend.repository.visit;

import com.aissatna.medilinkbackend.dto.visit.nurse.NurseVisitLineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VisitProjectionRepository extends VisitRepository {

      @Query(value = "SELECT new com.aissatna.medilinkbackend.dto.visit.nurse.NurseVisitLineDTO( " +
            "v.id," +
            "v.date," +
            "v.startTime," +
            "v.endTime," +
            "v.status," +
            "v.patient.id," +
            "v.patient.firstName," +
            "v.patient.lastName," +
            "v.patient.address," +
            "v.patient.phone" +
            ") " +
            "FROM " +
            "Visit v " +
            "WHERE v.nurse.id = ?1 "
            
    )
    Page<NurseVisitLineDTO> getNurseVisitLines(Pageable pageable,Long currentUserId);

}
