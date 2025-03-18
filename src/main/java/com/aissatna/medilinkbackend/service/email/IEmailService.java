package com.aissatna.medilinkbackend.service.email;

import org.springframework.scheduling.annotation.Async;

public interface IEmailService {
    void sendNewPassword(String email, String newPassword, boolean isNewAccount);
    void sendExportAttachment(String email, String subject, String fileName, byte[] fileBytes);
}
