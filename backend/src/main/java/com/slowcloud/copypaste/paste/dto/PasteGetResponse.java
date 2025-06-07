package com.slowcloud.copypaste.paste.dto;

import com.slowcloud.copypaste.paste.entity.SyntaxHighlight;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PasteGetResponse {
    private final long id;
    private final String content;
    private final SyntaxHighlight syntaxHighlight;
}
