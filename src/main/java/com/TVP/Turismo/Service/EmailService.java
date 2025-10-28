package com.TVP.Turismo.Service;

import com.TVP.Turismo.Repository.EmailLogRepository;
// Si mantienes EmailRepository como alias, igualmente funciona, pero prefiero el repo específico:
import com.TVP.Turismo.DTO.EmailDTO;
import com.TVP.Turismo.entity.EmailLog;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class EmailService {

    private final EmailLogRepository repo; // usa el repo específico

    public EmailService(EmailLogRepository repo) {
        this.repo = repo;
    }

    public Long enviarMail(EmailDTO dto) {
        // Aquí iría el envío real (SMTP). Simulamos OK y persistimos el log.
        EmailLog log = EmailLog.builder()
                .fromAddr("no-reply@tvp-turismo.co") // podrías permitir que venga en el DTO si lo agregas
                .toAddr(dto.getTo())
                .subject(dto.getSubject())
                .text(dto.getBody())         // <<--- body, no text()
                .status("OK")
                .sentAt(OffsetDateTime.now())
                .build();

        return repo.save(log).getId();
    }
}
