package com.TVP.Turismo.messaging;

import com.TVP.Turismo.DTO.EmailDTO;
import com.TVP.Turismo.DTO.EmailMessage;
import com.TVP.Turismo.Service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import static com.TVP.Turismo.Config.RabbitConfig.QUEUE_EMAIL;

/**
 * Componente que escucha los mensajes de RabbitMQ y 
 * utiliza EmailService para procesarlos (enviar correo y guardar log).
 */
@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Método ejecutado automáticamente cuando llega un mensaje
     * al queue “tvp.email”.
     */
    @RabbitListener(queues = QUEUE_EMAIL)
    public void onEmailMessage(EmailMessage msg) {
        // Reutiliza la lógica de tu servicio existente
        EmailDTO dto = new EmailDTO(msg.getTo(), msg.getSubject(), msg.getBody());
        emailService.enviarMail(dto);

        System.out.println("📩 EmailConsumer recibió mensaje de RabbitMQ y lo procesó correctamente.");
    }
}
