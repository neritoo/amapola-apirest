package com.gavilan.amapolaapirest.Autenticación.servicio;

import com.gavilan.amapolaapirest.Autenticación.entidad.Usuario;
import com.gavilan.amapolaapirest.Autenticación.repositorio.UsuarioRepository;
import com.gavilan.amapolaapirest.Excepciones.UsuarioException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = this.usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsuarioException("No se encontró al usuario: " + username));

        return new User(usuario.getUsername(),
                usuario.getContraseña(),
                usuario.isEnabled(),
                true, true, true,
                getAutoridades(usuario));

    }

    private Collection<? extends GrantedAuthority> getAutoridades(Usuario usuario) {
        List<GrantedAuthority> roles = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(authority -> logger.info("Rol: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return roles;
    }
}
