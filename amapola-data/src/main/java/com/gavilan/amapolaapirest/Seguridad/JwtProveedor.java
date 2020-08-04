package com.gavilan.amapolaapirest.Seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProveedor {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());

        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new com.gavilan.amapolaapirest.Excepciones.KeyStoreException("Excepción al cargar el keystore");
        }
    }

    public String generarToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("Authorities", principal.getAuthorities())
                .signWith(getPrivateKey())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMillis))
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validarToken(String jwt) {
        parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e ) {
            throw new com.gavilan.amapolaapirest.Excepciones.KeyStoreException("Excepción al obtener la private key del keystore");
        }
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new com.gavilan.amapolaapirest.Excepciones.KeyStoreException("Excepción al obtener la public key del keystore");
        }
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
