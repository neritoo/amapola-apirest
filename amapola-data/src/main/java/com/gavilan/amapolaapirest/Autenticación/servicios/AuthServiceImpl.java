package com.gavilan.amapolaapirest.Autenticación.servicios;

import com.gavilan.amapolaapirest.Autenticación.dominio.Usuario;
import com.gavilan.amapolaapirest.Autenticación.dto.AuthResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenRequest;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.UsuarioRequest;
import com.gavilan.amapolaapirest.Autenticación.repositorios.UsuarioRepository;
import com.gavilan.amapolaapirest.Excepciones.RefreshTokenException;
import com.gavilan.amapolaapirest.Excepciones.UsuarioException;
import com.gavilan.amapolaapirest.Seguridad.JwtProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author: Eze Gavilán
 **/

@Service
public class AuthServiceImpl implements AuthService {

    private final RefrescarTokenService refrescarTokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtProveedor jwtProveedor;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthServiceImpl(RefrescarTokenService refrescarTokenService, AuthenticationManager authenticationManager, JwtProveedor jwtProveedor, UsuarioRepository usuarioRepository) {
        this.refrescarTokenService = refrescarTokenService;
        this.authenticationManager = authenticationManager;
        this.jwtProveedor = jwtProveedor;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public AuthResponse iniciarSesion(UsuarioRequest usuarioRequest) {
        Authentication authenticate = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioRequest.getUsername(),
                        usuarioRequest.getContraseña()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = this.jwtProveedor.generarToken(authenticate);

        Usuario usuario = this.usuarioRepository.findByUsername(usuarioRequest.getUsername())
                .orElseThrow(() -> new UsuarioException("No se encontró el usuario: " +
                        usuarioRequest.getUsername()));

        if (usuario.getRoles() == null) {
            throw new UsuarioException("No se encontraron roles");
        }

        AuthResponse authResponse = new AuthResponse();

        authResponse.setAuthToken(token);
        authResponse.setRefreshToken(this.refrescarTokenService.generarRefreshToken().getToken());
        authResponse.setExpiraEn(new Date(new Date().getTime() +
                this.jwtProveedor.getJwtExpirationInMillis()));
        authResponse.setUsername(usuario.getUsername());
        authResponse.setEmail(usuario.getEmail());
        authResponse.setRoles(usuario.getRoles());

        return authResponse;
    }

    @Override
    public RefrescarTokenResponse refrescarToken(RefrescarTokenRequest refrescarTokenRequest) {

        this.refrescarTokenService.validarRefreshToken(refrescarTokenRequest.getRefreshToken());

        String token = jwtProveedor.generarTokenConUsername(refrescarTokenRequest.getUsername());

        RefrescarTokenResponse tokenResponse = new RefrescarTokenResponse();
        tokenResponse.setUsername(refrescarTokenRequest.getUsername());
        tokenResponse.setAuthToken(token);
        tokenResponse.setRefreshToken(refrescarTokenRequest.getRefreshToken());
        tokenResponse.setExpiraEn(new Date(new Date().getTime() + this.jwtProveedor.getJwtExpirationInMillis()));

        return tokenResponse;
    }

    @Override
    public Usuario getCurrentUser() {

        if (this.estaLogueado()) {
            User usuarioLogueado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return this.usuarioRepository.findByUsername(usuarioLogueado.getUsername())
                    .orElseThrow(() -> new UsuarioException("Usuario no encontrado: ".concat(usuarioLogueado.getUsername())));
        } else {
            throw new RefreshTokenException("No hay un usuario logueado");
        }
    }

    @Override
    public void cerrarSesion(RefrescarTokenRequest refrescarTokenRequest) {

        this.refrescarTokenService.validarRefreshToken(refrescarTokenRequest.getRefreshToken());
        this.refrescarTokenService.eliminarRefreshToken(refrescarTokenRequest.getRefreshToken());
    }

    @Override
    public boolean estaLogueado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
