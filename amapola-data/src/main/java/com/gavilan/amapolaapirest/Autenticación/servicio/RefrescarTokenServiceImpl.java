package com.gavilan.amapolaapirest.Autenticación.servicio;

import com.gavilan.amapolaapirest.Autenticación.entidad.RefreshToken;
import com.gavilan.amapolaapirest.Autenticación.repositorio.RefreshTokenRepository;
import com.gavilan.amapolaapirest.Excepciones.RefreshTokenException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefrescarTokenServiceImpl implements RefrescarTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generarRefreshToken() {

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setFechaCreacion(new Date());

        return this.refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validarRefreshToken(String token) {

        this.refreshTokenRepository.findByToken(token)
                .orElseThrow( () -> new RefreshTokenException("Refresh token inválido"));
    }

    @Override
    public void eliminarRefreshToken(String token) {

        this.refreshTokenRepository.deleteByToken(token);
    }
}
