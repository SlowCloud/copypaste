package com.slowcloud.copypaste.controller;

import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Paste> getPaste(@RequestParam String pasteKey) {
        Paste paste = pasteService.getPasteFromPasteKey(pasteKey);
        return ResponseEntity.ok().body(paste);
    }

    @GetMapping("/{pasteId}")
    public ResponseEntity<Paste> getPasteByPasteId(@PathVariable long pasteId) {
        Paste paste = pasteService.getPasteFromPasteId(pasteId);
        return ResponseEntity.ok().body(paste);
    }

}
