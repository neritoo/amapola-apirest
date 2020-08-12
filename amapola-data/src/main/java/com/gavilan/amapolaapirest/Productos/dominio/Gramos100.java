package com.gavilan.amapolaapirest.Productos.dominio;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Deprecated
@Entity
@NoArgsConstructor
public class Gramos100 extends TipoPrecio {

    @Override
    public Double calcularSubTotal() {
        return null;
    }
}
