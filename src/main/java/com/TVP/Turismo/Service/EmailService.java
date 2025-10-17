package com.TVP.Turismo.Service;

import com.TVP.Turismo.entity.Email;
import com.TVP.Turismo.entity.EmailLog;
import com.TVP.Turismo.Repository.EmailLogRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailLogRepository emailLogRepository;

    // Si app.mail.from no está, usa spring.mail.username
    @Value("${app.mail.from:${spring.mail.username}}")
    private String defaultFrom;

    public EmailService(JavaMailSender mailSender, EmailLogRepository emailLogRepository) {
        this.mailSender = mailSender;
        this.emailLogRepository = emailLogRepository;
    }

    public void enviarMail(Email email){
        String status = "OK";
        String errorMsg = null;

        try {
            MimeMessage mime = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mime, false, "UTF-8");

            // Remitente fijo (serio) tomado de properties
            helper.setFrom(defaultFrom);

            // Destinatario dinámico (desde la petición)
            helper.setTo(email.getTo());

            // Opcional: si vino "from" en el body, úsalo como Reply-To
            if (email.getFrom() != null && !email.getFrom().isBlank()) {
                helper.setReplyTo(email.getFrom());
            }

            helper.setSubject(email.getSubject());
            helper.setText(email.getText(), false); // true si mandas HTML

            mailSender.send(mime);

        } catch (Exception e) {
            status = "ERROR";
            errorMsg = e.getMessage();
        } finally {
            EmailLog log = EmailLog.builder()
                    .fromAddr(defaultFrom)          // guardamos el remitente real usado
                    .toAddr(email.getTo())
                    .subject(email.getSubject())
                    .text(email.getText())
                    .status(status)
                    .errorMessage(errorMsg)
                    .sentAt(OffsetDateTime.now())
                    .build();
            emailLogRepository.save(log);
        }

        if ("ERROR".equals(status)) {
            throw new RuntimeException("Fallo enviando correo: " + errorMsg);
        }
    }
}
