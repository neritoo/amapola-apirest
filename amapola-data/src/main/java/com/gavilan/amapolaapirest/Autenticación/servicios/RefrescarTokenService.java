package com.gavilan.amapolaapirest.Autenticación.servicios;

import com.gavilan.amapolaapirest.Autenticación.dominio.RefreshToken;

public interface RefrescarTokenService {

    RefreshToken generarRefreshToken();

    void validarRefreshToken(String token);

    void eliminarRefreshToken(String token);
}
