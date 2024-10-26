package com.aissatna.medilinkbackend.controller.auth;

import com.aissatna.medilinkbackend.dto.auth.JwtAuthDTO;
import com.aissatna.medilinkbackend.dto.auth.SignInDTO;
import com.aissatna.medilinkbackend.service.auth.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthDTO> signIn(@RequestBody SignInDTO singInDTO){
        return ResponseEntity.ok(authService.signIn(singInDTO));
    }

    @PutMapping("/password/reset")
    public ResponseEntity<Void> resetAndSendNewPassword(@RequestParam String email) {
        authService.resetPasswordAndSendEmail(email);
        return ResponseEntity.noContent().build();
    }

}
