package com.slowcloud.copypaste.paste.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PasteCreateResponse {
    private final long pasteId;
}
