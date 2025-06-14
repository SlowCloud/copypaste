package com.slowcloud.copypaste.security.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.slowcloud.copypaste.security.dto.SignInRequest;
import com.slowcloud.copypaste.security.dto.SignUpRequest;
import com.slowcloud.copypaste.security.entity.CopyPasteUser;
import com.slowcloud.copypaste.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String EMAIL = "fake@email.com";

    @Mock
    UserRepository userRepository;

    static PasswordEncoder passwordEncoder;
    static JwtEncoder jwtEncoder;
    static JwtDecoder jwtDecoder;
    UserService userService;

    @BeforeAll
    static void staticSetup() throws JOSEException {

        passwordEncoder = new BCryptPasswordEncoder();

        RSAKey jwk = new RSAKeyGenerator(2048).generate();
        JWKSet jwkSet = new JWKSet(jwk);
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(jwkSet);
        jwtEncoder = new NimbusJwtEncoder(jwkSource);

        jwtDecoder = NimbusJwtDecoder.withPublicKey(jwk.toRSAPublicKey()).build();

    }

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository, passwordEncoder, jwtEncoder);
    }

    @Test
    void createUserTest() {

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();

        userService.createUser(signUpRequest);

        Mockito.verify(userRepository, Mockito.times(1))
                .save(Mockito.any(CopyPasteUser.class));

    }

    @Test
    void createExistUserTest() {

        Mockito.when(userRepository.existsByUsername(Mockito.any(String.class)))
                .thenReturn(true);

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();

        assertThrows(RuntimeException.class, () -> userService.createUser(signUpRequest));

    }

    @Test
    void signInTest() {

        SignInRequest signInRequest = SignInRequest.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        CopyPasteUser copyPasteUser =CopyPasteUser.builder()
                .username(USERNAME)
                .password(passwordEncoder.encode(PASSWORD))
                .build();

        Mockito.when(userRepository.existsByUsername(Mockito.any(String.class)))
                .thenReturn(true);

        Mockito.when(userRepository.findByUsername(Mockito.any(String.class)))
                .thenReturn(copyPasteUser);

        String responseToken = userService.signIn(signInRequest);

        assertNotNull(responseToken);
        assertNotEquals(0, responseToken.length());

        assertDoesNotThrow(() -> jwtDecoder.decode(responseToken));

    }

}