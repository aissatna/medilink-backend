package com.aissatna.medilinkbackend.service.email;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
@Slf4j

public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;

    @Override
    @Async("asyncTaskExecutor")
    public void sendNewPassword(String email, String newPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("MediLink - Your New Password");

            // Create HTML content
            String htmlContent = "<p>Hello,</p>"
                    + "<p>Your password for your MediLink account has been reset. Your new password is: "
                    + "<b>" + newPassword + "</b></p>"
                    + "<p>Please log in to MediLink and change your password immediately.</p>"
                    + "<p>Thank you,</p>"
                    + "<p>The MediLink Team</p>";

            helper.setText(htmlContent, true);
            mailSender.send(message);

            log.info("New password email sent to {}", email);
        } catch (Exception e) {
            log.error("Failed to send email to {}", email, e);
        }
    }
}
