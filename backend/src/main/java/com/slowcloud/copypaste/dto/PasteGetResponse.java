package com.slowcloud.copypaste.dto;

import com.slowcloud.copypaste.entity.SyntaxHighlight;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PasteGetResponse {
    private final long id;
    private final String content;
    private final SyntaxHighlight syntaxHighlight;
}
