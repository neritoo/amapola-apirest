package com.gavilan.amapolaapirest.Autenticación.controladores;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ControladorRefrescarToken {

    @PostMapping("/refresh/token")
    public void refrescarTokens() {
        return;
    }
}
