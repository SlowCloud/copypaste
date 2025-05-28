package com.slowcloud.copypaste.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.slowcloud.copypaste.dto.PasteResponseDto;
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

    private Paste getPasteEntityFixture() {
        Paste paste = new Paste();
        paste.setContent(CONTENT);
        paste.setSyntaxHighlight(SYNTAX_HIGHLIGHT);
        return paste;
    }
    
    private PasteResponseDto getPasteResponseDtoFixture() {
        return PasteResponseDto.builder()
            .id(PASTE_ID)
            .content(CONTENT)
            .syntaxHighlight(SYNTAX_HIGHLIGHT)
            .build();
    }

    @Test
    void getPasteFromPasteId(@Autowired MockMvcTester mockMvcTester) {

        Paste paste = getPasteEntityFixture();
        PasteResponseDto pasteResponseDto = getPasteResponseDtoFixture();

        when(pasteService.getPasteFromPasteId(1)).thenReturn(pasteResponseDto);

        mockMvcTester.get().uri(String.format("/api/paste/%d", PASTE_ID))
        .exchange()
        .assertThat()
        .hasStatusOk()
        .bodyJson()
        .convertTo(PasteResponseDto.class)
        .satisfies(res -> {
            assertAll(
                () -> assertEquals(paste.getContent(), res.getContent()),
                () -> assertEquals(paste.getSyntaxHighlight(), res.getSyntaxHighlight())
            );
        });
    
    }

}
