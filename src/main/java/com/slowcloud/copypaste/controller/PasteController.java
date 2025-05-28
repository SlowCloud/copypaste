package com.slowcloud.copypaste.controller;

import org.springframework.web.bind.annotation.RestController;

import com.slowcloud.copypaste.dto.PasteResponseDto;
import com.slowcloud.copypaste.service.PasteService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/paste")
public class PasteController {

    private final PasteService pasteService;

    @GetMapping("/{pasteId}")
    public ResponseEntity<PasteResponseDto> getPasteByPasteId(@PathVariable long pasteId) {
        PasteResponseDto pasteResponseDto = pasteService.getPasteFromPasteId(pasteId);
        return ResponseEntity.ok(pasteResponseDto);
    }

}
