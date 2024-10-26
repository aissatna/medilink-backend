package com.aissatna.medilinkbackend.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JwtAuthDTO {
    private String accessToken;
}
