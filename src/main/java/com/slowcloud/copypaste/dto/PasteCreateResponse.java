package com.slowcloud.copypaste.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PasteCreateResponse {
    private final long pasteId;
}
