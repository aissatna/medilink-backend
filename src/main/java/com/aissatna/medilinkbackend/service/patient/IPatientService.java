package com.aissatna.medilinkbackend.service.patient;

import com.aissatna.medilinkbackend.dto.patient.PatientDTO;
import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.model.Patient;
import org.springframework.data.domain.Pageable;

public interface IPatientService {
    PageDTO<PatientLineDTO> getPatientsTable(Pageable pageable, String search);
    Patient addPatient(PatientDTO patientDTO);
    void deletePatient(Long patientId);
    Patient updatePatient(Long patientId, PatientDTO patientDTO);
    Patient getPatientById(Long patientId);
}
