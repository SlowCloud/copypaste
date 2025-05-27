package com.slowcloud.copypaste.controller;

import org.springframework.web.bind.annotation.RestController;

import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.service.PasteService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/paste")
public class PasteController {

    private static Logger logger = LoggerFactory.getLogger(PasteController.class);

    private final PasteService pasteService;

    @GetMapping("/{pasteKey}")
    public ResponseEntity<Paste> getPaste(@PathVariable String pasteKey) {
        Paste paste = pasteService.getPasteFromPasteKey(pasteKey);
        return ResponseEntity.ok().body(paste);
    }

}
