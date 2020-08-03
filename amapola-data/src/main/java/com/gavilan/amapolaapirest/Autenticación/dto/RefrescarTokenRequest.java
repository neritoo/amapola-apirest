package com.gavilan.amapolaapirest.Autenticación.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefrescarTokenRequest {

    @NotBlank
    private String refreshToken;
    private String username;
}
