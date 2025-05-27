package com.slowcloud.copypaste.service;

import org.springframework.stereotype.Service;

import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.repository.PasteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PasteService {

    private final PasteRepository pasteRepository;

    public Paste getPasteFromPasteKey(String pasteKey) {
        return pasteRepository.findByPasteKey(pasteKey).orElseThrow();
    }

    public Paste getPasteFromPasteId(long pasteId) {
        return pasteRepository.findById(pasteId).orElseThrow();
    }

}
