package com.gavilan.amapolaapirest.Autenticación.controladores;

import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenRequest;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenResponse;
import com.gavilan.amapolaapirest.Autenticación.servicio.AuthService;
import com.gavilan.amapolaapirest.Excepciones.RefreshTokenException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ControladorRefrescarToken {

    private final AuthService authService;

    public ControladorRefrescarToken(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<?> refrescarTokens(@Valid @RequestBody RefrescarTokenRequest refrescarTokenRequest) {

        Map<String, Object> response = new HashMap<>();
        RefrescarTokenResponse tokenResponse;

        try {
            tokenResponse = this.authService.refrescarToken(refrescarTokenRequest);
        } catch (RefreshTokenException e) {
            response.put("mensaje", "Error al refrescar token");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
