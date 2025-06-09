package com.slowcloud.copypaste.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Data
public class SignUpRequest {
    @NotBlank
    @Size(max = 25)
    private final String username;
    @Size(min = 6, max = 25)
    private final String password;
    @Email
    private final String email;
}
