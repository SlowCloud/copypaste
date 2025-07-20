package com.slowcloud.copypaste.security.controller;

import com.slowcloud.copypaste.security.dto.SignInRequest;
import com.slowcloud.copypaste.security.dto.SignUpRequest;
import com.slowcloud.copypaste.security.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SecurityController {

    private final UserService userService;

    // must return username and email
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.createUser(signUpRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> signin(@RequestBody @Valid SignInRequest signInRequest) {
        String jwt = userService.signIn(signInRequest);
        return ResponseEntity.ok(jwt);
    }

}
