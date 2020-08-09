package com.gavilan.amapolaapirest.Autenticación.controladores;

import com.gavilan.amapolaapirest.Autenticación.dto.AuthResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.UsuarioRequest;
import com.gavilan.amapolaapirest.Autenticación.servicios.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class ControladorIniciarSesion {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse iniciarSesion(@RequestBody UsuarioRequest usuarioRequest) {

        return this.authService.iniciarSesion(usuarioRequest);
    }
}
