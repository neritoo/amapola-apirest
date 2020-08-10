package com.gavilan.amapolaapirest.Productos.dominio;

import javax.persistence.Entity;

/**
 * @author Ezequiel Gavilán
 */

@Entity
public class EnStock extends Estado {

    public EnStock() {
        super.nombre = "EN_STOCK";
    }

    @Override
    public void descontarStock() {

    }

    @Override
    public void aumentarStock() {

    }
}
