package com.slowcloud.copypaste.dto;

import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.entity.SyntaxHighlight;

public record PasteResponseDto(
    long id,
    String pasteKey,
    String content,
    SyntaxHighlight syntaxHighlight
) {
    public static PasteResponseDto of(Paste paste) {
        return new PasteResponseDto(paste.getId(), paste.getPasteKey(), paste.getContent(), paste.getSyntaxHighlight());
    }
}
