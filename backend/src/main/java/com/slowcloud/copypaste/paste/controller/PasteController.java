package com.slowcloud.copypaste.paste.controller;

import org.springframework.web.bind.annotation.RestController;

import com.slowcloud.copypaste.paste.dto.PasteCreateRequest;
import com.slowcloud.copypaste.paste.dto.PasteCreateResponse;
import com.slowcloud.copypaste.paste.dto.PasteGetResponse;
import com.slowcloud.copypaste.paste.service.PasteService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<List<PasteGetResponse>> getMethodName(@PageableDefault(sort = "id") Pageable pageable) {
        List<PasteGetResponse> pagePasteResponseDto = pasteService.getPastes(pageable);
        return ResponseEntity.ok(pagePasteResponseDto);
    }

    @PostMapping
    public ResponseEntity<PasteCreateResponse> createPaste(@RequestBody @Valid PasteCreateRequest pasteCreateRequest) {
        PasteCreateResponse pasteCreateResponse = pasteService.createPaste(pasteCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pasteCreateResponse);
    }

}
