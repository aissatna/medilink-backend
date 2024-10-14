package com.aissatna.medilinkbackend.dto.patient;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PatientDTO {
    private String lastName;
    private String medicalNumber;
    private String gender;
    private String birthDate;
    private String address;
    private String phone;
    private String firstName;
    private String email;
}
