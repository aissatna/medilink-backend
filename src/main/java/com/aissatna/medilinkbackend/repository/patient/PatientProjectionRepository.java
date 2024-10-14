package com.aissatna.medilinkbackend.repository.patient;

import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientProjectionRepository extends PatientRepository {

    @Query(value = "SELECT new com.aissatna.medilinkbackend.dto.patient.PatientLineDTO( " +
            "p.id," +
            "p.firstName," +
            "p.lastName," +
            "p.gender," +
            "p.medicalNumber," +
            "p.birthDate," +
            "p.address," +
            "p.phone," +
            "p.email " +
            ")" +
            "FROM " +
            "Patient p " +
            "WHERE ((CAST (UNACCENT (LOWER(p.firstName)) AS STRING )  LIKE  ('%' || UNACCENT (LOWER(?1) ) || '%')) " +
            "OR (CAST (UNACCENT (LOWER(p.lastName)) AS STRING )  LIKE  ('%' || UNACCENT (LOWER(?1) ) || '%'))" +
            "OR LOWER(p.medicalNumber)  LIKE  ('%' ||  LOWER(?1)  || '%')" +
            " ) "
    )
    Page<PatientLineDTO> getPatientLines(Pageable pageable, String search);
}
