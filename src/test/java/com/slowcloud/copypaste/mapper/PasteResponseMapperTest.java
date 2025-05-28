package com.slowcloud.copypaste.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.slowcloud.copypaste.dto.PasteResponseDto;
import com.slowcloud.copypaste.entity.Paste;
import com.slowcloud.copypaste.entity.SyntaxHighlight;

@SpringBootTest(classes = PasteResponseMapperImpl.class)
class PasteResponseMapperTest {

    private static final SyntaxHighlight SYNTAX_HIGHLIGHT = SyntaxHighlight.NONE;
    private static final String CONTENT = "hello!";
    private static final String PASTE_KEY = "customKey";
    private static final int PASTE_ID = 1;

    private Paste getPasteEntityFixture() {
        Paste paste = new Paste();
        paste.setContent(CONTENT);
        paste.setPasteKey(PASTE_KEY);
        paste.setSyntaxHighlight(SYNTAX_HIGHLIGHT);
        return paste;
    }
    
    private PasteResponseDto getPasteResponseDtoFixture() {
        return new PasteResponseDto(PASTE_ID, PASTE_KEY, CONTENT, SYNTAX_HIGHLIGHT);
    }
    
    @Autowired
    PasteResponseMapper pasteResponseMapper;

    @Test
    void instanceCheck() {
        assertNotNull(pasteResponseMapper);
    }

    @Test
    void toDto() {
        Paste paste = getPasteEntityFixture();
        
        PasteResponseDto generatedDto = pasteResponseMapper.toDto(paste);

        PasteResponseDto fixtureDto = getPasteResponseDtoFixture();
        assertAll(
            () -> assertEquals(generatedDto.content(), fixtureDto.content()),
            () -> assertEquals(generatedDto.pasteKey(), fixtureDto.pasteKey()),
            () -> assertEquals(generatedDto.syntaxHighlight(), fixtureDto.syntaxHighlight())
        );
    }

    @Test
    void toEntity() {
        PasteResponseDto dto = getPasteResponseDtoFixture();
        
        Paste generatedPaste = pasteResponseMapper.toEntity(dto);

        Paste fixturePaste = getPasteEntityFixture();
        assertAll(
            () -> assertEquals(generatedPaste.getContent(), fixturePaste.getContent()),
            () -> assertEquals(generatedPaste.getPasteKey(), fixturePaste.getPasteKey()),
            () -> assertEquals(generatedPaste.getSyntaxHighlight(), fixturePaste.getSyntaxHighlight())
        );
    }

}
