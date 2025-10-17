package com.TVP.Turismo.entity;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Email {
    private String from;     // opcional: lo usaré como reply-to
    private String to;       // destinatario elegido en cada request
    private String subject;
    private String text;
}
