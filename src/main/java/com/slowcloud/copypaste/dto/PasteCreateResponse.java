package com.slowcloud.copypaste.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PasteCreateResponse {
    private final long pasteId;
}
