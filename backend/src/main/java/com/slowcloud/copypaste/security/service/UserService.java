package com.slowcloud.copypaste.security.service;

import com.slowcloud.copypaste.security.dto.SignInRequest;
import com.slowcloud.copypaste.security.dto.SignUpRequest;
import com.slowcloud.copypaste.security.entity.Authority;
import com.slowcloud.copypaste.security.entity.CopyPasteUser;
import com.slowcloud.copypaste.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    public static final int EXPIRE_SECOND = 600;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public void createUser(SignUpRequest user) {

        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException();
        }

        CopyPasteUser copyPasteUser = CopyPasteUser.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .authority(Authority.USER)
                .build();

        userRepository.save(copyPasteUser);

    }

    public String signIn(SignInRequest signInRequest) {

        if(!userRepository.existsByUsername(signInRequest.getUsername())) {
            throw new RuntimeException();
        }

        CopyPasteUser user = userRepository.findByUsername(signInRequest.getUsername());
        if(passwordEncoder.matches(user.getPassword(), signInRequest.getPassword())) {
            throw new RuntimeException();
        }

        var now = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("copypaste")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRE_SECOND))
                .subject("access_token")
                .claim("username", user.getUsername())
                .build();

        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet));

        return jwt.getTokenValue();

    }

//    public void updateUser(UserDetails user) {
//
//    }
//
//    public void deleteUser(String username) {
//
//    }
//
//    public void changePassword(String oldPassword, String newPassword) {
//
//    }
//
//    public boolean userExists(String username) {
//
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}
