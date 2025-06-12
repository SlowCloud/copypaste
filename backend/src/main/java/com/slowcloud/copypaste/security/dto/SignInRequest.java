package com.slowcloud.copypaste.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SignInRequest {
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
}
