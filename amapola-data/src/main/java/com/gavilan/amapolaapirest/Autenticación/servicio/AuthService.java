package com.gavilan.amapolaapirest.Autenticación.servicio;

import com.gavilan.amapolaapirest.Autenticación.dto.AuthResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenRequest;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.UsuarioRequest;
import com.gavilan.amapolaapirest.Autenticación.entidad.Usuario;

public interface AuthService {

    AuthResponse iniciarSesion(UsuarioRequest usuario);

    RefrescarTokenResponse refrescarToken(RefrescarTokenRequest refrescarTokenRequest);

    Usuario getCurrentUser();

    void cerrarSesion(RefrescarTokenRequest refrescarTokenRequest);

    boolean estaLogueado();

}
