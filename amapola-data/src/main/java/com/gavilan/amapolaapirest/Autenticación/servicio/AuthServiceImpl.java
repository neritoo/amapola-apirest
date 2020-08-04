package com.gavilan.amapolaapirest.Autenticación.servicio;

import com.gavilan.amapolaapirest.Autenticación.dto.AuthResponse;
import com.gavilan.amapolaapirest.Autenticación.dto.RefrescarTokenRequest;
import com.gavilan.amapolaapirest.Autenticación.dto.UsuarioRequest;
import com.gavilan.amapolaapirest.Autenticación.entidad.Usuario;
import com.gavilan.amapolaapirest.Autenticación.repositorio.UsuarioRepository;
import com.gavilan.amapolaapirest.Excepciones.UsuarioException;
import com.gavilan.amapolaapirest.Seguridad.JwtProveedor;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: Eze Gavilán
 **/

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    // private final PasswordEncoder passwordEncoder;
    private final RefrescarTokenService refrescarTokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtProveedor jwtProveedor;
    private final UsuarioRepository usuarioRepository;

    @Override
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

        for (int i=0; i < usuario.getRoles().size(); i++) {
           authResponse.getRoles().add(usuario.getRoles().get(i).getNombre());
        }

        return authResponse;
    }

    @Override
    public AuthResponse refrescarToken(RefrescarTokenRequest refrescarTokenRequest) {
        return null;
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public void cerrarSesion(RefrescarTokenRequest refrescarTokenRequest) {

    }
}
