package com.slowcloud.copypaste.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.entity.SyntaxHighlight;
import com.slowcloud.copypaste.service.PasteService;

@WebMvcTest({PasteController.class})
class PasteControllerTest {

    @MockitoBean
    PasteService pasteService;

    @Test
    void test(@Autowired MockMvcTester mockMvcTester) {

        Paste paste = new Paste();
        paste.setContent("hello!");
        paste.setPasteKey("customKey");
        paste.setSyntaxHighlight(SyntaxHighlight.NONE);

        when(pasteService.getPasteFromPasteKey("customKey")).thenReturn(paste);

        mockMvcTester.get().uri("/api/paste/customKey").exchange().assertThat()
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
