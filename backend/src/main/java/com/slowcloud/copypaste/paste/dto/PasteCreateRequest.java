package com.slowcloud.copypaste.paste.dto;

import com.slowcloud.copypaste.paste.entity.SyntaxHighlight;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Builder
@Value
public class PasteCreateRequest {
    @Length(max = 10000, message = "텍스트 길이 제한을 초과하였습니다.")
    private final String content;
    @NotNull
    private final SyntaxHighlight syntaxHighlight;
}
