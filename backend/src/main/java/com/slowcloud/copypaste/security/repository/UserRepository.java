package com.slowcloud.copypaste.security.repository;

import com.slowcloud.copypaste.security.entity.CopyPasteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CopyPasteUser, Long> {
    
    boolean existsByUsername(String username);

    CopyPasteUser findByUsername(String username);

}
