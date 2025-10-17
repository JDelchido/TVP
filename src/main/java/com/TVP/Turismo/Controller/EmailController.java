package com.TVP.Turismo.Controller;

import com.TVP.Turismo.entity.Email;
import com.TVP.Turismo.Service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/envio")  // ¡sin tilde!
    public ResponseEntity<String> enviar(@RequestBody Email email) {
        service.enviarMail(email);
        return ResponseEntity.accepted().body("Correo enviado y log guardado");
    }
}
