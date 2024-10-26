package com.aissatna.medilinkbackend.service.patient;

import com.aissatna.medilinkbackend.configuration.app.AppContext;
import com.aissatna.medilinkbackend.dto.patient.PatientDTO;
import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.exception.ResourceNotFoundException;
import com.aissatna.medilinkbackend.mapper.PatientMapper;
import com.aissatna.medilinkbackend.model.Patient;
import com.aissatna.medilinkbackend.model.enums.GenderEnum;
import com.aissatna.medilinkbackend.repository.patient.PatientProjectionRepository;
import com.aissatna.medilinkbackend.repository.patient.PatientRepository;
import com.aissatna.medilinkbackend.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class PatientService implements IPatientService {

    private final PatientProjectionRepository patientProjectionRepository;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final AppContext appContext;

    @Override
    public PageDTO<PatientLineDTO> getPatientsTable(Pageable pageable, String search) {
        search = search != null && !search.isBlank() ? search.toLowerCase() : "";
        return new PageDTO<>(patientProjectionRepository.getPatientLines(pageable, appContext.getCurrentUser().getCabinet().getId(), search));
    }

    @Override
    public Patient addPatient(PatientDTO newPatient) {
        Patient newPatientEntity = patientMapper.mapToEntity(newPatient);
        return patientProjectionRepository.save(newPatientEntity);
    }

    @Override
    public void deletePatient(Long patientId) {
        patientRepository.delete(getPatientById(patientId));
    }

    @Override
    public Patient updatePatient(Long patientId, PatientDTO patientDTO) {
        Patient existingPatient = getPatientById(patientId);
        existingPatient.setFirstName(patientDTO.getFirstName())
                .setLastName(patientDTO.getLastName())
                .setGender(patientDTO.getGender().equals("M") ? GenderEnum.M : GenderEnum.F)
                .setMedicalNumber(patientDTO.getMedicalNumber())
                .setBirthDate(DateUtil.parseLocalDate(patientDTO.getBirthDate()))
                .setEmail(patientDTO.getEmail())
                .setPhone(patientDTO.getPhone())
                .setAddress(patientDTO.getAddress());

        return patientProjectionRepository.save(existingPatient);
    }


    @Override
    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Patient with id %s not found", patientId))
        );
    }
}


