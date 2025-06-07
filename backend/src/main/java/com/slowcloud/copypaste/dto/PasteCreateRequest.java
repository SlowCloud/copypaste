package com.slowcloud.copypaste.dto;

import com.slowcloud.copypaste.entity.SyntaxHighlight;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PasteCreateRequest {
    @Max(value = 10000L, message = "텍스트 길이 제한을 초과하였습니다.")
    private final String content;
    @NotNull
    private final SyntaxHighlight syntaxHighlight;
}
