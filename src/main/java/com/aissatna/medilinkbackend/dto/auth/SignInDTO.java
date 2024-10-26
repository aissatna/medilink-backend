package com.aissatna.medilinkbackend.dto.auth;

import lombok.Data;

@Data
public class SignInDTO {
    private String email;
    private String password;
}
