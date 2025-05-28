package com.slowcloud.copypaste.dto;

import com.slowcloud.copypaste.entity.SyntaxHighlight;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PasteResponseDto {
    private final long id;
    private final String content;
    private final SyntaxHighlight syntaxHighlight;
}
