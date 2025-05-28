package com.slowcloud.copypaste.controller;

import org.springframework.web.bind.annotation.RestController;

import com.slowcloud.copypaste.dto.PasteResponseDto;
import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.service.PasteService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/paste")
public class PasteController {

    private final PasteService pasteService;

    @GetMapping
    public ResponseEntity<PasteResponseDto> getPaste(@RequestParam String pasteKey) {
        Paste paste = pasteService.getPasteFromPasteKey(pasteKey);
        PasteResponseDto pasteResponseDto = toPasteResponseDto(paste);
        return ResponseEntity.ok(pasteResponseDto);
    }

    @GetMapping("/{pasteId}")
    public ResponseEntity<PasteResponseDto> getPasteByPasteId(@PathVariable long pasteId) {
        Paste paste = pasteService.getPasteFromPasteId(pasteId);
        PasteResponseDto pasteResponseDto = toPasteResponseDto(paste);
        return ResponseEntity.ok(pasteResponseDto);
    }

    private static PasteResponseDto toPasteResponseDto(Paste paste) {
        return new PasteResponseDto(paste.getId(), paste.getPasteKey(), paste.getContent(), paste.getSyntaxHighlight());
    }

}
