package com.gavilan.amapolaapirest.Productos.dominio;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Deprecated
@Entity
@NoArgsConstructor
public class Unidad extends TipoPrecio {

    @Override
    public Double calcularSubTotal() {
        return null;
    }
}
