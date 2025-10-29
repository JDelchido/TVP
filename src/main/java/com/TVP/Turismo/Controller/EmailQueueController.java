package com.TVP.Turismo.Controller;

import com.TVP.Turismo.DTO.EmailMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.TVP.Turismo.Config.RabbitConfig.*;

/**
 * Controller responsable de recibir peticiones HTTP y publicar mensajes
 * hacia la cola de RabbitMQ (tvp.email).
 */
@RestController
@RequestMapping("/mail")
public class EmailQueueController {

    private final RabbitTemplate rabbitTemplate;

    public EmailQueueController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Endpoint para encolar un mensaje de correo en RabbitMQ.
     * 
     * Ejemplo POST:
     * {
     *   "to": "vargaspjuan@javeriana.edu.co",
     *   "subject": "TVP Turismo – RabbitMQ",
     *   "body": "Mensaje enviado vía RabbitMQ y procesado por MS-Notificaciones.",
     *   "from": "no-reply@tvp-turismo.co"
     * }
     */
    @PostMapping("/enqueue")
    public ResponseEntity<String> enqueue(@RequestBody EmailMessage msg) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_EMAIL, msg);
        return ResponseEntity.accepted().body("✅ Mensaje encolado en RabbitMQ para procesamiento.");
    }
}
