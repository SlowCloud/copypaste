package com.slowcloud.copypaste.paste.repository;

import com.slowcloud.copypaste.paste.entity.Paste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasteRepository extends JpaRepository<Paste, Long> {
}
