package com.aissatna.medilinkbackend.dto.user.nurse;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;

    private boolean avatarUpdated;
}
