package com.aissatna.medilinkbackend.mapper;

import com.aissatna.medilinkbackend.configuration.app.AppContext;
import com.aissatna.medilinkbackend.dto.patient.PatientDTO;
import com.aissatna.medilinkbackend.dto.patient.PatientLineDTO;
import com.aissatna.medilinkbackend.model.Patient;
import com.aissatna.medilinkbackend.model.enums.GenderEnum;
import com.aissatna.medilinkbackend.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientMapper {
    private final AppContext appContext;


    public Patient mapToEntity(PatientDTO dto) {
        GenderEnum gender = dto.getGender().equals("M") ? GenderEnum.M : GenderEnum.F;
        return new Patient()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setGender(gender)
                .setMedicalNumber(dto.getMedicalNumber())
                .setBirthDate(DateUtil.parseLocalDate(dto.getBirthDate()))
                .setEmail(dto.getEmail())
                .setPhone(dto.getPhone())
                .setAddress(dto.getAddress())
                .setCabinet(appContext.getCurrentUser().getCabinet());
    }

    public PatientLineDTO mapToDTO(Patient patient) {
        return new PatientLineDTO()
                .setId(patient.getId())
                .setFirstName(patient.getFirstName())
                .setLastName(patient.getLastName())
                .setMedicalNumber(patient.getMedicalNumber())
                .setGender(patient.getGender().name())
                .setBirthDate(DateUtil.formatLocalDateToString(patient.getBirthDate(), DateUtil.DD_MM_YYYY, DateUtil.PARIS_TIME_ZONE))
                .setVisits(0)
                .setAddress(patient.getAddress())
                .setPhone(patient.getPhone())
                .setEmail(patient.getEmail());
    }

}
