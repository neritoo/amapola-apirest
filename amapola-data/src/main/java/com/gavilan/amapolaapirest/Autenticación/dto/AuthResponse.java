package com.gavilan.amapolaapirest.Autenticación.dto;

import com.gavilan.amapolaapirest.Autenticación.entidad.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String authToken;

    private String username;

    private String refreshToken;

    private Date expiraEn;

    private String email;

    private List<Rol> roles;

    public AuthResponse() {
        this.roles = new ArrayList<>();
    }
}
