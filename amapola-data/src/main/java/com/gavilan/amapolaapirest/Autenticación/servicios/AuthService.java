package com.gavilan.amapolaapirest.Autenticación.servicios;

import com.gavilan.amapolaapirest.Autenticación.dominio.Usuario;
import com.gavilan.amapolaapirest.Autenticación.dto.AuthResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenRequest;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.UsuarioRequest;

public interface AuthService {

    AuthResponse iniciarSesion(UsuarioRequest usuario);

    RefrescarTokenResponse refrescarToken(RefrescarTokenRequest refrescarTokenRequest);

    Usuario getCurrentUser();

    void cerrarSesion(RefrescarTokenRequest refrescarTokenRequest);

    boolean estaLogueado();

}
