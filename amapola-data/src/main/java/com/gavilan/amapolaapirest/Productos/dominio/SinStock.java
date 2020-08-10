package com.gavilan.amapolaapirest.Productos.dominio;

import javax.persistence.Entity;

/**
 * @author Ezequiel Gavilán
 */

@Entity
public class SinStock extends Estado {

    public SinStock() {
        super.nombre = "SIN_STOCK";
    }

    @Override
    public void descontarStock() {

    }

    @Override
    public void aumentarStock() {

    }
}
