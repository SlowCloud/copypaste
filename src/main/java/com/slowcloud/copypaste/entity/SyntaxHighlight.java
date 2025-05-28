package com.slowcloud.copypaste.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SyntaxHighlight {

    NONE, JAVA, JAVASCRIPT, PYTHON;

    @JsonCreator
    public static SyntaxHighlight of(String name) {
        for(SyntaxHighlight syntaxHighlight : values()) {
            if(syntaxHighlight.name().equalsIgnoreCase(name)) {
                return syntaxHighlight;
            }
        }
        throw new IllegalArgumentException();
    }

    @JsonValue
    public String getName() {
        return this.name();
    }
    
}
