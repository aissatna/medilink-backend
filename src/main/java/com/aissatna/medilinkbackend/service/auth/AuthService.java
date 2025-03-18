package com.aissatna.medilinkbackend.service.auth;

import com.aissatna.medilinkbackend.dto.auth.JwtAuthDTO;
import com.aissatna.medilinkbackend.dto.auth.SignInDTO;
import com.aissatna.medilinkbackend.exception.ResourceNotFoundException;
import com.aissatna.medilinkbackend.repository.user.UserRepository;
import com.aissatna.medilinkbackend.service.auth.jwt.JwtService;
import com.aissatna.medilinkbackend.service.email.IEmailService;
import com.aissatna.medilinkbackend.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final IEmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override

    public JwtAuthDTO signIn(SignInDTO singInDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(singInDTO.getEmail(), singInDTO.getPassword()));
        var user = userRepository.findByEmail(singInDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Email %s not found", singInDTO.getEmail())));

        return new JwtAuthDTO().setAccessToken(jwtService.generateToken(user));
    }

    @Override
    public void resetPasswordAndSendEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Email %s not found", email)));
        String newPassword = PasswordUtil.generateRandomPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        emailService.sendNewPassword(email, newPassword,false);
    }
}
