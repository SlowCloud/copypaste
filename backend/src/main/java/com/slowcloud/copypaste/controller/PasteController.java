package com.slowcloud.copypaste.controller;

import org.springframework.web.bind.annotation.RestController;

import com.slowcloud.copypaste.dto.PasteCreateRequest;
import com.slowcloud.copypaste.dto.PasteCreateResponse;
import com.slowcloud.copypaste.dto.PasteGetResponse;
import com.slowcloud.copypaste.service.PasteService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/paste")
public class PasteController {

    private final PasteService pasteService;

    @GetMapping("/{pasteId}")
    @Validated
    public ResponseEntity<PasteGetResponse> getPasteByPasteId(@PathVariable @Min(1) long pasteId) {
        PasteGetResponse pasteResponseDto = pasteService.getPasteFromPasteId(pasteId);
        return ResponseEntity.ok(pasteResponseDto);
    }

    @PostMapping
    public ResponseEntity<PasteCreateResponse> createPaste(@RequestBody @Valid PasteCreateRequest pasteCreateRequest) {
        PasteCreateResponse pasteCreateResponse = pasteService.createPaste(pasteCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pasteCreateResponse);
    }

}
