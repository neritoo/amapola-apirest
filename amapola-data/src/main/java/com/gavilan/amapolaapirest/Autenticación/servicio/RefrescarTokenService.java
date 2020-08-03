package com.gavilan.amapolaapirest.Autenticación.servicio;

import com.gavilan.amapolaapirest.Autenticación.entidad.RefreshToken;

public interface RefrescarTokenService {

    RefreshToken generarRefreshToken();

    void validarRefreshToken(String token);

    void eliminarRefreshToken(String token);
}
