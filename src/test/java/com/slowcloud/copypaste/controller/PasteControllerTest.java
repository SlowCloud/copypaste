package com.slowcloud.copypaste.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.entity.SyntaxHighlight;
import com.slowcloud.copypaste.service.PasteService;

@WebMvcTest({PasteController.class})
class PasteControllerTest {

    private static final String CUSTOM_KEY = "customKey";
    private static final int PASTE_ID = 1;

    @MockitoBean
    PasteService pasteService;

    private Paste getPasteEntityFixture() {
        Paste paste = new Paste();
        paste.setContent("hello!");
        paste.setPasteKey(CUSTOM_KEY);
        paste.setSyntaxHighlight(SyntaxHighlight.NONE);
        return paste;
    }

    @Test
    void getPasteFromPasteKey(@Autowired MockMvcTester mockMvcTester) {

        Paste paste = getPasteEntityFixture();

        when(pasteService.getPasteFromPasteKey(CUSTOM_KEY)).thenReturn(paste);

        mockMvcTester.get().uri("/api/paste").param("pasteKey", CUSTOM_KEY)
        .exchange()
        .assertThat()
        .hasStatusOk()
        .bodyJson()
        .convertTo(Paste.class)
        .satisfies(res -> {
            assertAll(
                () -> assertEquals(paste.getContent(), res.getContent()),
                () -> assertEquals(paste.getPasteKey(), res.getPasteKey()),
                () -> assertEquals(paste.getSyntaxHighlight(), res.getSyntaxHighlight())
            );
        });
    
    }

    @Test
    void getPasteFromPasteId(@Autowired MockMvcTester mockMvcTester) {

        Paste paste = getPasteEntityFixture();

        when(pasteService.getPasteFromPasteId(1)).thenReturn(paste);

        mockMvcTester.get().uri(String.format("/api/paste/%d", PASTE_ID))
        .exchange()
        .assertThat()
        .hasStatusOk()
        .bodyJson()
        .convertTo(Paste.class)
        .satisfies(res -> {
            assertAll(
                () -> assertEquals(paste.getContent(), res.getContent()),
                () -> assertEquals(paste.getPasteKey(), res.getPasteKey()),
                () -> assertEquals(paste.getSyntaxHighlight(), res.getSyntaxHighlight())
            );
        });
    
    }

}
