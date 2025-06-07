package com.slowcloud.copypaste.paste.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Paste {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @Enumerated(EnumType.STRING)
    private SyntaxHighlight syntaxHighlight;

}