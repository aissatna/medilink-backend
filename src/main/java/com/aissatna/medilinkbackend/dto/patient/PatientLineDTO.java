package com.aissatna.medilinkbackend.dto.patient;

import com.aissatna.medilinkbackend.model.Patient;
import com.aissatna.medilinkbackend.model.enums.GenderEnum;
import com.aissatna.medilinkbackend.util.date.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PatientLineDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String medicalNumber;
    private String gender;
    private String birthDate;
    private int visits;

    private String address;
    private String phone;
    private String email;

    public PatientLineDTO(Long id, String firstName, String lastName,
                          GenderEnum genderEnum, String medicalNumber,
                          LocalDate birthDate, String address, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.medicalNumber = medicalNumber;
        this.gender = genderEnum.name();
        this.birthDate = DateUtil.formatLocalDateToString(birthDate, DateUtil.DD_MM_YYYY, DateUtil.PARIS_TIME_ZONE);
        this.visits = 0;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

}
