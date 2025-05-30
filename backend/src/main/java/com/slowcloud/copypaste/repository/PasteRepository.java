package com.slowcloud.copypaste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slowcloud.copypaste.entity.Paste;


public interface PasteRepository extends JpaRepository<Paste, Long> {
}
