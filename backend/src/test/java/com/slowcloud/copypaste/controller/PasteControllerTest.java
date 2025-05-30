package com.slowcloud.copypaste.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slowcloud.copypaste.dto.PasteCreateRequest;
import com.slowcloud.copypaste.dto.PasteCreateResponse;
import com.slowcloud.copypaste.dto.PasteGetResponse;
import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.entity.SyntaxHighlight;
import com.slowcloud.copypaste.service.PasteService;

@WebMvcTest({PasteController.class})
class PasteControllerTest {

    private static final SyntaxHighlight SYNTAX_HIGHLIGHT = SyntaxHighlight.NONE;
    private static final String CONTENT = "hello!";
    private static final int PASTE_ID = 1;

    @MockitoBean
    PasteService pasteService;

    @Autowired
    ObjectMapper objectMapper;

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
    void getPasteFromPasteId(@Autowired MockMvcTester mockMvcTester) {
        Paste paste = getPasteEntityFixture();

        PasteGetResponse pasteGetResponse = getPasteResponseDtoFixture();
        when(pasteService.getPasteFromPasteId(1)).thenReturn(pasteGetResponse);

        mockMvcTester.get().uri(String.format("/api/paste/%d", PASTE_ID))
        .exchange()
        .assertThat()
        .hasStatusOk()
        .bodyJson()
        .convertTo(PasteGetResponse.class)
        .satisfies(res -> {
            assertAll(
                () -> assertEquals(paste.getContent(), res.getContent()),
                () -> assertEquals(paste.getSyntaxHighlight(), res.getSyntaxHighlight())
            );
        });
    
    }

    @Test
    void createPaste(@Autowired MockMvcTester mockMvcTester) throws JsonProcessingException {
        PasteCreateRequest pasteCreateRequest = PasteCreateRequest.builder()
            .content(CONTENT)
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
            .satisfies(res -> {
                assertEquals(pasteCreateResponse.getPasteId(), res.getPasteId());
            });
    }

}
