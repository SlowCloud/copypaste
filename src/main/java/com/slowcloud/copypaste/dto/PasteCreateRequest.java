package com.slowcloud.copypaste.dto;

import com.slowcloud.copypaste.entity.SyntaxHighlight;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PasteCreateRequest {
    String content;
    SyntaxHighlight syntaxHighlight;
}
