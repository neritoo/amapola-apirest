package com.gavilan.amapolaapirest.Autenticación.servicio;

import com.gavilan.amapolaapirest.Autenticación.dto.AuthResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenRequest;
import com.gavilan.amapolaapirest.Autenticación.dto.UsuarioRequest;
import org.springframework.security.core.userdetails.User;

public interface AuthService {

    AuthResponse iniciarSesion(UsuarioRequest usuario);

    AuthResponse refrescarToken(RefrescarTokenRequest refrescarTokenRequest);

    User getCurrentUser();

    void cerrarSesion(RefrescarTokenRequest refrescarTokenRequest);

}
