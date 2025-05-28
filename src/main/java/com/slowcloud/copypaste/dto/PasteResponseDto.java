package com.slowcloud.copypaste.dto;

import com.slowcloud.copypaste.entity.SyntaxHighlight;

public record PasteResponseDto(
    long id,
    String pasteKey,
    String content,
    SyntaxHighlight syntaxHighlight
) {
}
