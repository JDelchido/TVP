package com.TVP.Turismo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "email_log")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // payload del envío
    @Column(nullable = false, length = 120)
    private String fromAddr;

    @Column(nullable = false, length = 120)
    private String toAddr;

    @Column(nullable = false, length = 200)
    private String subject;

    @Lob
    private String text;

    // resultado
    @Column(nullable = false, length = 20)
    private String status;  // "OK" | "ERROR"

    @Column(length = 400)
    private String errorMessage;

    @Column(nullable = false)
    private OffsetDateTime sentAt;

    @PrePersist
    void prePersist() {
        if (sentAt == null) sentAt = OffsetDateTime.now();
        if (status == null)  status = "OK";
    }
}
