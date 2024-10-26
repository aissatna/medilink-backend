package com.aissatna.medilinkbackend.service.email;

public interface IEmailService {
    void sendNewPassword(String email, String newPassword);
}
