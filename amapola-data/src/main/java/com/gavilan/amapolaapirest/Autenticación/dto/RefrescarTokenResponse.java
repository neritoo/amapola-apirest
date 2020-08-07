package com.gavilan.amapolaapirest.Autenticación.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefrescarTokenResponse {

    private String username;
    private String authToken;
    private String refreshToken;
    private Date expiraEn;
}
