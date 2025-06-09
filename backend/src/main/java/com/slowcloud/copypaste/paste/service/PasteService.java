package com.slowcloud.copypaste.paste.service;

import com.slowcloud.copypaste.paste.dto.PasteCreateRequest;
import com.slowcloud.copypaste.paste.dto.PasteCreateResponse;
import com.slowcloud.copypaste.paste.dto.PasteGetResponse;
import com.slowcloud.copypaste.paste.entity.Paste;
import com.slowcloud.copypaste.paste.repository.PasteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PasteService {

    private final PasteRepository pasteRepository;

    public PasteGetResponse getPasteFromPasteId(long pasteId) {
        Paste paste = pasteRepository.findById(pasteId).orElseThrow();
        return pasteToPasteGetResponse(paste);
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

    public List<PasteGetResponse> getPastes(Pageable pageable) {
        return pasteRepository
            .findAll(pageable)
            .map(PasteService::pasteToPasteGetResponse)
            .getContent();
    }

    private static PasteGetResponse pasteToPasteGetResponse(Paste paste) {
        return PasteGetResponse.builder()
            .id(paste.getId())
            .content(paste.getContent())
            .syntaxHighlight(paste.getSyntaxHighlight())
            .build();
    }

}
