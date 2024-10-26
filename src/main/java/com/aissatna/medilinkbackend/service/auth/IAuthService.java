package com.aissatna.medilinkbackend.service.auth;

import com.aissatna.medilinkbackend.dto.auth.JwtAuthDTO;
import com.aissatna.medilinkbackend.dto.auth.SignInDTO;

public interface IAuthService {
    JwtAuthDTO signIn(SignInDTO singInDTO);
    void resetPasswordAndSendEmail(String email);
}
