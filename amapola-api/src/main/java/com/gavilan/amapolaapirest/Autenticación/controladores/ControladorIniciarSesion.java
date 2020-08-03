package com.gavilan.amapolaapirest.Autenticación.controladores;

import com.gavilan.amapolaapirest.Autenticación.dto.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ControladorIniciarSesion {

    @PostMapping("/login")
    public AuthResponse iniciarSesion() {
        return null;
    }
}
