package com.slowcloud.copypaste.service;

import org.springframework.stereotype.Service;

import com.slowcloud.copypaste.dto.PasteResponseDto;
import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.mapper.PasteResponseMapper;
import com.slowcloud.copypaste.repository.PasteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PasteService {

    private final PasteRepository pasteRepository;
    private final PasteResponseMapper pasteResponseMapper;

    public PasteResponseDto getPasteFromPasteKey(String pasteKey) {
        Paste paste = pasteRepository.findByPasteKey(pasteKey).orElseThrow();
        return pasteResponseMapper.toDto(paste);
    }

    public PasteResponseDto getPasteFromPasteId(long pasteId) {
        Paste paste = pasteRepository.findById(pasteId).orElseThrow();
        return pasteResponseMapper.toDto(paste);
    }

}
