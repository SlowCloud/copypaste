package com.slowcloud.copypaste.paste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slowcloud.copypaste.paste.entity.Paste;

public interface PasteRepository extends JpaRepository<Paste, Long> {
}
