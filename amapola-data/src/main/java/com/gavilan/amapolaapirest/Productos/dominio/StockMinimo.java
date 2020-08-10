package com.gavilan.amapolaapirest.Productos.dominio;

import javax.persistence.Entity;

/**
 * @author Ezequiel Gavilán
 */

@Entity
public class StockMinimo extends Estado {

    public StockMinimo() {
        super.nombre = "STOCK_MINIMO";
    }

    @Override
    public void descontarStock() {

    }

    @Override
    public void aumentarStock() {

    }
}
