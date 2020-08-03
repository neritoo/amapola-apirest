package com.gavilan.amapolaapirest.Excepciones;

public class RefreshTokenException extends RuntimeException {

    public RefreshTokenException(String exMensaje) {
        super(exMensaje);
    }
}
