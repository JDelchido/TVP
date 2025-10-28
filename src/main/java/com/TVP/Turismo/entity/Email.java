package com.TVP.Turismo.entity;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String from;     // emisor (opcional)
    private String to;       // receptor
    private String subject;  // asunto
    private String body;     // contenido
}