package com.gavilan.amapolaapirest.Autenticación.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String authToken;

    private String username;

    private String refreshToken;

    private Date expiraEn;
}
