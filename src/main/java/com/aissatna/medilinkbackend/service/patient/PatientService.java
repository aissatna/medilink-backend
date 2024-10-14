package com.aissatna.medilinkbackend.service.patient;

import com.aissatna.medilinkbackend.dto.patient.PatientDTO;
import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import com.aissatna.medilinkbackend.dto.shared.PageDTO;
import com.aissatna.medilinkbackend.mapper.PatientMapper;
import com.aissatna.medilinkbackend.model.Patient;
import com.aissatna.medilinkbackend.model.enums.GenderEnum;
import com.aissatna.medilinkbackend.repository.patient.PatientProjectionRepository;
import com.aissatna.medilinkbackend.repository.patient.PatientRepository;
import com.aissatna.medilinkbackend.util.date.DateUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class PatientService implements IPatientService {

    private PatientProjectionRepository patientProjectionRepository;
    private PatientRepository patientRepository;

    private PatientMapper patientMapper;

    @Override
    public PageDTO<PatientLineDTO> getPatientsTable(Pageable pageable, String search) {
        search = search != null && !search.isBlank() ? search.toLowerCase() : "";
        return new PageDTO<>(patientProjectionRepository.getPatientLines(pageable, search));
    }

    @Override
    public Patient addPatient(PatientDTO newPatient) {
        Patient newPatientEntity = patientMapper.mapToEntity(newPatient);
        return patientProjectionRepository.save(newPatientEntity);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.findById(id).ifPresent(patient -> {
            patientRepository.delete(patient);
        });
    }

    @Override
    public Patient updatePatient(Long id, PatientDTO patientDTO) {
        Patient existingPatient = getPatientById(id);
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
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Patient with id %s not found", id))
        );
    }
}


