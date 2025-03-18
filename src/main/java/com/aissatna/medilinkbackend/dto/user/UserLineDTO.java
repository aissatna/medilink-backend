package com.aissatna.medilinkbackend.dto.user;

import com.aissatna.medilinkbackend.model.enums.GenderEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserLineDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phone;
    private String email;
    private String photoUrl;

    public UserLineDTO(Long id, String firstName,
                       String lastName, GenderEnum gender,
                       String phone, String email,
                       String photoUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender.name();
        this.phone = phone;
        this.email = email;
        this.photoUrl = photoUrl;
    }
}

