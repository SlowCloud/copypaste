package com.slowcloud.copypaste.service;

import org.springframework.stereotype.Service;

import com.slowcloud.copypaste.dto.PasteResponseDto;
import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.repository.PasteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PasteService {

    private final PasteRepository pasteRepository;

    public PasteResponseDto getPasteFromPasteKey(String pasteKey) {
        Paste paste = pasteRepository.findByPasteKey(pasteKey).orElseThrow();
        return PasteResponseDto.builder()
            .id(paste.getId())
            .content(paste.getContent())
            .syntaxHighlight(paste.getSyntaxHighlight())
            .build();
    }

    public PasteResponseDto getPasteFromPasteId(long pasteId) {
        Paste paste = pasteRepository.findById(pasteId).orElseThrow();
        return PasteResponseDto.builder()
            .id(paste.getId())
            .content(paste.getContent())
            .syntaxHighlight(paste.getSyntaxHighlight())
            .build();
    }

}
