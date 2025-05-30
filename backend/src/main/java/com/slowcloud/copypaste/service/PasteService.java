package com.slowcloud.copypaste.service;

import org.springframework.stereotype.Service;

import com.slowcloud.copypaste.dto.PasteCreateRequest;
import com.slowcloud.copypaste.dto.PasteCreateResponse;
import com.slowcloud.copypaste.dto.PasteGetResponse;
import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.repository.PasteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PasteService {

    private final PasteRepository pasteRepository;

    public PasteGetResponse getPasteFromPasteId(long pasteId) {
        Paste paste = pasteRepository.findById(pasteId).orElseThrow();
        return PasteGetResponse.builder()
            .id(paste.getId())
            .content(paste.getContent())
            .syntaxHighlight(paste.getSyntaxHighlight())
            .build();
    }

    public PasteCreateResponse createPaste(PasteCreateRequest pasteCreateRequest) {
        Paste paste = Paste.builder()
            .content(pasteCreateRequest.getContent())
            .syntaxHighlight(pasteCreateRequest.getSyntaxHighlight())
            .build();
        Paste createdPaste = pasteRepository.save(paste);
        return PasteCreateResponse.builder()
            .pasteId(createdPaste.getId())
            .build();
    }

}
