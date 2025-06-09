package com.slowcloud.copypaste.security.service;

import com.slowcloud.copypaste.security.dto.SignUpRequest;
import com.slowcloud.copypaste.security.entity.CopyPasteUser;
import com.slowcloud.copypaste.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(SignUpRequest user) {

        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException();
        }

        CopyPasteUser copyPasteUser = CopyPasteUser.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build();

        userRepository.save(copyPasteUser);

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
