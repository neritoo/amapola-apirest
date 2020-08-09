package com.gavilan.amapolaapirest.Autenticación.controladores;

import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenRequest;
import com.gavilan.amapolaapirest.Autenticación.servicios.AuthService;
import com.gavilan.amapolaapirest.Excepciones.RefreshTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class ControladorCerrarSesion {

    private final AuthService authService;

    @Autowired
    public ControladorCerrarSesion(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> cerrarSesion(@Valid @RequestBody RefrescarTokenRequest refrescarTokenRequest) {

        Map<String, Object> response = new HashMap<>();

        try {
            this.authService.cerrarSesion(refrescarTokenRequest);
        } catch (RefreshTokenException e) {
            response.put("mensaje", "Error al cerrar sesión");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Sesión cerrada", HttpStatus.OK);
    }
}
