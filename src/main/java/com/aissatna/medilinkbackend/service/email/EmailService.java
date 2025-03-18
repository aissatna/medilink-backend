package com.aissatna.medilinkbackend.service.email;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
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
    public void sendNewPassword(String email, String newPassword, boolean isNewAccount) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("MediLink");
            helper.setTo(email);
            helper.setSubject(isNewAccount ? "MediLink - Welcome! Your Account is Ready" : "MediLink - Your New Password");
            // Create HTML content
            String htmlContent = isNewAccount ?
                    """
                            <p>Hello,</p>
                            <p>Welcome to MediLink! Your account has been successfully created. Here is your initial password: 
                            <b>%s</b></p>
                            <p>We recommend logging in and changing your password immediately for security reasons.</p>
                            <p>Thank you for joining us!</p>
                            <p>The MediLink Team</p>
                            """.formatted(newPassword)
                    :
                    """
                            <p>Hello,</p>
                            <p>Your password for your MediLink account has been reset. Your new password is: 
                            <b>%s</b></p>
                            <p>Please log in to MediLink and change your password immediately.</p>
                            <p>Thank you,</p>
                            <p>The MediLink Team</p>
                            """.formatted(newPassword);

            helper.setText(htmlContent, true);
            mailSender.send(message);

            log.info("{} email sent to {}", isNewAccount ? "Account creation" : "Password reset", email);
        } catch (Exception e) {
            log.error("Failed to send email to {}", email, e);
        }
    }


    @Async("asyncTaskExecutor")
    @Override
    public void sendExportAttachment(String email, String subject, String fileName, byte[] fileBytes) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("MediLink");
            helper.setTo(email);
            helper.setSubject(subject);

            // Create HTML content
            String htmlContent = """
                    <p>Hello,</p>
                    <p>Please find attached the requested export file.</p>
                    <p>If you have any questions, feel free to contact us.</p>
                    <p>Best regards,</p>
                    <p>The MediLink Team</p>
                    """;
            helper.setText(htmlContent, true);

            // Attach the file
            helper.addAttachment(fileName, new ByteArrayResource(fileBytes));

            mailSender.send(message);

            log.info("Export email [subject: {}] sent to {}",subject,email);
        } catch (Exception e) {
            log.error("Failed to send export email to {}", email, e);
        }
    }
}

