package com.slowcloud.copypaste.paste.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slowcloud.copypaste.paste.dto.PasteCreateRequest;
import com.slowcloud.copypaste.paste.dto.PasteCreateResponse;
import com.slowcloud.copypaste.paste.dto.PasteGetResponse;
import com.slowcloud.copypaste.paste.entity.Paste;
import com.slowcloud.copypaste.paste.entity.SyntaxHighlight;
import com.slowcloud.copypaste.paste.service.PasteService;
import com.slowcloud.copypaste.security.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Import(SecurityConfig.class)
@WebMvcTest({PasteController.class})
class PasteControllerTest {

    private static final SyntaxHighlight SYNTAX_HIGHLIGHT = SyntaxHighlight.NONE;
    private static final String CONTENT = "hello!";
    private static final int PASTE_ID = 1;
    private static final String TOO_LONG_CONTENT = "1".repeat(20_000);

    @MockitoBean
    PasteService pasteService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvcTester mockMvcTester;

    private Paste getPasteEntityFixture() {
        Paste paste = new Paste();
        paste.setContent(CONTENT);
        paste.setSyntaxHighlight(SYNTAX_HIGHLIGHT);
        return paste;
    }
    
    private PasteGetResponse getPasteResponseDtoFixture() {
        return PasteGetResponse.builder()
            .id(PASTE_ID)
            .content(CONTENT)
            .syntaxHighlight(SYNTAX_HIGHLIGHT)
            .build();
    }

    @Test
    void getPasteFromPasteId() {
        Paste paste = getPasteEntityFixture();

        PasteGetResponse pasteGetResponse = getPasteResponseDtoFixture();
        when(pasteService.getPasteFromPasteId(1)).thenReturn(pasteGetResponse);

        mockMvcTester.get().uri(String.format("/api/paste/%d", PASTE_ID))
        .exchange()
        .assertThat()
        .hasStatusOk()
        .bodyJson()
        .convertTo(PasteGetResponse.class)
        .satisfies(res -> assertAll(
            () -> assertEquals(paste.getContent(), res.getContent()),
            () -> assertEquals(paste.getSyntaxHighlight(), res.getSyntaxHighlight())
        ));
    }

    @Test
    void createPaste() throws JsonProcessingException {
        PasteCreateRequest pasteCreateRequest = PasteCreateRequest.builder()
            .content(CONTENT)
            .syntaxHighlight(SYNTAX_HIGHLIGHT)
            .build();

        PasteCreateResponse pasteCreateResponse = PasteCreateResponse.builder()
            .pasteId(PASTE_ID)
            .build();
        when(pasteService.createPaste(pasteCreateRequest)).thenReturn(pasteCreateResponse);

        mockMvcTester.post().uri("/api/paste")
            .content(objectMapper.writeValueAsString(pasteCreateRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .exchange()
            .assertThat()
            .hasStatus(HttpStatus.CREATED)
            .bodyJson()
            .convertTo(PasteCreateResponse.class)
            .satisfies(res -> assertEquals(pasteCreateResponse.getPasteId(), res.getPasteId()));
    }

    @Test
    void createPasteFailTooLongContent() throws JsonProcessingException {
        PasteCreateRequest pasteCreateRequest = PasteCreateRequest.builder()
            .content(TOO_LONG_CONTENT)
            .syntaxHighlight(SYNTAX_HIGHLIGHT)
            .build();

        PasteCreateResponse pasteCreateResponse = PasteCreateResponse.builder()
            .pasteId(PASTE_ID)
            .build();
        when(pasteService.createPaste(pasteCreateRequest)).thenReturn(pasteCreateResponse);

        mockMvcTester.post().uri("/api/paste")
            .content(objectMapper.writeValueAsString(pasteCreateRequest))
            .contentType(MediaType.APPLICATION_JSON)
            .exchange()
            .assertThat()
            .hasStatus(HttpStatus.BAD_REQUEST);
    }

}
