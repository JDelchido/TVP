package com.TVP.Turismo.Controller;

import com.TVP.Turismo.DTO.EmailDTO;
import com.TVP.Turismo.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService service;

    @PostMapping("/envio")
    public ResponseEntity<String> enviar(@RequestBody EmailDTO dto) {
        Long id = service.enviarMail(dto);
        return ResponseEntity.accepted()
                .body("TVP Turismo: correo procesado y log guardado (email_log.id=" + id + ").");
    }
}
