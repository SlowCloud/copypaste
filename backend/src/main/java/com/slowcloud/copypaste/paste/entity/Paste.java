package com.slowcloud.copypaste.paste.entity;

import jakarta.persistence.*;
import lombok.*;

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