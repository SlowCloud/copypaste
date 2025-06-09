package com.slowcloud.copypaste.security.controller;

import com.slowcloud.copypaste.security.dto.SignUpRequest;
import com.slowcloud.copypaste.security.repository.UserRepository;
import com.slowcloud.copypaste.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SecurityController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.createUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
